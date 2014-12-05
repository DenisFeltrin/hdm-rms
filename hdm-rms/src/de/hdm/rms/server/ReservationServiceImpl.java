package de.hdm.rms.server;

import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;




//import com.google.gwt.sample.stockwatcher.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReservationServiceImpl extends RemoteServiceServlet implements
		ReservationService {
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;

	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();

	}

	@Override
	public void insertUser(User u) {
		uMapper.insertUser(u);

	}

	@Override
	public void insertRoom(Room r) {
		rMapper.insertRoom(r);
		
	}

}
