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

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.superfeedr.extension.notification.HttpExtension;
import org.xmlpull.v1.XmlPullParser;

public class HttpProvider implements PacketExtensionProvider{

	public PacketExtension parseExtension(XmlPullParser parser) throws Exception {
		
		String extensionName = parser.getName();
		String code = parser.getAttributeValue(null, "code");
		String infos = null;
		
		int currentTag = parser.next();
		while (!extensionName.equals(parser.getName()) && currentTag != XmlPullParser.END_TAG){
			if (currentTag == XmlPullParser.TEXT){
				infos = parser.getText();
			}
			
			currentTag = parser.next();
		}
		
		return new HttpExtension(code, infos);
	}

}
