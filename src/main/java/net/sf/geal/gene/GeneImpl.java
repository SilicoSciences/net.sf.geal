package net.sf.geal.gene;

import net.sf.geal.mutator.gene.MutatorGene;
import net.sf.kerner.utils.impl.util.Util;

public class GeneImpl<P> implements Gene<P> {

    private volatile MutatorGene<P> mutator;

    private volatile P value;

    public GeneImpl(final P value) {
        this.value = value;
    }

    public GeneImpl(final Gene<P> template) {
        this.value = template.express();
        this.mutator = template.getMutator();
    }

    public synchronized void mutate() {
        impress(getMutator().mutate(express()));
    }

    public synchronized void setMutator(final MutatorGene<P> mutator) {
        this.mutator = mutator;
    }

    public synchronized MutatorGene<P> getMutator() {
        return mutator;
    }

    public synchronized P express() {
        return value;
    }

    public synchronized void impress(final P property) {
        this.value = property;
    };

    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized GeneImpl<P> clone() {
        return new GeneImpl<P>(this);
    }

}
