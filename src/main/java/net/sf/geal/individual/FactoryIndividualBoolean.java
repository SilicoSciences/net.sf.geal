package net.sf.geal.individual;

import net.sf.geal.genome.Genome;
import net.sf.kerner.utils.exception.ExceptionNotImplemented;

public class FactoryIndividualBoolean implements FactoryIndividual {

    @Override
    public IndividualBoolean create() {
        throw new ExceptionNotImplemented("Not supported");
    }

    @Override
    public IndividualBoolean create(final int generation, final Genome genome) {
        final IndividualBoolean result = new IndividualBoolean(generation, genome);
        return result;
    }

}
