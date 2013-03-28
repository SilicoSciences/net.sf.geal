package net.sf.geal.gene;

import net.sf.geal.mutator.gene.MutatorGene;
import net.sf.kerner.utils.impl.util.Util;

public class GeneImpl implements Gene {

    private volatile MutatorGene mutator;

    private volatile Object value;

    public GeneImpl(final Gene template) {
        value = template.express();
        mutator = template.getMutator();
    }

    public GeneImpl(final Object value) {
        this.value = value;
    }

    @Override
    public synchronized GeneImpl clone() {
        return new GeneImpl(this);
    }

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized Object express() {
        return value;
    }

    @Override
    public synchronized MutatorGene getMutator() {
        return mutator;
    }

    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    };

    @Override
    public synchronized void impress(final Object property) {
        value = property;
    }

    @Override
    public synchronized void mutate() {
        impress(getMutator().mutate(express()));
    }

    public synchronized void setMutator(final MutatorGene mutator) {
        this.mutator = mutator;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
