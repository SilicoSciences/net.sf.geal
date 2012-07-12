package net.sf.geal;

import net.sf.geal.gene.Gene;
import net.sf.geal.population.Population;

public interface ListenerEvolution<R, P, G extends Gene<P>> {

    void newPopulation(Population<R, P, G> population);

}
