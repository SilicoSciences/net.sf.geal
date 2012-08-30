package net.sf.geal.individual;

import java.util.concurrent.Future;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.kerner.utils.impl.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class IndividualAbstract<R, P, G extends Gene<P>> implements Individual<R, P, G> {

    private static final Logger log = LoggerFactory.getLogger(IndividualAbstract.class);

    protected Future<Double> fitness = null;

    private final int generation;

    private final Genome<R, P, G> genome;

    public IndividualAbstract(final Genome<R, P, G> genome) {
        this(0, genome);
    }

    public IndividualAbstract(final int generation, final Genome<R, P, G> genome) {
        this.genome = genome;
        this.generation = generation;

    }

    protected IndividualAbstract(final int generation, final Genome<R, P, G> genome, final Future<Double> fitness) {
        this(0, genome);
        this.fitness = fitness;
    }

    protected abstract void calculateFitness();

    @Override
    public abstract IndividualAbstract<R, P, G> clone();

    @Override
    public int compareTo(final Individual<R, P, G> o) {
        // bigger is better
        return Double.compare(o.getFitness(), this.getFitness());
    }

    @Override
    public boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized double getFitness() {

        if (fitness == null) {
            calculateFitness();
        }
        try {
            return fitness.get();
        } catch (final Exception e) {
            if (log.isErrorEnabled()) {
                log.error("failed to calculate fitness", e.getLocalizedMessage());
            }
            return -1;
        }
    }

    @Override
    public int getGeneration() {
        // generation final
        return generation;
    }

    @Override
    public Genome<R, P, G> getGenome() {
        // genome final
        return genome;
    }

    @Override
    public int hashCode() {
        synchronized (genome) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((genome == null) ? 0 : genome.hashCode());
            return result;
        }
    }

    @Override
    public String toString() {
        // generation final
        // fitnessCache volatile
        // genome final
        return generation + ":" + genome.toString();
    }

}
