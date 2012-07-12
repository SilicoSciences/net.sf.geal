package net.sf.geal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.geal.FitnessCalculatorBoolean;
import net.sf.geal.GeneticAlgorithmImpl;
import net.sf.geal.TerminatorBoolean;
import net.sf.geal.gene.GeneBoolean;
import net.sf.geal.genome.GenomeBoolean;
import net.sf.geal.individual.IndividualImpl;
import net.sf.geal.population.PopulationImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestTerminatorBoolean {

    private TerminatorBoolean terminator;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        terminator = new TerminatorBoolean();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testVisit01() {
        final GenomeBoolean g1 = new GenomeBoolean();
        g1.getGenes().add(new GeneBoolean(true));
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final PopulationImpl pop = new PopulationImpl();
        pop.add(i1);
        final GeneticAlgorithmImpl ga = new GeneticAlgorithmImpl(pop);
        assertTrue(terminator.visit(ga));
    }

    @Test
    public final void testVisit02() {
        final GenomeBoolean g1 = new GenomeBoolean();
        g1.getGenes().add(new GeneBoolean(true));
        g1.getGenes().add(new GeneBoolean(false));
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final PopulationImpl pop = new PopulationImpl();
        pop.add(i1);
        final GeneticAlgorithmImpl ga = new GeneticAlgorithmImpl(pop);
        assertFalse(terminator.visit(ga));
    }

    @Test
    public final void testVisit03() {
        final GenomeBoolean g1 = new GenomeBoolean();
        g1.getGenes().add(new GeneBoolean(true));
        g1.getGenes().add(new GeneBoolean(true));
        g1.getGenes().add(new GeneBoolean(true));
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final PopulationImpl pop = new PopulationImpl();
        pop.add(i1);
        final GeneticAlgorithmImpl ga = new GeneticAlgorithmImpl(pop);
        assertTrue(terminator.visit(ga));
    }

    @Test
    public final void testVisit04() {
        final GenomeBoolean g1 = new GenomeBoolean();
        final GenomeBoolean g2 = new GenomeBoolean();
        g1.getGenes().add(new GeneBoolean(true));
        g1.getGenes().add(new GeneBoolean(true));
        g2.getGenes().add(new GeneBoolean(true));
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final PopulationImpl pop = new PopulationImpl();
        pop.add(i1);
        pop.add(i2);
        final GeneticAlgorithmImpl ga = new GeneticAlgorithmImpl(pop);
        assertTrue(terminator.visit(ga));
    }

    @Test
    public final void testVisit05() {
        final GenomeBoolean g1 = new GenomeBoolean();
        final GenomeBoolean g2 = new GenomeBoolean();
        g1.getGenes().add(new GeneBoolean(true));
        g1.getGenes().add(new GeneBoolean(true));
        g2.getGenes().add(new GeneBoolean(true));
        g2.getGenes().add(new GeneBoolean(false));
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final PopulationImpl pop = new PopulationImpl();
        pop.add(i1);
        pop.add(i2);
        final GeneticAlgorithmImpl ga = new GeneticAlgorithmImpl(pop);
        assertTrue(terminator.visit(ga));
    }

}
