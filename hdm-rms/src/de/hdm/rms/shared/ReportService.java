package de.hdm.rms.shared;

import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportservice")
public interface ReportService extends RemoteService {

	ArrayList<User> getAllUsers() throws IllegalArgumentException;

	User getOneUserIdByNickname(String selectedNickname);

	ArrayList<Room> getAllRooms() throws IllegalArgumentException;

	Room getOneRoomIdByName(String selectedRoom);

	ArrayList<Reservation> getReservationByHostId(int selectedUserId) throws IllegalArgumentException;
	
	ArrayList<Reservation> getReservationByRoomId(int selectedRoomId) throws IllegalArgumentException;

}