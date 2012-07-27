package net.sf.geal;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;

public interface FitnessCalculator<R, P, G extends Gene<P>, I extends Individual<R, P, G>> {

    double getFitness(I individual);

}
