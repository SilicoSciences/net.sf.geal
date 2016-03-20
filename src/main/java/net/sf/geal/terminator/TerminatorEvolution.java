package net.sf.geal.terminator;

import net.sf.geal.GeneticAlgorithm;
import net.sf.kerner.utils.collections.filter.Filter;
import net.sf.kerner.utils.visitor.Visitor;

public interface TerminatorEvolution extends Filter<GeneticAlgorithm> {

	void setEnabled(boolean enabled);

	boolean isEnabled();

}
