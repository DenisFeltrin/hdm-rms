package de.hdm.rms.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;

public interface ReportServiceAsync {

	void getAllUsers(AsyncCallback<ArrayList<UserRms>> callback);

	void getOneUserIdByNickname(String selectedNickname, AsyncCallback<UserRms> asyncCallback);

	void getAllRooms(AsyncCallback<ArrayList<Room>> asyncCallback);

	void getOneRoomIdByName(String selectedRoom, AsyncCallback<Room> asyncCallback);

	void getReservationByHostId(int selectedUserId, AsyncCallback<ArrayList<Reservation>> asyncCallback);

	void getReservationByRoomIdWithDate(int selectedRoomId, String fromDate, String toDate, AsyncCallback<ArrayList<Reservation>> Callback);

	void getReservationByHostIdWithDate(int selectedRoomId, String fromDate, String toDate, AsyncCallback<ArrayList<Reservation>> asyncCallback);
	
	void init(AsyncCallback<Void> callback);

	void getReservationByRoomId(int selectedRoomId, AsyncCallback<ArrayList<Reservation>> callback);

	void getMembersOfReservation(int reservationId, AsyncCallback<ArrayList<UserRms>>  Callback);

	void getMemberIdOfInvitation(int resId,
			AsyncCallback<ArrayList<Invitation>>  Callback);

}