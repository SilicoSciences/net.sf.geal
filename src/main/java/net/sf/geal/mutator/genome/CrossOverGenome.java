package net.sf.geal.mutator.genome;

import net.sf.geal.genome.GenomePair;

public interface CrossOverGenome {

    GenomePair cross(GenomePair genomes);

    double getFrequency();

}
