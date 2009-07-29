package org.superfeedr.extension.subscription;

import java.net.URL;

public class SubscriptionFeedExtension extends SubUnSubFeedExtension {

	public static final String ELEMENT_NAME = "subscribe";

	public SubscriptionFeedExtension(final String jid, final URL feedURL) {
		super(jid, feedURL);
	}

	public String getElementName() {
		return ELEMENT_NAME;
	}

}
