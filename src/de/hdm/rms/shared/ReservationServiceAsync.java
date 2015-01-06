package de.hdm.rms.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
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

	void deleteInvitationById(int invitationId, AsyncCallback<Void> callback);

	void updateInvitationById(Invitation i, AsyncCallback<Void> callback);

	void deleteReservationById(int reservationId, AsyncCallback<Void> callback);

	void updateReservationById(Reservation r, AsyncCallback<Void> callback);

	void OneReservationById(int reservationId,
			AsyncCallback<Reservation> asyncCallback);

	void loadAllReservationsByHostId(int temp_user_id,
			AsyncCallback<ArrayList<Reservation>> asyncCallback);

	void loadAllReservations(AsyncCallback<ArrayList<Reservation>> asyncCallback);

	void loadAllReservationsAsList(
			AsyncCallback<ArrayList<ReservationListObj>> asyncCallback);

	void loadInvitationsById(
			AsyncCallback<ArrayList<InvitationListObj>> asyncCallback);
	
}