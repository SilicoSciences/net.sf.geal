package net.sf.geal.genome;

import java.util.Iterator;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.kerner.utils.collections.list.impl.UtilList;
import net.sf.kerner.utils.impl.util.Util;

public abstract class GenomeAbstract<R, P, G extends Gene<P>> implements Genome<R, P, G> {

    private List<G> genes = UtilList.newList();

    @Override
    public synchronized String toString() {
        return getGenes().toString();
    }

    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGenes() == null) ? 0 : getGenes().hashCode());
        return result;
    }

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    public synchronized List<G> getGenes() {
        return genes;
    }

    public synchronized void setGenes(final List<G> genes) {
        this.genes = genes;
    }

    public Iterator<G> iterator() {
        return getGenes().iterator();
    }

    @Override
    public abstract GenomeAbstract<R, P, G> clone();
}
