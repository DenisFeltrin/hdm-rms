package de.hdm.rms.shared.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class InvitationListObj.
 */
public class InvitationListObj extends Invitation implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The E mail. */
	private String EMail;
	
	/** The Nickname. */
	private String Nickname;

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the e mail.
	 *
	 * @return the e mail
	 */
	public String getEMail() {
		return EMail;
	}
	
	/**
	 * Sets the e mail.
	 *
	 * @param eMail the new e mail
	 */
	public void setEMail(String eMail) {
		EMail = eMail;
	}

	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return Nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname) {
		Nickname = nickname;
	}

}