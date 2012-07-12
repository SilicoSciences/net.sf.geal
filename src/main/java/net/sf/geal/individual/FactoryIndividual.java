package net.sf.geal.individual;

import java.util.Collection;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.mutator.genome.CrossOverGenome;
import net.sf.geal.mutator.genome.MutatorGenome;

public interface FactoryIndividual<R, P, G extends Gene<P>> {

    List<MutatorGenome<R, P, G>> getMutators();

    List<CrossOverGenome<R, P, G>> getCrossOvers();

    Collection<Individual<R, P, G>> breed(IndividualPair<R, P, G> pair);

}
