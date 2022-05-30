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

import com.prowidesoftware.swift.model.mx.MxPain00100111;
import com.prowidesoftware.swift.model.mx.dic.CustomerCreditTransferInitiationV11;
import com.prowidesoftware.swift.model.mx.dic.GroupHeader95;
import com.prowidesoftware.swift.model.mx.dic.PartyIdentification135;
import com.prowidesoftware.swift.model.mx.dic.PaymentInstruction40;

import java.math.BigDecimal;

/**
 * This example shows how to create a new MX message using the Java model to set its content. Using the default
 * serialization options and no header.<br>
 * Outputs this:<br>
 *
 * <pre>
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <Doc:Document xmlns:Doc="urn:iso:std:iso:20022:tech:xsd:pain.001.001.11">
 *     <Doc:CstmrCdtTrfInitn>
 *         <Doc:GrpHdr>
 *             <Doc:CtrlSum>100</Doc:CtrlSum>
 *         </Doc:GrpHdr>
 *         <Doc:PmtInf>
 *             <Doc:Dbtr>
 *                 <Doc:Nm>foo</Doc:Nm>
 *             </Doc:Dbtr>
 *         </Doc:PmtInf>
 *     </Doc:CstmrCdtTrfInitn>
 * </Doc:Document>
 * </pre>
 */
public class MxCreation1Example {

    public static void main(String[] args) {
        /*
         * Initialize the MX object
         */
        MxPain00100111 mx = new MxPain00100111();

        /*
         * Construct element content using the business dictionary
         */
        PaymentInstruction40 pi = new PaymentInstruction40()
                .setDbtr(new PartyIdentification135()
                        .setNm("foo")
                );

        CustomerCreditTransferInitiationV11 ccti = new CustomerCreditTransferInitiationV11()
                .setGrpHdr(new GroupHeader95()
                        .setCtrlSum(new BigDecimal(100))
                )
                .addPmtInf(pi);

        mx.setCstmrCdtTrfInitn(ccti);

        /*
         * Print the generated message in its XML format
         */
        System.out.println(mx.message());
    }
}
