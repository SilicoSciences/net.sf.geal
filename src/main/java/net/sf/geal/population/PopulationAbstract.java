package net.sf.geal.population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.geal.gene.Gene;
import net.sf.geal.individual.Individual;
import net.sf.geal.individual.ValidatorIndividual;
import net.sf.kerner.utils.collections.Equalator;
import net.sf.kerner.utils.collections.filter.FilterApplier;
import net.sf.kerner.utils.collections.impl.filter.FilterApplierProto;
import net.sf.kerner.utils.impl.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PopulationAbstract<R, P, G extends Gene<P>, I extends Individual<R, P, G>> implements
        Population<R, P, G, I> {

    private static final Logger log = LoggerFactory.getLogger(PopulationAbstract.class);

    private final FilterApplier<I> filterApplier = new FilterApplierProto<I>();

    private Set<I> individuals;

    public PopulationAbstract() {
        individuals = new LinkedHashSet<I>();
    }

    protected PopulationAbstract(final Collection<? extends I> individuals) {
        this.individuals = new LinkedHashSet<I>(individuals);
    }

    /**
     * @return {@code false} if given {@code Individual} was rejected by any filter or was already contained by this
     *         {@code Population}; {@code true} otherwise
     */
    @Override
    public synchronized boolean add(final I individual) {
        final boolean accept = filterApplier.filter(individual);
        if (accept) {
            final boolean ac = individuals.add(individual);
            if (ac) {
                if (log.isDebugEnabled()) {
                    log.debug("added " + individual);
                }
                individual.triggerCalculation();
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("rejected, alreday there " + individual);
                }
            }
            return ac;
        }
        if (log.isDebugEnabled()) {
            log.debug("rejected by filters " + individual);
        }
        return false;
    }

    /**
     * @return {@code false} if one of given individuals was rejected by any filter or was already contained by this
     *         {@code Population}; {@code true} otherwise
     */
    @Override
    public synchronized boolean addAll(final Collection<? extends I> individuala) {
        boolean result = true;
        for (final I i : individuala) {
            final boolean accept = add(i);
            if (accept == false) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public synchronized void addValidator(final ValidatorIndividual<R, P, G, I> validator) {
        filterApplier.addFilter(validator);
    }

    @Override
    public abstract PopulationAbstract<R, P, G, I> clone();

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized I find(final I individual, final Equalator<I> equalator) {
        for (final I i : getIndividuals()) {
            if (equalator.areEqual(individual, i)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public synchronized I find(final int hashCode) {
        for (final I i : getIndividuals()) {
            if (i.hashCode() == hashCode) {
                return i;
            }
        }
        return null;
    }

    @Override
    public synchronized List<I> getIndividuals() {
        return new ArrayList<I>(individuals);
    }

    @Override
    public synchronized int getSize() {
        return getIndividuals().size();
    }

    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIndividuals() == null) ? 0 : getIndividuals().hashCode());
        return result;
    }

    @Override
    public synchronized Iterator<I> iterator() {
        return getIndividuals().iterator();
    }

    @Override
    public synchronized String toString() {
        return getIndividuals().toString();
    }

    @Override
    public synchronized void trim(final int newSize) {
        if (getSize() < newSize) {
            return;
        }
        individuals = new LinkedHashSet<I>(getIndividuals().subList(0, newSize));
    }

}
