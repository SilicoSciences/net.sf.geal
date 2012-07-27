package net.sf.geal.population;

import java.util.Collection;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;
import net.sf.geal.individual.ValidatorIndividual;
import net.sf.kerner.utils.collections.Equalator;

public interface Population<R, P, G extends Gene<P>, I extends Individual<R, P, G>> extends Iterable<I> {

    boolean add(I individual);

    boolean addAll(Collection<? extends I> individuals);

    void addValidator(ValidatorIndividual<R, P, G, I> validator);

    Population<R, P, G, I> clone();

    I find(I individual, Equalator<I> equalator);

    I find(int hashCode);

    List<I> getIndividuals();

    int getSize();

    Population<R, P, G, I> getSubPopulation(int size);

    void trim(int newSize);

}
