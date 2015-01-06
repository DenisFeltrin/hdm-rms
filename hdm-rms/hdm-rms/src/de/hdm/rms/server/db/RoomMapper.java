package de.hdm.rms.server.db;

import de.hdm.rms.server.db.DatebaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

public class RoomMapper {

		private static RoomMapper roomMapper = null;

		protected RoomMapper() {
		}

		public static RoomMapper roomMapper() {

			if (roomMapper == null) {
				roomMapper = new RoomMapper();

			}
			return roomMapper;
		}

		/* Methoden */

		public void insertRoom(Room r) {
			Connection con = DatebaseConnection.connection();
			try {
				Statement state = con.createStatement();
				String sqlquery = "INSERT INTO Room (Capacity, Name) VALUES ("
						+ "'"
						+ r.getCapaticity()
						+ "','"
						+ r.getName()
						+ "') ;";
				state.executeUpdate(sqlquery);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Room OneRoomById(int roomId) {
			Connection con = DatebaseConnection.connection();
			Room r = new Room();

			try {
				Statement state = con.createStatement();
				ResultSet rs = state.executeQuery("SELECT *  FROM `Room` "  + "WHERE `Id`= '1' ;" );
	 			
				   if (rs.next()) {
				        // Ergebnis-Tupel in Objekt umwandeln
				       // u.setId(userId);
				        r.setName(rs.getString("Name"));
				        r.setCapaticity(rs.getString("Capacity"));
				        
				        r.setId(rs.getInt("Id"));

				        return r;
				      }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return null;
		}

		public void deleteRoomById(int roomId) {
			 
			Connection con = DatebaseConnection.connection();
			Room r = new Room();
			
			try {
				
			     Statement stmt = con.createStatement();

			      stmt.executeUpdate("DELETE FROM Room " + "WHERE id=" + 1 +";");	
	 
			} catch (Exception e) {
				e.printStackTrace();
	 		}
		
	 	}
		
		public Boolean updateRoom(Room r) {
			Connection con = DatebaseConnection.connection();

			try{
				Statement state = con.createStatement();
				
				state.executeUpdate( "UPDATE  `Room`" + "SET `Capacity`=\"" + "99" + "\", " +  "`Name`=\"" + "TestRaum" + "\" "  + "WHERE Id="  +"1" );
				
					return true;

			} catch (Exception e){
				e.printStackTrace();
				
			}
			
			return null;
		}

	}