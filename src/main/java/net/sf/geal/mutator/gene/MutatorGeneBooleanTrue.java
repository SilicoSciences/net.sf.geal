package net.sf.geal.mutator.gene;

public class MutatorGeneBooleanTrue implements MutatorGene {

    @Override
    public Boolean mutate(final Object oldValue) {
        return Boolean.TRUE;
    }

}
