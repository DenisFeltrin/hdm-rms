package de.hdm.rms.server;

import java.util.ArrayList;
import de.hdm.rms.shared.ReportService;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService {
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;

	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
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

		User us = new User();
		us = uMapper.getUserIdByUserNickname(selectedNickname);

		return us;
	}

	@Override
	public ArrayList<Room> getAllRooms() throws IllegalArgumentException{
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
		init();

		Room r = new Room();

		return r;
	}

}