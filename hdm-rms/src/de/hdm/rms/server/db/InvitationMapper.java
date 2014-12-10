package de.hdm.rms.server.db;

import java.sql.Connection;
import java.sql.Statement;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Room;

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

}