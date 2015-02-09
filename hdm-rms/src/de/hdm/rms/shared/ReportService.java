package de.hdm.rms.shared;

import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportservice")
public interface ReportService extends RemoteService {

	public void init() throws IllegalArgumentException;
	
	ArrayList<UserRms> getAllUsers() throws IllegalArgumentException;

	UserRms getOneUserIdByNickname(String selectedNickname);

	ArrayList<Room> getAllRooms() throws IllegalArgumentException;

	Room getOneRoomIdByName(String selectedRoom);

	ArrayList<Reservation> getReservationByHostId(int selectedUserId) throws IllegalArgumentException;
	
	ArrayList<Reservation> getReservationByRoomId(int selectedRoomId)throws IllegalArgumentException;

	ArrayList<Reservation> getReservationByRoomIdWithDate(int selectedRoomId, String fromDate, String toDate) throws IllegalArgumentException;

	ArrayList<Reservation> getReservationByHostIdWithDate(int selectedRoomId, String fromDate, String toDate) throws IllegalArgumentException;
	
	ArrayList<UserRms> getMembersOfReservation(int reservationId) throws IllegalArgumentException;

	ArrayList<Invitation> getMemberIdOfInvitation(int resId) throws IllegalArgumentException;

}