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

import com.prowidesoftware.swift.model.mx.MxPacs00800108;
import com.prowidesoftware.swift.model.mx.MxWriteConfiguration;
import com.prowidesoftware.swift.model.mx.dic.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

/**
 * This example shows how to create a new MX payment pacs.008.001.02 using the Java model to set its content.<br><br>
 * <p>
 * Outputs this MX message:<br>
 * <pre>
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <Document xmlns="urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08">
 *     <FIToFICstmrCdtTrf>
 *         <GrpHdr>
 *             <MsgId>TBEXO12345</MsgId>
 *             <CreDtTm>2022-05-30T19:41:37.724-03:00</CreDtTm>
 *             <NbOfTxs>1</NbOfTxs>
 *             <SttlmInf>
 *                 <SttlmMtd>INDA</SttlmMtd>
 *                 <SttlmAcct>
 *                     <Id>
 *                         <Othr>
 *                             <Id>00010013800002001234</Id>
 *                         </Othr>
 *                     </Id>
 *                 </SttlmAcct>
 *             </SttlmInf>
 *             <InstgAgt>
 *                 <FinInstnId>
 *                     <BICFI>FOOBARC0XXX</BICFI>
 *                 </FinInstnId>
 *             </InstgAgt>
 *             <InstdAgt>
 *                 <FinInstnId>
 *                     <BICFI>BANKANC0XXX</BICFI>
 *                 </FinInstnId>
 *             </InstdAgt>
 *         </GrpHdr>
 *         <CdtTrfTxInf>
 *             <PmtId>
 *                 <InstrId>TBEXO12345</InstrId>
 *                 <EndToEndId>TBEXO12345</EndToEndId>
 *                 <TxId>TBEXO12345</TxId>
 *             </PmtId>
 *             <IntrBkSttlmAmt Ccy="USD">23453</IntrBkSttlmAmt>
 *             <IntrBkSttlmDt>2022-05-30</IntrBkSttlmDt>
 *             <ChrgBr>DEBT</ChrgBr>
 *             <Dbtr>
 *                 <Nm>JOE DOE</Nm>
 *                 <PstlAdr>
 *                     <AdrLine>310 Field Road, NY</AdrLine>
 *                 </PstlAdr>
 *             </Dbtr>
 *             <DbtrAcct>
 *                 <Id>
 *                     <Othr>
 *                         <Id>01111001759234567890</Id>
 *                     </Othr>
 *                 </Id>
 *             </DbtrAcct>
 *             <DbtrAgt>
 *                 <FinInstnId>
 *                     <BICFI>FOOBARC0XXX</BICFI>
 *                 </FinInstnId>
 *             </DbtrAgt>
 *             <CdtrAgt>
 *                 <FinInstnId>
 *                     <BICFI>BANKANC0XXX</BICFI>
 *                 </FinInstnId>
 *             </CdtrAgt>
 *             <Cdtr>
 *                 <Nm>TEST CORP</Nm>
 *                 <PstlAdr>
 *                     <AdrLine>Nellis ABC, NV</AdrLine>
 *                 </PstlAdr>
 *             </Cdtr>
 *             <CdtrAcct>
 *                 <Id>
 *                     <Othr>
 *                         <Id>00013500510020179998</Id>
 *                     </Othr>
 *                 </Id>
 *             </CdtrAcct>
 *         </CdtTrfTxInf>
 *     </FIToFICstmrCdtTrf>
 * </Document>
 * </pre><br><br>
 * <p>
 * This MX messages is the equivalent to the MT103 simple credit transfer:
 * <pre>
 * {1:F01FOOBARC0AXXX0344000050}{2:I103BANKANC0XXXXN}{4:"
 * 	:20:TBEXO12345
 * 	:23B:CRED
 * 	:32A:150519USD23453,
 * 	:50K:/01111001759234567890
 * 	JOE DOE
 * 	310 Field Road, NY
 * 	:53B:/00010013800002001234
 * 	FOO BANK
 * 	:59:/00013500510020179998
 * 	TEST CORP
 * 	Nellis ABC, NV
 * 	:71A:OUR
 * 	:72:/TIPO/422
 * 	-}";
 * 	</pre>
 */
