package net.sf.geal.terminator;

import net.sf.geal.GeneticAlgorithm;

public class TerminatorPopulationSize extends TerminatorAbstract {

	@Override
	public synchronized Boolean visit(final GeneticAlgorithm element) {
		if (!isEnabled()) {
			return false;
		}
		if (element.getMaxPopulationSize() < 1) {
			throw new IllegalStateException("invalid population size " + element.getMaxPopulationSize());
		}
		if (element.getCurrentPopulation().getSize() >= element.getMaxPopulationSize()) {
			return true;
		}
		return false;
	}

}
