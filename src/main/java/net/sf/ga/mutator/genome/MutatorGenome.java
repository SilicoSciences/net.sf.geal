package net.sf.ga.mutator.genome;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;

public interface MutatorGenome<R, P, G extends Gene<P>> {

    Genome<R, P, G> mutate(Genome<R, P, G> genome);

    double getFrequency();

}
