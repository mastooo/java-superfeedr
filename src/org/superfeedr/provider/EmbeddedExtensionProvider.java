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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.xmlpull.v1.XmlPullParser;


abstract public class EmbeddedExtensionProvider implements PacketExtensionProvider
{

	final public PacketExtension parseExtension(XmlPullParser parser) throws Exception
	{
        String namespace = parser.getNamespace();
        String name = parser.getName();
        Map<String, String> attMap = new HashMap<String, String>();
        
        for(int i=0; i<parser.getAttributeCount(); i++)
        {
        	attMap.put(parser.getAttributeName(i), parser.getAttributeValue(i));
        }
        List<PacketExtension> extensions = new ArrayList<PacketExtension>();
        
        do
        {
            int tag = parser.next();

            if (tag == XmlPullParser.START_TAG){
            	String extensionName = parser.getName();
            	String extensionNamespace = parser.getNamespace();
            	extensions.add(PacketParserUtils.parsePacketExtension(extensionName, extensionNamespace, parser));
            }
            	
        } while (!name.equals(parser.getName()));

		return createReturnExtension(name, namespace, attMap, extensions);
	}

	abstract protected PacketExtension createReturnExtension(String currentElement, String currentNamespace, Map<String, String> attributeMap, List<? extends PacketExtension> content);
}
