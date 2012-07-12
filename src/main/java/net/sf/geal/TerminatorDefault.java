package net.sf.geal;

import net.sf.geal.gene.Gene;

public class TerminatorDefault<R, P, G extends Gene<P>> implements TerminatorEvolution<R, P, G> {

    public Boolean visit(final GeneticAlgorithm<R, P, G> element) {
        // never terminate
        return false;
    }

}
