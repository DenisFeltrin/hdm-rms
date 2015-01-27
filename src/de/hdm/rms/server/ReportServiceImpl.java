package de.hdm.rms.server;

import java.util.ArrayList;
import de.hdm.rms.shared.ReportService;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {
	
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;

	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
	}

	ReportServiceImpl(){
		
	}
	
	public ArrayList<User> getAllUsers() throws IllegalArgumentException {
 		ArrayList<User> listit = uMapper.loadAllUsers();
		if (!listit.isEmpty()) {
			return listit;
		} else {
			return null;
		}
	}
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
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
		init();

		Room r = new Room();

		return r;
	}

}