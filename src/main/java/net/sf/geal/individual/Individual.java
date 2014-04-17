package net.sf.geal.individual;

import net.sf.geal.genome.Genome;
import net.sf.kerner.utils.Cloneable;

public interface Individual extends Comparable<Individual>, Cloneable<Individual> {

    @Override
    Individual clone();

    double getFitness();

    int getGeneration();

    Genome getGenome();

    void triggerCalculation();

}
