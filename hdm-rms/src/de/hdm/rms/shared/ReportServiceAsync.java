package de.hdm.rms.shared;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;

/**
* Das Interface ReportServiceAsync wird automatisch erstellt und wurde nicht verändert, daher wird an dieser Stelle
* auf eine ausführliche Dokumentation verzichtet.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
public interface ReportServiceAsync {

	void getAllUsers(AsyncCallback<ArrayList<UserRms>> callback);

	void getOneUserIdByNickname(String selectedNickname, AsyncCallback<UserRms> asyncCallback);

	void getAllRooms(AsyncCallback<ArrayList<Room>> asyncCallback);

	void getOneRoomIdByName(String selectedRoom, AsyncCallback<Room> asyncCallback);

	void getReservationByRoomIdWithDate(int selectedRoomId, String fromDate, String toDate, AsyncCallback<ArrayList<Reservation>> asyncCallback);

	void getReservationByHostIdWithDate(int selectedRoomId, String fromDate, String toDate, AsyncCallback<ArrayList<Reservation>> asyncCallback);
	
	void init(AsyncCallback<Void> callback);

	void getInvitationMemberIdOfReservation(int reservationId, AsyncCallback<ArrayList<Invitation>> callback);

	void getMembersOfReservation(int memberId, AsyncCallback<ArrayList<UserRms>> callback);

	void loadMembersForReservationReport1(int roomId, AsyncCallback<ArrayList<String>> callback);
	
	void loadMembersForReservationReport2(int roomId, AsyncCallback<ArrayList<String>> callback);
	
}