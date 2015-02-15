package net.sf.geal.genome.impl;

import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.GenomeAbstract;
import net.sf.kerner.utils.collections.list.UtilList;

public class GenomeIntegerSum extends GenomeAbstract {

    public GenomeIntegerSum() {
        super();
    }

    public GenomeIntegerSum(final List<Gene> genes) {
        super(genes);
    }

    @Override
    public GenomeIntegerSum clone() {
        final GenomeIntegerSum result = new GenomeIntegerSum(UtilList.clone(getGenes()));
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
