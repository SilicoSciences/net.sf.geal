package net.sf.geal.individual;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import net.sf.geal.ExceptionRuntimeGA;
import net.sf.geal.FitnessCalculatorBoolean;
import net.sf.geal.gene.GeneBoolean;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomeAbstract;
import net.sf.geal.genome.GenomeBoolean;
import net.sf.geal.individual.FactoryIndividualDefault;
import net.sf.geal.individual.IndividualImpl;
import net.sf.geal.individual.IndividualPair;
import net.sf.geal.mutator.genome.CrossOverGenomeDefault;
import net.sf.geal.mutator.genome.MutatorGenomeDefault;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestFactoryIndividualDefault {

    private FactoryIndividualDefault factory;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        factory = new FactoryIndividualDefault();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = ExceptionRuntimeGA.class)
    public final void testBreed01() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        factory.breed(new IndividualPair(i1, i2));
    }

    @Test(expected = ExceptionRuntimeGA.class)
    public final void testBreed02() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final CrossOverGenomeDefault cross = new CrossOverGenomeDefault(1);
        cross.setStartIndex(0);
        cross.setStopIndex(1);
        factory.setCrossOvers(Arrays.asList(cross));
        factory.breed(new IndividualPair(i1, i2));
    }

    @Ignore
    @Test
    public final void testBreed03() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final CrossOverGenomeDefault cross = new CrossOverGenomeDefault(1);
        cross.setStartIndex(0);
        cross.setStopIndex(2);
        final MutatorGenomeDefault mutator = new MutatorGenomeDefault(0);
        factory.setCrossOvers(Arrays.asList(cross));
        factory.setMutators(Arrays.asList(mutator));
        final List newOnes = (List) factory.breed(new IndividualPair(i1, i2));
        assertEquals(Boolean.TRUE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(0)).getGenes()
                .get(0).express());
        assertEquals(Boolean.TRUE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(0)).getGenes()
                .get(1).express());
        assertEquals(Boolean.TRUE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(0)).getGenes()
                .get(2).express());
        assertEquals(Boolean.TRUE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(0)).getGenes()
                .get(3).express());

        assertEquals(Boolean.FALSE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(1)).getGenes()
                .get(0).express());
        assertEquals(Boolean.FALSE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(1)).getGenes()
                .get(1).express());
        assertEquals(Boolean.FALSE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(1)).getGenes()
                .get(2).express());
        assertEquals(Boolean.FALSE, ((GenomeAbstract<List<Boolean>, Boolean, GeneBoolean>) newOnes.get(1)).getGenes()
                .get(3).express());
    }

    @Ignore
    @Test
    public final void testBreed04() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final CrossOverGenomeDefault cross = new CrossOverGenomeDefault(1);
        cross.setStartIndex(0);
        cross.setStopIndex(2);
        final MutatorGenomeDefault mutator = new MutatorGenomeDefault(0);
        factory.setCrossOvers(Arrays.asList(cross));
        factory.setMutators(Arrays.asList(mutator));
        List newOnes = (List) factory.breed(new IndividualPair(i1, i2));
        assertEquals(1, ((IndividualImpl) newOnes.get(0)).getGeneration());
        assertEquals(1, ((IndividualImpl) newOnes.get(1)).getGeneration());

        newOnes = (List) factory.breed(new IndividualPair((IndividualImpl) newOnes.get(0), (IndividualImpl) newOnes
                .get(1)));
        assertEquals(2, ((IndividualImpl) newOnes.get(0)).getGeneration());
        assertEquals(2, ((IndividualImpl) newOnes.get(1)).getGeneration());
    }

}
