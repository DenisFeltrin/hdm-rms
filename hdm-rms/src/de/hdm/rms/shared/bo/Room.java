package de.hdm.rms.shared.bo;

public class Room extends BusinessObject{

	
	private static final long serialVersionUID = 1L;
	
	private String description;
	private int maxMember;
	public int getMaxMember() {
		return maxMember;
	}
	public void setMaxMember(int maxMember) {
		this.maxMember = maxMember;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
