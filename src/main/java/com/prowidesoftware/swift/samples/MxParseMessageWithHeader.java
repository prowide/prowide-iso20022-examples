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

import com.prowidesoftware.swift.model.mx.MxPacs00800107;
import com.prowidesoftware.swift.model.mx.dic.ActiveCurrencyAndAmount;
import com.prowidesoftware.swift.utils.Lib;

import java.io.IOException;

/**
 * Parses a pacs.008 from a resource file
 *
 * <p>Running this will produce:<br>
 *
 * <pre>
 * Header from: ABCDUS33XXX
 * Header to: EFGHUS33XXX
 * Header reference: 12312312312
 * Amount: USD 5000
 * </pre>
 */
public class MxParseMessageWithHeader {

    public static void main(String[] args) throws IOException {

        // parse the XML message content from a resource file
        MxPacs00800107 mx = MxPacs00800107.parse(Lib.readResource("pacs.008.001.07.xml"));

        // access message header data from the java model
        // you can further cast this to a specific AppHdr implementation to read more data
        System.out.println("Header from: " + mx.getAppHdr().from());
        System.out.println("Header to: " + mx.getAppHdr().to());
        System.out.println("Header reference: " + mx.getAppHdr().reference());

        // access message document data from the java model
        ActiveCurrencyAndAmount amount = mx.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getIntrBkSttlmAmt();
        System.out.println("Amount: " + amount.getCcy() + " " + amount.getValue());
    }

}
