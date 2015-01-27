package de.hdm.rms.server;

import java.util.ArrayList;
import de.hdm.rms.shared.ReportService;
import de.hdm.rms.server.db.ReservationMapper;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {
	
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;
	private ReservationMapper resMapper = null;


	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
		this.resMapper = ReservationMapper.reservationMapper();
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
	public ArrayList<Reservation> getReservationByHostId(int selectedUserId) throws IllegalArgumentException {

    	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
    	 reservationArray = resMapper.loadReservationsByUserId(selectedUserId);
		
		return reservationArray;
	}
	
	@Override
	public ArrayList<Reservation> getReservationByRoomId(int selectedRoomId) throws IllegalArgumentException {

    	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
    	 reservationArray = resMapper.loadReservationsByRoomId(selectedRoomId);
		
		return reservationArray;
	}

}