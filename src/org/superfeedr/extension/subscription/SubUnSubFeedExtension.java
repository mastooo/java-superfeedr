package org.superfeedr.extension.subscription;

import java.net.URL;

import org.jivesoftware.smack.packet.PacketExtension;

public abstract class SubUnSubFeedExtension implements PacketExtension{
	
	private URL feedURL;
	private String jid;
	
	public SubUnSubFeedExtension(String jid, URL feedURL){
		this.jid = jid;
		this.feedURL = feedURL;
	}

	public String getNamespace() {
		return null;
	}

	public String toXML() {
		StringBuilder builder = new StringBuilder("<");
		builder.append(getElementName());
		builder.append(" node=\"");
		builder.append(feedURL.toString());
		builder.append("\" jid=\"");
		builder.append(jid);
		builder.append("\"/>\n");
		return builder.toString();
	}

}
