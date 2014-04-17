package net.sf.geal.mutator.genome;

import net.sf.geal.genome.Genome;
import net.sf.geal.mutator.MutatorAbstract;
import net.sf.kerner.utils.math.UtilRandom;

public class MutatorPointDefault extends MutatorAbstract implements MutatorPoint {

    public final static double DEFAULT_FREQUENCY = 0.1;

    public MutatorPointDefault() {
        super(DEFAULT_FREQUENCY);
    }

    public MutatorPointDefault(final double frequency) {
        super(frequency);
    }

    @Override
    public synchronized Genome mutate(final Genome genome) {
        final Genome result = genome.clone();

        for (int i = 0; i < genome.getGenes().size(); i++) {
            if (UtilRandom.generateWithProbability(getFrequency())) {
                result.getGenes().get(i).mutate();
            }
        }

        return result;
    }

}
