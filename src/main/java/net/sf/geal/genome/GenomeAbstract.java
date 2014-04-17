package net.sf.geal.genome;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import net.sf.geal.gene.Gene;
import net.sf.kerner.utils.collections.list.impl.UtilList;

public abstract class GenomeAbstract implements Genome {

    private List<Gene> genes = UtilList.newList();

    private Properties properties = new Properties();

    public GenomeAbstract() {

    }

    public GenomeAbstract(final List<Gene> genes) {
        this.genes = genes;
    }

    @Override
    public abstract GenomeAbstract clone();

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof GenomeAbstract))
            return false;
        final GenomeAbstract other = (GenomeAbstract) obj;
        if (genes == null) {
            if (other.genes != null)
                return false;
        } else if (!genes.equals(other.genes))
            return false;
        return true;
    }

    @Override
    public synchronized List<Gene> getGenes() {
        return genes;
    }

    @Override
    public synchronized Properties getProperties() {
        return properties;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genes == null) ? 0 : genes.hashCode());
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
    public synchronized void setProperties(final Properties properties) {
        this.properties = properties;
    }

    @Override
    public synchronized String toString() {
        return getProperties().toString() + ", " + getGenes().toString();
    }
}
