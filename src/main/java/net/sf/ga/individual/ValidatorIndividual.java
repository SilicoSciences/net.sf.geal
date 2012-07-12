package net.sf.ga.individual;

import net.sf.geal.gene.Gene;
import net.sf.kerner.utils.collections.filter.Filter;

public interface ValidatorIndividual<R, P, G extends Gene<P>> extends Filter<Individual<R, P, G>> {

}
