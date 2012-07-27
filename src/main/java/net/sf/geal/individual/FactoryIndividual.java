package net.sf.geal.individual;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.kerner.utils.Factory;

public interface FactoryIndividual<R, P, G extends Gene<P>, I extends Individual<R, P, G>> extends Factory<I> {

    public I create(final int generation, final Genome<R, P, G> genome);

}
