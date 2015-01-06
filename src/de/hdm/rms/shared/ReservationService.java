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
	
	void updateInvitationById(Invitation i);

	User OneUserById(int userId);

	void deleteUserById(int userId);

	void updateUserById(User u);

	Room OneRoomById(int roomId);

	void updateRoomById(Room r);

	void deleteRoomById(int roomId);

	void deleteInvitationById(int invitationId);

	void deleteReservationById(int reservationId);

	void updateReservationById(Reservation r);

	Reservation OneReservationById(int reservationId);

	ArrayList<Reservation> loadAllReservationsByHostId(int temp_user_id);

	ArrayList<Reservation> loadAllReservations();

	ArrayList<ReservationListObj> loadAllReservationsAsList();

	ArrayList<InvitationListObj> loadInvitationsById();

}