package org.superfeedr;

public interface OnResponseHandler {
	
	
	public void onSuccess(Object response);
	
	/**
	 * If there was some error during the process, this method is called with some info to deal with
	 * @param infos some info about the error.
	 */
	public void onError(String infos);
}
