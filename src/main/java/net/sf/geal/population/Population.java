package net.sf.geal.population;

import java.util.Collection;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;
import net.sf.geal.individual.ValidatorIndividual;
import net.sf.kerner.utils.collections.Equalator;

public interface Population<R, P, G extends Gene<P>> extends Iterable<Individual<R, P, G>> {

    boolean add(Individual<R, P, G> individual);

    boolean addAll(Collection<? extends Individual<R, P, G>> individuals);

    void addValidator(ValidatorIndividual<R, P, G> validator);

    List<Individual<R, P, G>> getIndividuals();

    Population<R, P, G> getSubPopulation(int size);

    void trim(int newSize);

    int getSize();

    Population<R, P, G> clone();

    Individual<R, P, G> find(int hashCode);

    Individual<R, P, G> find(Individual<R, P, G> individual, Equalator<Individual<R, P, G>> equalator);

}
