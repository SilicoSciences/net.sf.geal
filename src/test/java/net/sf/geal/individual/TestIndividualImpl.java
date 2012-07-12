package net.sf.geal.individual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.sf.geal.FitnessCalculatorBoolean;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomeBoolean;
import net.sf.geal.individual.IndividualImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestIndividualImpl {

    private IndividualImpl i1;
    private IndividualImpl i2;
    private final FitnessCalculatorBoolean fitnessCalculator = new FitnessCalculatorBoolean();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testHashCode01() {
        final Genome g1 = new GenomeBoolean();
        g1.getGenes().add(new GenomeBoolean(true));
        final Genome g2 = new GenomeBoolean();
        g2.getGenes().add(new GenomeBoolean(true));
        i1 = new IndividualImpl(g1, fitnessCalculator);
        i2 = new IndividualImpl(g2, fitnessCalculator);
        assertTrue(g1.hashCode() == g2.hashCode());
    }

    @Test
    public final void testEqualsObject01() {
        final Genome g1 = new GenomeBoolean();
        g1.getGenes().add(new GenomeBoolean(true));
        final Genome g2 = new GenomeBoolean();
        g2.getGenes().add(new GenomeBoolean(true));
        i1 = new IndividualImpl(g1, fitnessCalculator);
        i2 = new IndividualImpl(g2, fitnessCalculator);
        assertTrue(g1.equals(g2.hashCode()));
    }

    @Ignore
    @Test
    public final void testGetGenome() {
        fail("Not yet implemented");
    }

    @Test
    public final void testGetGeneration() {
        final Genome g1 = new GenomeBoolean();
        g1.getGenes().add(new GenomeBoolean(true));
        final Genome g2 = new GenomeBoolean();
        g2.getGenes().add(new GenomeBoolean(true));
        i1 = new IndividualImpl(g1, fitnessCalculator);
        i2 = new IndividualImpl(g2, fitnessCalculator);
        assertEquals(0, i1.getGeneration());
        assertEquals(0, i2.getGeneration());
    }

    @Ignore
    @Test
    public final void testGetFitness() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public final void testCompareTo() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public final void testClone() {
        fail("Not yet implemented");
    }

}
