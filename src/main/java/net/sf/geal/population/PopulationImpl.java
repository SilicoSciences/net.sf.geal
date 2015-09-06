package net.sf.geal.population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.geal.individual.Individual;
import net.sf.geal.individual.ValidatorIndividual;
import net.sf.kerner.utils.collections.UtilCollection;
import net.sf.kerner.utils.collections.filter.FilterApplier;
import net.sf.kerner.utils.collections.filter.FilterApplierProto;
import net.sf.kerner.utils.collections.list.UtilList;
import net.sf.kerner.utils.equal.Equalator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopulationImpl implements Population {

    private static final Logger log = LoggerFactory.getLogger(PopulationImpl.class);

    private final FilterApplier<Individual> filterApplier = new FilterApplierProto<Individual>();

    private Set<Individual> individuals;

    public PopulationImpl() {
        individuals = new LinkedHashSet<Individual>();
    }

    protected PopulationImpl(final Collection<? extends Individual> individuals) {
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
                    // log.debug("added " + individual);
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
    public synchronized PopulationImpl clone() {
        return new PopulationImpl(UtilList.clone(getIndividuals()));
    }

    @Override
    public synchronized Individual find(final Individual individual,
            final Equalator<Individual> equalator) {
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
    public synchronized double getAverageFitness() {
        double result = 0;
        for (Individual i : getIndividuals()) {
            result += i.getFitness();
        }
        return result / getSize();
    }

    @Override
    public synchronized List<Individual> getIndividuals() {
        if (UtilCollection.nullOrEmpty(individuals)) {
            return Collections.emptyList();
        }
        final List<Individual> result = new ArrayList<Individual>(individuals);
        Collections.sort(result);
        return result;
    }

    @Override
    public synchronized double getMaxFitness() {
        double result = Double.MIN_VALUE;
        for (Individual i : getIndividuals()) {
            if (i.getFitness() > result) {
                result = i.getFitness();
            }
        }
        return result;
    }

    @Override
    public synchronized int getSize() {
        return getIndividuals().size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public synchronized Population getSubPopulation(final int size) {
        @SuppressWarnings("rawtypes")
        final List list = new ArrayList(getIndividuals());
        if (size <= list.size()) {
            return new PopulationImpl(UtilList.clone(list.subList(0, size)));
        } else {
            return clone();
        }
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
    public synchronized Collection<Individual> trim(final int newSize) {
        if (getSize() < newSize) {
            return Collections.emptyList();
        }
        List<Individual> indis = getIndividuals();
        individuals = new LinkedHashSet<Individual>(indis.subList(0, newSize));
        return new ArrayList<Individual>(indis.subList(newSize, indis.size()));
    }

}
