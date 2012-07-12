package net.sf.ga.individual;

import net.sf.ga.FitnessCalculator;
import net.sf.ga.population.Population;
import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.kerner.utils.impl.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualImpl<R, P, G extends Gene<P>> implements Individual<R, P, G> {

    private static final Logger log = LoggerFactory.getLogger(IndividualImpl.class);

    private Genome<R, P, G> genome;

    private final int generation;

    private final FitnessCalculator<R, P, G> fitnessCalculator;

    private volatile transient double fitness = -2;

    private volatile Population<R, P, G> population;

    public IndividualImpl(final int generation, final Genome<R, P, G> genome,
            final FitnessCalculator<R, P, G> fitnessCalculator) {
        this.genome = genome;
        this.generation = generation;
        this.fitnessCalculator = fitnessCalculator;
    }

    public IndividualImpl(final Genome<R, P, G> genome, final FitnessCalculator<R, P, G> fitnessCalculator) {
        this(0, genome, fitnessCalculator);
    }

    public Genome<R, P, G> getGenome() {
        return genome;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGenome(final Genome<R, P, G> genome) {
        this.genome = genome;
    }

    public Population<R, P, G> getPopulation() {
        return population;
    }

    public void setPopulation(final Population<R, P, G> population) {
        this.population = population;
    }

    public double getFitness() {
        if (fitness == -2) {
            fitness = fitnessCalculator.getFitness(this);
            if (log.isInfoEnabled()) {
                log.info("got a fitness: " + fitness + ":" + getGenome());
            }
        }
        if (log.isInfoEnabled()) {
            log.info("return cached fitness: " + fitness + ":" + getGenome());
        }
        return fitness;
    }

    public int compareTo(final Individual<R, P, G> o) {
        return fitnessCalculator.compare(this, o);
    }

    @Override
    public String toString() {
        return getGeneration() + ":" + getGenome().toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGenome() == null) ? 0 : getGenome().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public IndividualImpl<R, P, G> clone() {

        return new IndividualImpl<R, P, G>(getGeneration(), getGenome().clone(), fitnessCalculator);

    }

}
