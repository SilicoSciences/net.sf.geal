package net.sf.geal.mutator.genome;

import static org.junit.Assert.assertTrue;

import java.util.List;

import net.sf.geal.gene.GeneBoolean;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomeBoolean;
import net.sf.geal.mutator.gene.MutatorGeneBooleanTrue;
import net.sf.geal.mutator.genome.MutatorGenomeDefault;
import net.sf.kerner.utils.collections.list.impl.ListUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestMutatorGenomeDefault {

    private MutatorGenomeDefault mutator;

    private Genome genome;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        mutator = new MutatorGenomeDefault();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testMutate01() {
        mutator.setFrequency(1);
        final List genes = ListUtil.newList();
        GeneBoolean g = new GeneBoolean(false);
        g.setMutator(new MutatorGeneBooleanTrue());
        genes.add(g);
        g = new GeneBoolean(false);
        g.setMutator(new MutatorGeneBooleanTrue());
        genes.add(g);
        g = new GeneBoolean(false);
        g.setMutator(new MutatorGeneBooleanTrue());
        genes.add(g);
        g = new GeneBoolean(false);
        g.setMutator(new MutatorGeneBooleanTrue());
        genes.add(g);
        genome = new GenomeBoolean(genes);
        genome = mutator.mutate(genome);
        for (final Object o : genome.getGenes()) {
            assertTrue(((GeneBoolean) o).express());
        }
    }
}
