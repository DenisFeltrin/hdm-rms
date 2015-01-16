package de.hdm.rms.shared.report;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String UserName;
	private String firstName;
	private String lastName;
	private String nickName;
	private String impressum;
	private String headerData;
	private String creationDate;
	private String RoomName;
	private String Capacity;
	
	private int id;

	private String getImpressum() {
		return this.impressum;
	}

	private String getHeaderData() {
		return this.headerData;
	}

	private String getTitle() {
		return this.title;
	}

	private String getCreationDate() {
		return this.creationDate;
	}

	public void setImpressum(String impressum) {
		this.impressum = impressum;
	}

	public void setHeaderData(String headerData) {
		this.headerData = headerData;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCreationDate(String creationdate) {
		this.creationDate = creationdate;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomName() {
		return RoomName;
	}

	public void setRoomName(String roomName) {
		RoomName = roomName;
	}

	public String getCapacity() {
		return Capacity;
	}

	public void setCapacity(String capacity) {
		Capacity = capacity;
	}

}
