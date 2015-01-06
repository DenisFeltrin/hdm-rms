package de.hdm.rms.shared.bo;

public class Room extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String capaticity;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCapaticity() {
		return capaticity;
	}
	
	public void setCapaticity(String capaticity) {
		this.capaticity = capaticity;
	}

}