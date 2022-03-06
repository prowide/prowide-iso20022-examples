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

import com.prowidesoftware.swift.model.mx.MxPacs00800102;
import com.prowidesoftware.swift.model.mx.MxPacs00800108;
import com.prowidesoftware.swift.model.mx.dic.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MxDateTimeAdaptersMarshal {

    public static void main(String[] args) throws DatatypeConfigurationException {

        XMLGregorianCalendar utc = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2021, Calendar.OCTOBER, 19, 12, 13, 14));
        utc.setTimezone(0);
        MxPacs00800108 mx = sample(utc);
        System.out.println("\nDefault adapters for calendar in UTC with no fraction of seconds");
        System.out.println(mx.message());

        XMLGregorianCalendar zoned = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2021, Calendar.OCTOBER, 19, 12, 13, 14));
        zoned.setTimezone(180);
        mx = sample(zoned);
        System.out.println("\nDefault adapters for zoned and no fraction of seconds");
        System.out.println(mx.message());

        XMLGregorianCalendar fractions = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2021, Calendar.OCTOBER, 19, 12, 13, 14));
        fractions.setFractionalSecond(new BigDecimal("0.123"));
        mx = sample(fractions);
        System.out.println("\nDefault adapters for zoned and fraction of seconds");
        System.out.println(mx.message());
    }

    private static MxPacs00800108 sample(final XMLGregorianCalendar cal) {
        final MxPacs00800108 mx = new MxPacs00800108();
        mx.setFIToFICstmrCdtTrf(new FIToFICustomerCreditTransferV08());
        mx.getFIToFICstmrCdtTrf().setGrpHdr(new GroupHeader93());
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setCreDtTm(cal);  // date time
        mx.getFIToFICstmrCdtTrf().getGrpHdr().setIntrBkSttlmDt(cal);  //date
        mx.getFIToFICstmrCdtTrf().addCdtTrfTxInf(new CreditTransferTransaction39());
        mx.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).setSttlmTmReq(new SettlementTimeRequest2());
        mx.getFIToFICstmrCdtTrf().getCdtTrfTxInf().get(0).getSttlmTmReq().setCLSTm(cal); // time
        return mx;
    }

}