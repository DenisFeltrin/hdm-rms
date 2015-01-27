package de.hdm.rms.shared.bo;
 

public class Reservation extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private String topic;
	private String startTime;
	private String endTime;

 	private int hostId;
	private int roomId;
	private int Id;
	
	
	public Reservation(String topic, String startTime,   int hostId, int roomId, int Id, String endTime ){
		
		this.topic = topic;
		this.startTime = startTime;
		this.setEndTime(endTime);
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

 

}