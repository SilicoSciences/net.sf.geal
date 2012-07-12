package net.sf.ga;

import java.util.List;

import net.sf.ga.individual.FactoryIndividual;
import net.sf.ga.population.Population;
import net.sf.geal.gene.Gene;

public interface GeneticAlgorithm<R, P, G extends Gene<P>> {

    void addListener(ListenerEvolution<R, P, G> listener);

    void addTerminator(TerminatorEvolution<R, P, G> terminator);

    Population<R, P, G> getCurrentPopulation();

    List<Population<R, P, G>> getHistory();

    FactoryIndividual<R, P, G> getFactoryIndividual();

    double getPercentageOfPairings();

    void evolve();

}
