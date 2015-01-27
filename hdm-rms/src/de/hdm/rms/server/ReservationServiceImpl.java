package de.hdm.rms.server;

import java.util.ArrayList;
import java.util.List;

import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.server.db.InvitationMapper;
import de.hdm.rms.server.db.ReservationMapper;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReservationServiceImpl extends RemoteServiceServlet implements ReservationService {
	
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;
	private InvitationMapper iMapper = null;
	private ReservationMapper resMapper = null;

	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
		this.iMapper = InvitationMapper.invitationMapper();
		this.resMapper = ReservationMapper.reservationMapper();
	}

 	public void insertUser(User u) {
		uMapper.insertUser(u);
	}

 	public void insertRoom(Room r) {
		rMapper.insertRoom(r);
	}

	@Override
	public int insertReservation(Reservation re) {
		resMapper.insertReservation(re);
		int resid = resMapper.selectReservationId(re);	
		return resid;
	}

	@Override
	public void insertInvitation(ArrayList<Invitation> i) {
		iMapper.insertInvitation(i);
	}

	@Override
	public User OneUserById(int userId) {
		User u = new User();
		u = uMapper.OneUserById(userId);
 		return u;
	}

	@Override
	public void deleteUserById(int userId) {
		
		uMapper.deleteUserById(userId);
	}

	@Override
	public void updateUserById(User u) {
		Boolean status =null;
		
		status = uMapper.updateUser(u);
	}

	@Override
	public Room OneRoomById(int roomId) {
		Room r = new Room();
		r = rMapper.OneRoomById(roomId);
 		return r;
	}

	@Override
	public void updateRoomById(Room r) {
		rMapper.updateRoomById(r);
	}

	@Override
	public void deleteRoomById(int roomId) {
		rMapper.deleteRoomById(roomId);
	}

	@Override
	public void deleteInvitationById(int invitationId) {
		
		iMapper.deleteInvitationById(invitationId);
		
	}

	@Override
	public void updateInvitationById(Invitation i) {
		Boolean status =null;
		
		status = iMapper.updateInvitation(i);
		
	}
	
	@Override
	public void deleteReservationById(int reservationId) {
		
		resMapper.deleteReservationById(reservationId);
		
	}

	@Override
	public void updateReservationById(Reservation r) {
		Boolean status =null;
		
		status = resMapper.updateReservation(r);
		
	}

	@Override
	public Reservation OneReservationById(int reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> loadAllReservationsByHostId(int temp_user_id) {

		ArrayList<Reservation> reservationlist = resMapper.loadReservationsByUserId(temp_user_id);
		if (!reservationlist.isEmpty()) {
			return reservationlist;
		} else {
			return null;
		}
		
	}

	@Override
	public ArrayList<Reservation> loadAllReservations() {

		ArrayList<Reservation> reservationlist = resMapper.loadAllReservations();
		if (!reservationlist.isEmpty()) {
			return reservationlist;
		} else {
			return null;
		}
 	}

	@Override
	public ArrayList<ReservationListObj> loadAllReservationsAsList() {
		ArrayList<ReservationListObj> reservationlist = resMapper.loadAllReservationsAsList();
		if (!reservationlist.isEmpty()) {
			return reservationlist;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<InvitationListObj> loadInvitationsById() {
		ArrayList<InvitationListObj> invitationList = iMapper.loadAllInvitationsById();
 			return invitationList;
		 
	}

	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = uMapper.loadAllUsers();
		return userList;
	}

	@Override
	public ArrayList<Room> getAllRooms() {
		ArrayList<Room> roomList = rMapper.loadAllRooms();
		return roomList;
	}

	@Override
	public User loadUserDateByNickname(String selectedNickname) {
		User u = new User();
		u = uMapper.getUserIdByUserNickname(selectedNickname);
		return u;
		
		
 	}

	@Override
	public Room getOneRoomIdByName(String selectedRoom) {

		Room ra = new Room();
		ra = rMapper.getOneRoomIdByName(selectedRoom);

		return ra;

	}

	
 //	@Override
//	public Reservation OneReservationById(int reservationId) {
//		Reservation r = new Reservation();
//		r = resMapper.OneReservationById(reservationId);
// 		return r;
//	}

}