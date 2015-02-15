package net.sf.geal.gene.impl;

import net.sf.geal.gene.GeneImpl;
import net.sf.geal.mutator.gene.MutatorGene;

public class GeneDouble extends GeneImpl {

    public GeneDouble(final Double value) {
        super(value);
    }

    public GeneDouble(final GeneDouble template) {
        super(template);
    }

    @Override
    public synchronized GeneDouble clone() {
        return new GeneDouble(this);
    }

    @Override
    public synchronized Double express() {
        return (Double) value;
    }

    @Override
    public synchronized MutatorGene getMutator() {
        return mutator;
    }

    @Override
    public synchronized void impress(final Object property) {
        if (property instanceof Double) {
            super.impress(property);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
