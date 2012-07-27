package net.sf.geal.genome.impl;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import net.sf.geal.gene.impl.GeneProperties;
import net.sf.geal.genome.GenomeAbstract;

public class GenomeProperties extends GenomeAbstract<Properties, Object, GeneProperties> {

    public GenomeProperties() {

    }

    public GenomeProperties(final GenomeProperties template) {
        for (final GeneProperties g : template.getGenes()) {
            getGenes().add(g.clone());
        }
    }

    public Properties express() {
        final Properties result = new Properties();
        for (final GeneProperties g : this) {
            result.put(g.getKey(), g.getValue());
        }
        return result;
    }

    public void impress(final Properties result) {
        for (final Entry<Object, Object> p : result.entrySet()) {
            for (final GeneProperties g : getGenes()) {
                if (g.getKey().equals(p.getKey())) {
                    g.setValue(p.getValue());
                }
            }
        }
    }

    public Iterator<GeneProperties> iterator() {
        return getGenes().iterator();
    }

    @Override
    public GenomeAbstract<Properties, Object, GeneProperties> clone() {
        return new GenomeProperties(this);
    }

}