public class MxCreation2Example {

    public static void main(String[] args) {
        /*
         * Initialize the MX object
         */
        MxPacs00800108 mx = new MxPacs00800108();

        /*
         * Initialize main message content main objects
         */
        mx.setFIToFICstmrCdtTrf(new FIToFICustomerCreditTransferV08().setGrpHdr(new GroupHeader93()));

        /*
         * General Information
         */
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setMsgId("TBEXO12345");
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setCreDtTm(OffsetDateTime.now(ZoneId.systemDefault()));
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setNbOfTxs("1");

        /*
         * Settlement Information
         */
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setSttlmInf(new SettlementInstruction7());
        mx.getFIToFICstmrCdtTrf().getGrpHdr().getSttlmInf().setSttlmMtd(SettlementMethod1Code.INDA);
        mx.getFIToFICstmrCdtTrf().getGrpHdr().getSttlmInf().setSttlmAcct(
                (new CashAccount38()).setId(
                        (new AccountIdentification4Choice()).setOthr(
                                (new GenericAccountIdentification1()).setId("00010013800002001234"))));

        /*
         * Instructing Agent
         */
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setInstgAgt(
                (new BranchAndFinancialInstitutionIdentification6()).setFinInstnId(
                        (new FinancialInstitutionIdentification18()).setBICFI("FOOBARC0XXX")));

        /*
         * Instructed Agent
         */
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setInstdAgt(
                (new BranchAndFinancialInstitutionIdentification6()).setFinInstnId(
                        (new FinancialInstitutionIdentification18()).setBICFI("BANKANC0XXX")));

        /*
         * Payment Transaction Information
         */
        CreditTransferTransaction39 cti = new CreditTransferTransaction39();

        /*
         * Transaction Identification
         */
        cti.setPmtId(new PaymentIdentification7());
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
        cti.setIntrBkSttlmDt(LocalDate.now());

        /*
         * Transaction Charges
         */
        cti.setChrgBr(ChargeBearerType1Code.DEBT);

        /*
         * Orderer Name & Address
         */
        cti.setDbtr(new PartyIdentification135());
        cti.getDbtr().setNm("JOE DOE");
        cti.getDbtr().setPstlAdr((new PostalAddress24()).addAdrLine("310 Field Road, NY"));

        /*
         * Orderer Account
         */
        cti.setDbtrAcct(
                (new CashAccount38()).setId(
                        (new AccountIdentification4Choice()).setOthr(
                                (new GenericAccountIdentification1()).setId("01111001759234567890"))));
        /*
         * Order Financial Institution
         */
        cti.setDbtrAgt(
                (new BranchAndFinancialInstitutionIdentification6()).setFinInstnId(
                        (new FinancialInstitutionIdentification18()).setBICFI("FOOBARC0XXX")));

        /*
         * Beneficiary Institution
         */
        cti.setCdtrAgt((new BranchAndFinancialInstitutionIdentification6()).setFinInstnId(
                (new FinancialInstitutionIdentification18()).setBICFI("BANKANC0XXX")));

        /*
         * Beneficiary Name & Address
         */
        cti.setCdtr(new PartyIdentification135());
        cti.getCdtr().setNm("TEST CORP");
        cti.getCdtr().setPstlAdr((new PostalAddress24().addAdrLine("Nellis ABC, NV")));

        /*
         * Beneficiary Account
         */
        cti.setCdtrAcct(
                (new CashAccount38()).setId(
                        (new AccountIdentification4Choice()).setOthr(
                                (new GenericAccountIdentification1()).setId("00013500510020179998"))));

        mx.getFIToFICstmrCdtTrf().addCdtTrfTxInf(cti);

        /*
         * Print the generated message in its XML format (without prefix)
         */
        MxWriteConfiguration conf = new MxWriteConfiguration();
        conf.documentPrefix = null;
        System.out.println(mx.message(conf));
    }

}
