package net.sf.geal.genome;

import java.util.List;

import net.sf.geal.gene.Gene;

public interface Genome<R, P, G extends Gene<P>> extends Iterable<G> {

    R express();

    void impress(R result);

    Genome<R, P, G> clone();

    List<G> getGenes();
}
