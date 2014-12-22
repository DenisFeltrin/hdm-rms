package de.hdm.rms.server.db;

import java.sql.Connection;
import java.sql.Statement;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

public class InvitationMapper {

	private static InvitationMapper invitationMapper = null;

	protected InvitationMapper() {
	}

	public static InvitationMapper invitationMapper() {

		if (invitationMapper == null) {
			invitationMapper = new InvitationMapper();

		}
		return invitationMapper;
	}
	
	public void insertInvitation(Invitation i) {
		Connection con = DatebaseConnection.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Invitation (MemberId, AcceptionStatus) VALUES ("
					+ "'"
					+ i.getMemberId()
					+ "','"
					+ i.getAcceptionStatus()
					+ "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteInvitationById(int invitationId) {
		 
		Connection con = DatebaseConnection.connection();
		Invitation i = new Invitation();
		
		try {
			
		     Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Invitation " + "WHERE id=" + 1 +";");	
 
		} catch (Exception e) {
			e.printStackTrace();
 		}
	
 	}
	
	public Boolean updateInvitation(Invitation i) {
		Connection con = DatebaseConnection.connection();

		try{
			Statement state = con.createStatement();
			
			state.executeUpdate( "UPDATE  `Invitation`" + "SET `AcceptionStatus`=\"" + "2" + "\", " +  "`CreationDate`=\"" + "31.12.2014" + "\" "  + "WHERE Id="  +"1" );
			
				return true;

		} catch (Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}

}