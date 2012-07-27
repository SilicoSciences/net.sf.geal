/**********************************************************************
Copyright (c) 2012 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package net.sf.geal.gene;

import net.sf.geal.individual.Individual;
import net.sf.geal.mutator.gene.MutatorGene;

/**
 * A distinct property that contributes to an {@link Individual individuals} fitness.
 * <p>
 * <b>Example:</b><br>
 * </p>
 * <p>
 * 
 * <pre>
 * TODO example
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:alexanderkerner24@gmail.com">Alexander Kerner</a>
 * @version Jul 27, 2012
 */
public interface Gene<P> {

    /**
     * Duplicate this {@code Gene}.
     * 
     * @return A new instance of {@code Gene} which holds a new instance of this {@code Gene}'s value.
     */
    Gene<P> clone();

    /**
     * Get current value for this {@code Gene}.
     * 
     * @return current value for this {@code Gene}
     */
    P express();

    /**
     * Get this {@Gene}'s {@link Mutator}.
     * 
     * @return this {@Gene}'s {@link Mutator}
     */
    MutatorGene<P> getMutator();

    /**
     * Set current value for this {@code Gene}.
     * 
     * @param property
     *            new value for this {@code Gene}
     */
    void impress(P property);

    /**
     * Trigger mutation of this {@code Gene}. Mutation will be delegated typically to this {@code Gene}'s
     * {@link MutatorGene}.
     */
    void mutate();

}
