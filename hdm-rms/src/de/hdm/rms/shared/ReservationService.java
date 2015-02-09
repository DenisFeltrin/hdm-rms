package de.hdm.rms.shared;

import java.util.ArrayList;
//import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("reservationservice")
public interface ReservationService extends RemoteService {
	
	void init() throws IllegalArgumentException;

	void insertUser(User u) throws IllegalArgumentException;

	void insertRoom(Room r) throws IllegalArgumentException;

	Reservation insertReservation(Reservation re) throws IllegalArgumentException;

	//void insertInvitation(String[] invitationListTemp) throws IllegalArgumentException;
	
	void updateInvitationById(Invitation i) throws IllegalArgumentException;

	User OneUserById(int userId) throws IllegalArgumentException;

	void deleteUserById(int userId) throws IllegalArgumentException;

	void updateUserById(User u) throws IllegalArgumentException;

	Room OneRoomById(int roomId) throws IllegalArgumentException;

	void updateRoomById(Room r) throws IllegalArgumentException;

	void deleteRoomById(int roomId) throws IllegalArgumentException;

	void deleteInvitationById(int invitationId) throws IllegalArgumentException;

	void deleteReservationById(int reservationId) throws IllegalArgumentException;

	void updateReservationById(Reservation r) throws IllegalArgumentException;

	Reservation OneReservationById(int reservationId) throws IllegalArgumentException;

	ArrayList<Reservation> loadAllReservationsByHostId(int temp_user_id) throws IllegalArgumentException;

	ArrayList<Reservation> loadAllReservations() throws IllegalArgumentException;

	ArrayList<ReservationListObj> loadAllReservationsAsList() throws IllegalArgumentException;

	ArrayList<InvitationListObj> loadInvitationsById() throws IllegalArgumentException;

	ArrayList<User> getAllUsers() throws IllegalArgumentException;

	ArrayList<Room> getAllRooms() throws IllegalArgumentException;
	
	Room getOneRoomIdByName(String selectedRoom) throws IllegalArgumentException;

	User loadUserDateByNickname(String selectedNickname) throws IllegalArgumentException;
	
	//String getUserEmail(String token, AsyncCallback<String> callback);
	String greetServer(String name) throws IllegalArgumentException;
	
	String getUserEmail(String token);

	LoginInfo login(String requestUri);

	LoginInfo loginDetails(String token);

}