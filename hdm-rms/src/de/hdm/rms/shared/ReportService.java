package de.hdm.rms.shared;

import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;
import java.util.ArrayList;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
* Das synchrone Interface ReportService erbt von der Klasse RemoteService und ermöglicht durch diese Konstellation das Durchführen
* von GWT-RPC´s. Es ist die Schnittstelle  zwischen der Klasse ReportServiceImpl und dem asynchronen Interface ReportServiceAsync.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
@RemoteServiceRelativePath("reportservice")
public interface ReportService extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	ArrayList<UserRms> getAllUsers() throws IllegalArgumentException;
	
	UserRms getOneUserIdByNickname(String selectedNickname);
	
	ArrayList<Room> getAllRooms() throws IllegalArgumentException;
	
	Room getOneRoomIdByName(String selectedRoom);
	
	ArrayList<Reservation> getReservationByRoomIdWithDate(int selectedRoomId, String fromDate, String toDate) throws IllegalArgumentException;
	
	ArrayList<Reservation> getReservationByHostIdWithDate(int selectedRoomId, String fromDate, String toDate) throws IllegalArgumentException;
	
	ArrayList<Invitation> getInvitationMemberIdOfReservation(int reservationId) throws IllegalArgumentException;
	
	ArrayList<UserRms> getMembersOfReservation(int memberId);
	
	ArrayList<String> loadMembersForReservationReport1(int roomId) throws IllegalArgumentException;
	
	ArrayList<String> loadMembersForReservationReport2(int roomId) throws IllegalArgumentException;
	
}