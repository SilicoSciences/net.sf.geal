package net.sf.geal;

import java.util.Comparator;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;

public interface FitnessCalculator<R, P, G extends Gene<P>> extends Comparator<Individual<R, P, G>> {

    double getFitness(Individual<R, P, G> individual);

}
