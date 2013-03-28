package net.sf.geal.terminator;

import net.sf.geal.GeneticAlgorithm;
import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;
import net.sf.kerner.utils.collections.visitor.Visitor;

public interface TerminatorEvolution<R, P, G extends Gene<P>, I extends Individual<R, P, G>> extends
        Visitor<Boolean, GeneticAlgorithm<R, P, G, I>> {

}
