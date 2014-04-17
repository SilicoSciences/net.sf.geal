package net.sf.geal.mutator.genome;

import net.sf.geal.genome.GenomePair;
import net.sf.geal.mutator.Mutator;

public interface MutatorCrossOver extends Mutator {

    GenomePair cross(GenomePair genomes);

}
