package net.sf.geal.population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.geal.individual.Individual;
import net.sf.geal.individual.ValidatorIndividual;
import net.sf.kerner.utils.collections.Equalator;
import net.sf.kerner.utils.collections.filter.FilterApplier;
import net.sf.kerner.utils.collections.impl.filter.FilterApplierProto;
import net.sf.kerner.utils.impl.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PopulationAbstract implements Population {

    private static final Logger log = LoggerFactory.getLogger(PopulationAbstract.class);

    private final FilterApplier<Individual> filterApplier = new FilterApplierProto<Individual>();

    private Set<Individual> individuals;

    public PopulationAbstract() {
        individuals = new LinkedHashSet<Individual>();
    }

    protected PopulationAbstract(final Collection<? extends Individual> individuals) {
        this.individuals = new LinkedHashSet<Individual>(individuals);
    }

    /**
     * @return {@code false} if given {@code Individual} was rejected by any
     *         filter or was already contained by this {@code Population};
     *         {@code true} otherwise
     */
    @Override
    public synchronized boolean add(final Individual individual) {
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
     * @return {@code false} if one of given individuals was rejected by any
     *         filter or was already contained by this {@code Population};
     *         {@code true} otherwise
     */
    @Override
    public synchronized boolean addAll(final Collection<? extends Individual> individuala) {
        boolean result = true;
        for (final Individual i : individuala) {
            final boolean accept = add(i);
            if (accept == false) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public synchronized void addValidator(final ValidatorIndividual validator) {
        filterApplier.addFilter(validator);
    }

    @Override
    public abstract PopulationAbstract clone();

    @Override
    public synchronized boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public synchronized Individual find(final Individual individual, final Equalator<Individual> equalator) {
        for (final Individual i : getIndividuals()) {
            if (equalator.areEqual(individual, i)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public synchronized Individual find(final int hashCode) {
        for (final Individual i : getIndividuals()) {
            if (i.hashCode() == hashCode) {
                return i;
            }
        }
        return null;
    }

    @Override
    public synchronized List<Individual> getIndividuals() {
        return new ArrayList<Individual>(individuals);
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
    public synchronized Iterator<Individual> iterator() {
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
        individuals = new LinkedHashSet<Individual>(getIndividuals().subList(0, newSize));
    }

}
