package net.sf.geal.terminator;

import net.sf.geal.GeneticAlgorithm;

public class TerminatorGenerations implements TerminatorEvolution {

    private volatile int maxGenerations = -1;

    public TerminatorGenerations() {

    }

    public TerminatorGenerations(final int maxGenerations) {
        super();
        this.maxGenerations = maxGenerations;
    }

    public int getMaxGenerations() {
        return maxGenerations;
    }

    public void setMaxGenerations(final int maxGenerations) {
        this.maxGenerations = maxGenerations;
    }

    @Override
    public Boolean visit(final GeneticAlgorithm element) {
        if (getMaxGenerations() < 1) {
            throw new IllegalStateException("invalid generations" + maxGenerations);
        }
        if (element.getHistory().size() > maxGenerations) {
            return true;
        }
        return false;
    }

}
