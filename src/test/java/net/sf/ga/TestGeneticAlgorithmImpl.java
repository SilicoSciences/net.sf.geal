package net.sf.ga;

import java.util.Arrays;

import net.sf.ga.individual.FactoryIndividualDefault;
import net.sf.ga.individual.IndividualImpl;
import net.sf.ga.mutator.genome.CrossOverGenomeDefault;
import net.sf.ga.mutator.genome.MutatorGenomeDefault;
import net.sf.ga.population.PopulationImpl;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.GenomeBoolean;
import net.sf.kerner.utils.collections.impl.UtilCollection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestGeneticAlgorithmImpl {

    GeneticAlgorithmImpl ga;

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

    @Test(expected = ExceptionRuntimeGA.class)
    public final void testEvolve01() {
        final PopulationImpl init = new PopulationImpl();
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final CrossOverGenomeDefault cross = new CrossOverGenomeDefault(1);
        cross.setStartIndex(0);
        cross.setStopIndex(2);
        final MutatorGenomeDefault mutator = new MutatorGenomeDefault(0);
        final FactoryIndividualDefault factory = new FactoryIndividualDefault();
        factory.setCrossOvers(Arrays.asList(cross));
        factory.setMutators(Arrays.asList(mutator));
        init.add(i1);
        init.add(i2);
        ga = new GeneticAlgorithmImpl(init);
        ga.setFactoryIndividual(factory);
        ga.setMaxPopulationSize(10);
        ga.evolve();
    }

    @Ignore
    @Test
    public final void testEvolve02() {
        final PopulationImpl init = new PopulationImpl();
        final Genome g1 = new GenomeBoolean(true, true, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final CrossOverGenomeDefault cross = new CrossOverGenomeDefault(0);
        final MutatorGenomeDefault mutator = new MutatorGenomeDefault(0);
        final FactoryIndividualDefault factory = new FactoryIndividualDefault();
        factory.setCrossOvers(Arrays.asList(cross));
        factory.setMutators(Arrays.asList(mutator));
        init.add(i1);
        init.add(i2);
        ga = new GeneticAlgorithmImpl(init);
        ga.setFactoryIndividual(factory);

        ga.addTerminator(new TerminatorBoolean());
        ga.setMaxPopulationSize(20);
        ga.evolve();
        System.out.println(UtilCollection.toString(ga.getCurrentPopulation().getSubPopulation(10)));
    }

    @Ignore
    @Test
    public final void testEvolve03() {
        final PopulationImpl init = new PopulationImpl();
        final Genome g1 = new GenomeBoolean(false, false, true, true);
        final Genome g2 = new GenomeBoolean(true, true, false, false);
        final IndividualImpl i1 = new IndividualImpl(g1, new FitnessCalculatorBoolean());
        final IndividualImpl i2 = new IndividualImpl(g2, new FitnessCalculatorBoolean());
        final CrossOverGenomeDefault cross = new CrossOverGenomeDefault(0.8);
        final MutatorGenomeDefault mutator = new MutatorGenomeDefault(0.1);
        final FactoryIndividualDefault factory = new FactoryIndividualDefault();
        factory.setFitnessCalculator(new FitnessCalculatorBoolean());
        factory.setCrossOvers(Arrays.asList(cross));
        factory.setMutators(Arrays.asList(mutator));
        init.add(i1);
        init.add(i2);
        ga = new GeneticAlgorithmImpl(init);
        ga.setFactoryIndividual(factory);

        ga.addTerminator(new TerminatorBoolean());
        ga.setMaxPopulationSize(40);
        ga.evolve();
        System.out.println(UtilCollection.toString(ga.getCurrentPopulation().getSubPopulation(10)));
    }
}
