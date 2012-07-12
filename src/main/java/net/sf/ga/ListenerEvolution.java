package net.sf.ga;

import net.sf.ga.population.Population;
import net.sf.geal.gene.Gene;

public interface ListenerEvolution<R, P, G extends Gene<P>> {

    void newPopulation(Population<R, P, G> population);

}
