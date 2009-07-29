package org.superfeedr.extension.subscription;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.packet.PacketExtension;

public class SubUnSubExtension implements PacketExtension{
	
	private List<SubUnSubFeedExtension> feedURLs;
	
	public static final boolean TYPE_SUBSCRIPTION = true;
	public static final boolean TYPE_UNSUBSCRIPTION = false;
	
	
	public static final String ELEMENT_NAME = "pubsub";
	public static final String NAMESPACE = "http://jabber.org/protocol/pubsub";
	
	public SubUnSubExtension(List<URL> feedsURLs, String jid, boolean subscription){
		if (feedsURLs == null || jid == null){
			throw new IllegalArgumentException("need URL to subscribe/unsubscribe to");
		}
		feedURLs = new ArrayList<SubUnSubFeedExtension>(feedsURLs.size());
			for (URL url : feedsURLs) {
				if (subscription){
					this.feedURLs.add(new SubscriptionFeedExtension(jid, url));
				}else{
					this.feedURLs.add(new UnSubscriptionFeedExtension(jid, url));
				}
			}
	}

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
		for (SubUnSubFeedExtension feed : feedURLs) {
			builder.append(feed.toXML());
		}
		builder.append("</");
		builder.append(getElementName());
		builder.append(">");
		return builder.toString();
	}
	
	public Iterator<SubUnSubFeedExtension> getFeedURLs(){
		return feedURLs == null ? null : feedURLs.iterator();
	}
}
