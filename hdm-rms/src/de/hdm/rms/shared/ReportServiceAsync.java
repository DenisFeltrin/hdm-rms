package de.hdm.rms.shared;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

public interface ReportServiceAsync {

	void getAllUsers(AsyncCallback<ArrayList<User>> callback);

	void getOneUserIdByNickname(String selectedNickname, AsyncCallback<User> asyncCallback);

	void getAllRooms(AsyncCallback<ArrayList<Room>> asyncCallback);

	void getOneRoomIdByName(String selectedRoom, AsyncCallback<Room> asyncCallback);

	void getReservationByHostId(int selectedUserId, AsyncCallback<ArrayList<Reservation>> asyncCallback);

	void getReservationByRoomId(int selectedRoomId, AsyncCallback<ArrayList<Reservation>> asyncCallback);

}