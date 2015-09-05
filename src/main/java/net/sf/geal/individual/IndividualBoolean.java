package net.sf.geal.individual;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.Genome;

public class IndividualBoolean extends IndividualAbstract {

	public static class FitnessCalculator implements Callable<Double> {

		private final IndividualBoolean i;

		public FitnessCalculator(final IndividualBoolean i) {
			this.i = i;
		}

		@Override
		public Double call() throws Exception {
			double result = 0;
			for (final Gene g : i.genome) {
				if ((Boolean) g.express()) {
					result++;
				}
			}
			return result;
		}
	}

	public IndividualBoolean(final Genome genome) {
		super(genome);
	}

	public IndividualBoolean(final int generation, final Genome genome) {
		super(generation, genome);
	}

	protected IndividualBoolean(final int generation, final Genome genome, final Future<Double> fitness) {
		super(generation, genome, fitness);
	}

	@Override
	protected synchronized void calculateFitness() {
		final ExecutorService e = Executors.newSingleThreadExecutor();
		fitness = e.submit(new FitnessCalculator(this));
		e.shutdown();
	}

	@Override
	public IndividualAbstract clone() {
		return new IndividualBoolean(generation, genome, fitness);
	}

}
