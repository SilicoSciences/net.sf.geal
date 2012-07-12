package net.sf.geal;

import net.sf.kerner.utils.impl.util.Util;

public class ObjectPair<F, S> {

    private F first;

    private S second;

    public ObjectPair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }

    public ObjectPair() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    @Override
    public String toString() {
        return getFirst() + "," + getSecond();
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(final F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(final S second) {
        this.second = second;
    }
}
