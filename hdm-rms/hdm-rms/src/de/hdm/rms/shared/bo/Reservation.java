package de.hdm.rms.shared.bo;

public class Reservation extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private String topic;
	private int startTime;
	private int length;
	private int organisatorId;
	private int roomId;
	private int intivationId;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getOrganisatorId() {
		return organisatorId;
	}

	public void setOrganisatorId(int organisatorId) {
		this.organisatorId = organisatorId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getIntivationId() {
		return intivationId;
	}

	public void setIntivationId(int intivationId) {
		this.intivationId = intivationId;
	}

}