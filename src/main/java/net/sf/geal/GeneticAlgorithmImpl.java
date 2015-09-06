package net.sf.geal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.geal.individual.Individual;
import net.sf.geal.individual.IndividualBreeder;
import net.sf.geal.individual.IndividualPair;
import net.sf.geal.population.Population;
import net.sf.geal.terminator.TerminatorEvolution;
import net.sf.geal.terminator.TerminatorGenerations;
import net.sf.geal.terminator.TerminatorPopulationSize;
import net.sf.kerner.utils.collections.list.UtilList;
import net.sf.kerner.utils.collections.set.UtilSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticAlgorithmImpl implements GeneticAlgorithm {

    public final static int DEFAULT_GROW_MAX_RETRY = 200;

    public final static int DEFAULT_MAX_OF_PAIRINGS = 20;

    public final static int DEFAULT_MAX_POPULATION_SIZE = 10000;

    public final static int DEFAULT_MAX_GENERATIONS = 10000;

    public final static double DEFAULT_PERCENTAGE_OF_PAIRINGS = 0.1;

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmImpl.class);

    private volatile int currentGrowRetry = 0;

    private volatile Population currentPopulation;

    private volatile IndividualBreeder factoryIndividual;

    private final List<Population> history = UtilList.newList();

    private final Set<ListenerEvolution> listeners = UtilSet.newSet();

    private volatile int maxGrowRetry = DEFAULT_GROW_MAX_RETRY;

    private volatile double percentageOfPairings;

    private final TerminatorPopulationSize terminatorPopulationSize = new TerminatorPopulationSize();

    private final TerminatorGenerations terminatorGenerations = new TerminatorGenerations(
            DEFAULT_MAX_GENERATIONS);

    private final List<TerminatorEvolution> terminators = UtilList.newList();

    private int maxPopulationSize = DEFAULT_MAX_POPULATION_SIZE;

    public GeneticAlgorithmImpl(final Population initPopulation) {
        currentPopulation = initPopulation;
        percentageOfPairings = DEFAULT_PERCENTAGE_OF_PAIRINGS;
        addTerminator(terminatorPopulationSize);
        addTerminator(terminatorGenerations);
    }

    @Override
    public synchronized void addListener(final ListenerEvolution listener) {
        listeners.add(listener);
    }

    @Override
    public synchronized void addTerminator(final TerminatorEvolution terminator) {
        terminators.add(terminator);
    }

    private void checkPreReqEvolve() {
        if (getIndividualBreeder() == null) {
            throw new ExceptionRuntimeGA("set individual factory first");
        }
        if (getCurrentPopulation() == null || getCurrentPopulation().getIndividuals().isEmpty()) {
            throw new ExceptionRuntimeGA("invalid population");
        }
    }

    private boolean checkRun() {
        for (final TerminatorEvolution t : getTerminators()) {
            if (t.visit(this)) {
                if (log.isInfoEnabled()) {
                    log.info(t + " requested termination");
                }
                return false;
            }
        }
        return true;
    }

    private void doEvolve() {
        history.add(getCurrentPopulation().clone());
        currentPopulation = evolve(getCurrentPopulation());
        if (log.isInfoEnabled()) {
            // log.info("got new population (top 6)"
            // +
            // UtilCollection.toString(getCurrentPopulation().getSubPopulation(6)));
            // for (final Individual g :
            // getCurrentPopulation().getSubPopulation(10)) {
            // log.info(g.getGenome().getProperties().toString());
            // }
        }
        for (final ListenerEvolution l : listeners) {
            l.newPopulation(currentPopulation);
        }
        if (log.isInfoEnabled()) {
            log.info("generations passed " + history.size() + ", population size: "
                    + currentPopulation.getSize());
        }
        // if (log.isDebugEnabled()) {
        // log.debug("history " + UtilCollection.toString(history));
        // }
    }

    @Override
    public synchronized void evolve() {
        checkPreReqEvolve();
        boolean run = true;
        while (run) {
            doEvolve();
            run = checkRun();
        }
        if (log.isInfoEnabled()) {
            log.info("all done");
        }
    }

    public synchronized void evolve(int generations) {
        checkPreReqEvolve();
        boolean run = true;
        for (int i = 0; i < generations; i++) {
            if (!run) {
                break;
            }
            doEvolve();
            run = checkRun();
        }
        if (log.isInfoEnabled()) {
            log.info("all done");
        }
    }

    private synchronized Population evolve(final Population population) {

        grow(population);
        trim(population);

        return population;
    }

    @Override
    public synchronized Population getCurrentPopulation() {
        return currentPopulation;
    }

    @Override
    public synchronized List<Population> getHistory() {
        // TODO make copy, populations may still be altered
        return Collections.unmodifiableList(history);
    }

    @Override
    public synchronized IndividualBreeder getIndividualBreeder() {
        return factoryIndividual;
    }

    @Override
    public synchronized int getMaxPopulationSize() {
        return maxPopulationSize;
    }

    @Override
    public synchronized double getPercentageOfPairings() {
        return percentageOfPairings;
    }

    public synchronized Population getPreviousPopulation() {
        return getHistory().get(0);
    }

    public synchronized List<TerminatorEvolution> getTerminators() {
        return terminators;
    }

    private void grow(final Population population) {

        final int oldSize = population.getSize();

        List<Individual> breedingIndividuals;

        if (population.getSize() > 2) {

            int breedingPopulationSize = (int) (population.getSize() * getPercentageOfPairings());
            if (breedingPopulationSize < 2) {
                breedingPopulationSize = 2;
            }
            if (breedingPopulationSize > DEFAULT_MAX_OF_PAIRINGS) {
                if (log.isDebugEnabled()) {
                    log.debug("high number of breeding individuals (" + breedingPopulationSize
                            + "), decrease to " + DEFAULT_MAX_OF_PAIRINGS);
                }
                breedingPopulationSize = DEFAULT_MAX_OF_PAIRINGS;

            }
            if (breedingPopulationSize % 2 != 0) {
                breedingPopulationSize++;
                if (log.isDebugEnabled()) {
                    log.debug("odd number of breeding individuals, increase to "
                            + breedingPopulationSize);
                }
            }

            breedingIndividuals = population.getSubPopulation(breedingPopulationSize)
                    .getIndividuals();

        } else {
            breedingIndividuals = new ArrayList<Individual>(population.clone().getIndividuals());
        }

        if (breedingIndividuals.size() < 2) {
            throw new IllegalArgumentException("Not enough individuals for evolution");
        }

        if (log.isDebugEnabled()) {
            log.debug("growing population with " + breedingIndividuals.size() + " individuals");
        }

        // shuffle to pick randomly from choosen subpopulation
        Collections.shuffle(breedingIndividuals);
        while (breedingIndividuals.size() > 1) {
            final Iterator<Individual> it = breedingIndividuals.iterator();
            final Individual i1 = it.next();
            it.remove();
            final Individual i2 = it.next();
            it.remove();
            final IndividualPair pair = new IndividualPair(i1, i2);
            final Collection<Individual> newPair = getIndividualBreeder().breed(pair);
            for (final Individual ii : newPair) {
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
            if (log.isDebugEnabled()) {
                log.debug("failed to grow, try again");
            }
            currentGrowRetry++;
            grow(population);
        } else if (currentGrowRetry >= maxGrowRetry) {
            if (log.isWarnEnabled()) {
                log.warn("failed to grow after " + currentGrowRetry);
            }
            currentGrowRetry = 0;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("done growing, new pop. size: " + population.getSize()
                        + ", max. fitness: " + getCurrentPopulation().getMaxFitness());
            }
            currentGrowRetry = 0;
        }
    }

    public synchronized void setIndividualBreeder(final IndividualBreeder factoryIndividual) {
        this.factoryIndividual = factoryIndividual;
    }

    public synchronized void setMaxPopulationSize(final int maxPopulationSize) {
        this.setMaxPopulationSize(maxPopulationSize, true);
    }

    public synchronized void setMaxPopulationSize(final int maxPopulationSize, boolean terminate) {
        if (maxPopulationSize <= 0) {
            throw new IllegalArgumentException("for " + maxPopulationSize);
        }
        this.maxPopulationSize = maxPopulationSize;
        terminatorPopulationSize.setEnabled(terminate);
    }

    public synchronized void setPercentageOfPairings(final double percentageOfPairings) {
        this.percentageOfPairings = percentageOfPairings;
    }

    private synchronized void trim(final Population population) {
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
