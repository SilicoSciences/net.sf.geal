package net.sf.geal.gene;

public class GeneBoolean extends GeneImpl<Boolean> {

    public GeneBoolean(final Boolean value) {
        super(value);
    }

    public GeneBoolean(final Gene<Boolean> template) {
        super(template);
    }

}
