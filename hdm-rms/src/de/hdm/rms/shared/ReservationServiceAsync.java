package de.hdm.rms.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.rms.shared.bo.User;

public interface ReservationServiceAsync {

	
	void insertUser(User u, AsyncCallback<Void> callback);
	
	
}
