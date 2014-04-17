package net.sf.geal.mutator.genome;

import net.sf.geal.genome.Genome;
import net.sf.geal.mutator.Mutator;

public interface MutatorPoint extends Mutator {

    Genome mutate(Genome genome);

}
