package net.sf.geal;

import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;
import net.sf.geal.individual.IndividualBreeder;
import net.sf.geal.population.Population;

public interface GeneticAlgorithm<R, P, G extends Gene<P>, I extends Individual<R, P, G>> {

    void addListener(ListenerEvolution<R, P, G, I> listener);

    void addTerminator(TerminatorEvolution<R, P, G, I> terminator);

    void evolve();

    Population<R, P, G, I> getCurrentPopulation();

    IndividualBreeder<R, P, G, I> getFactoryIndividual();

    List<Population<R, P, G, I>> getHistory();

    double getPercentageOfPairings();

}
