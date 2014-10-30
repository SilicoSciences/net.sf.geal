package net.sf.geal.genome.impl;

import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.GenomeAbstract;
import net.sf.kerner.utils.collections.list.UtilList;

public class GenomeInteger extends GenomeAbstract {

    public GenomeInteger() {
        super();
    }

    public GenomeInteger(final List<Gene> genes) {
        super(genes);
    }

    @Override
    public GenomeInteger clone() {
        final GenomeInteger result = new GenomeInteger(UtilList.clone(getGenes()));
        result.getProperties().putAll(getProperties());
        return result;
    }

    @Override
    public Integer express() {
        return 1;
    }

    @Override
    public void impress(final Object result) {
        System.out.println("impress genome");
    }
}
