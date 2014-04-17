package net.sf.geal.genome.impl;

import java.util.Map.Entry;
import java.util.Properties;

import net.sf.geal.gene.Gene;
import net.sf.geal.gene.impl.GeneProperties;
import net.sf.geal.genome.GenomeAbstract;

public class GenomeProperties extends GenomeAbstract {

    public GenomeProperties() {

    }

    public GenomeProperties(final GenomeProperties template) {
        for (final Gene g : template.getGenes()) {
            getGenes().add(g.clone());
        }
    }

    @Override
    public GenomeProperties clone() {
        return new GenomeProperties(this);
    }

    @Override
    public Properties express() {
        final Properties result = new Properties();
        for (final Gene g : this) {
            result.put(((GeneProperties) g).getKey(), ((GeneProperties) g).getValue());
        }
        return result;
    }

    @Override
    public void impress(final Object result) {
        for (final Entry<Object, Object> p : ((Properties) result).entrySet()) {
            for (final Gene g : getGenes()) {
                if (((GeneProperties) g).getKey().equals(p.getKey())) {
                    ((GeneProperties) g).setValue(p.getValue());
                }
            }
        }
    }
}
