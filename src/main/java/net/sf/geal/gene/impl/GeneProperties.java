package net.sf.geal.gene.impl;

import net.sf.geal.gene.GeneImpl;
import net.sf.kerner.utils.impl.util.Util;

public class GeneProperties extends GeneImpl {

    private volatile String key;

    public GeneProperties() {
        super(null);
    }

    public GeneProperties(final GeneProperties template) {
        this(template.getKey(), template.getValue());
        setMutator(template.getMutator());
    }

    public GeneProperties(final String key, final Object value) {
        super(value);
        this.key = key;
    }

    @Override
    public synchronized GeneProperties clone() {
        return new GeneProperties(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    public synchronized String getKey() {
        return key;
    }

    public synchronized Object getValue() {
        return express();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    public synchronized void setKey(final String key) {
        this.key = key;
    }

    public synchronized void setValue(final Object value) {
        impress(value);
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }

}
