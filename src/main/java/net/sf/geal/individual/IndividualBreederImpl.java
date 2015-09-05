package net.sf.geal.individual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.sf.geal.ExceptionRuntimeGA;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomePair;
import net.sf.geal.mutator.genome.MutatorCrossOver;
import net.sf.geal.mutator.genome.MutatorCrossOverDefault;
import net.sf.geal.mutator.genome.MutatorPoint;
import net.sf.geal.mutator.genome.MutatorPointDefault;
import net.sf.kerner.utils.collections.UtilCollection;

public class IndividualBreederImpl implements IndividualBreeder {

    // private static final Logger log =
    // LoggerFactory.getLogger(IndividualBreederImpl.class);

    private List<? extends MutatorCrossOver> crossOvers;

    private final FactoryIndividual factoryIndividual;

    private List<? extends MutatorPoint> mutators;

    public IndividualBreederImpl(FactoryIndividual factoryIndividual) {
        mutators = Arrays.asList(new MutatorPointDefault());
        crossOvers = Arrays.asList(new MutatorCrossOverDefault());
        this.factoryIndividual = factoryIndividual;
    }

    public IndividualBreederImpl(final List<? extends MutatorPoint> mutators,
            final List<? extends MutatorCrossOver> crossOvers,
            final FactoryIndividual factoryIndividual) {

        this.mutators = mutators;
        this.crossOvers = crossOvers;

        this.factoryIndividual = factoryIndividual;
    }

    @Override
    public Collection<Individual> breed(final IndividualPair pair) {

        if (getCrossOvers() == null) {
            throw new ExceptionRuntimeGA("set cross overs first");
        }

        if (getMutators() == null) {
            throw new ExceptionRuntimeGA("set mutators first");
        }

        final Collection<Individual> result = UtilCollection.newCollection();

        for (final MutatorCrossOver c : getCrossOvers()) {

            final GenomePair i = c.cross(new GenomePair(pair.getFirst().getGenome(), pair
                    .getSecond().getGenome()));
            Genome f = i.getFirst();
            Genome s = i.getSecond();
            for (final MutatorPoint m : getMutators()) {
                f = m.mutate(f);
                s = m.mutate(s);

            }
            result.add(factoryIndividual.create(pair.getFirst().getGeneration() + 1, f));
            result.add(factoryIndividual.create(pair.getSecond().getGeneration() + 1, s));
        }

        // if (log.isDebugEnabled()) {
        // log.debug("breeded new individuals: " +
        // UtilCollection.toString(result));
        // }

        return result;
    }

    @Override
    public List<MutatorCrossOver> getCrossOvers() {
        return new ArrayList<MutatorCrossOver>(crossOvers);
    }

    @Override
    public List<MutatorPoint> getMutators() {
        return new ArrayList<MutatorPoint>(mutators);
    }

    public void setCrossOvers(final List<MutatorCrossOver> crossOvers) {
        this.crossOvers = crossOvers;
    }

    public void setMutators(final List<MutatorPoint> mutators) {
        this.mutators = mutators;
    }

}
