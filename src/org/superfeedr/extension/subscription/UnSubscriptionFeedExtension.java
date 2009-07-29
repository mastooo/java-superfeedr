package org.superfeedr.extension.subscription;

import java.net.URL;

public class UnSubscriptionFeedExtension extends SubUnSubFeedExtension {

	public static final String ELEMENT_NAME = "unsubscribe";

	public UnSubscriptionFeedExtension(final String jid, final URL feedURL) {
		super(jid, feedURL);
	}

	public String getElementName() {
		return ELEMENT_NAME;
	}

}
