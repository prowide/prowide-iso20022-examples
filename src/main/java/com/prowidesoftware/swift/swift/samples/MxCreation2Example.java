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
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.prowidesoftware.swift.model.mx.MxPacs00800102;
import com.prowidesoftware.swift.model.mx.dic.AccountIdentification4Choice;
import com.prowidesoftware.swift.model.mx.dic.ActiveCurrencyAndAmount;
import com.prowidesoftware.swift.model.mx.dic.BranchAndFinancialInstitutionIdentification4;
import com.prowidesoftware.swift.model.mx.dic.CashAccount16;
import com.prowidesoftware.swift.model.mx.dic.ChargeBearerType1Code;
import com.prowidesoftware.swift.model.mx.dic.CreditTransferTransactionInformation11;
import com.prowidesoftware.swift.model.mx.dic.FIToFICustomerCreditTransferV02;
import com.prowidesoftware.swift.model.mx.dic.FinancialInstitutionIdentification7;
import com.prowidesoftware.swift.model.mx.dic.GenericAccountIdentification1;
import com.prowidesoftware.swift.model.mx.dic.GroupHeader33;
import com.prowidesoftware.swift.model.mx.dic.PartyIdentification32;
import com.prowidesoftware.swift.model.mx.dic.PaymentIdentification3;
import com.prowidesoftware.swift.model.mx.dic.PostalAddress6;
import com.prowidesoftware.swift.model.mx.dic.SettlementInformation13;
import com.prowidesoftware.swift.model.mx.dic.SettlementMethod1Code;

