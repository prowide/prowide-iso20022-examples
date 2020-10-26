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

import com.prowidesoftware.swift.io.parser.MxParser;
import com.prowidesoftware.swift.model.MxId;
import com.prowidesoftware.swift.model.mx.AbstractMX;

/**
 * This examples shows how to parse an MX messages when the message type is unknown.
 *
 * <p>The generic AbstractMX is useful as parameter for other modules, such as validation or translations. You can also
 * check the message type and cast the message to a specific instance if required.
 *
 * @since 7.6
 */
public class MxParseExample2 {

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

		// detect message
		MxId id = new MxParser(xml).detectMessage();

		// parse into generic structure
		AbstractMX mx = AbstractMX.parse(xml, id);
	
		System.out.println("Namespace: " + mx.getNamespace());
    }

}
