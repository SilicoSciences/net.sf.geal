package net.sf.geal;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;

public abstract class FitnessCalculatorAbstract<R, P, G extends Gene<P>> implements FitnessCalculator<R, P, G> {

    public int compare(final Individual<R, P, G> o1, final Individual<R, P, G> o2) {
        // Calculation inverted, so max is higher
        return Double.compare(getFitness(o2), getFitness(o1));
    }

    // protected double getFitnessFromSame(final Individual<R, P, G> individual) {
    // final Population<R, P, G> pop = individual.getPopulation();
    // final Individual<R, P, G> copy = pop.find(individual.hashCode());
    // if (copy == null) {
    // return -2;
    // }
    // return copy.getFitness();
    // }

}
