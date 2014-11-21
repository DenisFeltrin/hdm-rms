package de.hdm.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.rms.shared.bo.User;


public class UserMapper {
	
	
	private static UserMapper userMapper = null; 
	
	protected UserMapper(){
	}
	

	public static UserMapper userMapper(){
		
		if(userMapper == null) {
			userMapper = new UserMapper(); 
		
		}
		return userMapper;
	}
	
	
	
	
	
	/*Methoden*/
	
	public User insertUser (User u) {
		Connection  con = DatebaseConnection.connection();
		
			try{
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery("INSERT INTO User(Id,Firstname,Lastname,EMail,Nickname)"  
				+ "Values (" + u.getId() + ",'" + u.getFirstName() + ",'" + u.getLastName()  + ",'" + u.getNickName()  + ",'" + u.getEmailAdress()  + "')" );
				
			}
			  catch (SQLException e) {
			      e.printStackTrace();
			    }

		return u; 
	}
		
	  
}
