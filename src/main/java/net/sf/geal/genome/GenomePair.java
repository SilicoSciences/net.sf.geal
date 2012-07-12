package net.sf.geal.genome;

import net.sf.geal.ObjectPair;
import net.sf.geal.gene.Gene;

public class GenomePair<R, P, G extends Gene<P>> extends ObjectPair<Genome<R, P, G>, Genome<R, P, G>> {

    public GenomePair() {
        super();

    }

    public GenomePair(final Genome<R, P, G> first, final Genome<R, P, G> second) {
        super(first, second);

    }

}
