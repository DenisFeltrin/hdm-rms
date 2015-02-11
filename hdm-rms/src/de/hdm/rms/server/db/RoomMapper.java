package de.hdm.rms.server.db;

import de.hdm.rms.server.db.DatebaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.hdm.rms.shared.bo.Room;

/**
* Die Klasse RoomMapper regelt alle Datenbank-Zugriffe, welche auf Objekte des Typs Room in der Datenbank
* zugreifen. Hierfür wird beim Durchführen der einzelnen Methoden mithilfe der Klasse DatebaseConnection jeweils
* eine Verbindung mit der Datenbank aufgebaut.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
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

	/**
	 * Methode, um einen neuen Raum in der Datenbank zu erstellen.
	 * 
	 * @param r
	 */
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

	/**
	 * Methode, um einen Raum über dessen Raum-Id aus der Datenbank zu erhalten.
	 * 
	 * @param roomId
	 * @return Raum vom Typ Room
	 */
	public Room OneRoomById(int roomId) {
		Connection con = DatebaseConnection.connection();
		Room r = new Room();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Room WHERE Id= '"
					+ roomId + "';");

			if (rs.next()) {
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

	/**
	 * Methode, um einen Raum mithilfe seiner Raumm-Id in der Datenbank zu löschen.
	 * 
	 * @param roomId
	 */
	public void deleteRoomById(int roomId) {

		Connection con = DatebaseConnection.connection();

		try {

			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM Room " + "WHERE id=" + roomId + ";");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Methode, um einen vorhandenen Raum mithilfe dessen Raum-Id in der Datenbank zu bearbeiten.
	 * 
	 * @param r
	 */
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

	/**
	 * Methode, um alle Räume aus der Datenbank zu erhalten.
	 * 
	 * @return ArrayList vom Typ Room
	 */
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

				resultList.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * Methode, um einen Raum mithilfe seines Raumnamens zu erhalten.
	 * 
	 * @param selectedRoom
	 * @return Raum vom Typ Room
	 */
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