package de.hdm.rms.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;

/**
* Die Klasse ReservationMapper regelt alle Datenbank-Zugriffe, welche auf Objekte des Typs Reservation in der Datenbank
* zugreifen. Hierfür wird beim Durchführen der einzelnen Methoden mithilfe der Klasse DatebaseConnection jeweils
* eine Verbindung mit der Datenbank aufgebaut.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
public class ReservationMapper {
	
	private static ReservationMapper reservationMapper = null;
	
	protected ReservationMapper() {
	}
	
	public static ReservationMapper reservationMapper() {

		if (reservationMapper == null) {
			reservationMapper = new ReservationMapper();		
		}
		return reservationMapper;
	}
	
	/**
	 * Methode, um eine neue Reservierung in der Datenbank zu stellen.
	 * 
	 * @param re
	 */
	public  void insertReservation(Reservation re) {
		Connection con = DatebaseConnection.connection();
		
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Reservation (`HostId`, `Topic`, `RoomId`, `EndTime`, `StartTime`) VALUES ("
					+ "'"
					+ re.getHostId()
					+ "','"
					+ re.getTopic()
					+ "','"
					+ re.getRoomId()
					+ "','"
					+ re.getEndTime() 
					+ "','"
					+ re.getStartTime()
					+ "') ;";
			state.executeUpdate(sqlquery);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methode, um alle Reservierungen in einem bestimmten Zeitfenster in einem bestimmten Raum zu erhalten.
	 * 
	 * @param temp_room_id
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList vom Typ Reservation
	 */
	public ArrayList<Reservation> loadReservationsByRoomIdWithDate(int temp_room_id, String fromDate, String toDate) {
		
		Connection con = DatebaseConnection.connection();
		
		ArrayList<Reservation> resultList = new ArrayList<>();
		
		try {
			Statement state = con.createStatement();

			//Korrektes SQL Querry ohne Datum
			//ResultSet result = state.executeQuery("SELECT * FROM `Reservation` WHERE `RoomId`=" + temp_room_id + ";");
			
			ResultSet result = state.executeQuery("SELECT * FROM `Reservation` WHERE `RoomId`=' " + temp_room_id + "' AND `StartTime` >=' " + fromDate + "' AND `EndTime` <=' " + toDate + "'; ");
			//ResultSet result = state.executeQuery("SELECT * FROM `Reservation` WHERE `HostId`=" + temp_room_id + " AND `StartTime` >=" + "'2015-01-24 15:11:39'" + " AND `EndTime` <=" + "'2015-01-24 17:11:39'" + ";");
			
			while (result.next() ) {
				
				Reservation r = new Reservation(); 
				r.setId(result.getInt("Id"));
				r.setHostId(result.getInt("HostId"));
				r.setRoomId(result.getInt("RoomId"));
				//r.setLength(result.getInt("Lenght"));
				r.setStartTime(result.getString("StartTime"));
				r.setEndTime(result.getString("EndTime"));
				r.setTopic(result.getString("Topic"));
				
				resultList.add(r); // Add person-object to Arraylist
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}

	/**
	 * Methode, um alle Reservierungen in einem bestimmten Zeitfenster von einem bestimmten User zu erhalten.
	 * 
	 * @param temp_host_id
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList vom Typ Reservation
	 */
	public ArrayList<Reservation> loadReservationsByHostIdWithDate(int temp_host_id, String fromDate, String toDate) {
		
		Connection con = DatebaseConnection.connection();
		
		ArrayList<Reservation> resultList = new ArrayList<>();
		
		try {
			Statement state = con.createStatement();
			
			//Korrektes SQL Querry ohne Datum
			//ResultSet result = state.executeQuery("SELECT * FROM `Reservation` WHERE `RoomId`=" + temp_room_id + ";");
			
			ResultSet result = state.executeQuery("SELECT * FROM `Reservation` WHERE `HostId`='" + temp_host_id + "'AND `StartTime` >='" + fromDate + "'AND `EndTime` <='" + toDate + "';");
//			ResultSet result = state.executeQuery("SELECT * FROM `Reservation` WHERE `HostId`=" + temp_host_id + " AND `StartTime` >=" + "'2015-01-24 15:11:39'" + " AND `EndTime` <=" + "'2015-01-24 17:11:39'" + ";");
			
			while (result.next() ) {
			 
				Reservation r = new Reservation(); 
				r.setId(result.getInt("Id"));
				r.setHostId(result.getInt("HostId"));
				r.setRoomId(result.getInt("RoomId"));
				//r.setLength(result.getInt("Lenght"));
				r.setStartTime(result.getString("StartTime"));
				r.setEndTime(result.getString("EndTime"));
				r.setTopic(result.getString("Topic"));
				
				resultList.add(r); // Add person-object to Arraylist
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/**
	 * 
	 * 
	 * @param re
	 * @return
	 */
	public Reservation selectReservationId(Reservation re) {
		Connection con = DatebaseConnection.connection();
		Reservation r = new Reservation(); 
		try {
			
			Statement state = con.createStatement();
			
			ResultSet result = state.executeQuery("SELECT MAX(Id) FROM Reservation  ;");
			while (result.next() ) {
				r.setId(result.getInt("MAX(Id)"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
		
	}
	
	/**
	 * Methode, um eine Reservierung mithilfe ihrer Reservierungs-Id in der Datenbank zu löschen.
	 * 
	 * @param reservationId
	 */
	public void deleteReservationById(int reservationId) {
		 
		Connection con = DatebaseConnection.connection();
		
		try {
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Reservation " + "WHERE id=" + reservationId +";");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methode, um eine vorhanden Reservierung mithilfe deren Id in der Datenbank zu bearbeiten.
	 * 
	 * @param r
	 * @return null
	 */
	public Boolean updateReservation(Reservation r) {
		Connection con = DatebaseConnection.connection();
		
		try{
			Statement state = con.createStatement();
			
			state.executeUpdate( "UPDATE  `Reservation`" + "SET `Topic`=\"" + "NeuThema" + "\", " +  "`Length`=\"" + "00:45:00" + "\", " +  "`StartTime`=\"" + "2014-02-02 01:01:01" + "\", " + "WHERE Id="  +"1" );
			
			return true;
			
		} catch (Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}

	/**
	 * Methode, um alle Reservierungen eines Users mithilfe dessen User-Id zu erhalten.
	 * 
	 * @param temp_user_id
	 * @return ArrayList vom Typ Reservation
	 */
	public ArrayList<Reservation> loadReservationsByID(int temp_user_id) {
		
		Connection con = DatebaseConnection.connection();
		
		ArrayList<Reservation> resultList = new ArrayList<>();
		
		try {
			Statement state = con.createStatement();
			//ResultSet result = state.executeQuery("SELECT * FROM Reservation " + "WHERE Id=" + temp_user_id +";");
			ResultSet result = state.executeQuery("SELECT * FROM Reservation  ;");
			while (result.next() ) {
				
				Reservation r = new Reservation(); 
				r.setId(result.getInt("Id"));
//				r.setRoomId(result.getInt("RoomId"));
//				r.setLength(result.getInt("Lenght"));
//				r.setStartTime(result.getInt("StartTime"));
//				r.setTopic(result.getString("Topic"));
				
				resultList.add(r); // Add person-object to Arraylist
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/**
	 * Methode, um alle Reservierungen aus der Datenbank zu erhalten.
	 * 
	 * @return ArrayList vom Typ Reservation
	 */
	public ArrayList<Reservation> loadAllReservations() {
		Connection con = DatebaseConnection.connection();
		
		ArrayList<Reservation> resultList = new ArrayList<>();
		try {
			Statement state = con.createStatement();
			//ResultSet result = state.executeQuery("SELECT * FROM Reservation " + "WHERE Id=" + temp_user_id +";");
			ResultSet result = state.executeQuery("SELECT * FROM Reservation  ;");
			while (result.next() ) {
			 
				Reservation r = new Reservation(); 
				r.setId(result.getInt("Id"));
				r.setRoomId(result.getInt("RoomId"));
//				r.setLength(result.getInt("Length"));
//				r.setEndTime(result.get("EndTime"));
//				r.setStartTime(result.getDate("StartTime"));
				r.setTopic(result.getString("Topic"));
				
				resultList.add(r); // Add person-object to Arraylist
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}

	/**
	 * Methode, um alle Reservierungen als ReservationListObj zu erhalten.
	 * 
	 * @return ArrayList vom Typ ReservationListObj
	 */
	public ArrayList<ReservationListObj> loadAllReservationsAsList() {
		Connection con = DatebaseConnection.connection();
		
		ArrayList<ReservationListObj> resultList = new ArrayList<>();
		try {
			Statement state = con.createStatement();
			
			ResultSet result = state.executeQuery("SELECT  Reservation.Id, Reservation.CreationDate,   EndTime, StartTime, Topic, RoomId, Room.Name, Capacity, EMail, Firstname, Lastname, Nickname FROM Reservation INNER JOIN Room ON Reservation.RoomId = Room.Id  Inner Join User On Reservation.HostId = User.Id;");
			while (result.next() ) {
				
				ReservationListObj r = new ReservationListObj(); 
				r.setId(result.getInt("Id"));
				r.setRoomId(result.getInt("RoomId"));
				r.setEndTime(result.getString("EndTime"));
				r.setStartTime(result.getString("StartTime"));
				r.setTopic(result.getString("Topic"));
				
				r.setFirstname(result.getString("Firstname"));
				r.setLastname(result.getString("Lastname"));
				r.setEMail(result.getString("EMail"));
				r.setNickname(result.getString("Nickname"));
				
				resultList.add(r); // Add person-object to Arraylist
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/**
	 * Methode, um eine Reservierung mithilfe deren Reservierungs-Id zu erhalten.
	 * 
	 * @param reservationId
	 * @return Reservierung vom Typ Reservation
	 */
	public Reservation OneReservationById(int reservationId) {
		
		Connection con = DatebaseConnection.connection();
		Reservation r = new Reservation();
		
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT *  FROM `Reservation` "  + "WHERE `Id`='" + reservationId +"';" );
			
			while (rs.next() ) {
				r.setId(rs.getInt("Id"));
				r.setStartTime(rs.getString("StartTime"));
				r.setEndTime(rs.getString("EndTime"));
				r.setRoomId(rs.getInt("RoomId"));
				r.setHostId(rs.getInt("HostId"));
				r.setTopic(rs.getString("Topic"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * Methode, um alle Reservierungen in einem gewählten Raum zu erhalten.
	 * 
	 * @param roomId
	 * @return ArrayList vom Typ Reservation
	 */
	public ArrayList<Reservation> loadAllReservationByRoomId(int roomId ) {
		Connection con = DatebaseConnection.connection();
		ArrayList<Reservation> resultList = new ArrayList<>();
		try {
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT * FROM `Reservation` WHERE `RoomId`=" + roomId +";");
			while (rs.next() ) {
				Reservation r = new Reservation(); 
				r.setId(rs.getInt("Id"));
				r.setStartTime(rs.getString("StartTime"));
				r.setEndTime(rs.getString("EndTime"));
				r.setRoomId(rs.getInt("RoomId"));
				r.setHostId(rs.getInt("HostId"));
				r.setTopic(rs.getString("Topic"));
				resultList.add(r); // Add person-object to Arraylist
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	/**
	 * Methode, um alle Teilnehmer einer Reservierung zu erhalten. Diese werden per Nickname in einen String geschrieben.
	 * 
	 * @param resId
	 * @return ArrayList vom Typ String
	 */
	public ArrayList<String> userForInvitation1(int resId) {
		
		Connection con = DatebaseConnection.connection();
		ArrayList<String> resultList = new ArrayList<>();
		try {
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT User.Lastname FROM ((Room Inner JOIN Reservation ON Room.Id = Reservation.RoomId) Inner JOIN Invitation ON Reservation.Id = Invitation.ReservationId) Inner JOIN User ON Invitation.MemberId = User.Id WHERE Reservation.Id ='" +resId+ "';");
			while (rs.next() ) {
				
				resultList.add(rs.getString("Lastname"));
				System.out.println(rs.getString("Lastname"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
		
	}
	
	/**
	 * Methode, um alle Teilnehmer einer Reservierung zu erhalten. Diese werden per Nickname in einen String geschrieben.
	 * 
	 * @param resId
	 * @return ArrayList vom Typ String
	 */
	public ArrayList<String> userForInvitation2(int resId) {
		
		Connection con = DatebaseConnection.connection();
		ArrayList<String> resultList = new ArrayList<>();
		try {
			Statement state = con.createStatement();
			
			ResultSet rs = state.executeQuery("SELECT User.Lastname FROM ((Room Inner JOIN Reservation ON Room.Id = Reservation.RoomId) Inner JOIN Invitation ON Reservation.Id = Invitation.ReservationId) Inner JOIN User ON Invitation.MemberId = User.Id WHERE Reservation.Id ='" +resId+ "';");
			while (rs.next() ) {
				
				resultList.add(rs.getString("Lastname"));
				System.out.println(rs.getString("Lastname"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
		
	}
	
}