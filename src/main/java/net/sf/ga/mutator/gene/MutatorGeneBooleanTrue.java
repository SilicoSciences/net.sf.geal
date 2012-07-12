package net.sf.ga.mutator.gene;


public class MutatorGeneBooleanTrue implements MutatorGene<Boolean> {

    public Boolean mutate(final Boolean oldValue) {
        return Boolean.TRUE;
    }

}
