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

import com.prowidesoftware.swift.model.mx.AppHdrFactory;
import com.prowidesoftware.swift.model.mx.BusinessAppHdrV02;
import com.prowidesoftware.swift.model.mx.MxCamt05600110;
import com.prowidesoftware.swift.model.mx.dic.CaseAssignment5;
import com.prowidesoftware.swift.model.mx.dic.FIToFIPaymentCancellationRequestV10;
import com.prowidesoftware.swift.model.mx.dic.Party40Choice;
import com.prowidesoftware.swift.model.mx.dic.PartyIdentification135;

/**
 * This example shows how to create a new MX message using the Java model to set its content.<br>
 * Outputs this:<br>
 *
 * <pre>
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <RequestPayload>
 * <h:AppHdr xmlns:h="urn:iso:std:iso:20022:tech:xsd:head.001.001.02">
 *     <h:Fr>
 *         <h:FIId>
 *             <h:FinInstnId>
 *                 <h:BICFI>AAAAUSXX</h:BICFI>
 *             </h:FinInstnId>
 *         </h:FIId>
 *     </h:Fr>
 *     <h:To>
 *         <h:FIId>
 *             <h:FinInstnId>
 *                 <h:BICFI>BBBBUSXX</h:BICFI>
 *             </h:FinInstnId>
 *         </h:FIId>
 *     </h:To>
 *     <h:BizMsgIdr>MYREF1234</h:BizMsgIdr>
 *     <h:MsgDefIdr>camt.056.001.10</h:MsgDefIdr>
 *     <h:BizSvc>foo.bar.1</h:BizSvc>
 *     <h:CreDt>2022-05-30T19:51:09+00:00</h:CreDt>
 * </h:AppHdr>
 * <Doc:Document xmlns:Doc="urn:iso:std:iso:20022:tech:xsd:camt.056.001.10">
 *     <Doc:FIToFIPmtCxlReq>
 *         <Doc:Assgnmt>
 *             <Doc:Assgnr>
 *                 <Doc:Pty>
 *                     <Doc:Nm>foo</Doc:Nm>
 *                 </Doc:Pty>
 *             </Doc:Assgnr>
 *         </Doc:Assgnmt>
 *     </Doc:FIToFIPmtCxlReq>
 * </Doc:Document>
 * </RequestPayload>
 * </pre>
 */
public class MxCreation3WithAppHdrExample {

    public static void main(String[] args) {
        /*
         * Initialize the MX object
         */
        MxCamt05600110 mx = new MxCamt05600110();

        /*
         * Construct element content using the business dictionary
         */
        mx.setFIToFIPmtCxlReq(new FIToFIPaymentCancellationRequestV10());
        mx.getFIToFIPmtCxlReq().setAssgnmt(new CaseAssignment5());
        mx.getFIToFIPmtCxlReq().getAssgnmt().setAssgnr(new Party40Choice());
        mx.getFIToFIPmtCxlReq().getAssgnmt().getAssgnr().setPty(new PartyIdentification135());
        mx.getFIToFIPmtCxlReq().getAssgnmt().getAssgnr().getPty().setNm("foo");

        /*
         * Create and add a message header
         */
        BusinessAppHdrV02 header = AppHdrFactory.createBusinessAppHdrV02("AAAAUSXX", "BBBBUSXX", "MYREF1234", mx.getMxId());
        header.setBizSvc("foo.bar.1");
        mx.setAppHdr(header);

        /*
         * Print the generated message in its XML format
         */
        System.out.println(mx.message());
    }
}
