package net.sf.geal.individual;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;

public interface Individual<R, P, G extends Gene<P>> extends Comparable<Individual<R, P, G>> {

    Individual<R, P, G> clone();

    double getFitness();

    int getGeneration();

    Genome<R, P, G> getGenome();

    void triggerCalculation();

}
