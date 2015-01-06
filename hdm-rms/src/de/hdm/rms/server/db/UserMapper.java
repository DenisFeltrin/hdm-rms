package de.hdm.rms.server.db;

import de.hdm.rms.server.db.DatebaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import de.hdm.rms.shared.bo.User;

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

	public void insertUser(User u) {
		Connection con = DatebaseConnection.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO User (Firstname, Lastname, EMail, Nickname) VALUES ("
					+ "'"
					+ u.getFirstName()
					+ "','"
					+ u.getLastName()
					+ "','"
					+ u.getEmailAdress()
					+ "','"
					+ u.getNickName()
					+ "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User OneUserById(int userId) {
		Connection con = DatebaseConnection.connection();
		User u = new User();

		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT *  FROM `User` "  + "WHERE `Id`= '1' ;" );
 			
			   if (rs.next()) {
			        // Ergebnis-Tupel in Objekt umwandeln
			       // u.setId(userId);
			        u.setFirstName(rs.getString("Firstname"));
			        u.setLastName(rs.getString("Lastname"));
			        u.setEmailAdress(rs.getString("EMail"));
			        u.setNickName(rs.getString("Nickname"));
			        
			        u.setId(rs.getInt("Id"));

			        return u;
			      }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	public void deleteUserById(int userId) {
 
		Connection con = DatebaseConnection.connection();
		User u = new User();
		
		try {
			
		     Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM User " + "WHERE id=" + 3 +";");	
 
		} catch (Exception e) {
			e.printStackTrace();
 		}
	
 	}

	public Boolean updateUser(User u) {
		Connection con = DatebaseConnection.connection();

		try{
			Statement state = con.createStatement();
			
			state.executeUpdate( "UPDATE  `User`" + "SET `Firstname`=\"" + "Gut" + "\", " +  "`Lastname`=\"" + "Gut" + "\", " +  "`Nickname`=\"" + "Gut" + "\", " +   "  `EMail`=\"" + "Gut" + "\" "  + "WHERE Id="  +"1" );
			
				return true;

		} catch (Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}
		
}