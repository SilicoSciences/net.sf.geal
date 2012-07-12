package net.sf.ga;

import java.util.Comparator;

import net.sf.ga.individual.Individual;
import net.sf.geal.gene.Gene;

public interface FitnessCalculator<R, P, G extends Gene<P>> extends Comparator<Individual<R, P, G>> {

    double getFitness(Individual<R, P, G> individual);

}
