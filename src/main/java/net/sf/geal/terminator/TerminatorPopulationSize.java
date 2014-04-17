package net.sf.geal.terminator;

import net.sf.geal.GeneticAlgorithm;

public class TerminatorPopulationSize implements TerminatorEvolution {

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

    @Override
    public Boolean visit(final GeneticAlgorithm element) {
        if (getMaxPopulationSize() < 1) {
            throw new IllegalStateException("invalid population size " + maxPopulationSize);
        }
        if (element.getCurrentPopulation().getSize() >= getMaxPopulationSize()) {
            return true;
        }
        return false;
    }

}
