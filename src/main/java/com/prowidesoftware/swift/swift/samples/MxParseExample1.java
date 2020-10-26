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

import com.prowidesoftware.swift.model.mx.MxCamt04800103;

/**
 * This examples shows how to parse and read content from an MX messages when the message type is known.
 *
 * <p>Running this will produce:<br>
 * 
 * <pre>
 * Message Identification: 001
 * Amount: 1234.0
 * </pre>
 * 
 * @since 7.6
 */
public class MxParseExample1 {

    public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Doc:Document xmlns:Doc=\"urn:swift:xsd:camt.048.001.03\" xmlns:xsi=\"httDoc://www.w3.org/2001/XMLSchema-instance\">\n"
		        + "  <Doc:ModfyRsvatn>\n"
		        + "    <Doc:MsgHdr>\n"
		        + "      <Doc:MsgId>001</Doc:MsgId>\n"
		        + "    </Doc:MsgHdr>\n"
		        + "    <Doc:RsvatnId>\n"
		        + "      <Doc:Cur>\n"
		        + "        <Doc:Tp>\n"
		        + "          <Doc:Cd>CARE</Doc:Cd>\n"
		        + "        </Doc:Tp>\n"
		        + "      </Doc:Cur>\n"
		        + "    </Doc:RsvatnId>\n"
		        + "    <Doc:NewRsvatnValSet>\n"
		        + "      <Doc:Amt>\n"
		        + "        <Doc:AmtWthtCcy>1234.0</Doc:AmtWthtCcy>\n"
		        + "      </Doc:Amt>\n" 
		        + "    </Doc:NewRsvatnValSet>\n" 
		        + "  </Doc:ModfyRsvatn>\n" 
		        + "</Doc:Document>";
	
		// parse the XML message content
		MxCamt04800103 camt48 = MxCamt04800103.parse(xml);
	
		// access message data from the java model
		System.out.println("Message Identification: " + camt48.getModfyRsvatn().getMsgHdr().getMsgId());
		System.out.println("Amount: " + camt48.getModfyRsvatn().getNewRsvatnValSet().getAmt().getAmtWthtCcy());
    }

}
