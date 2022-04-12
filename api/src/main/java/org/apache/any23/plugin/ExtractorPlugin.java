/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.any23.plugin;

import org.apache.any23.extractor.Extractor;
import org.apache.any23.extractor.ExtractorFactory;

/**
 * This interface defines an Any23 extractor plugin that can be detected and registered from the library classpath.
 *
 * @author Michele Mostarda (mostarda@fbk.eu)
 *
 * @deprecated ExtractorFactory now supports
 *             <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ServiceLoader.html"> META-INF/services</a>
 *             discovery via the {@link java.util.ServiceLoader}, deprecating this class.
 *
 *             Instead implement a subinterface of {@link org.apache.any23.extractor.Extractor} and ensure that your
 *             plugin is in compliance with the META-INF/services mechanism.
 */
@Deprecated
public interface ExtractorPlugin<T extends Extractor<?>> {

    /**
     * @return the {@link org.apache.any23.extractor.ExtractorFactory} for the plugin.
     */
    ExtractorFactory<T> getExtractorFactory();

}
