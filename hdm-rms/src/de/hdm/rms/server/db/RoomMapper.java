package de.hdm.rms.server.db;

import de.hdm.rms.server.db.DatebaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;

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
					+ "'" + r.getCapaticity() + "','" + r.getName() + "') ;";
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
			ResultSet rs = state.executeQuery("SELECT * FROM Room WHERE Id= '"
					+ roomId + "';");

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

			stmt.executeUpdate("DELETE FROM Room " + "WHERE id=" + roomId + ";");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateRoomById(Room r) {

		Connection con = DatebaseConnection.connection();

		try {

			Statement state = con.createStatement();

			state.executeUpdate("UPDATE `Room` SET `Name`= '" + r.getName()
					+ "', " + "`Capacity`= '" + r.getCapaticity() + "' "
					+ "WHERE `Id` = '" + r.getId() + "';");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public ArrayList<Room> loadAllRooms() {
		Connection con = DatebaseConnection.connection();
		ArrayList<Room> resultList = new ArrayList<>();

		try {
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM Room");

			while (result.next()) {
				Room r = new Room();
				r.setId(result.getInt("Id"));
				r.setName(result.getString("Name"));
				// r.setCapaticity(result.getString("Capaticty"));

				resultList.add(r); // Add person-object to Arraylist
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public Room getOneRoomIdByName(String selectedRoom) {

		Connection con = DatebaseConnection.connection();

		Room r = new Room();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Room WHERE name='"
					+ selectedRoom + "';");

			while (rs.next()) {

				r.setId(rs.getInt("Id"));
				r.setName(rs.getString("Name"));
				r.setCapaticity(rs.getString("Capacity"));

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return r;

	}

}