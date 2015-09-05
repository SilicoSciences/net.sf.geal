package net.sf.geal.genome.impl;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.GenomeAbstract;

public class GenomeProperties extends GenomeAbstract {

	public GenomeProperties() {

	}

	public GenomeProperties(final GenomeProperties template) {
		for (final Gene g : template.getGenes()) {
			getGenes().add(g.clone());
		}
	}

	@Override
	public GenomeProperties clone() {
		return new GenomeProperties(this);
	}

}
