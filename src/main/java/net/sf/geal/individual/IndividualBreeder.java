package net.sf.geal.individual;

import java.util.Collection;
import java.util.List;

import net.sf.geal.mutator.genome.MutatorCrossOver;
import net.sf.geal.mutator.genome.MutatorPoint;

public interface IndividualBreeder {

    Collection<Individual> breed(IndividualPair pair);

    List<MutatorCrossOver> getCrossOvers();

    List<MutatorPoint> getMutators();

}
