package de.hdm.rms.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;

/**
* Die Klasse InvitationMapper regelt alle Datenbank-Zugriffe, welche auf Objekte des Typs Invitation in der Datenbank
* zugreifen. Hierfür wird beim Durchführen der einzelnen Methoden mithilfe der Klasse DatebaseConnection jeweils
* eine Verbindung mit der Datenbank aufgebaut.
* 
* @author Mario Theiler, Denis Feltrin, Björn Zimmermann
* @version 1.0
*/
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
	
	/**
	 * Methode, um eine neue Einladung in der Datenbank zu stellen.
	 * 
	 * @param i
	 */
	public void insertInvitation(Invitation i) {
		Connection con = DatebaseConnection.connection();
		try {
			
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Invitation (MemberId, ReservationId, AcceptionStatus) VALUES ("
					+ "'"
					+ i.getMemberId()
					+ "','"
					+ i.getReservationId()
					+ "','"
					+ i.getAcceptionStatus()
					+ "') ;";
			state.executeUpdate(sqlquery);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode, um eine vorhanden Einladung mithilfe deren Id in der Datenbank zu löschen.
	 * 
	 * @param invitationId
	 */
	public void deleteInvitationById(int invitationId) {
		
		Connection con = DatebaseConnection.connection();
//		Invitation i = new Invitation();
		
		try {
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Invitation " + "WHERE id=" + 1 +";");	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methode, um eine vorhanden Einladung mithilfe deren Id in der Datenbank zu bearbeiten.
	 * 
	 * @param i
	 * @return null
	 */
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
	
	/**
	 * Methode, um alle Einladungen vom Typ Invitation zu erhalten.
	 * 
	 * @return Arraylist vom Typ InvitationListObj
	 */
	public ArrayList<InvitationListObj> loadAllInvitationsById() {
		Connection con = DatebaseConnection.connection();
		ArrayList<InvitationListObj> resultList = new ArrayList<>();
		try {
			Statement state = con.createStatement();
			
			ResultSet result = state.executeQuery("  SELECT AcceptionStatus, Firstname, Lastname, EMail, Nickname FROM Invitation INNER JOIN User ON Invitation.MemberId = User.Id");
			while (result.next() ) {
				
				InvitationListObj i = new InvitationListObj(); 
//				i.setId(result.getInt("Id"));
				i.setFirstName(result.getString("Firstname"));
				i.setLastName(result.getString("Lastname"));
				i.setEMail(result.getString("EMail"));
//				i.setAcceptionStatus(result.getInt("acceptionStatus"));
				
//				i.setNickname(result.getString("Topic"));
//				r.setCapacity(result.getInt("Capacity"));
				resultList.add(i); // Add person-object to Arraylist
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	/**
	 * Methode, um alle Einladungen einer Reservierungs in der Datenbank zu erhalten.
	 * 
	 * @param resid
	 * @return ArrayList vom Typ Invitation
	 */
	public ArrayList<Invitation> loadUserIdByReservationId(int resid) {
		Connection con = DatebaseConnection.connection();
		ArrayList<Invitation> resultList = new ArrayList<>();
		try {
			Statement state = con.createStatement();
			
			ResultSet result = state.executeQuery("SELECT * FROM `Invitation` WHERE `Id`=" + 37 +";");
			while (result.next() ) {
				Invitation i = new Invitation(); 
				i.setMemberId(result.getInt("MemberId"));
				resultList.add(i); // Add person-object to Arraylist
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

}