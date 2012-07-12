package net.sf.geal.individual;

import java.util.Collection;
import java.util.List;

import net.sf.geal.ExceptionRuntimeGA;
import net.sf.geal.FitnessCalculator;
import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomePair;
import net.sf.geal.mutator.genome.CrossOverGenome;
import net.sf.geal.mutator.genome.MutatorGenome;
import net.sf.kerner.utils.collections.impl.UtilCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactoryIndividualDefault<R, P, G extends Gene<P>> implements FactoryIndividual<R, P, G> {

    private static final Logger log = LoggerFactory.getLogger(FactoryIndividualDefault.class);

    private List<MutatorGenome<R, P, G>> mutators;

    private List<CrossOverGenome<R, P, G>> crossOvers;

    private FitnessCalculator<R, P, G> fitnessCalculator;

    public Collection<Individual<R, P, G>> breed(final IndividualPair<R, P, G> pair) {

        if (getCrossOvers() == null) {
            throw new ExceptionRuntimeGA("set cross overs first");
        }

        if (getMutators() == null) {
            throw new ExceptionRuntimeGA("set mutators first");
        }

        if (getFitnessCalculator() == null) {
            throw new ExceptionRuntimeGA("set fitness calculator first");
        }

        final Collection<Individual<R, P, G>> result = UtilCollection.newCollection();

        for (final CrossOverGenome<R, P, G> c : getCrossOvers()) {

            final GenomePair<R, P, G> i = c.cross(new GenomePair<R, P, G>(pair.getFirst().getGenome(), pair.getSecond()
                    .getGenome()));
            Genome<R, P, G> f = i.getFirst();
            Genome<R, P, G> s = i.getSecond();
            for (final MutatorGenome<R, P, G> m : getMutators()) {
                f = m.mutate(f);
                s = m.mutate(s);

            }
            result.add(new IndividualImpl<R, P, G>(pair.getFirst().getGeneration() + 1, f, fitnessCalculator));
            result.add(new IndividualImpl<R, P, G>(pair.getSecond().getGeneration() + 1, s, fitnessCalculator));
        }

        // if (log.isDebugEnabled()) {
        // log.debug("breeded new individuals: " + UtilCollection.toString(result));
        // }

        return result;
    }

    public List<MutatorGenome<R, P, G>> getMutators() {
        return mutators;
    }

    public void setMutators(final List<MutatorGenome<R, P, G>> mutators) {
        this.mutators = mutators;
    }

    public List<CrossOverGenome<R, P, G>> getCrossOvers() {
        return crossOvers;
    }

    public void setCrossOvers(final List<CrossOverGenome<R, P, G>> crossOvers) {
        this.crossOvers = crossOvers;
    }

    public FitnessCalculator<R, P, G> getFitnessCalculator() {
        return fitnessCalculator;
    }

    public void setFitnessCalculator(final FitnessCalculator<R, P, G> fitnessCalculator) {
        this.fitnessCalculator = fitnessCalculator;
    }

}
