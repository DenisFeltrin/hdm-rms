package de.hdm.rms.server.db;

import com.google.appengine.api.rdbms.AppEngineDriver;
import java.sql.*;

/**
* Die Klasse DatebaseConnection enthält alle relevanten Informationen, um eine Verbindung zur Datenbank herzustellen.
* <p>Die Klasse wird in jedem Mapper instantiiert, wenn dieser eine Verbindung zur Datenbank aufbaut.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
public class DatebaseConnection {
 		  
	private static Connection con = null;

	public static Connection connection() {
		  
		/**Falls die DB-Connection noch nicht besteht, führe nachfolgende Befehle aus.*/
		if (con == null) {
				 
			try {
				DriverManager.registerDriver(new AppEngineDriver());
		    
//				con = DriverManager.getConnection("jdbc:mysql://188.40.58.9:3306/itprojekt_02", "itprojekt", "blabla123");
				con = DriverManager.getConnection("jdbc:mysql://188.40.58.9:3306/test_delete_plz3", "test_delete_plz3", "blabla123");

//				con = DriverManager.getConnection("jdbc:google:rdbms://hdm1337:hdmsql/hotelverwaltung", "root", ""); // Create connection with user-credentials
		   } 
		   
		   catch (SQLException e1) {
		    con = null;
		   
		    e1.printStackTrace();
		   }
		  }
		  
		  return con;
		 }

}