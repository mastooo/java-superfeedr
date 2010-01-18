package org.superfeedr.extension.subscription;

import java.net.URL;
import java.util.*;


public class SubUnSubExtension extends PseudoPubSubPacketExtension{
	
	private List<SubUnSubFeedExtension> feedURLs;
	
	public static final boolean TYPE_SUBSCRIPTION = true;
	public static final boolean TYPE_UNSUBSCRIPTION = false;
	
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

	
	
	public Collection<SubUnSubFeedExtension> getFeedURLs(){
		return feedURLs == null ? null : Collections.unmodifiableCollection(feedURLs);
	}

	@Override
	protected String toXMLInternal() {
		StringBuilder builder = new StringBuilder();
		for (SubUnSubFeedExtension feed : feedURLs) {
			builder.append(feed.toXML());
		}
		return builder.toString();
	}
}
