package de.hdm.rms.server;

import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.server.db.InvitationMapper;
import de.hdm.rms.server.db.ReservationMapper;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
import com.google.gwt.user.client.Window;
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
		Boolean status =null;
		
		status = rMapper.updateRoom(r);
	}

	@Override
	public void deleteRoomById(int roomId) {
		
		rMapper.deleteRoomById(roomId);
		
	}

}