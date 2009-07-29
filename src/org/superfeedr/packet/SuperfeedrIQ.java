package org.superfeedr.packet;

import org.jivesoftware.smack.packet.IQ;

public class SuperfeedrIQ extends IQ{

	private String xmlChild;
	
	public SuperfeedrIQ(String xmlChild){
		this.xmlChild = xmlChild;
	}
	
	@Override
	public String getChildElementXML() {
		return xmlChild;
	}

}
