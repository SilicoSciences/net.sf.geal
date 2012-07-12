package net.sf.geal.mutator.gene;

public interface MutatorGene<P> {

    P mutate(P oldValue);

}
