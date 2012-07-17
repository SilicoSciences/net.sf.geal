package net.sf.geal.individual;

import net.sf.geal.FitnessCalculator;
import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;
import net.sf.geal.population.Population;
import net.sf.kerner.utils.impl.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualImpl<R, P, G extends Gene<P>> implements Individual<R, P, G> {

    private static final Logger log = LoggerFactory.getLogger(IndividualImpl.class);

    private Genome<R, P, G> genome;

    private final int generation;

    private final FitnessCalculator<R, P, G> fitnessCalculator;

    private volatile double fitnessCache = 0;

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

    protected IndividualImpl(final int generation, final Genome<R, P, G> genome,
            final FitnessCalculator<R, P, G> fitnessCalculator, final double fitness) {
        this(0, genome, fitnessCalculator);
        this.fitnessCache = fitness;
    }

    private void init() {

        if (fitnessCache != 0 && log.isWarnEnabled()) {
            log.warn("overwriting fitness " + fitnessCache);
        }

        if (log.isInfoEnabled()) {
            log.info("calculating fitness for " + getGenome());
        }
        fitnessCache = fitnessCalculator.getFitness(this);
        if (log.isInfoEnabled()) {
            log.info("got fitness " + fitnessCache + " for " + getGenome());
        }

    }

    public synchronized Genome<R, P, G> getGenome() {
        return genome;
    }

    public synchronized int getGeneration() {
        return generation;
    }

    public synchronized void setGenome(final Genome<R, P, G> genome) {
        this.genome = genome;
    }

    public synchronized Population<R, P, G> getPopulation() {
        return population;
    }

    public synchronized void setPopulation(final Population<R, P, G> population) {
        this.population = population;
        if (fitnessCache == 0) {
            init();
        }
    }

    public synchronized double getFitness() {

        if (fitnessCache == 0) {
            init();
        } else {
            if (log.isDebugEnabled()) {
                log.debug("return cached fitness: " + fitnessCache + ":" + getGenome());
            }
        }
        return fitnessCache;
    }

    public int compareTo(final Individual<R, P, G> o) {
        // must not be synchronized
        return fitnessCalculator.compare(this, o);
    }

    @Override
    public synchronized String toString() {
        return getGeneration() + ":" + getFitness() + ":" + getGenome().toString();
    }

    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGenome() == null) ? 0 : getGenome().hashCode());
        return result;
    }

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized IndividualImpl<R, P, G> clone() {

        return new IndividualImpl<R, P, G>(getGeneration(), getGenome().clone(), fitnessCalculator, getFitness());

    }

}
