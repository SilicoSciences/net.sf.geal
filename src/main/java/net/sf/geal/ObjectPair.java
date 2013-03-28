package net.sf.geal;

import net.sf.kerner.utils.impl.util.Util;

/**
 * 
 @deprecated use {@link net.sf.kerner.uitls.ObjectPair} instead
 */
@Deprecated
public class ObjectPair<F, S> {

    private F first;

    private S second;

    public ObjectPair() {
    }

    public ObjectPair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((first == null) ? 0 : first.hashCode());
        result = prime * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }

    public void setFirst(final F first) {
        this.first = first;
    }

    public void setSecond(final S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return getFirst() + "," + getSecond();
    }
}
