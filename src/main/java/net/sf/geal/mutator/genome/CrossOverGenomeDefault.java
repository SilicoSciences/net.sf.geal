package net.sf.geal.mutator.genome;

import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomePair;
import net.sf.geal.mutator.MutatorAbstract;
import net.sf.kerner.utils.collections.list.impl.ListUtil;
import net.sf.kerner.utils.math.MathUtils;
import net.sf.kerner.utils.math.RandomFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrossOverGenomeDefault<R, P, G extends Gene<P>> extends MutatorAbstract implements
        CrossOverGenome<R, P, G> {

    private static final Logger log = LoggerFactory.getLogger(CrossOverGenomeDefault.class);

    public final static double DEFAULT_FREQUENCY = 0.8;

    private int startIndex = -1;
    private int stopIndex = -1;

    private boolean indicesRandom = true;

    public CrossOverGenomeDefault() {
        super(DEFAULT_FREQUENCY);
    }

    public CrossOverGenomeDefault(final double frequency) {
        super(frequency);
    }

    public GenomePair<R, P, G> cross(final GenomePair<R, P, G> genomes) {
        final int smallestSize = (int) MathUtils.min(genomes.getFirst().getGenes().size(), genomes.getSecond()
                .getGenes().size());
        final Genome<R, P, G> result1 = genomes.getFirst().clone();
        final Genome<R, P, G> result2 = genomes.getSecond().clone();

        // if (log.isDebugEnabled()) {
        // log.debug("smallest size " + smallestSize);
        // }

        if (indicesRandom) {
            startIndex = RandomFactory.generateBetween(0, smallestSize - 1);
        }
        if (indicesRandom) {
            stopIndex = RandomFactory.generateBetween(startIndex, smallestSize - 1);
        }
        // if (log.isDebugEnabled()) {
        // log.debug("crossover at " + startIndex + "-" + stopIndex);
        // }

        final List<G> subList1 = ListUtil.newList(result1.getGenes().subList(startIndex, stopIndex));
        final List<G> subList2 = ListUtil.newList(result2.getGenes().subList(startIndex, stopIndex));

        ListUtil.setAll(result1.getGenes(), subList2, startIndex);
        ListUtil.setAll(result2.getGenes(), subList1, startIndex);

        return new GenomePair<R, P, G>(result1, result2);
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(final int startIndex) {
        indicesRandom = false;
        this.startIndex = startIndex;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public void setStopIndex(final int stopIndex) {
        indicesRandom = false;
        this.stopIndex = stopIndex;
    }

    public boolean isIndicesRandom() {
        return indicesRandom;
    }

    public void setIndicesRandom(final boolean indicesRandom) {
        this.indicesRandom = indicesRandom;
    }

}
