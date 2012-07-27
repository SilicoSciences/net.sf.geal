package net.sf.geal.individual;

import java.util.Collection;
import java.util.List;

import net.sf.geal.ExceptionRuntimeGA;
import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomePair;
import net.sf.geal.mutator.genome.CrossOverGenome;
import net.sf.geal.mutator.genome.MutatorGenome;
import net.sf.kerner.utils.collections.impl.UtilCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualBreederImpl<R, P, G extends Gene<P>, I extends Individual<R, P, G>> implements
        IndividualBreeder<R, P, G, I> {

    private static final Logger log = LoggerFactory.getLogger(IndividualBreederImpl.class);

    private List<CrossOverGenome<R, P, G>> crossOvers;

    private final FactoryIndividual<R, P, G, I> factoryIndividual;

    private List<MutatorGenome<R, P, G>> mutators;

    public IndividualBreederImpl(final List<MutatorGenome<R, P, G>> mutators,
            final List<CrossOverGenome<R, P, G>> crossOvers, final FactoryIndividual<R, P, G, I> factoryIndividual) {

        this.mutators = mutators;
        this.crossOvers = crossOvers;

        this.factoryIndividual = factoryIndividual;
    }

    public Collection<I> breed(final IndividualPair<R, P, G, I> pair) {

        if (getCrossOvers() == null) {
            throw new ExceptionRuntimeGA("set cross overs first");
        }

        if (getMutators() == null) {
            throw new ExceptionRuntimeGA("set mutators first");
        }

        final Collection<I> result = UtilCollection.newCollection();

        for (final CrossOverGenome<R, P, G> c : getCrossOvers()) {

            final GenomePair<R, P, G> i = c.cross(new GenomePair<R, P, G>(pair.getFirst().getGenome(), pair.getSecond()
                    .getGenome()));
            Genome<R, P, G> f = i.getFirst();
            Genome<R, P, G> s = i.getSecond();
            for (final MutatorGenome<R, P, G> m : getMutators()) {
                f = m.mutate(f);
                s = m.mutate(s);

            }
            result.add(factoryIndividual.create(pair.getFirst().getGeneration() + 1, f));
            result.add(factoryIndividual.create(pair.getSecond().getGeneration() + 1, s));
        }

        // if (log.isDebugEnabled()) {
        // log.debug("breeded new individuals: " + UtilCollection.toString(result));
        // }

        return result;
    }

    public List<CrossOverGenome<R, P, G>> getCrossOvers() {
        return crossOvers;
    }

    public List<MutatorGenome<R, P, G>> getMutators() {
        return mutators;
    }

    public void setCrossOvers(final List<CrossOverGenome<R, P, G>> crossOvers) {
        this.crossOvers = crossOvers;
    }

    public void setMutators(final List<MutatorGenome<R, P, G>> mutators) {
        this.mutators = mutators;
    }

}
