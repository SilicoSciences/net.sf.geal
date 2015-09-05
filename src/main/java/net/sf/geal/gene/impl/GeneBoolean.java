package net.sf.geal.gene.impl;

import net.sf.geal.gene.GeneImpl;
import net.sf.geal.mutator.gene.MutatorGene;
import net.sf.geal.mutator.gene.MutatorGeneBoolean;

public class GeneBoolean extends GeneImpl {

	public GeneBoolean(final Boolean value) {
		super(value);
		setMutator(new MutatorGeneBoolean());
	}

	public GeneBoolean(final GeneBoolean template) {
		super(template);
		setMutator(new MutatorGeneBoolean());
	}

	@Override
	public synchronized GeneBoolean clone() {
		return new GeneBoolean(this);
	}

	@Override
	public synchronized Boolean express() {
		return (Boolean) value;
	}

	@Override
	public synchronized MutatorGene getMutator() {
		return mutator;
	}

	@Override
	public synchronized void impress(final Object property) {
		if (property instanceof Boolean) {
			super.impress(property);
		} else {
			throw new IllegalArgumentException();
		}
	}

}
