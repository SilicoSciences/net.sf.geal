package net.sf.geal.gene;

import net.sf.ga.mutator.gene.MutatorGene;

public interface Gene<P> {

    /**
     * Get current value for this {@code Gene}.
     * 
     * @return current value for this {@code Gene}
     */
    P express();

    /**
     * Set current value for this {@code Gene}.
     * 
     * @param property
     */
    void impress(P property);

    /**
     * Trigger mutation of this {@code Gene}. Mutation will be delegated to this gene's {@link MutatorGene}.
     */
    void mutate();

    MutatorGene<P> getMutator();

    Gene<P> clone();

}
