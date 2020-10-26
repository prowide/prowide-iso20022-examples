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

import java.math.BigDecimal;

import com.prowidesoftware.swift.model.mx.MxPain00100108;
import com.prowidesoftware.swift.model.mx.dic.CustomerCreditTransferInitiationV08;
import com.prowidesoftware.swift.model.mx.dic.GroupHeader48;
import com.prowidesoftware.swift.model.mx.dic.PartyIdentification43;
import com.prowidesoftware.swift.model.mx.dic.PaymentInstruction22;

/**
 * This example shows how to create a new MX message using the Java model to set its content.<br>
 * Outputs this:<br>
 * 
 * <pre>
 * <?xml version="1.0" encoding="UTF-8"?>
 * <Doc:Document xmlns:Doc="urn:iso:std:iso:20022:tech:xsd:pain.001.001.08" xmlns:xsi="{http://www.w3.org/2000/xmlns/}Doc">
 *  <Doc:CstmrCdtTrfInitn>
 *   <Doc:GrpHdr>
 *    <Doc:CtrlSum>100</Doc:CtrlSum>
 *   </Doc:GrpHdr>
 *  
 *   <Doc:PmtInf>
 *    <Doc:Dbtr>
 *     <Doc:Nm>foo</Doc:Nm>
 *    </Doc:Dbtr>
 *   </Doc:PmtInf>
 *  </Doc:CstmrCdtTrfInitn>
 * </Doc:Document>
 * </pre>
 */
public class MxCreation1Example {

    public static void main(String[] args) {
    	/*
    	 * Initialize the MX object
    	 */
		MxPain00100108 pain001001 = new MxPain00100108();
	
		/*
		 * Construct element content using the business dictionary
		 */
		PaymentInstruction22 pi = new PaymentInstruction22()
			.setDbtr(new PartyIdentification43()
				.setNm("foo")
			);
		
		CustomerCreditTransferInitiationV08 ccti = new CustomerCreditTransferInitiationV08()
			.setGrpHdr(new GroupHeader48()
				.setCtrlSum(new BigDecimal(100))
			)
			.addPmtInf(pi);
	
		pain001001.setCstmrCdtTrfInitn(ccti);
	
		/*
		 * Print the generated message in its XML format
		 */
		System.out.println(pain001001.message());
    }
}
