package de.hdm.rms.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReservationServiceAsync {

	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	
}
