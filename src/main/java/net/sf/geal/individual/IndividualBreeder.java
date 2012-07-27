package net.sf.geal.individual;

import java.util.Collection;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.mutator.genome.CrossOverGenome;
import net.sf.geal.mutator.genome.MutatorGenome;

public interface IndividualBreeder<R, P, G extends Gene<P>, I extends Individual<R, P, G>> {

    Collection<I> breed(IndividualPair<R, P, G, I> pair);

    List<CrossOverGenome<R, P, G>> getCrossOvers();

    List<MutatorGenome<R, P, G>> getMutators();

}
