package net.sf.geal;

import net.sf.geal.gene.Gene;

public class TerminatorPopulationSize<R, P, G extends Gene<P>> implements TerminatorEvolution<R, P, G> {

    private volatile int maxPopulationSize = -1;

    public TerminatorPopulationSize() {

    }

    public TerminatorPopulationSize(final int maxPopulationSize) {
        super();
        this.maxPopulationSize = maxPopulationSize;
    }

    public int getMaxPopulationSize() {
        return maxPopulationSize;
    }

    public void setMaxPopulationSize(final int maxPopulationSize) {
        this.maxPopulationSize = maxPopulationSize;
    }

    public Boolean visit(final GeneticAlgorithm<R, P, G> element) {
        if (getMaxPopulationSize() < 1) {
            throw new IllegalStateException("invalid population size " + maxPopulationSize);
        }
        if (element.getCurrentPopulation().getSize() >= getMaxPopulationSize()) {
            return true;
        }
        return false;
    }

}
