package net.sf.ga.individual;

import net.sf.ga.population.Population;
import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;

public interface Individual<R, P, G extends Gene<P>> extends Comparable<Individual<R, P, G>> {

    Genome<R, P, G> getGenome();

    Individual<R, P, G> clone();

    int getGeneration();

    double getFitness();

    void setPopulation(Population<R, P, G> population);

    Population<R, P, G> getPopulation();

}
