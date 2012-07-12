package net.sf.ga.individual;

import net.sf.ga.ObjectPair;
import net.sf.geal.gene.Gene;

public class IndividualPair<R, P, G extends Gene<P>> extends ObjectPair<Individual<R, P, G>, Individual<R, P, G>> {

    public IndividualPair() {
        super();

    }

    public IndividualPair(final Individual<R, P, G> first, final Individual<R, P, G> second) {
        super(first, second);

    }

}
