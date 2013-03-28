package net.sf.geal.individual;

import java.util.Collection;
import java.util.List;

import net.sf.geal.mutator.genome.CrossOverGenome;
import net.sf.geal.mutator.genome.MutatorGenome;

public interface IndividualBreeder {

    Collection<Individual> breed(IndividualPair pair);

    List<CrossOverGenome> getCrossOvers();

    List<MutatorGenome> getMutators();

}
