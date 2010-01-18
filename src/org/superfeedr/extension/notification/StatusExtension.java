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
package org.superfeedr.extension.notification;

import java.util.Date;

public class StatusExtension extends DefaultSuperfeerExtension {
	
	private String feedURL;
	
	private HttpExtension httpExtension;
	
	private Date nextFetch;

    private String title;
	
	public StatusExtension() {
	}
	
	public StatusExtension(String title, String feedURL, Date nextFetch, HttpExtension extension){
        this.title = title;
		this.feedURL = feedURL;
		this.httpExtension = extension;
		this.nextFetch = nextFetch;
	}
	
	public String getFeedURL(){
		return feedURL;
	}

	/**
	 * @return the httpExtension
	 */
	public HttpExtension getHttpExtension() {
		return httpExtension;
	}
	
	public Date getNextFetch(){
		return nextFetch;
	}

    public String getTitle() {
        return title;
    }
}
