package de.hdm.rms.shared.bo;
 

// TODO: Auto-generated Javadoc
/**
 * The Class Reservation.
 */
public class Reservation extends BusinessObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The topic. */
	private String topic;
	
	/** The start time. */
	private String startTime;
	
	/** The end time. */
	private String endTime;

 	/** The host id. */
	 private int hostId;
	
	/** The room id. */
	private int roomId;
	
	/** The Id. */
	private int Id;
	
	
	/**
	 * Instantiates a new reservation.
	 *
	 * @param topic the topic
	 * @param startTime the start time
	 * @param hostId the host id
	 * @param roomId the room id
	 * @param Id the id
	 * @param endTime the end time
	 */
	public Reservation(String topic, String startTime,   int hostId, int roomId, int Id, String endTime ){
		
		this.topic = topic;
		this.startTime = startTime;
		this.setEndTime(endTime);
 		this.hostId = hostId;
		this.roomId = roomId; 
		this.Id = Id; 
		
		
		
	}

	/**
	 * Instantiates a new reservation.
	 */
	public Reservation(  ){
		
	  
		
		
		
	}
	
	/**
	 * Gets the topic.
	 *
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Sets the topic.
	 *
	 * @param topic the new topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the room id.
	 *
	 * @return the room id
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * Sets the room id.
	 *
	 * @param roomId the new room id
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/* (non-Javadoc)
	 * @see de.hdm.rms.shared.bo.BusinessObject#getId()
	 */
	public int getId() {
		return Id;
	}

	/* (non-Javadoc)
	 * @see de.hdm.rms.shared.bo.BusinessObject#setId(int)
	 */
	public void setId (int Id) {
		this.Id = Id;
	}

	/**
	 * Gets the host id.
	 *
	 * @return the host id
	 */
	public int getHostId() {
		return hostId;
	}

	/**
	 * Sets the host id.
	 *
	 * @param hostId the new host id
	 */
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

 

}