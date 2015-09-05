package net.sf.geal.genome;

import java.util.List;
import java.util.Properties;

import net.sf.geal.gene.Gene;

public interface Genome extends Iterable<Gene> {

	Genome clone();

	List<Gene> getGenes();

	Properties getProperties();

	void setProperties(Properties properties);
}
