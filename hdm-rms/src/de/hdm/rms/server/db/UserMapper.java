package de.hdm.rms.server.db;

import de.hdm.rms.server.db.DatebaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.rms.shared.bo.UserRms;

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

	/* Methoden */

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

				// Ergebnis-Tupel in Objekt umwandeln
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

	public void deleteUserById(int userId) {

		Connection con = DatebaseConnection.connection();
		UserRms u = new UserRms();

		try {

			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM User " + "WHERE id=" + userId + ";");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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

	public ArrayList<UserRms> loadAllUsers() {
		Connection con = DatebaseConnection.connection();
		ArrayList<UserRms> resultList = new ArrayList<>();

		try {
			Statement state = con.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM User");

			while (result.next()) {
				UserRms u = new UserRms(); // Create new person-object to fill
											// with values from database
				u.setId(result.getInt("id"));
				u.setNickName(result.getString("nickname"));

				resultList.add(u); // Add person-object to Arraylist
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	public String getEmailByAdress(int userId) {

		Connection con = DatebaseConnection.connection();
		UserRms u = new UserRms();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM User WHERE Id='"
					+ userId + "'");
			while (rs.next()) {
				// u.setId(rs.getInt("Id"));
				// u.setFirstName(rs.getString("Firstname"));
				// u.setLastName(rs.getString("Lastname"));
				u.setEmailAdress(rs.getString("EMail"));
				// u.setNickName(rs.getString("Nickname"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u.getEmailAdress();

	}

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