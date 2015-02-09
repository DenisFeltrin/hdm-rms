package de.hdm.rms.server.db;

import com.google.appengine.api.rdbms.AppEngineDriver;
import java.sql.*;

public class DatebaseConnection {
 		  
		 private static Connection con = null;

		 public static Connection connection() {
		  
		  //Falls die DB-Connection noch nicht besteht, f�hre nachfolgende Befehle aus
		  if (con == null) {
		   
		   try {
		    
			   DriverManager.registerDriver(new AppEngineDriver());
		    
		  	//con = DriverManager.getConnection("jdbc:mysql://188.40.58.9:3306/itprojekt_02", "itprojekt", "blabla123");
		    con = DriverManager.getConnection("jdbc:mysql://188.40.58.9:3306/test_delete_plz3", "test_delete_plz3", "blabla123");

		    //con = DriverManager.getConnection("jdbc:google:rdbms://hdm1337:hdmsql/hotelverwaltung", "root", ""); // Create connection with user-credentials
		    
			  //Google korrekter String:
		    //con = DriverManager.getConnection("jdbc:google:rdbms://hdmitp:rmsdb/hdmitp_rms", "root", ""); // Create connection with user-credentials
			 
		   }
		   
		   //Bei fehlerhafter Verbindung wird keine Verbindung aufgebaut (con=null)
		   catch (SQLException e1) {
		    con = null;
		   
		    e1.printStackTrace();
		   }
		  }
		  
		  //Bei erfolgreicher Verbindung wird die Connection zur�ckgegeben
		  return con;
		 }

}