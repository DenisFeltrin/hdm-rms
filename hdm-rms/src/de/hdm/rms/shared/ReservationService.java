package de.hdm.rms.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("reservationservice")
public interface ReservationService extends RemoteService {

	void insertUser(User u);

	void insertRoom(Room r);

	void insertReservation(Reservation re);

	void insertInvitation(Invitation i);

	User OneUserById(int userId);

	void deleteUserById(int userId);

	void updateUserById(User u);

	Room OneRoomById(int roomId);

	void updateRoomById(Room r);

	void deleteRoomById(int roomId);

}