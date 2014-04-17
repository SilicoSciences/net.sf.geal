package net.sf.geal.gene.impl;

import net.sf.geal.gene.GeneImpl;
import net.sf.geal.mutator.gene.MutatorGene;

public class GeneInteger extends GeneImpl {

    public GeneInteger(final GeneInteger template) {
        super(template);
    }

    public GeneInteger(final Integer value) {
        super(value);
    }

    @Override
    public synchronized GeneInteger clone() {
        return new GeneInteger(this);
    }

    @Override
    public synchronized Integer express() {
        return (Integer) value;
    }

    @Override
    public synchronized MutatorGene getMutator() {
        return mutator;
    }

    @Override
    public synchronized void impress(final Object property) {
        if (property instanceof Integer) {
            super.impress(property);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
