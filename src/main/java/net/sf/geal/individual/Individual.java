package net.sf.geal.individual;

import net.sf.geal.genome.Genome;

public interface Individual extends Comparable<Individual> {

    Individual clone();

    double getFitness();

    int getGeneration();

    Genome getGenome();

    void triggerCalculation();

}
