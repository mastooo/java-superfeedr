package org.superfeedr.extension.subscription;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SubscriptionListExtension extends PseudoPubSubPacketExtension{

	private List<SubscriptionExtension> subscriptions;
	
	private String jid;
	
	private long page;

	public SubscriptionListExtension(List<SubscriptionExtension> subscriptions, String jid, long page) {
		this.subscriptions = subscriptions;
		this.jid = jid;
		this.page = page;
	}

	@Override
	protected String toXMLInternal() {
		StringBuilder builder = new StringBuilder("<subscriptions ");
		if (jid != null){
			builder.append("jid=\"");
			builder.append(jid);
			builder.append("\" ");
		}
		builder.append("superfeedr:page=\"");
		builder.append(page);
		builder.append("\"");
		if (subscriptions != null && !subscriptions.isEmpty()){
			builder.append(">\n");
			for (SubscriptionExtension subscription : subscriptions) {
				builder.append(subscription);
			}
			builder.append("</subscriptions>");
		}else{
			builder.append("/>");
		}
		return builder.toString();
	}

	/**
	 * @return the subscriptions
	 */
	public Collection<SubscriptionExtension> getSubscriptions() {
		return subscriptions == null ? null : Collections.unmodifiableCollection(subscriptions);
	}

	/**
	 * @return the jid
	 */
	public String getJid() {
		return jid;
	}

	/**
	 * @return the page
	 */
	public long getPage() {
		return page;
	}

}
