package de.hdm.rms.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.i18n.shared.DateTimeFormat;

import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;

public class ReservationMapper {

	private static ReservationMapper reservationMapper = null;

	protected ReservationMapper() {
	}

	public static ReservationMapper reservationMapperv() {

		if (reservationMapper == null) {
			reservationMapper = new ReservationMapper();

		}
		return reservationMapper;
	}

	public  void insertReservation(Reservation re) {
		Connection con = DatebaseConnection.connection();
		int resID = 0;
		 
		
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
					System.out.println(""+r.getId());
					resultList.add(r); // Add person-object to Arraylist
				 }

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return resultList;
	}

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
	
//	INSERT INTO `test_delete_plz3`.`Reservation` (`Id`, `HostId`, `Topic`, `RoomId`, `EndTime`, `StartTime`, `CreationDate`) VALUES (NULL, '2', 'tesssssss', '4', '2015-01-06 00:00:00', '2015-01-06 04:00:00', CURRENT_TIMESTAMP);
	
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
	
	public void deleteReservationById(int reservationId) {
		 
		Connection con = DatebaseConnection.connection();
	
		
		try {
			
		     Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Reservation " + "WHERE id=" + reservationId +";");	
 
		} catch (Exception e) {
			e.printStackTrace();
 		}
	
 	}
	
	public Boolean updateReservation(Reservation r) {
		Connection con = DatebaseConnection.connection();

		try{
			Statement state = con.createStatement();
			
			state.executeUpdate("UPDATE Reservation SET Topic='" + r.getTopic() + "', StartTime='" + r.getStartTime() + "', EndTime='" + r.getEndTime() + "' WHERE Id='" +r.getId()+"';");
			
				return true;

		} catch (Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}

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
					//r.setRoomId(result.getInt("RoomId"));
				//	r.setLength(result.getInt("Lenght"));
				//	r.setStartTime(result.getInt("StartTime"));
				//	r.setTopic(result.getString("Topic"));
//
					
					resultList.add(r); // Add person-object to Arraylist
				 }

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return resultList;
		}

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
			//	r.setLength(result.getInt("Length"));
			//	r.setEndTime(result.get("EndTime"));
			//	r.setStartTime(result.getDate("StartTime"));
				r.setTopic(result.getString("Topic"));
				
				
//
				
				resultList.add(r); // Add person-object to Arraylist
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	
	
	
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
				
			//	r.setCapacity(result.getInt("Capacity"));

 				r.setFirstname(result.getString("Firstname"));
				r.setLastname(result.getString("Lastname"));
				r.setEMail(result.getString("EMail"));
				r.setNickname(result.getString("Nickname"));

				
//
				
				resultList.add(r); // Add person-object to Arraylist
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}
		
	 

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
	
	

}