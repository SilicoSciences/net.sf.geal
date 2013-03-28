package net.sf.geal.individual;

import java.util.Collection;
import java.util.List;

import net.sf.geal.ExceptionRuntimeGA;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomePair;
import net.sf.geal.mutator.genome.CrossOverGenome;
import net.sf.geal.mutator.genome.MutatorGenome;
import net.sf.kerner.utils.collections.impl.UtilCollection;

public class IndividualBreederImpl implements IndividualBreeder {

    // private static final Logger log =
    // LoggerFactory.getLogger(IndividualBreederImpl.class);

    private List<CrossOverGenome> crossOvers;

    private final FactoryIndividual factoryIndividual;

    private List<MutatorGenome> mutators;

    public IndividualBreederImpl(final List<MutatorGenome> mutators, final List<CrossOverGenome> crossOvers,
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

        for (final CrossOverGenome c : getCrossOvers()) {

            final GenomePair i = c.cross(new GenomePair(pair.getFirst().getGenome(), pair.getSecond().getGenome()));
            Genome f = i.getFirst();
            Genome s = i.getSecond();
            for (final MutatorGenome m : getMutators()) {
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
    public List<CrossOverGenome> getCrossOvers() {
        return crossOvers;
    }

    @Override
    public List<MutatorGenome> getMutators() {
        return mutators;
    }

    public void setCrossOvers(final List<CrossOverGenome> crossOvers) {
        this.crossOvers = crossOvers;
    }

    public void setMutators(final List<MutatorGenome> mutators) {
        this.mutators = mutators;
    }

}
