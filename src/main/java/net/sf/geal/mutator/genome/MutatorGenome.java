package net.sf.geal.mutator.genome;

import net.sf.geal.genome.Genome;

public interface MutatorGenome {

    double getFrequency();

    Genome mutate(Genome genome);

}
