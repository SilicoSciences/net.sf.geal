package net.sf.geal.mutator;

public class MutatorAbstract {

    private volatile double frequency;

    public MutatorAbstract(final double frequency) {
        super();
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(final double frequency) {
        this.frequency = frequency;
    }

}
