package org.superfeedr.extension.subscription;

import org.jivesoftware.smack.packet.PacketExtension;

public class SubscriptionExtension implements PacketExtension{
	
	private static final String ELEMENT_NAME = "subscription";
	
	private String node;
	
	private String subscription;
	
	private String jid;
	
	public SubscriptionExtension(String node, String subscription, String jid){
		this.node = node;
		this.subscription = subscription;
		this.jid = jid;
	}

	/**
	 * @return the node
	 */
	public String getNode() {
		return node;
	}

	/**
	 * @return the subscription
	 */
	public String getSubscription() {
		return subscription;
	}

	/**
	 * @return the jid
	 */
	public String getJid() {
		return jid;
	}

	public String getElementName() {
		return ELEMENT_NAME;
	}

	public String getNamespace() {
		return null;
	}

	public String toXML() {
		StringBuilder builder = new StringBuilder("<");
		builder.append(ELEMENT_NAME);
		builder.append(" node=\"");
		builder.append(node);
		builder.append("\" subscription=\"");
		builder.append(subscription);
		builder.append("\" jid=\"");
		builder.append(jid);
		builder.append("\" />");
		return builder.toString();
	}

}
