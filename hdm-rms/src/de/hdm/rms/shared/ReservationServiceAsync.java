package de.hdm.rms.shared;

import java.util.ArrayList;
//import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

public interface ReservationServiceAsync {
	
	void insertUser(User u, AsyncCallback<Void> callback);

	void insertRoom(Room r, AsyncCallback<Void> callback);

	void insertReservation(Reservation re, AsyncCallback<Reservation> callback);

	void OneUserById(int userId, AsyncCallback<User> callback);

	void deleteUserById(int userId, AsyncCallback<Void> callback);

	void updateUserById(User u, AsyncCallback<Void> callback);

	void OneRoomById(int roomId, AsyncCallback<Room> callback);

	void updateRoomById(Room r, AsyncCallback<Void> callback);

	void deleteRoomById(int roomId, AsyncCallback<Void> callback);

	void deleteInvitationById(int invitationId, AsyncCallback<Void> callback);

	void updateInvitationById(Invitation i, AsyncCallback<Void> callback);

	void deleteReservationById(int reservationId, AsyncCallback<Void> callback);

	void updateReservationById(Reservation r, AsyncCallback<Void> callback);

	void OneReservationById(int reservationId, AsyncCallback<Reservation> callback);

	void loadAllReservationsByHostId(int temp_user_id, AsyncCallback<ArrayList<Reservation>> callback);

	void loadAllReservations(AsyncCallback<ArrayList<Reservation>> callback);

	void loadAllReservationsAsList(
			AsyncCallback<ArrayList<ReservationListObj>> callback);

	void loadInvitationsById(
			AsyncCallback<ArrayList<InvitationListObj>> callback);

	void getAllUsers(AsyncCallback<ArrayList<User>> callback);

	void getAllRooms(AsyncCallback<ArrayList<Room>> callback);
	
	void getOneRoomIdByName(String selectedRoom,AsyncCallback<Room> callback);

	void loadUserDateByNickname(String selectedNickname, AsyncCallback<User> callback);

//	void insertInvitation(ArrayList<Invitation> invitationListTemp, AsyncCallback<Void> callback);

	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	
	void getUserEmail(String token, AsyncCallback<String> callback);

//	void loginDetails(String token, AsyncCallback<LoginInfo> callback);

	void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

	//void getUserEmail(String token, AsyncCallback<String> callback, AsyncCallback<String> callback2);

	void init(AsyncCallback<Void> callback);

	void loginDetails(String token, AsyncCallback<LoginInfo> asyncCallback);
 
}