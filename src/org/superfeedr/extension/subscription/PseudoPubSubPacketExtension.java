package org.superfeedr.extension.subscription;

import org.jivesoftware.smack.packet.PacketExtension;

public abstract class PseudoPubSubPacketExtension implements PacketExtension{
	
	public static final String ELEMENT_NAME = "pubsub";
	public static final String NAMESPACE = "http://jabber.org/protocol/pubsub";
	
	public String getElementName() {
		return ELEMENT_NAME;
	}

	public String getNamespace() {
		return NAMESPACE;
	}

	public String toXML() {
		StringBuilder builder = new StringBuilder("<");
		builder.append(getElementName());
		builder.append(" xmlns=\"");
		builder.append(getNamespace());
		builder.append("\">\n");
		builder.append(toXMLInternal());
		builder.append("</");
		builder.append(getElementName());
		builder.append(">");
		return builder.toString();
	}
	
	protected abstract String toXMLInternal();
}
