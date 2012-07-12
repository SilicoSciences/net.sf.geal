package net.sf.ga;

import java.util.List;

import net.sf.ga.individual.Individual;
import net.sf.geal.gene.GeneBoolean;

public class FitnessCalculatorBoolean extends FitnessCalculatorAbstract<List<Boolean>, Boolean, GeneBoolean> implements
        FitnessCalculator<List<Boolean>, Boolean, GeneBoolean> {

    public double getFitness(final Individual<List<Boolean>, Boolean, GeneBoolean> individual) {
        // double result = getFitnessFromSame(individual);
        // if (result > 0) {
        // return result;
        // }
        double result = 0;
        for (final GeneBoolean g : individual.getGenome().getGenes()) {
            if (g.express()) {
                result++;
            }
        }
        return result;
    }
}
