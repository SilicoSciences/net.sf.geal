package net.sf.geal.genome;

import java.util.Iterator;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.kerner.utils.collections.list.impl.UtilList;
import net.sf.kerner.utils.impl.util.Util;

public abstract class GenomeAbstract implements Genome {

    private List<Gene> genes = UtilList.newList();

    @Override
    public abstract GenomeAbstract clone();

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized List<Gene> getGenes() {
        return genes;
    }

    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGenes() == null) ? 0 : getGenes().hashCode());
        return result;
    }

    @Override
    public Iterator<Gene> iterator() {
        return getGenes().iterator();
    }

    public synchronized void setGenes(final List<Gene> genes) {
        this.genes = genes;
    }

    @Override
    public synchronized String toString() {
        return getGenes().toString();
    }
}
