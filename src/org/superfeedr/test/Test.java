/**
 * Copyright (c) 2009 julien
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.superfeedr.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPConnection;
import org.superfeedr.OnNotificationHandler;
import org.superfeedr.OnResponseHandler;
import org.superfeedr.Superfeedr;
import org.superfeedr.extension.notification.ItemExtension;
import org.superfeedr.extension.notification.SuperfeedrEventExtension;

public class Test {
	public static void main(final String[] args) throws Exception {
		
		List<URL> urls = new ArrayList<URL>();
		urls.add(new URL("http://search.twitter.com/search.atom?q=jackson"));

		XMPPConnection.DEBUG_ENABLED = true;
		Superfeedr feedr = new Superfeedr("YOUR_LOGIN_HER", "YOUR_PASSWORD_HER", "superfeedr.com");
		
		feedr.getSubscriptionList(new OnResponseHandler() {
			
			public void onSuccess(Object obj) {
				System.err.println(obj);
			}
			
			public void onError(String infos) {
				System.err.println("Error");
			}
		});

		feedr.addOnNotificationHandler(new OnNotificationHandler() {
			public void onNotification(final SuperfeedrEventExtension event) {
				System.err.println(event.getStatus().getFeedURL());
				System.err.println(event.getStatus().getHttpExtension().getCode());
				System.err.println(event.getStatus().getHttpExtension().getInfo());
				if (event.getItems().getItemsCount() == 0) {
					System.out.println("No items");
				} else
					for (ItemExtension item : event.getItems().getItems()) {
						System.out.println(item.getEntry().getTitle());
					}
				System.out.println("\n\n");
			}
		});
		
		feedr.unsubscribe(urls, new OnResponseHandler() {
			
			public void onSuccess(Object response) {
				// TODO Auto-generated method stub
				
			}
			
			public void onError(String infos) {
				// TODO Auto-generated method stub
				
			}
		});

		while (true) {
			Thread.sleep(1000);
		}
	}
}
