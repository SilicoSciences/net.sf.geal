package net.sf.geal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.FactoryIndividual;
import net.sf.geal.individual.Individual;
import net.sf.geal.individual.IndividualPair;
import net.sf.geal.population.Population;
import net.sf.kerner.utils.collections.impl.UtilCollection;
import net.sf.kerner.utils.collections.list.impl.UtilList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneticAlgorithmImpl<R, P, G extends Gene<P>> implements GeneticAlgorithm<R, P, G> {

    private static final Logger log = LoggerFactory.getLogger(GeneticAlgorithmImpl.class);

    public final static double DEFAULT_PERCENTAGE_OF_PAIRINGS = 0.1;

    public final static int DEFAULT_MAX_POPULATION_SIZE = 1000;

    private final List<Population<R, P, G>> history = UtilList.newList();

    private final List<ListenerEvolution<R, P, G>> listeners = UtilList.newList();

    private final List<TerminatorEvolution<R, P, G>> terminators = UtilList.newList();

    private volatile FactoryIndividual<R, P, G> factoryIndividual;

    private volatile Population<R, P, G> currentPopulation;

    private final TerminatorPopulationSize<R, P, G> terminatorPopulationSize = new TerminatorPopulationSize<R, P, G>(
            DEFAULT_MAX_POPULATION_SIZE);

    private volatile double percentageOfPairings;

    public GeneticAlgorithmImpl(final Population<R, P, G> initPopulation) {
        this.currentPopulation = initPopulation;
        this.percentageOfPairings = DEFAULT_PERCENTAGE_OF_PAIRINGS;
        addTerminator(terminatorPopulationSize);
    }

    public Population<R, P, G> getCurrentPopulation() {
        return currentPopulation;
    }

    public Population<R, P, G> getPreviousPopulation() {
        return getHistory().get(0);
    }

    public List<Population<R, P, G>> getHistory() {
        // TODO make copy, populations may still be altered
        return Collections.unmodifiableList(history);
    }

    public FactoryIndividual<R, P, G> getFactoryIndividual() {
        return factoryIndividual;
    }

    public void setFactoryIndividual(final FactoryIndividual<R, P, G> factoryIndividual) {
        this.factoryIndividual = factoryIndividual;
    }

    public double getPercentageOfPairings() {
        return percentageOfPairings;
    }

    public void setPercentageOfPairings(final double percentageOfPairings) {
        this.percentageOfPairings = percentageOfPairings;
    }

    public List<TerminatorEvolution<R, P, G>> getTerminators() {
        return terminators;
    }

    public void addTerminator(final TerminatorEvolution<R, P, G> terminator) {
        terminators.add(terminator);
    }

    public int getMaxPopulationSize() {
        return terminatorPopulationSize.getMaxPopulationSize();
    }

    public void setMaxPopulationSize(final int maxPopulationSize) {
        terminatorPopulationSize.setMaxPopulationSize(maxPopulationSize);
    }

    public void addListener(final ListenerEvolution<R, P, G> listener) {
        this.listeners.add(listener);
    }

    public void evolve() {
        if (getFactoryIndividual() == null) {
            throw new ExceptionRuntimeGA("set individual factory first");
        }
        boolean run = true;
        while (run) {
            history.add(getCurrentPopulation().clone());
            currentPopulation = evolve(getCurrentPopulation());
            if (log.isInfoEnabled()) {
                log.info("got new population "
                        + UtilCollection.toString(getCurrentPopulation().getSubPopulation(getMaxPopulationSize())));
            }
            for (final ListenerEvolution<R, P, G> l : listeners) {
                l.newPopulation(currentPopulation);
            }
            if (log.isInfoEnabled()) {
                log.info("generations passed " + history.size());
            }
            if (log.isDebugEnabled()) {
                log.debug("history " + UtilCollection.toString(history));
            }

            for (final TerminatorEvolution<R, P, G> t : getTerminators()) {
                if (t.visit(this)) {
                    run = false;
                    break;
                }
            }
        }
    }

    private Population<R, P, G> evolve(final Population<R, P, G> population) {

        grow(population);
        trim(population);

        return population;
    }

    private void grow(final Population<R, P, G> population) {

        List<Individual<R, P, G>> breedingIndividuals;

        if (population.getSize() > 2) {

            int breedingPopulationSize = (int) (population.getSize() * getPercentageOfPairings());
            if (breedingPopulationSize < 2) {
                breedingPopulationSize = 2;
            }

            breedingIndividuals = population.getSubPopulation(breedingPopulationSize).getIndividuals();

            // if (log.isDebugEnabled()) {
            // log.debug("new individual size: " + breedingIndividuals.size());
            // }
        } else {
            breedingIndividuals = new ArrayList<Individual<R, P, G>>(population.clone().getIndividuals());
        }

        boolean success = false;
        while (breedingIndividuals.size() > 1 || success == false) {
            // shuffle to pick randomly from choosen subpopulation
            Collections.shuffle(breedingIndividuals);
            final Iterator<Individual<R, P, G>> it = breedingIndividuals.iterator();
            final IndividualPair<R, P, G> pair = new IndividualPair<R, P, G>(it.next(), it.next());
            final Collection<Individual<R, P, G>> newPair = getFactoryIndividual().breed(pair);
            for (final Individual<R, P, G> i : newPair) {
                success = population.add(i);
                if (success == false) {
                    if (log.isInfoEnabled()) {
                        log.info("individual rejected: " + i.getGenome());
                    }
                    break;
                }
            }
            if (success) {
                it.remove();

                // if (log.isDebugEnabled()) {
                // log.debug("new individual size: " + breedingIndividuals.size());
                // }
            }
        }
    }

    private void trim(final Population<R, P, G> population) {
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
