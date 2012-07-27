package net.sf.geal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;
import net.sf.geal.individual.IndividualBreeder;
import net.sf.geal.individual.IndividualPair;
import net.sf.geal.population.Population;
import net.sf.kerner.utils.collections.impl.UtilCollection;
import net.sf.kerner.utils.collections.list.impl.UtilList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticAlgorithmImpl<R, P, G extends Gene<P>, I extends Individual<R, P, G>> implements
        GeneticAlgorithm<R, P, G, I> {

    public final static int DEFAULT_GROW_MAX_RETRY = 200;

    public final static int DEFAULT_MAX_OF_PAIRINGS = 20;

    public final static int DEFAULT_MAX_POPULATION_SIZE = 10000;

    public final static double DEFAULT_PERCENTAGE_OF_PAIRINGS = 0.1;

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmImpl.class);

    private volatile int currentGrowRetry = 0;

    private volatile Population<R, P, G, I> currentPopulation;

    private volatile IndividualBreeder<R, P, G, I> factoryIndividual;

    private final List<Population<R, P, G, I>> history = UtilList.newList();

    private final List<ListenerEvolution<R, P, G, I>> listeners = UtilList.newList();

    private volatile int maxGrowRetry = DEFAULT_GROW_MAX_RETRY;

    private volatile double percentageOfPairings;

    private final TerminatorPopulationSize<R, P, G, I> terminatorPopulationSize = new TerminatorPopulationSize<R, P, G, I>(
            DEFAULT_MAX_POPULATION_SIZE);

    private final List<TerminatorEvolution<R, P, G, I>> terminators = UtilList.newList();

    public GeneticAlgorithmImpl(final Population<R, P, G, I> initPopulation) {
        this.currentPopulation = initPopulation;
        this.percentageOfPairings = DEFAULT_PERCENTAGE_OF_PAIRINGS;
        addTerminator(terminatorPopulationSize);
    }

    public synchronized void addListener(final ListenerEvolution<R, P, G, I> listener) {
        this.listeners.add(listener);
    }

    public synchronized void addTerminator(final TerminatorEvolution<R, P, G, I> terminator) {
        terminators.add(terminator);
    }

    public synchronized void evolve() {
        if (getFactoryIndividual() == null) {
            throw new ExceptionRuntimeGA("set individual factory first");
        }
        boolean run = true;
        while (run) {
            history.add(getCurrentPopulation().clone());
            currentPopulation = evolve(getCurrentPopulation());
            if (log.isInfoEnabled()) {
                log.info("got new population (top 50)"
                        + UtilCollection.toString(getCurrentPopulation().getSubPopulation(50)));
            }
            for (final ListenerEvolution<R, P, G, I> l : listeners) {
                l.newPopulation(currentPopulation);
            }
            if (log.isInfoEnabled()) {
                log.info("generations passed " + history.size());
            }
            if (log.isDebugEnabled()) {
                log.debug("history " + UtilCollection.toString(history));
            }

            for (final TerminatorEvolution<R, P, G, I> t : getTerminators()) {
                if (t.visit(this)) {
                    run = false;
                    if (log.isInfoEnabled()) {
                        log.info(t + " requested termination");
                    }
                    break;
                }
            }
        }
        if (log.isInfoEnabled()) {
            log.info("all done");
        }
    }

    private Population<R, P, G, I> evolve(final Population<R, P, G, I> population) {

        grow(population);
        trim(population);

        return population;
    }

    public synchronized Population<R, P, G, I> getCurrentPopulation() {
        return currentPopulation;
    }

    public synchronized IndividualBreeder<R, P, G, I> getFactoryIndividual() {
        return factoryIndividual;
    }

    public synchronized List<Population<R, P, G, I>> getHistory() {
        // TODO make copy, populations may still be altered
        return Collections.unmodifiableList(history);
    }

    public synchronized int getMaxPopulationSize() {
        return terminatorPopulationSize.getMaxPopulationSize();
    }

    public synchronized double getPercentageOfPairings() {
        return percentageOfPairings;
    }

    public synchronized Population<R, P, G, I> getPreviousPopulation() {
        return getHistory().get(0);
    }

    public synchronized List<TerminatorEvolution<R, P, G, I>> getTerminators() {
        return terminators;
    }

    private void grow(final Population<R, P, G, I> population) {

        final int oldSize = population.getSize();

        List<I> breedingIndividuals;

        if (population.getSize() > 2) {

            int breedingPopulationSize = (int) (population.getSize() * getPercentageOfPairings());
            if (breedingPopulationSize < 2) {
                breedingPopulationSize = 2;
            }
            if (breedingPopulationSize > DEFAULT_MAX_OF_PAIRINGS) {
                if (log.isDebugEnabled()) {
                    log.debug("high number of breeding individuals (" + breedingPopulationSize + "), decrease to "
                            + DEFAULT_MAX_OF_PAIRINGS);
                }
                breedingPopulationSize = DEFAULT_MAX_OF_PAIRINGS;

            }
            if (breedingPopulationSize % 2 != 0) {
                breedingPopulationSize++;
                if (log.isDebugEnabled()) {
                    log.debug("odd number of breeding individuals, increase to " + breedingPopulationSize);
                }
            }

            breedingIndividuals = population.getSubPopulation(breedingPopulationSize).getIndividuals();

        } else {
            breedingIndividuals = new ArrayList<I>(population.clone().getIndividuals());
        }

        if (log.isInfoEnabled()) {
            log.info("growing population with " + breedingIndividuals.size() + " individuals");
        }

        // shuffle to pick randomly from choosen subpopulation
        Collections.shuffle(breedingIndividuals);
        while (breedingIndividuals.size() > 1) {
            final Iterator<I> it = breedingIndividuals.iterator();
            final I i1 = it.next();
            it.remove();
            final I i2 = it.next();
            it.remove();
            final IndividualPair<R, P, G, I> pair = new IndividualPair<R, P, G, I>(i1, i2);
            final Collection<I> newPair = getFactoryIndividual().breed(pair);
            for (final I ii : newPair) {
                final boolean success = population.add(ii);
                if (success) {

                } else {
                    if (log.isDebugEnabled()) {
                        log.debug("individual rejected: " + ii.getGenome());
                    }
                }
            }
        }

        if (oldSize == population.getSize() && currentGrowRetry <= maxGrowRetry) {
            if (log.isInfoEnabled()) {
                log.info("failed to grow, try again");
            }
            currentGrowRetry++;
            grow(population);
        } else if (currentGrowRetry >= maxGrowRetry) {
            if (log.isWarnEnabled()) {
                log.warn("failed to grow after " + currentGrowRetry);
            }
            currentGrowRetry = 0;
        } else {
            if (log.isInfoEnabled()) {
                log.info("done growing, new pop. size: " + population.getSize());
            }
            currentGrowRetry = 0;
        }
    }

    public synchronized void setFactoryIndividual(final IndividualBreeder<R, P, G, I> factoryIndividual) {
        this.factoryIndividual = factoryIndividual;
    }

    public synchronized void setMaxPopulationSize(final int maxPopulationSize) {
        terminatorPopulationSize.setMaxPopulationSize(maxPopulationSize);
    }

    public synchronized void setPercentageOfPairings(final double percentageOfPairings) {
        this.percentageOfPairings = percentageOfPairings;
    }

    private synchronized void trim(final Population<R, P, G, I> population) {
        if (population.getSize() < getMaxPopulationSize()) {
            return;
        }
        final int oldSize = population.getSize();
        population.trim(getMaxPopulationSize());
        if (log.isDebugEnabled()) {
            log.debug("trimmed population size from " + oldSize + " to " + population.getSize());
        }
    }

}