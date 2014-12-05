package de.hdm.rms.server.db;
import de.hdm.rms.server.db.DatebaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.rms.shared.bo.Room;
public class RoomMapper {


	//Test, ob Projekt hochgeladen wurde (05.12.2014, 15 Uhr)
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

		public void insertUser(Room r) {
			Connection con = DatebaseConnection.connection();
			try {
				Statement state = con.createStatement();
				String sqlquery = "INSERT INTO User (Firstname, Lastname, nickname, email) VALUES ("
						+ "'"
						+ u.getFirstName()
						+ "','"
						+ u.getLastName()
						+ "','"
						+ u.getNickName()
						+ "','"
						+ u.getEmailAdress()
						+ "') ;";
				state.executeUpdate(sqlquery);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}


