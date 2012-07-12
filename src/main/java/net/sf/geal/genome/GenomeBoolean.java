package net.sf.geal.genome;

import java.util.Iterator;
import java.util.List;

import net.sf.geal.ExceptionRuntimeGA;
import net.sf.geal.gene.GeneBoolean;
import net.sf.geal.mutator.gene.MutatorGeneBoolean;
import net.sf.kerner.utils.collections.list.impl.ListUtil;

public class GenomeBoolean extends GenomeAbstract<List<Boolean>, Boolean, GeneBoolean> {

    private final static MutatorGeneBoolean mutator = new MutatorGeneBoolean();

    public GenomeBoolean(final Genome<List<Boolean>, Boolean, GeneBoolean> template) {
        this(template.getGenes());
    }

    public GenomeBoolean(final Boolean... genes) {
        for (final Boolean b : genes) {
            final GeneBoolean g = new GeneBoolean(b);
            g.setMutator(mutator);
            getGenes().add(g);
        }
    }

    public GenomeBoolean(final List<GeneBoolean> genes) {
        for (final GeneBoolean g : genes) {
            getGenes().add(new GeneBoolean(g));
        }
    }

    public List<Boolean> express() {
        final List<Boolean> result = ListUtil.newList();
        for (final GeneBoolean g : getGenes()) {
            result.add(g.express());
        }
        return result;
    }

    public void impress(final List<Boolean> result) {
        if (result.size() != getGenes().size()) {
            throw new ExceptionRuntimeGA("too many/ few elements to impress");
        }
        final Iterator<Boolean> it = result.iterator();
        for (final GeneBoolean g : getGenes()) {
            g.impress(it.next());
        }
    }

    @Override
    public GenomeAbstract<List<Boolean>, Boolean, GeneBoolean> clone() {
        return new GenomeBoolean(this);
    }
}
