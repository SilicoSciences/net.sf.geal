package net.sf.geal.gene;

import net.sf.geal.ExceptionRuntimeGA;
import net.sf.geal.mutator.gene.MutatorGene;

public class GeneImpl implements Gene {

    protected volatile MutatorGene mutator;

    protected volatile Object value;

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
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof GeneImpl))
            return false;
        final GeneImpl other = (GeneImpl) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
        // return super.equals(obj);
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
        // return super.hashCode();
    }

    @Override
    public synchronized void impress(final Object property) {
        value = property;
    }

    @Override
    public synchronized void mutate() {
        if (mutator == null) {
            throw new ExceptionRuntimeGA("set mutator first");
        }
        impress(getMutator().mutate(express()));
    }

    public synchronized void setMutator(final MutatorGene mutator) {
        this.mutator = mutator;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "=" + value.toString();
    }

}
