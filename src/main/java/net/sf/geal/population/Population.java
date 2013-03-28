package net.sf.geal.population;

import java.util.Collection;
import java.util.List;

import net.sf.geal.individual.Individual;
import net.sf.geal.individual.ValidatorIndividual;
import net.sf.kerner.utils.collections.Equalator;

public interface Population extends Iterable<Individual> {

    boolean add(Individual individual);

    boolean addAll(Collection<? extends Individual> individuals);

    void addValidator(ValidatorIndividual validator);

    Population clone();

    Individual find(Individual individual, Equalator<Individual> equalator);

    Individual find(int hashCode);

    List<Individual> getIndividuals();

    int getSize();

    Population getSubPopulation(int size);

    void trim(int newSize);

}
