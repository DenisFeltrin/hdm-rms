package de.hdm.rms.shared.bo;

import java.io.Serializable;

public class InvitationListObj extends Invitation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	
	private String lastName;
	
	private String EMail;
	
	private String Nickname;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEMail() {
		return EMail;
	}
	
	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public String getNickname() {
		return Nickname;
	}

	public void setNickname(String nickname) {
		Nickname = nickname;
	}

}