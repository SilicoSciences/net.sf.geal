package net.sf.geal;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;
import net.sf.geal.population.Population;

public interface ListenerEvolution<R, P, G extends Gene<P>, I extends Individual<R, P, G>> {

    void newPopulation(Population<R, P, G, I> population);

}
