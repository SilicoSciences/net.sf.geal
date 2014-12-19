package net.sf.geal.mutator.gene;

import net.sf.jranges.range.integerrange.RangeInteger;
import net.sf.jranges.range.integerrange.impl.ZeroPositiveIntegerRange;
import net.sf.kerner.utils.math.UtilRandom;

public class MutatorGeneInteger implements MutatorGene {

    private final RangeInteger range;

    public MutatorGeneInteger(final int low, final int high, final int interval) {
        range = new ZeroPositiveIntegerRange(low, high, interval);
    }

    @Override
    public Object mutate(final Object oldValue) {
        int result = UtilRandom.generateBetween(range.getStart(), range.getStop());
        while (!range.includes(result)) {
            result = UtilRandom.generateBetween(range.getStart(), range.getStop());
        }
        return result;
    }

}
