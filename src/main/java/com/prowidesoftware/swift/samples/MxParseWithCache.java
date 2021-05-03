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

import com.prowidesoftware.swift.model.mx.JaxbContextCacheImpl;
import com.prowidesoftware.swift.model.mx.JaxbContextLoader;
import com.prowidesoftware.swift.model.mx.MxCamt04800103;

/**
 * The parse methods available in the specific classes such as MxCamt04800103 or in the AbstractMX class uses JAXB
 * unmarshaller. By default implementation will create a new JAXBContext for each parse invocation with has a huge
 * performance penalty when processing many messages.
 *
 * <p>To deal with it the implementation supports injecting a cache for the JaxbContext. This is managed by a singleton
 * class {@link JaxbContextLoader}.
 *
 * <p>When a cache implementation is set in the loader, the created JAXBContext will be cached and re-used between parse
 * and write calls for the same message types, significantly improving the overall performance.
 *
 * <p>The JaxbContextCacheImpl is a default out-of-the-box cache implementation based on a simple ConcurrentHashMap,
 * with no eviction. This implementation is aimed to avoid additional third party dependencies. Then in this example
 * you can see how to implement and add your own version of the cache, using any third party library you want such as
 * Ecache.
 */
public class MxParseWithCache {

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Doc:Document xmlns:Doc=\"urn:swift:xsd:camt.048.001.03\" xmlns:xsi=\"httDoc://www.w3.org/2001/XMLSchema-instance\">\n"
                + "  <Doc:ModfyRsvatn>\n"
                + "    <Doc:MsgHdr>\n"
                + "      <Doc:MsgId>001</Doc:MsgId>\n"
                + "    </Doc:MsgHdr>\n"
                + "    <Doc:RsvatnId>\n"
                + "      <Doc:Cur>\n"
                + "        <Doc:Tp>\n"
                + "          <Doc:Cd>CARE</Doc:Cd>\n"
                + "        </Doc:Tp>\n"
                + "      </Doc:Cur>\n"
                + "    </Doc:RsvatnId>\n"
                + "    <Doc:NewRsvatnValSet>\n"
                + "      <Doc:Amt>\n"
                + "        <Doc:AmtWthtCcy>1234.0</Doc:AmtWthtCcy>\n"
                + "      </Doc:Amt>\n"
                + "    </Doc:NewRsvatnValSet>\n"
                + "  </Doc:ModfyRsvatn>\n"
                + "</Doc:Document>";

        // the default parse call does not use any cache, so if we call the parser twice, two instances of the
        // jaxb cache will be created.
        MxCamt04800103.parse(xml);
        MxCamt04800103.parse(xml);

        // For better performance we can set the default implementation of the cache, thus the second time we parse
        // the same message type, the jaxb context will be reused
        JaxbContextLoader.INSTANCE.setCacheImpl(new JaxbContextCacheImpl());
        MxCamt04800103.parse(xml);
        MxCamt04800103.parse(xml);

        // Finally for a better cache implementation, with eviction for example, we can plug in our own implementation
        // of the cache, in this example using Guava
        JaxbContextLoader.INSTANCE.setCacheImpl(new GuavaJaxbContextCache());
        MxCamt04800103.parse(xml);
        MxCamt04800103.parse(xml);
    }

}
