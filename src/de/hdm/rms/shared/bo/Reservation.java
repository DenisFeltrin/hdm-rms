package de.hdm.rms.shared.bo;

public class Reservation extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private String topic;
	private int startTime;
	private int length;
	private int hostId;
	private int roomId;
	private int Id;
	
	
	public Reservation(String topic, int startTime, int length, int hostId, int roomId, int Id ){
		
		this.topic = topic;
		this.startTime = startTime;
		this.length = length;
		this.hostId = hostId;
		this.roomId = roomId; 
		this.Id = Id; 
		
		
		
	}

	public Reservation(  ){
		
	  
		
		
		
	}
	
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

	public int getId() {
		return Id;
	}

	public void setId (int Id) {
		this.Id = Id;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

}