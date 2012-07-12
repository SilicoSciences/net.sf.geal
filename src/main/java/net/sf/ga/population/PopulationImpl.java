package net.sf.ga.population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.ga.individual.Individual;
import net.sf.ga.individual.ValidatorIndividual;
import net.sf.geal.gene.Gene;
import net.sf.kerner.utils.collections.Equalator;
import net.sf.kerner.utils.collections.filter.FilterApplier;
import net.sf.kerner.utils.collections.impl.filter.FilterApplierProto;
import net.sf.kerner.utils.collections.list.impl.ListUtil;
import net.sf.kerner.utils.impl.util.Util;

public class PopulationImpl<R, P, G extends Gene<P>> implements Population<R, P, G> {

    private volatile Set<Individual<R, P, G>> individuals;

    private final FilterApplier<Individual<R, P, G>> filterApplier = new FilterApplierProto<Individual<R, P, G>>();

    public PopulationImpl() {
        individuals = new LinkedHashSet<Individual<R, P, G>>();
    }

    private PopulationImpl(final Collection<? extends Individual<R, P, G>> individuals) {
        this.individuals = new LinkedHashSet<Individual<R, P, G>>(individuals);
    }

    public synchronized void addValidator(final ValidatorIndividual<R, P, G> validator) {
        filterApplier.addFilter(validator);
    }

    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIndividuals() == null) ? 0 : getIndividuals().hashCode());
        return result;
    }

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized String toString() {
        return getIndividuals().toString();
    }

    /**
     * @return {@code false} if given {@code Individual} was rejected by any filter or was already contained by this
     *         {@code Population}; {@code true} otherwise
     */
    public synchronized boolean add(final Individual<R, P, G> individual) {
        final boolean accept = filterApplier.filter(individual);
        if (accept) {

            final boolean ac = individuals.add(individual);
            if (ac) {
                individual.setPopulation(this);
            }
            return ac;
        }
        return false;
    }

    /**
     * @return {@code false} if one of given individuals was rejected by any filter or was already contained by this
     *         {@code Population}; {@code true} otherwise
     */
    public synchronized boolean addAll(final Collection<? extends Individual<R, P, G>> individuala) {
        boolean result = true;
        for (final Individual<R, P, G> i : individuala) {
            final boolean accept = add(i);
            if (accept == false) {
                result = false;
            }
        }
        return result;
    }

    public synchronized Iterator<Individual<R, P, G>> iterator() {
        return getIndividuals().iterator();
    }

    public synchronized int getSize() {
        return getIndividuals().size();
    }

    public synchronized List<Individual<R, P, G>> getIndividuals() {
        return new ArrayList<Individual<R, P, G>>(individuals);
    }

    @Override
    public synchronized Population<R, P, G> clone() {
        final Population<R, P, G> result = new PopulationImpl<R, P, G>();
        for (final Individual<R, P, G> i : getIndividuals()) {
            result.add(i.clone());
        }
        return result;
    }

    public synchronized void trim(final int newSize) {
        if (getSize() < newSize) {
            return;
        }
        individuals = new LinkedHashSet<Individual<R, P, G>>(getIndividuals().subList(0, newSize));
    }

    public synchronized Population<R, P, G> getSubPopulation(final int size) {
        if (size >= getSize()) {
            return clone();
        }
        if (size < 1) {
            throw new IllegalArgumentException("invalid size " + size);
        }
        List<Individual<R, P, G>> result = ListUtil.newList(getIndividuals());
        Collections.sort(result);
        result = result.subList(0, size);
        return new PopulationImpl<R, P, G>(result).clone();
    }

    public Individual<R, P, G> find(final int hashCode) {
        for (final Individual<R, P, G> i : getIndividuals()) {
            if (i.hashCode() == hashCode) {
                return i;
            }
        }
        return null;
    }

    public Individual<R, P, G> find(final Individual<R, P, G> individual, final Equalator<Individual<R, P, G>> equalator) {
        for (final Individual<R, P, G> i : getIndividuals()) {
            if (equalator.areEqual(individual, i)) {
                return i;
            }
        }
        return null;
    }

}