/**
 * This example shows how to create a new MX payment pacs.008.001.02 using the Java model to set its content.<br><br>
 * 
 * Outputs this MX message:<br>
 * <pre>
 * <?xml version="1.0" encoding="UTF-8"?>
 * <Doc:Document xmlns:Doc="urn:iso:std:iso:20022:tech:xsd:pacs.008.001.02" xmlns:xsi="{http://www.w3.org/2000/xmlns/}Doc">
 *  <Doc:FIToFICstmrCdtTrf>
 *   <Doc:GrpHdr>
 *    <Doc:MsgId>TBEXO12345</Doc:MsgId>
 *   
 *    <Doc:CreDtTm>2015-05-19T13:15:23.336-03:00</Doc:CreDtTm>
 *   
 *    <Doc:NbOfTxs>1</Doc:NbOfTxs>
 *   
 *    <Doc:SttlmInf>
 *     <Doc:SttlmMtd>INDA</Doc:SttlmMtd>
 *    
 *     <Doc:SttlmAcct>
 *      <Doc:Id>
 *       <Doc:Othr>
 *        <Doc:Id>00010013800002001234</Doc:Id>
 *       </Doc:Othr>
 *      </Doc:Id>
 *     </Doc:SttlmAcct>
 *    </Doc:SttlmInf>
 *   
 *    <Doc:InstgAgt>
 *     <Doc:FinInstnId>
 *      <Doc:BIC>FOOBARC0XXX</Doc:BIC>
 *     </Doc:FinInstnId>
 *    </Doc:InstgAgt>
 *   
 *    <Doc:InstdAgt>
 *     <Doc:FinInstnId>
 *      <Doc:BIC>BANKANC0XXX</Doc:BIC>
 *     </Doc:FinInstnId>
 *    </Doc:InstdAgt>
 *   </Doc:GrpHdr>
 *  
 *   <Doc:CdtTrfTxInf>
 *    <Doc:PmtId>
 *     <Doc:InstrId>TBEXO12345</Doc:InstrId>
 *    
 *     <Doc:EndToEndId>TBEXO12345</Doc:EndToEndId>
 *    
 *     <Doc:TxId>TBEXO12345</Doc:TxId>
 *    </Doc:PmtId>
 *   
 *    <Doc:IntrBkSttlmAmt Ccy="USD" >23453</Doc:IntrBkSttlmAmt>
 *   
 *    <Doc:IntrBkSttlmDt>2015-05-19T13:15:23.352-03:00</Doc:IntrBkSttlmDt>
 *   
 *    <Doc:ChrgBr>DEBT</Doc:ChrgBr>
 *   
 *    <Doc:Dbtr>
 *     <Doc:Nm>JOE DOE</Doc:Nm>
 *    
 *     <Doc:PstlAdr>
 *      <Doc:AdrLine>310 Field Road, NY</Doc:AdrLine>
 *     </Doc:PstlAdr>
 *    </Doc:Dbtr>
 *   
 *    <Doc:DbtrAcct>
 *     <Doc:Id>
 *      <Doc:Othr>
 *       <Doc:Id>01111001759234567890</Doc:Id>
 *      </Doc:Othr>
 *    </Doc:Id>
 *    </Doc:DbtrAcct>
 *   
 *    <Doc:DbtrAgt>
 *     <Doc:FinInstnId>
 *      <Doc:BIC>FOOBARC0XXX</Doc:BIC>
 *     </Doc:FinInstnId>
 *    </Doc:DbtrAgt>
 *   
 *    <Doc:CdtrAgt>
 *     <Doc:FinInstnId>
 *      <Doc:BIC>BANKANC0XXX</Doc:BIC>
 *     </Doc:FinInstnId>
 *    </Doc:CdtrAgt>
 *   
 *    <Doc:Cdtr>
 *     <Doc:Nm>TEST CORP</Doc:Nm>
 *    
 *     <Doc:PstlAdr>
 *      <Doc:AdrLine>Nellis ABC, NV</Doc:AdrLine>
 *     </Doc:PstlAdr>
 *    </Doc:Cdtr>
 *   
 *    <Doc:CdtrAcct>
 *     <Doc:Id>
 *      <Doc:Othr>
 *       <Doc:Id>00013500510020179998</Doc:Id>
 *      </Doc:Othr>
 *     </Doc:Id>
 *    </Doc:CdtrAcct>
 *   </Doc:CdtTrfTxInf>
 *  </Doc:FIToFICstmrCdtTrf>
 * </Doc:Document>
 * </pre><br><br>
 *
 * This MX messages is the equivalent to the MT103 simple credit transfer:
 * <pre>
 * {1:F01FOOBARC0AXXX0344000050}{2:I103BANKANC0XXXXN}{4:"
 *	:20:TBEXO12345
 *	:23B:CRED
 *	:32A:150519USD23453,
 *	:50K:/01111001759234567890
 *	JOE DOE
 *	310 Field Road, NY
 *	:53B:/00010013800002001234
 *	FOO BANK
 *	:59:/00013500510020179998
 *	TEST CORP
 *	Nellis ABC, NV
 *	:71A:OUR
 *	:72:/TIPO/422
 *	-}";
 *	</pre>
 *
 * @since 7.7
 */
public class MxCreation2Example {

