package net.sf.ga.mutator.genome;

import net.sf.ga.mutator.MutatorAbstract;
import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.kerner.utils.math.RandomFactory;

public class MutatorGenomeDefault<R, P, G extends Gene<P>> extends MutatorAbstract implements MutatorGenome<R, P, G> {

    public final static double DEFAULT_FREQUENCY = 0.1;

    public MutatorGenomeDefault() {
        super(DEFAULT_FREQUENCY);
    }

    public MutatorGenomeDefault(final double frequency) {
        super(frequency);
    }

    public synchronized Genome<R, P, G> mutate(final Genome<R, P, G> genome) {
        final Genome<R, P, G> result = genome.clone();

        for (int i = 0; i < genome.getGenes().size(); i++) {
            if (RandomFactory.generateWithProbability(getFrequency())) {
                result.getGenes().get(i).mutate();
            }
        }

        return result;
    }

}
