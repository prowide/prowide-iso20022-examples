/*
 * Copyright 2006-2020 Prowide
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

import com.prowidesoftware.swift.model.mx.BusinessAppHdrV01;
import com.prowidesoftware.swift.model.mx.MxPacs00800107;
import com.prowidesoftware.swift.utils.Lib;

import java.io.IOException;

/**
 * Reads content from the message header, using the specific header implementation model
 *
 * <p>Running this will produce:<br>
 * <pre>
 * Header from: ABCDEFGH
 * </pre>
 */
public class MxParseMessageWithHeader2 {

    public static void main(String[] args) throws IOException {

        // parse the XML message content from a resource file
        MxPacs00800107 mx = MxPacs00800107.parse(Lib.readResource("pacs.008.001.07_ClrSysMmbId.xml"));

        // you can further cast this to a specific AppHdr implementation to read more data
        BusinessAppHdrV01 header = (BusinessAppHdrV01) mx.getAppHdr();
        System.out.println("Header from: " + header.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
    }

}
