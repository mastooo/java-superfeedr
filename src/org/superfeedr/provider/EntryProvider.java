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
package org.superfeedr.provider;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.superfeedr.Superfeedr;
import org.superfeedr.extension.notification.AuthorExtension;
import org.superfeedr.extension.notification.EntryExtension;
import org.xmlpull.v1.XmlPullParser;

public class EntryProvider implements PacketExtensionProvider{

	public PacketExtension parseExtension(XmlPullParser parser) throws Exception {
		
		String name = parser.getName();
		
		String id = null;
		String link = null;
		String linkType = null;
		Date published = null;
		String summary = null;
		String title = null;
        String content = null;
        Collection<AuthorExtension> authors = new LinkedList<AuthorExtension>();
		
		int tag = parser.next();
		
		while (!name.equals(parser.getName())){
			if (tag == XmlPullParser.START_TAG){
				if ("title".equals(parser.getName())){
					parser.next();
					title = parser.getText();
				}else if ("summary".equals(parser.getName())){
					parser.next();
					summary = parser.getText();
                }else if ("content".equals(parser.getName())){
                    parser.next();
                    content = parser.getText();
				}else if ("link".equals(parser.getName())){
					link = parser.getAttributeValue(null, "href");
					linkType = parser.getAttributeValue(null, "type");
				}else if ("id".equals(parser.getName())){
					parser.next();
					id = parser.getText();
                }else if ("authors".equals(parser.getName())) {
                    parser.next();
                }else if ("author".equals(parser.getName())) {
                    parser.next();
                    authors.add(new AuthorExtension(parser.getText()));
				}else if ("published".equals(parser.getName())){
					parser.next();
					published = Superfeedr.convertDate(parser.getText());
				}
			}
			tag = parser.next();
		}
		return new EntryExtension(id, link, linkType, published, title, summary, content, authors);
	}

}
