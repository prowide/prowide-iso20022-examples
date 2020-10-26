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

import com.prowidesoftware.swift.io.parser.MxParser;
import com.prowidesoftware.swift.model.MxId;
import com.prowidesoftware.swift.model.mx.AbstractMX;
import com.prowidesoftware.swift.model.mx.MxCamt04800103;

/**
 * This examples shows how to parse an MX messages when the message type is unknown using the AbstractMX.
 */
public class MxParseUnknownMessageType {

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

		// parse into generic structure
		AbstractMX mx = AbstractMX.parse(xml);
	
		System.out.println("Parsed message type: " + mx.getMxId().id());

		// check the parsed type and cast to specific model
		if ("camt.048.001.03".equals(mx.getMxId().id())) {
			MxCamt04800103 camt = (MxCamt04800103) mx;
			System.out.println("Message id: " + camt.getModfyRsvatn().getMsgHdr().getMsgId());
		}
    }

}
