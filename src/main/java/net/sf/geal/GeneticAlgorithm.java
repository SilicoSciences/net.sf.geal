package net.sf.geal;

import java.util.List;

import net.sf.geal.individual.IndividualBreeder;
import net.sf.geal.population.Population;
import net.sf.geal.terminator.TerminatorEvolution;

public interface GeneticAlgorithm {

	void addListener(ListenerEvolution listener);

	void addTerminator(TerminatorEvolution terminator);

	void evolve();

	int getMaxPopulationSize();

	Population getCurrentPopulation();

	IndividualBreeder getIndividualBreeder();

	List<Population> getHistory();

	double getPercentageOfPairings();

}
