package net.sf.ga.mutator.gene;

import net.sf.jranges.range.integer.IntegerRange;
import net.sf.jranges.range.integer.impl.ZeroPositiveIntegerRange;
import net.sf.kerner.utils.math.RandomFactory;

public class MutatorGeneInteger implements MutatorGene<Object> {

    private final IntegerRange range;

    public MutatorGeneInteger(final int low, final int high, final int interval) {
        this.range = new ZeroPositiveIntegerRange(low, high, interval);
    }

    public Object mutate(final Object oldValue) {
        int result = RandomFactory.generateBetween(range.getStart(), range.getStop());
        while (!range.includes(result)) {
            result = RandomFactory.generateBetween(range.getStart(), range.getStop());
        }
        return result;
    }

}
