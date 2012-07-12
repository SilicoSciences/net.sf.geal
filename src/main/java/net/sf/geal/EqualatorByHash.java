package net.sf.geal;

import net.sf.kerner.utils.collections.Equalator;

public class EqualatorByHash<T> implements Equalator<T> {

    public boolean areEqual(final T o1, final T o2) {
        return o1.hashCode() == o2.hashCode();
    }

}
