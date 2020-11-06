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

import com.prowidesoftware.swift.model.mx.MxPacs00900109;
import com.prowidesoftware.swift.model.mx.dic.CreditTransferTransaction44;
import com.prowidesoftware.swift.utils.Lib;

import java.io.IOException;
import java.util.UUID;

/**
 * Parses a pacs.009 from a resource file and generates and adds a UETR field in all transactions
 */
public class MxModificationAddingUETR {

    public static void main(String[] args) throws IOException {

        // parse the XML message content from a resource file
        MxPacs00900109 mx = MxPacs00900109.parse(Lib.readResource("pacs.009.001.09.xml"));

        // iterate all transactions in the message
        for (CreditTransferTransaction44 tx : mx.getFICdtTrf().getCdtTrfTxInf()) {

            // create a new unique end to end reference
            UUID uuid = UUID.randomUUID();
            String uuid36 = uuid.toString();

            // set the reference to the corresponding field
            tx.getPmtId().setUETR(uuid36);
        }

        // print the modified XML
        System.out.println(mx.message());
    }

}
