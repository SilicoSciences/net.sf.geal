package net.sf.geal.mutator.genome;

import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomePair;
import net.sf.geal.mutator.MutatorAbstract;
import net.sf.kerner.utils.collections.list.impl.UtilList;
import net.sf.kerner.utils.math.UtilMath;
import net.sf.kerner.utils.math.UtilRandom;

public class MutatorCrossOverDefault extends MutatorAbstract implements MutatorCrossOver {

    // private static final Logger log =
    // LoggerFactory.getLogger(CrossOverGenomeDefault.class);

    public final static double DEFAULT_FREQUENCY = 0.8;

    private int startIndex = -1;
    private int stopIndex = -1;

    private boolean indicesRandom = true;

    public MutatorCrossOverDefault() {
        super(DEFAULT_FREQUENCY);
    }

    public MutatorCrossOverDefault(final double frequency) {
        super(frequency);
    }

    @Override
    public GenomePair cross(final GenomePair genomes) {
        final int smallestSize = (int) UtilMath.min(genomes.getFirst().getGenes().size(), genomes
                .getSecond().getGenes().size());
        final Genome result1 = genomes.getFirst().clone();
        final Genome result2 = genomes.getSecond().clone();

        // if (log.isDebugEnabled()) {
        // log.debug("smallest size " + smallestSize);
        // }

        if (indicesRandom) {
            startIndex = UtilRandom.generateBetween(0, smallestSize - 1);
        }
        if (indicesRandom) {
            stopIndex = UtilRandom.generateBetween(startIndex, smallestSize - 1);
        }
        // if (log.isDebugEnabled()) {
        // log.debug("crossover at " + startIndex + "-" + stopIndex);
        // }

        final List<Gene> subList1 = UtilList.newList(result1.getGenes().subList(startIndex,
                stopIndex));
        final List<Gene> subList2 = UtilList.newList(result2.getGenes().subList(startIndex,
                stopIndex));

        UtilList.setAll(result1.getGenes(), subList2, startIndex);
        UtilList.setAll(result2.getGenes(), subList1, startIndex);

        return new GenomePair(result1, result2);
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public boolean isIndicesRandom() {
        return indicesRandom;
    }

    public void setIndicesRandom(final boolean indicesRandom) {
        this.indicesRandom = indicesRandom;
    }

    public void setStartIndex(final int startIndex) {
        indicesRandom = false;
        this.startIndex = startIndex;
    }

    public void setStopIndex(final int stopIndex) {
        indicesRandom = false;
        this.stopIndex = stopIndex;
    }

}
