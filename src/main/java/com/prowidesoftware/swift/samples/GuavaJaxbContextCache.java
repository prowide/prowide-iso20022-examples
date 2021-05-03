/*
 * Copyright 2006-2021 Prowide
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.prowidesoftware.swift.samples;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.prowidesoftware.swift.model.mx.AbstractMX;
import com.prowidesoftware.swift.model.mx.JaxbContextCache;

import javax.xml.bind.JAXBContext;
import java.util.concurrent.ExecutionException;

/**
 * Guava based example of plugable Jaxb context cache to use with the {@link com.prowidesoftware.swift.model.mx.JaxbContextLoader}
 */
public class GuavaJaxbContextCache implements JaxbContextCache {

    private final Cache<Class<? extends AbstractMX>, JAXBContext> cache = CacheBuilder.newBuilder().maximumSize(100).build();

    public JAXBContext get(final Class messageClass, final Class<?>[] classes) throws ExecutionException {
        return cache.get(messageClass, () -> JAXBContext.newInstance(classes));
    }

}