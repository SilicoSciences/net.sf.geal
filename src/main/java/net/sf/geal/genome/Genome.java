package net.sf.geal.genome;

import java.util.List;

import net.sf.geal.gene.Gene;

public interface Genome extends Iterable<Gene> {

    Genome clone();

    Object express();

    List<Gene> getGenes();

    void impress(Object result);
}
