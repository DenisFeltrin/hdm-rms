package de.hdm.rms.shared.bo;

// TODO: Auto-generated Javadoc
/**
 * The Class Room.
 */
public class Room extends BusinessObject{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The name. */
	private String name;
	
	/** The capaticity. */
	private String capaticity;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the capaticity.
	 *
	 * @return the capaticity
	 */
	public String getCapaticity() {
		return capaticity;
	}
	
	/**
	 * Sets the capaticity.
	 *
	 * @param capaticity the new capaticity
	 */
	public void setCapaticity(String capaticity) {
		this.capaticity = capaticity;
	}

}