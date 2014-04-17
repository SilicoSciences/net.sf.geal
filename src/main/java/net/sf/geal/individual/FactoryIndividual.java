package net.sf.geal.individual;

import net.sf.geal.genome.Genome;
import net.sf.kerner.utils.Factory;

public interface FactoryIndividual extends Factory<Individual> {

    public Individual create(final int generation, final Genome genome);

}
