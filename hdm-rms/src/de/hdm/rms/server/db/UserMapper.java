package de.hdm.rms.server.db;

import de.hdm.rms.server.db.DatebaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.rms.shared.bo.UserRms;

/**
* Die Klasse UserMapper regelt alle Datenbank-Zugriffe, welche auf Objekte des Typs User in der Datenbank
* zugreifen. Hierfür wird beim Durchführen der einzelnen Methoden mithilfe der Klasse DatebaseConnection jeweils
* eine Verbindung mit der Datenbank aufgebaut.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
public class UserMapper {

	private static UserMapper userMapper = null;

	protected UserMapper() {
	}

	public static UserMapper userMapper() {

		if (userMapper == null) {
			userMapper = new UserMapper();

		}
		return userMapper;
	}

	/**
	 * Methode, um einen neuen User in der Datenbank zu stellen.
	 * 
	 * @param u
	 */
	public void insertUser(UserRms u) {
		Connection con = DatebaseConnection.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO User (Firstname, Lastname, EMail, Nickname) VALUES ("
					+ "'"
					+ u.getFirstName()
					+ "','"
					+ u.getLastName()
					+ "','"
					+ u.getEmailAdress() + "','" + u.getNickName() + "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode, um einen User über dessen User-Id aus der Datenbank zu erhalten.
	 * 
	 * @param userId
	 * @return User vom Typ User
	 */
	public UserRms OneUserById(int userId) {
		Connection con = DatebaseConnection.connection();

		UserRms u = new UserRms();

		try {

			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM User WHERE Id='"
							+ userId + "'");

			while (rs.next()) {

				u.setId(rs.getInt("Id"));
				u.setFirstName(rs.getString("Firstname"));
				u.setLastName(rs.getString("Lastname"));
				u.setNickName(rs.getString("Nickname"));
				u.setEmailAdress(rs.getString("EMail"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	/**
	 * Methode, um einen User über seine eindeutige User-Id aus der Datenbank zu erhalten.
	 * 
	 * @param userId
	 * @return ArrayList vom Typ UserRms
	 */
	public ArrayList<UserRms> AllUserById(int userId) {
		Connection con = DatebaseConnection.connection();
		ArrayList<UserRms> resultList = new ArrayList<>();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT *  FROM `User` WHERE `Id`= '"
							+ userId + "';");

			while (rs.next()) {
				UserRms u = new UserRms();

				u.setId(rs.getInt("Id"));
				u.setFirstName(rs.getString("Firstname"));
				u.setLastName(rs.getString("Lastname"));
				u.setEmailAdress(rs.getString("EMail"));
				u.setNickName(rs.getString("Nickname"));
				resultList.add(u);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * Methode, um einen User mithilfe seiner User-Id in der Datenbank zu löschen.
	 * 
	 * @param userId
	 */
	public void deleteUserById(int userId) {

		Connection con = DatebaseConnection.connection();

		try {

			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM User " + "WHERE id=" + userId + ";");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Methode, um einen vorhandenen User mithilfe dessen User-Id in der Datenbank zu bearbeiten.
	 * 
	 * @param u
	 */
	public Boolean updateUser(UserRms u) {
		Connection con = DatebaseConnection.connection();

		try {
			Statement state = con.createStatement();

			state.executeUpdate("UPDATE User SET Firstname='" + u.getFirstName() + "', Lastname='" + u.getLastName() + "', EMail='" + u.getEmailAdress() + "', Nickname='" +u.getNickName() +"' WHERE Id='" +u.getId()+"';");

			return true;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	/**
	 * Methode, um alle User aus der Datenbank zu erhalten.
	 * 
	 * @return ArrayList vom Typ User
	 */
	public ArrayList<UserRms> loadAllUsers() {
		Connection con = DatebaseConnection.connection();
		ArrayList<UserRms> resultList = new ArrayList<>();

		try {
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM User");

			while (result.next()) {
				UserRms u = new UserRms();
				u.setId(result.getInt("id"));
				u.setNickName(result.getString("nickname"));

				resultList.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	/**
	 * Methode, um die Email-Adresse eines Users mithilfe dessen User-Id zu erhalten.
	 * 
	 * @param userId
	 * @return String mit E-Mail-Adresse
	 */
	public String getEmailByAdress(int userId) {

		Connection con = DatebaseConnection.connection();
		UserRms u = new UserRms();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM User WHERE Id='"
					+ userId + "'");
			while (rs.next()) {
//				u.setId(rs.getInt("Id"));
//				u.setFirstName(rs.getString("Firstname"));
//				u.setLastName(rs.getString("Lastname"));
				u.setEmailAdress(rs.getString("EMail"));
//				u.setNickName(rs.getString("Nickname"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u.getEmailAdress();

	}

	/**
	 * Methode, um einen User mithilfe seines Usernamens zu erhalten.
	 * 
	 * @param selectedNickname
	 * @return User vom Typ User
	 */
	public UserRms getUserIdByUserNickname(String selectedNickname) {

		Connection con = DatebaseConnection.connection();

		UserRms u = new UserRms();

		try {

			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM User WHERE Nickname='"
							+ selectedNickname + "'");

			while (rs.next()) {

				u.setId(rs.getInt("Id"));
				u.setFirstName(rs.getString("Firstname"));
				u.setLastName(rs.getString("Lastname"));
				u.setEmailAdress(rs.getString("EMail"));
				u.setNickName(rs.getString("Nickname"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}
	
	
	
	
	public UserRms getOneUserbyId(int userId) {
		Connection con = DatebaseConnection.connection();

		UserRms u = new UserRms();

		try {

			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM User WHERE Id= '"
							+ userId + "' ;");

			while (rs.next()) {

				u.setId(rs.getInt("Id"));
				u.setFirstName(rs.getString("Firstname"));
				u.setLastName(rs.getString("Lastname"));
				u.setNickName(rs.getString("Nickname"));
				u.setEmailAdress(rs.getString("EMail"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}
	

	/**
	 * Methode, um einen User mithilfe seiner EMail-Adresse aus der Datenbank zu erhalten.
	 * 
	 * @param eMailAdress
	 * @return User vom Typ UserRms
	 */
	public UserRms getOneUserIdByEmailAdress(String eMailAdress) {
		Connection con = DatebaseConnection.connection();

		UserRms u = new UserRms();

		try {

			Statement state = con.createStatement();
			ResultSet rs = state
					.executeQuery("SELECT * FROM User WHERE EMail='"
							+ eMailAdress + "'");

			while (rs.next()) {

				u.setId(rs.getInt("Id"));
				u.setFirstName(rs.getString("Firstname"));
				u.setLastName(rs.getString("Lastname"));
				u.setNickName(rs.getString("Nickname"));
				u.setEmailAdress(rs.getString("EMail"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

}