/*******************************************************************************
 * Copyright (c) 2019 Prowide Inc.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as 
 *     published by the Free Software Foundation, either version 3 of the 
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *     
 *     Check the LGPL at <http://www.gnu.org/licenses/> for more details.
 *******************************************************************************/
package com.prowidesoftware.swift.samples.integrator;

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
 *
 * @since 7.6
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
