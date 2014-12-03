package net.sf.geal.individual;

import java.util.concurrent.Future;

import net.sf.geal.genome.Genome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class IndividualAbstract implements Individual {

    private static final Logger log = LoggerFactory.getLogger(IndividualAbstract.class);

    protected Future<Double> fitness = null;

    private final int generation;

    private final Genome genome;

    public IndividualAbstract(final Genome genome) {
        this(0, genome);
    }

    public IndividualAbstract(final int generation, final Genome genome) {
        this.genome = genome;
        this.generation = generation;

    }

    protected IndividualAbstract(final int generation, final Genome genome,
            final Future<Double> fitness) {
        this(0, genome);
        this.fitness = fitness;
    }

    protected abstract void calculateFitness();

    @Override
    public abstract IndividualAbstract clone();

    @Override
    public int compareTo(final Individual o) {
        // bigger is better
        return Double.compare(o.getFitness(), getFitness());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof IndividualAbstract))
            return false;
        final IndividualAbstract other = (IndividualAbstract) obj;
        if (genome == null) {
            if (other.genome != null)
                return false;
        } else if (!genome.equals(other.genome))
            return false;
        return true;
    }

    @Override
    public double getFitness() {

        if (fitness == null) {
            calculateFitness();
        }
        try {
            return fitness.get();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getGeneration() {
        // generation final
        return generation;
    }

    @Override
    public Genome getGenome() {
        // genome final
        return genome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genome == null) ? 0 : genome.hashCode());
        return result;
    }

    @Override
    public String toString() {
        // generation final
        // fitnessCache volatile
        // genome final
        return generation + ":" + genome.toString();
    }

}
