package net.sf.geal.gene.impl;

import net.sf.geal.gene.Gene;
import net.sf.geal.gene.GeneImpl;

public class GeneBoolean extends GeneImpl<Boolean> {

    public GeneBoolean(final Boolean value) {
        super(value);
    }

    public GeneBoolean(final Gene<Boolean> template) {
        super(template);
    }

}
