package net.sf.geal.individual;

import net.sf.geal.ObjectPair;
import net.sf.geal.gene.Gene;

public class IndividualPair<R, P, G extends Gene<P>, I extends Individual<R, P, G>> extends ObjectPair<I, I> {

    public IndividualPair() {
        super();

    }

    public IndividualPair(final I first, final I second) {
        super(first, second);

    }

}
