package net.sf.geal.genome.impl;

import java.util.Arrays;
import java.util.List;

import net.sf.geal.gene.Gene;
import net.sf.geal.genome.GenomeAbstract;
import net.sf.kerner.utils.collections.list.UtilList;

public class GenomeBoolean extends GenomeAbstract {

	public GenomeBoolean() {
		super();
	}

	public GenomeBoolean(final List<Gene> genes) {
		super(genes);
	}

	public GenomeBoolean(final Gene... genes) {
		super(Arrays.asList(genes));
	}

	@Override
	public GenomeBoolean clone() {
		final GenomeBoolean result = new GenomeBoolean(UtilList.clone(getGenes()));
		result.getProperties().putAll(getProperties());
		return result;
	}

}
