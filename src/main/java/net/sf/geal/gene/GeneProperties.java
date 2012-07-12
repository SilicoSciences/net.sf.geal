package net.sf.geal.gene;

public class GeneProperties extends GeneImpl<Object> {

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

    public synchronized void setKey(final String key) {
        this.key = key;
    }

    public synchronized String getKey() {
        return key;
    }

    public synchronized void setValue(final Object value) {
        impress(value);
    }

    public synchronized Object getValue() {
        return express();
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }

}
