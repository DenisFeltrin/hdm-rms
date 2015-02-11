package de.hdm.rms.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("reservationservice")
public interface ReservationService extends RemoteService {
	
	void insertUser(UserRms u);

	void insertRoom(Room r);

	Reservation insertReservation(Reservation re);

	//void insertInvitation(ArrayList<Invitation> invitationListTemp);
	
	void updateInvitationById(Invitation i);

	UserRms OneUserById(int userId);

	void deleteUserById(int userId);

	void updateUserById(UserRms u);

	Room OneRoomById(int roomId);

	void updateRoomById(Room r);

	void deleteRoomById(int roomId);

	void deleteInvitationById(int invitationId);

	void deleteReservationById(int reservationId);

	void updateReservationById(Reservation r);

	Reservation OneReservationById(int reservationId);

	ArrayList<Reservation> loadAllReservationsByHostId(int temp_user_id);

	ArrayList<Reservation> loadAllReservations();

	ArrayList<ReservationListObj> loadAllReservationsAsList(int temp_user_id);

	ArrayList<InvitationListObj> loadInvitationsById();

	ArrayList<UserRms> getAllUsers();

	ArrayList<Room> getAllRooms();
	
	Room getOneRoomIdByName(String selectedRoom);

	UserRms loadUserDateByNickname(String selectedNickname);

//	String getUserEmail(String token, AsyncCallback<String> callback);
	String greetServer(String name) throws IllegalArgumentException;

//	LoginInfo login(String hostPageBaseURL);
//
//	LoginInfo loginDetails(String token);
	

	LoginInfo login(String requestUri);	
	
	LoginInfo loginDetails(String token);
	String getUserEmail(String token);

	UserRms getUserDataFromEmail(String eMailAdress);

	Invitation setInviteStatus(int invStatus, int resDd, int userId);

	int loadAcceptionStatus(int resId, int memId);

	ArrayList<InvitationListObj> loadAllInvitationData(int temp_user_id);

	void insertInvitation(ArrayList<Invitation> invitationListTemp);

	void sendInvitation(ArrayList<UserRms> userInvitationArray,
			Reservation res, String roomName, int userId);

}