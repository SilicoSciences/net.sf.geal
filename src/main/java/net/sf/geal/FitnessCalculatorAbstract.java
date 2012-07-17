package net.sf.geal;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class FitnessCalculatorAbstract<R, P, G extends Gene<P>> implements FitnessCalculator<R, P, G> {

    private static final Logger log = LoggerFactory.getLogger(FitnessCalculatorAbstract.class);

    private class Runner implements Callable<Double> {

        private final Individual<R, P, G> o1;

        public Runner(final Individual<R, P, G> o1) {
            this.o1 = o1;
        }

        public Double call() throws Exception {
            return getFitness(o1);
        }

    }

    public int compare(final Individual<R, P, G> o1, final Individual<R, P, G> o2) {
        final ExecutorService exe = Executors.newFixedThreadPool(2);
        try {
            final Future<Double> f1f = exe.submit(new Runner(o1));
            final Future<Double> f2f = exe.submit(new Runner(o2));
            final double f1 = f1f.get();
            final double f2 = f2f.get();
            exe.shutdown();
            return Double.compare(f2, f1);
        } catch (final Exception e) {
            if (log.isErrorEnabled()) {
                log.error(e.getLocalizedMessage(), e);
            }
            return compareFallBack(o1, o2);
        }
    }

    public int compareFallBack(final Individual<R, P, G> o1, final Individual<R, P, G> o2) {
        final double f1 = getFitness(o1);
        final double f2 = getFitness(o2);
        // Calculation inverted, so max is higher
        return Double.compare(f2, f1);
    }
}
