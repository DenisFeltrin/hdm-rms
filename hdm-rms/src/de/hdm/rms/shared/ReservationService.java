package de.hdm.rms.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("reservationservice")
public interface ReservationService extends RemoteService {

	void insertUser(User u);

	void insertRoom(Room r);

}
