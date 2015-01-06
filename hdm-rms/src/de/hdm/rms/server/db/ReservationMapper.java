package de.hdm.rms.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

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

	public void insertReservation(Reservation re) {
		Connection con = DatebaseConnection.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Reservation (HostId, Topic, RoomId, Length, StartTime) VALUES ("
					+ "'"
					+ re.getHostId()
					+ "','"
					+ re.getTopic()
					+ "','"
					+ re.getRoomId()
					+ "','"
					+ re.getLength()
					+ "','"
					+ re.getStartTime()
					+ "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteReservationById(int reservationId) {
		 
		Connection con = DatebaseConnection.connection();
		Reservation r = new Reservation();
		
		try {
			
		     Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Reservation " + "WHERE id=" + 3 +";");	
 
		} catch (Exception e) {
			e.printStackTrace();
 		}
	
 	}
	
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

	public ArrayList<Reservation> loadReservationsByID(int temp_user_id) {
		// TODO Auto-generated method stub
		
		
		Connection con = DatebaseConnection.connection();

			 
			ArrayList<Reservation> resultList = new ArrayList<>();

			try {
				Statement state = con.createStatement();
				ResultSet result = state.executeQuery("SELECT * FROM Reservatio " + "WHERE Id=" + temp_user_id +";");

				while (result.next() ) {
					Reservation r = new Reservation(); 
											
					r.setId(result.getInt("Id"));
					r.setRoomId(result.getInt("RoomId"));
					r.setLength(result.getInt("Lenght"));
					r.setStartTime(result.getInt("StartTime"));
					r.setTopic(result.getString("Topic"));

					
					resultList.add(r); // Add person-object to Arraylist
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return resultList;
		}
		
	 

//	public Reservation OneReservationById(int reservationId) {
//		
//		Connection con = DatebaseConnection.connection();
//		Reservation r = new Reservation();
//
//		try {
//			Statement state = con.createStatement();
//			ResultSet rs = state.executeQuery("SELECT *  FROM `Reservation` "  + "WHERE `Id`= '1' ;" );
// 			
//			   if (rs.next()) {
//			        // Ergebnis-Tupel in Objekt umwandeln
//			       // u.setId(userId);
//			        r.setStartTime(rs.getString("StartTime"));
//			        r.setLength(rs.getString("Length"));
//			        r.setRoom(rs.getString("Room"));
//			        r.setNickname(rs.getString("Nickname"));
//			        r.setTopic(rs.getTopic("Topic"));
//			        
//			        r.setId(rs.getInt("Id"));
//			        
//			        return r;
//			      }
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
//		return null;
//	}
	
	

}