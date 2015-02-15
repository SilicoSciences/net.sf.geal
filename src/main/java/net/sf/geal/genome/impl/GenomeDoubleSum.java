package net.sf.geal.genome.impl;

import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.GenomeAbstract;
import net.sf.kerner.utils.collections.list.UtilList;

public class GenomeDoubleSum extends GenomeAbstract {

    public GenomeDoubleSum() {
        super();
    }

    public GenomeDoubleSum(final Gene gene) {
        super(gene);
    }

    public GenomeDoubleSum(final List<Gene> genes) {
        super(genes);
    }

    @Override
    public GenomeDoubleSum clone() {
        final GenomeDoubleSum result = new GenomeDoubleSum(UtilList.clone(getGenes()));
        result.getProperties().putAll(getProperties());
        return result;
    }

    @Override
    public synchronized Integer express() {
        int sum = 0;
        for (final Gene g : genes) {
            sum += (Integer) g.express();
        }
        return sum;
    }

    @Override
    public synchronized void impress(final Object result) {
        for (final Gene g : genes) {
            g.impress(result);
        }
    }
}
