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
package org.superfeedr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.superfeedr.extension.SuperfeedrEventExtension;

/**
 * The SuperFeedr Class is the entry point to access the superFeedr XMPP
 * functionnalities. To use this Class, you should already have a superfeedr
 * account.
 * 
 * @author thomas RICARD
 * 
 */
public class Superfeedr implements PacketListener {

	// The pseudo ISO 8601 date formatter that misses the timezone (thanks to
	// java)
	private static DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * This class converts a ISO 8601 date represented as a string to a java
	 * Date Object
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertDate(final String date) {
		try {
			return m_ISO8601Local.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	// The current connection to the XMPP server
	private XMPPConnection connection;

	// The JID used by this superfeedR instance
	private String jid;

	// The Listeners of event on this superfeedr instance
	private ArrayList<OnNotificationHandler> onNotificationHandlers = new ArrayList<OnNotificationHandler>();

	// The server this instance is connected to
	private String server;

	/**
	 * Constructor of a superfeedr instance that will connect to the specified
	 * server using the jid and password as credentials
	 * 
	 * @param jid
	 *            the jid you want to connect with
	 * @param password
	 *            the password to use
	 * @param server
	 *            the server you connect to
	 */
	public Superfeedr(final String jid, final String password, final String server) throws XMPPException {
		if (jid == null || password == null || server == null)
			throw new IllegalArgumentException("arguments cannot be null");

		this.jid = jid;
		this.server = server;

		// Creating the connection to the XMPP server
		connection = new XMPPConnection(this.server);

		// Now, time to connect and auth
		try {
			connection.connect();
			connection.login(this.jid, password);
			connection.addPacketListener(this, new PacketTypeFilter(Message.class));
		} catch (XMPPException e) {
			if (connection != null && connection.isConnected()) {
				connection.disconnect();
			}
			// Let the caller deal with the exception
			throw e;
		}
	}
	
	/**
	 * This method is used to close the connection to the Superfeer Server.
	 * @throws XMPPException
	 */
	public void close() throws XMPPException{
		if (connection != null && connection.isConnected()) {
			connection.disconnect();
		}
	}

	/**
	 * Add handler as a notification handler on this superfeedr. When a
	 * notification is received, a SuperfeedrEventExtension object is created
	 * and passed to each listener
	 * 
	 * @param handler
	 *            the handler to be added
	 */
	public void addOnNotificationHandler(final OnNotificationHandler handler) {
		if (handler != null && !onNotificationHandlers.contains(handler)) {
			onNotificationHandlers.add(handler);
		}
	}

	/**
	 * 
	 * @param event
	 */
	private void fireOnNotificationHandlers(final SuperfeedrEventExtension event) {
		for (OnNotificationHandler handler : onNotificationHandlers) {
			handler.onNotification(event);
		}
	}

	/**
	 * this is a method responsible of parsing the received message and contruct
	 * the corresponding java object for dispatch
	 */
	public void processPacket(final Packet packet) {
		fireOnNotificationHandlers((SuperfeedrEventExtension) ((Message) packet).getExtension(SuperfeedrEventExtension.NAMESPACE));
	}

	/**
	 * Try to remove the specified handler from the list of notification handler
	 * 
	 * @param handler
	 *            the notification handler to be removed from the list
	 */
	public void removeOnNotificationHandler(final OnNotificationHandler handler) {
		onNotificationHandlers.remove(handler);
	}
}
