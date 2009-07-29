package org.superfeedr;

/**
 * This interface must be implemented by the class that is used as callback for subscribe/unsubscribe call
 * @author thomas
 *
 */
public interface onSubUnsubscriptionHandler {

	/**
	 * If the subscription/unsubscription was a success
	 */
	public void onSubUnsubscription();
	
	/**
	 * If there was some error during the process, this method is called with some info to deal with
	 * @param infos some info about the error.
	 */
	public void onError(String infos);
}
