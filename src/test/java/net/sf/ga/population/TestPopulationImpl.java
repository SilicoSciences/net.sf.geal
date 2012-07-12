package net.sf.ga.population;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import net.sf.ga.FitnessCalculatorBoolean;
import net.sf.ga.individual.IndividualImpl;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomeBoolean;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestPopulationImpl {

    PopulationImpl pop;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        pop = new PopulationImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testAdd01() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        pop.add(i1);
        pop.add(i2);
        assertEquals(2, pop.getSize());
    }

    @Test
    public final void testAddAll01() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        pop.addAll(Arrays.asList(i1, i2));
        assertEquals(2, pop.getSize());
    }

    @Test
    public final void testGetIndividuals01() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        pop.addAll(Arrays.asList(i1, i2));
        final List individuals = pop.getIndividuals();
        assertEquals(Arrays.asList(i1, i2).toArray(), individuals.toArray());
    }

    @Test
    public final void testGetSubPopulation01() {
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        pop.addAll(Arrays.asList(i1, i2));
        final Population sub = pop.getSubPopulation(1);
        System.out.println(sub);
        assertEquals(1, sub.getSize());
        final List individuals = sub.getIndividuals();
        assertFalse(individuals.get(0) == i1);
        assertTrue(individuals.get(0).equals(i1));

    }

}