    public static void main(String[] args) {
    	/*
    	 * Initialize the MX object
    	 */
		MxPacs00800102 mx = new MxPacs00800102();
	
		/*
		 * Initialize main message content main objects
		 */
		mx.setFIToFICstmrCdtTrf(new FIToFICustomerCreditTransferV02().setGrpHdr(new GroupHeader33()));
		
		/*
		 * General Information
		 */
		mx.getFIToFICstmrCdtTrf().getGrpHdr().setMsgId("TBEXO12345");
		mx.getFIToFICstmrCdtTrf().getGrpHdr().setCreDtTm(getXMLGregorianCalendarNow());
		mx.getFIToFICstmrCdtTrf().getGrpHdr().setNbOfTxs("1");
		
		/*
		 * Settlement Information
		 */
		mx.getFIToFICstmrCdtTrf().getGrpHdr().setSttlmInf(new SettlementInformation13());
		mx.getFIToFICstmrCdtTrf().getGrpHdr().getSttlmInf().setSttlmMtd(SettlementMethod1Code.INDA);
		mx.getFIToFICstmrCdtTrf().getGrpHdr().getSttlmInf().setSttlmAcct(
				(new CashAccount16()).setId(
						(new AccountIdentification4Choice()).setOthr(
								(new GenericAccountIdentification1()).setId("00010013800002001234"))));

		/*
		 * Instructing Agent
		 */
		mx.getFIToFICstmrCdtTrf().getGrpHdr().setInstgAgt(
				(new BranchAndFinancialInstitutionIdentification4()).setFinInstnId(
						(new FinancialInstitutionIdentification7()).setBIC("FOOBARC0XXX")));

		/*
		 * Instructed Agent
		 */
		mx.getFIToFICstmrCdtTrf().getGrpHdr().setInstdAgt(
				(new BranchAndFinancialInstitutionIdentification4()).setFinInstnId(
						(new FinancialInstitutionIdentification7()).setBIC("BANKANC0XXX")));

		/*
		 * Payment Transaction Information
		 */
		CreditTransferTransactionInformation11 cti = new CreditTransferTransactionInformation11();
		
		/*
		 * Transaction Identification
		 */
		cti.setPmtId(new PaymentIdentification3());
		cti.getPmtId().setInstrId("TBEXO12345");
		cti.getPmtId().setEndToEndId("TBEXO12345");
		cti.getPmtId().setTxId("TBEXO12345");
		
		/*
		 * Transaction Amount
		 */
		ActiveCurrencyAndAmount amount = new ActiveCurrencyAndAmount();
		amount.setCcy("USD");
		amount.setValue(new BigDecimal("23453"));
		cti.setIntrBkSttlmAmt(amount);
		
		/*
		 * Transaction Value Date
		 */
		cti.setIntrBkSttlmDt(getXMLGregorianCalendarNow());
		
		/*
		 * Transaction Charges
		 */
		cti.setChrgBr(ChargeBearerType1Code.DEBT);
		
		/*
		 * Orderer Name & Address
		 */
		cti.setDbtr(new PartyIdentification32());
		cti.getDbtr().setNm("JOE DOE");
		cti.getDbtr().setPstlAdr((new PostalAddress6()).addAdrLine("310 Field Road, NY"));
		
		/*
		 * Orderer Account
		 */
		cti.setDbtrAcct(
				(new CashAccount16()).setId(
						(new AccountIdentification4Choice()).setOthr(
								(new GenericAccountIdentification1()).setId("01111001759234567890"))));
		/*
		 * Order Financial Institution
		 */
		cti.setDbtrAgt(
				(new BranchAndFinancialInstitutionIdentification4()).setFinInstnId(
						(new FinancialInstitutionIdentification7()).setBIC("FOOBARC0XXX")));
		
		/*
		 * Beneficiary Institution
		 */
		cti.setCdtrAgt((new BranchAndFinancialInstitutionIdentification4()).setFinInstnId((new FinancialInstitutionIdentification7()).setBIC("BANKANC0XXX")));
		
		/*
		 * Beneficiary Name & Address
		 */
		cti.setCdtr(new PartyIdentification32());
		cti.getCdtr().setNm("TEST CORP");
		cti.getCdtr().setPstlAdr((new PostalAddress6().addAdrLine("Nellis ABC, NV")));
		
		/*
		 * Beneficiary Account
		 */
		cti.setCdtrAcct(
				(new CashAccount16()).setId(
						(new AccountIdentification4Choice()).setOthr(
								(new GenericAccountIdentification1()).setId("00013500510020179998"))));
		
		mx.getFIToFICstmrCdtTrf().addCdtTrfTxInf(cti);
		
		/*
		 * Print the generated message in its XML format
		 */
		System.out.println(mx.message());
    }
    
    public static XMLGregorianCalendar getXMLGregorianCalendarNow() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = null;
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
        XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }
}
