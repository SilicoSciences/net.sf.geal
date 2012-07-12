package net.sf.ga.mutator.gene;

public interface MutatorGene<P> {

    P mutate(P oldValue);

}
