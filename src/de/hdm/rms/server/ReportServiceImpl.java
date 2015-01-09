package de.hdm.rms.server;

import java.util.ArrayList;
import java.util.List;

import de.hdm.rms.shared.ReportService;
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
public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService {
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

	public ArrayList<User> getAllUsers() throws IllegalArgumentException {
		init();
		ArrayList<User> listit = uMapper.getAllUsers();
		if (!listit.isEmpty()) {
			return listit;
		} else {
			return null;
		}
	}

	@Override
	public User getOneUserIdByNickname(String selectedNickname) {
		init();

		User u = new User();

		return u;
	}
	
	

}