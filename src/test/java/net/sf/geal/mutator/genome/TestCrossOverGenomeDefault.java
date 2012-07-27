package net.sf.geal.mutator.genome;

import static org.junit.Assert.assertEquals;
import net.sf.geal.gene.impl.GeneBoolean;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomePair;
import net.sf.geal.genome.impl.GenomeBoolean;
import net.sf.geal.mutator.genome.CrossOverGenomeDefault;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestCrossOverGenomeDefault {

    private CrossOverGenomeDefault m;

    private Genome g1;
    private Genome g2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        m = new CrossOverGenomeDefault();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testMutate01() {
        m.setFrequency(1);
        g1 = new GenomeBoolean(false, false, true, true);
        g2 = new GenomeBoolean(true, true, false, false);
        m.setStartIndex(0);
        m.setStopIndex(2);
        final GenomePair np = m.cross(new GenomePair(g1, g2));

        g1 = (Genome) np.getFirst();
        g2 = (Genome) np.getSecond();

        assertEquals(Boolean.TRUE, ((GeneBoolean) g1.getGenes().get(0)).express());
        assertEquals(Boolean.TRUE, ((GeneBoolean) g1.getGenes().get(1)).express());
        assertEquals(Boolean.TRUE, ((GeneBoolean) g1.getGenes().get(2)).express());
        assertEquals(Boolean.TRUE, ((GeneBoolean) g1.getGenes().get(3)).express());

        assertEquals(Boolean.FALSE, ((GeneBoolean) g2.getGenes().get(0)).express());
        assertEquals(Boolean.FALSE, ((GeneBoolean) g2.getGenes().get(1)).express());
        assertEquals(Boolean.FALSE, ((GeneBoolean) g2.getGenes().get(2)).express());
        assertEquals(Boolean.FALSE, ((GeneBoolean) g2.getGenes().get(3)).express());

        // System.out.println(g1);
        // System.out.println(g2);

    }
}
