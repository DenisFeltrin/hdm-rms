package de.hdm.rms.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

public interface ReservationServiceAsync {
	
	void insertUser(User u, AsyncCallback<Void> callback);

	void insertRoom(Room r, AsyncCallback<Void> asyncCallback);

	void insertReservation(Reservation re, AsyncCallback<Void> asyncCallback);

	 void insertInvitation(Invitation i, AsyncCallback<Void> asyncCallback);

	void OneUserById(int userId, AsyncCallback<User> asyncCallback);

	void deleteUserById(int userId, AsyncCallback<Void> asyncCallback);

	void updateUserById(User u, AsyncCallback<Void> asyncCallback);

	void OneRoomById(int roomId, AsyncCallback<Room> asyncCallback);

	void updateRoomById(Room r, AsyncCallback<Void> asyncCallback);

	void deleteRoomById(int roomId, AsyncCallback<Void> asyncCallback);
	
}