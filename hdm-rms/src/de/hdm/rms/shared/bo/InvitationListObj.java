package de.hdm.rms.shared.bo;

import java.io.Serializable;

public class InvitationListObj extends Invitation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String roomName; 
	private String roomCappa; 
	private String Topic;
	private String Acceptionstatus;
	private String resId; 
	private String startTime; 
	private String endTime; 

	
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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomCappa() {
		return roomCappa;
	}

	public void setRoomCappa(String roomCappa) {
		this.roomCappa = roomCappa;
	}

	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	public String getAcceptionstatus() {
		return Acceptionstatus;
	}

	public void setAcceptionstatus(String acceptionstatus) {
		Acceptionstatus = acceptionstatus;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}