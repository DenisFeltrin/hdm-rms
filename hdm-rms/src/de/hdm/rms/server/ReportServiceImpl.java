package de.hdm.rms.server;

import java.sql.Date;
import java.util.ArrayList;

import de.hdm.rms.shared.ReportService;
import de.hdm.rms.server.db.InvitationMapper;
import de.hdm.rms.server.db.ReservationMapper;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {
	
	//public static final int DEFAULT_REPORT_ID = 10000;
	
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;
	private ReservationMapper resMapper = null;
	private InvitationMapper iMapper = null;

	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
		this.resMapper = ReservationMapper.reservationMapper();
		this.iMapper = InvitationMapper.invitationMapper();
		
	}

	public ArrayList<User> getAllUsers() throws IllegalArgumentException {
 		ArrayList<User> listit = uMapper.loadAllUsers();
		if (!listit.isEmpty()) {
			return listit;
		} else {
			return null;
		}
	}

	@Override
	public User getOneUserIdByNickname(String selectedNickname) {
 
		User us = new User();
		us = uMapper.getUserIdByUserNickname(selectedNickname);

		return us;
	}

	@Override
	public ArrayList<Room> getAllRooms() throws IllegalArgumentException{
 		ArrayList<Room> listit = rMapper.loadAllRooms();
		if (!listit.isEmpty()) {
			return listit;
		} else {
			return null;
		}
	}

	@Override
	public Room getOneRoomIdByName(String selectedRoom) {
 
		Room r = new Room();
		r = rMapper.getOneRoomIdByName(selectedRoom);

		return r;
	}

	@Override
	public ArrayList<Reservation> getReservationByHostIdWithDate(int selectedUserId, String fromDate, String toDate) {

    	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
    	 reservationArray = resMapper.loadReservationsByHostIdWithDate(selectedUserId, fromDate, toDate);
		
		return reservationArray;
	}
	
//	@Override
//	public ArrayList<Reservation> getReservationByRoomId(int selectedRoomId) throws IllegalArgumentException {
//
//    	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
//    	 reservationArray = resMapper.loadReservationsByRoomId(selectedRoomId);
//		
//		return reservationArray;
//	}
	
//	@Override
//	public ArrayList<Reservation> getReservationByRoomIdWithDate(int selectedRoomId, Date fromDate, Date toDate) throws IllegalArgumentException {
//
//    	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
//    	 reservationArray = resMapper.loadReservationsByRoomId(selectedRoomId);
//		
//		return reservationArray;
//	}

	@Override
	public ArrayList<Reservation> getReservationByRoomIdWithDate(int selectedRoomId, String fromDate, String toDate) {
   	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
   	 reservationArray = resMapper.loadReservationsByRoomIdWithDate(selectedRoomId, fromDate, toDate);
		
		return reservationArray;
	}

	@Override
	public ArrayList<Invitation> getInvitationMemberIdOfReservation(int resID) throws IllegalArgumentException {

		 ArrayList <Invitation> userArrayOnlyId = new ArrayList <Invitation>();

	//	ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
	//	reservationArray = resMapper.loadAllReservationByRoomId(resID);
		
//		ArrayList <User> userArrayComplete = new ArrayList <User>();
		
 
 			 userArrayOnlyId = iMapper.loadUserIdByReservationId(resID);
 			//int tempUserId = 2;
// 			userArrayComplete.add(uMapper.OneUserById(tempUserId)); 
 			  

		return userArrayOnlyId;
	}

	@Override
	public ArrayList<Reservation> getReservationByHostId(int selectedUserId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> getReservationByRoomId(int selectedRoomId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> getMembersOfReservation(int memberId) {
		
		ArrayList <User> userArrayComplete = new ArrayList <User>();
		userArrayComplete.add(uMapper.OneUserById(memberId));
		
		return userArrayComplete;
	}

	@Override
	public ArrayList<String> loadMembersForReservation(int roomId) throws IllegalArgumentException {
	   	 ArrayList<String> userArray = new ArrayList <String>();
	   	 
	   	 userArray = resMapper.userForInvitation(roomId);
	   	// System.out.println(userArray.get(0).toString());
			
			return userArray;
	}

}