package de.hdm.rms.server;

import java.util.ArrayList;
import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.server.db.InvitationMapper;
import de.hdm.rms.server.db.ReservationMapper;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReservationServiceImpl extends RemoteServiceServlet implements
		ReservationService {

	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;
	private InvitationMapper iMapper = null;
	private ReservationMapper resMapper = null;

	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
		this.iMapper = InvitationMapper.invitationMapper();
		this.resMapper = ReservationMapper.reservationMapperv();
	}

	public void insertUser(User u) {
		uMapper.insertUser(u);
	}

	public void insertRoom(Room r) {
		rMapper.insertRoom(r);
	}

	@Override
	public void insertReservation(Reservation re) {
		resMapper.insertReservation(re);
	}

	@Override
	public void insertInvitation(Invitation i) {
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
		Boolean status = null;

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
		Boolean status = null;

		status = iMapper.updateInvitation(i);

	}

	@Override
	public void deleteReservationById(int reservationId) {

		resMapper.deleteReservationById(reservationId);

	}

	@Override
	public void updateReservationById(Reservation r) {
		Boolean status = null;

		status = resMapper.updateReservation(r);

	}

	@Override
	public Reservation OneReservationById(int reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> loadReservationsByID(int temp_user_id) {

		ArrayList<Reservation> reservationlist = resMapper
				.loadReservationsByID(temp_user_id);
		if (!reservationlist.isEmpty()) {
			return reservationlist;
		} else {
			return null;
		}

	}

	@Override
	public ArrayList<Room> getAllRooms() throws IllegalArgumentException {
		init();
		ArrayList<Room> listit = rMapper.getAllRooms();
		if (!listit.isEmpty()) {
			return listit;
		} else {
			return null;
		}
	}

	@Override
	public Room getOneRoomIdByName(String selectedRoom) {

		Room ra = new Room();
		ra = rMapper.getOneRoomIdByName(selectedRoom);

		return ra;
	}

	// @Override
	// public Reservation OneReservationById(int reservationId) {
	// Reservation r = new Reservation();
	// r = resMapper.OneReservationById(reservationId);
	// return r;
	// }

}