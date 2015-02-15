package net.sf.geal;

import java.util.Arrays;

import net.sf.geal.gene.impl.GeneDouble;
import net.sf.geal.genome.Genome;
import net.sf.geal.genome.impl.GenomeDoubleSum;
import net.sf.geal.individual.FactoryIndividual;
import net.sf.geal.individual.Individual;
import net.sf.geal.individual.IndividualBreederImpl;
import net.sf.geal.individual.IndividualDoubleSum;
import net.sf.geal.mutator.gene.MutatorGeneDouble;
import net.sf.geal.mutator.genome.MutatorCrossOverDefault;
import net.sf.geal.mutator.genome.MutatorPointDefault;
import net.sf.geal.population.Population;
import net.sf.geal.population.PopulationImpl;
import net.sf.geal.terminator.TerminatorGenerations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGeneticAlgorithmImpl {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    private GeneticAlgorithmImpl ga;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        ga = null;
    }

    @Test
    public final void testEvolve01() {

        // build an initial set of genes
        final GeneDouble g1 = new GeneDouble(0.001);
        final GeneDouble g2 = new GeneDouble(0.002);

        // define the valid range of values
        // valid range is between 0 and 1
        g1.setMutator(new MutatorGeneDouble(0, 1, 0.001));
        g2.setMutator(new MutatorGeneDouble(0, 1, 0.001));

        // build initial set of genomes
        // the higher the number the better
        final GenomeDoubleSum gg1 = new GenomeDoubleSum(g1);
        final GenomeDoubleSum gg2 = new GenomeDoubleSum(g2);

        // build initial set of individuals
        final IndividualDoubleSum i1 = new IndividualDoubleSum(gg1);
        final IndividualDoubleSum i2 = new IndividualDoubleSum(gg2);

        // build an individual breeder
        final IndividualBreederImpl ib = new IndividualBreederImpl(
                Arrays.asList(new MutatorPointDefault(0.5)),
                Arrays.asList(new MutatorCrossOverDefault()), new FactoryIndividual() {

                    @Override
                    public IndividualDoubleSum create() {
                        throw new ExceptionRuntimeGA("Not supported");
                    }

                    @Override
                    public synchronized IndividualDoubleSum create(final int generation,
                            final Genome genome) {
                        final IndividualDoubleSum result = new IndividualDoubleSum(generation,
                                genome);
                        return result;
                    }
                });

        // put everything together
        final PopulationImpl p = new PopulationImpl();
        p.add(i1);
        p.add(i2);

        ga = new GeneticAlgorithmImpl(p);

        ga.setIndividualBreeder(ib);

        // check whats going on
        ga.addListener(new ListenerEvolution() {
            @Override
            public void newPopulation(final Population population) {
                final Individual hans = ga.getCurrentPopulation().getSubPopulation(1)
                        .getIndividuals().get(0);
                System.out.println("Currently fittest individuum: " + hans);
            }
        });

        // stop optimization after 100 iterations
        ga.addTerminator(new TerminatorGenerations(100));

        // finally start
        ga.evolve();

        final Individual hans = ga.getCurrentPopulation().getSubPopulation(1).getIndividuals()
                .get(0);
        System.out.println("Fittest individuum: " + hans);
    }
}
