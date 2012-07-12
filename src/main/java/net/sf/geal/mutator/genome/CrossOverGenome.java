package net.sf.geal.mutator.genome;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.GenomePair;

public interface CrossOverGenome<R, P, G extends Gene<P>> {

    GenomePair<R, P, G> cross(GenomePair<R, P, G> genomes);

    double getFrequency();

}
