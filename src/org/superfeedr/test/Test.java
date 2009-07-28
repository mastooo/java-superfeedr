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

import java.util.Iterator;

import org.jivesoftware.smack.XMPPConnection;
import org.superfeedr.OnNotificationHandler;
import org.superfeedr.Superfeedr;
import org.superfeedr.extension.ItemExtension;
import org.superfeedr.extension.SuperfeedrEventExtension;

public class Test {
	public static void main(final String[] args) throws Exception {
		XMPPConnection.DEBUG_ENABLED = true;
		Superfeedr feedr = new Superfeedr("thomas", "qwerty", "superfeedr.com");

		feedr.addOnNotificationHandler(new OnNotificationHandler() {
			public void onNotification(final SuperfeedrEventExtension event) {
				System.err.println(event.getStatus().getFeedURL());
				System.err.println(event.getStatus().getHttpExtension().getCode());
				System.err.println(event.getStatus().getHttpExtension().getInfo());
				if (event.getItems().getItemsCount() == 0) {
					System.out.println("No items");
				} else
					for (Iterator<ItemExtension> iterator = event.getItems().getItems(); iterator.hasNext();) {
						ItemExtension item = iterator.next();
						System.out.println(item.getEntry().getTitle());
					}
				System.out.println("\n\n");
			}
		});

		while (true) {
			Thread.sleep(1000);
		}
	}
}
