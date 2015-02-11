package de.hdm.rms.server;

import java.util.ArrayList;
import de.hdm.rms.shared.ReportService;
import de.hdm.rms.server.db.InvitationMapper;
import de.hdm.rms.server.db.ReservationMapper;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
* Die Klasse RepotsServiceImpl liegt auf dem Server und ist, wie der Name bereits sagt, für die Implementierung von
* Datenbankzugriffen verantwortlich. Sie gibt die eingehenden Anfragen vom Client an die Mapper-Klassen weiter. Antworten
* an den Client gibt sie genau entgegengesetzt zurück.
* <p>Diese Klasse erbt von RemoteServiceServlet und implementiert das synchrone Interface ReportService.
* Durch diese Konstellation wird eine GWT RPC-Kommunikation dieser Klasse ermöglicht.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
@SuppressWarnings("serial")
public class ReportServiceImpl extends RemoteServiceServlet implements ReportService {
	
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;
	private ReservationMapper resMapper = null;
	private InvitationMapper iMapper = null;

	/**Damit die Mapper-Klassen in dieser Klasse verwendet werden können, müssen sie an dieser Stelle initialisiert werden.*/
	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
		this.resMapper = ReservationMapper.reservationMapper();
		this.iMapper = InvitationMapper.invitationMapper();
	}

	/**
	 * Diese Methode ermöglicht die Abfrage aller in der Datenbank vorhandenen User des Systems.
	 * @return ArrayList vom Typ User
	 */
	public ArrayList<UserRms> getAllUsers() throws IllegalArgumentException {
 		ArrayList<UserRms> listit = uMapper.loadAllUsers();
		if (!listit.isEmpty()) {
			return listit;
		} else {
			return null;
		}
	}
	
	/**
	 * Diese Methode ermöglicht die Abfrage einer einelnen User-Id über dessen Nickname.
	 * @return Objekt vom Typ User
	 */
	@Override
	public UserRms getOneUserIdByNickname(String selectedNickname) {
 
		UserRms us = new UserRms();
		us = uMapper.getUserIdByUserNickname(selectedNickname);

		return us;
	}

	/**
	 * Diese Methode ermöglicht die Abfrage aller in der Datenbank vorhandenen Räume des Systems.
	 * @return ArrayList vom Typ Room
	 */
	@Override
	public ArrayList<Room> getAllRooms() throws IllegalArgumentException{
 		ArrayList<Room> listit = rMapper.loadAllRooms();
		if (!listit.isEmpty()) {
			return listit;
		} else {
			return null;
		}
	}

	/**
	 * Diese Methode ermöglicht die Abfrage einer einelnen Raum-Id über dessen Raumname.
	 * @return Objekt vom Typ Room
	 */
	@Override
	public Room getOneRoomIdByName(String selectedRoom) {
 
		Room r = new Room();
		r = rMapper.getOneRoomIdByName(selectedRoom);

		return r;
	}

	/**
	 * Diese Methode ermöglicht die Abfrage aller Reservierungen eines bestimmten Users zwischen zwei Datumsparametern.
	 * @return ArrayList vom Typ Reservation
	 */
	@Override
	public ArrayList<Reservation> getReservationByHostIdWithDate(int selectedUserId, String fromDate, String toDate) {

    	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
    	 reservationArray = resMapper.loadReservationsByHostIdWithDate(selectedUserId, fromDate, toDate);
		
		return reservationArray;
	}

	/**
	 * Diese Methode ermöglicht die Abfrage aller Reservierungen in einem bestimmten Raum zwischen zwei Datumsparametern.
	 * @return ArrayList vom Typ Reservation
	 */
	@Override
	public ArrayList<Reservation> getReservationByRoomIdWithDate(int selectedRoomId, String fromDate, String toDate) {
   	 ArrayList <Reservation> reservationArray = new ArrayList <Reservation>();
   	 reservationArray = resMapper.loadReservationsByRoomIdWithDate(selectedRoomId, fromDate, toDate);
		
		return reservationArray;
	}

	/**
	 * Diese Methode ermöglicht die Abfrage aller Teilnehmer einer Reservierung mithilfe dessen Reservierungs-Id.
	 * @return ArrayList vom Typ Invitation
	 */
	@Override
	public ArrayList<Invitation> getInvitationMemberIdOfReservation(int resID) throws IllegalArgumentException {

		ArrayList <Invitation> userArrayOnlyId = new ArrayList <Invitation>();

		userArrayOnlyId = iMapper.loadUserIdByReservationId(resID);

		return userArrayOnlyId;
	}

	/**
	 * Diese Methode ermöglicht die Abfrage aller User, die an einer Reservierung teilnehmen.
	 * @return ArrayList vom Typ User
	 */
	@Override
	public ArrayList<UserRms> getMembersOfReservation(int memberId) {
		
		ArrayList <UserRms> userArrayComplete = new ArrayList <UserRms>();
		userArrayComplete.add(uMapper.OneUserById(memberId));
		
		return userArrayComplete;
	}

	/**
	 * Diese Methode ermöglicht die Abfrage aller User, die an einer Reservierung in einem bestimmten Raum teilnehmen,
	 * mithilfe der Raum-Id.
	 * @return ArrayList vom Typ String
	 */
	@Override
	public ArrayList<String> loadMembersForReservationReport1(int roomId) throws IllegalArgumentException {
	   	 ArrayList<String> userArray = new ArrayList <String>();
	   	 
	   	 userArray = resMapper.userForInvitation1(roomId);
			
			return userArray;
	}
	
	/**
	 * Diese Methode ermöglicht die Abfrage aller User, die an einer Reservierung in einem bestimmten Raum teilnehmen,
	 * mithilfe der User-Id.
	 * @return ArrayList vom Typ String
	 */
	@Override
	public ArrayList<String> loadMembersForReservationReport2(int userId) throws IllegalArgumentException {
	   	 ArrayList<String> userArray = new ArrayList <String>();
	   	 
	   	 userArray = resMapper.userForInvitation2(userId);
			
			return userArray;
	}

}