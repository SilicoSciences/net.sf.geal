package net.sf.ga;

import java.util.List;

import net.sf.ga.individual.Individual;
import net.sf.geal.gene.GeneBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminatorBoolean implements TerminatorEvolution<List<Boolean>, Boolean, GeneBoolean> {

    private static final Logger log = LoggerFactory.getLogger(TerminatorBoolean.class);

    public Boolean visit(final GeneticAlgorithm<List<Boolean>, Boolean, GeneBoolean> element) {
        for (final Individual<List<Boolean>, Boolean, GeneBoolean> e : element.getCurrentPopulation()) {
            if (isPerfekt(e)) {
                if (log.isInfoEnabled()) {
                    log.info("found perfekt " + e);
                }
                return true;
            }
        }
        return false;
    }

    private boolean isPerfekt(final Individual<List<Boolean>, Boolean, GeneBoolean> e) {
        for (final GeneBoolean g : e.getGenome()) {
            if (g.express()) {
                // good
            } else {
                return false;
            }
        }
        return true;
    }

}
