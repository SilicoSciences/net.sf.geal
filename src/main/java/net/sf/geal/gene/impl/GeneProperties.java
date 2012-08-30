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

package net.sf.geal.gene.impl;

import net.sf.geal.gene.GeneImpl;
import net.sf.kerner.utils.impl.util.Util;

/**
 * TODO description
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
public class GeneProperties extends GeneImpl<Object> {

    private volatile String key;

    public GeneProperties() {
        super(null);
    }

    public GeneProperties(final GeneProperties template) {
        this(template.getKey(), template.getValue());
        setMutator(template.getMutator());
    }

    public GeneProperties(final String key, final Object value) {
        super(value);
        this.key = key;
    }

    @Override
    public synchronized GeneProperties clone() {
        return new GeneProperties(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return Util.equalsOnHashCode(this, obj);
    }

    public synchronized String getKey() {
        return key;
    }

    public synchronized Object getValue() {
        return express();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    public synchronized void setKey(final String key) {
        this.key = key;
    }

    public synchronized void setValue(final Object value) {
        impress(value);
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }

}
