package net.sf.geal.mutator.gene;

import net.sf.kerner.utils.math.RandomFactory;

public class MutatorGeneBoolean implements MutatorGene {

    @Override
    public Boolean mutate(final Object oldValue) {
        return RandomFactory.generate();
    }

}
