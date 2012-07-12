package net.sf.geal.mutator.gene;

import net.sf.kerner.utils.math.RandomFactory;

public class MutatorGeneBoolean implements MutatorGene<Boolean> {

    public Boolean mutate(final Boolean oldValue) {
        return RandomFactory.generate();
    }

}
