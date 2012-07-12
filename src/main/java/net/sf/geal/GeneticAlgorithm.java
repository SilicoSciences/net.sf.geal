package net.sf.geal;

import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.FactoryIndividual;
import net.sf.geal.population.Population;

public interface GeneticAlgorithm<R, P, G extends Gene<P>> {

    void addListener(ListenerEvolution<R, P, G> listener);

    void addTerminator(TerminatorEvolution<R, P, G> terminator);

    Population<R, P, G> getCurrentPopulation();

    List<Population<R, P, G>> getHistory();

    FactoryIndividual<R, P, G> getFactoryIndividual();

    double getPercentageOfPairings();

    void evolve();

}
