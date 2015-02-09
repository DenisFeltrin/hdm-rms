package de.hdm.rms.shared.bo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ReservationListObj.
 */
public class ReservationListObj extends Reservation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The room name. */
	private String roomName;
	
	/** The Capacity. */
	private String Capacity;
	
	/** The E mail. */
	private String EMail;
	
	/** The Firstname. */
	private String Firstname;
	
	/** The Lastname. */
	private String Lastname;
	
	/** The Nickname. */
	private String Nickname;
	
	/**
	 * Gets the room name.
	 *
	 * @return the room name
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/**
	 * Sets the room name.
	 *
	 * @param roomName the new room name
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public String getCapacity() {
		return Capacity;
	}
	
	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(String capacity) {
		Capacity = capacity;
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
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getFirstname() {
		return Firstname;
	}
	
	/**
	 * Sets the firstname.
	 *
	 * @param firstname the new firstname
	 */
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	
	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastname() {
		return Lastname;
	}
	
	/**
	 * Sets the lastname.
	 *
	 * @param lastname the new lastname
	 */
	public void setLastname(String lastname) {
		Lastname = lastname;
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