package net.sf.geal.mutator.gene;

import net.sf.jranges.range.doublerange.RangeDouble;
import net.sf.jranges.range.doublerange.impl.ZeroPositiveDoubleRange;
import net.sf.kerner.utils.math.UtilRandom;

public class MutatorGeneDouble implements MutatorGene {

    private final RangeDouble range;

    public MutatorGeneDouble(final double low, final double high, final double interval) {
        range = new ZeroPositiveDoubleRange(low, high, interval);
    }

    @Override
    public Object mutate(final Object oldValue) {
        double result = UtilRandom.generateBetween(range.getStart(), range.getStop());
        while (!range.includes(result)) {
            result = UtilRandom.generateBetween(range.getStart(), range.getStop());
        }
        return result;
    }

}
