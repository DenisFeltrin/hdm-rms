package de.hdm.rms.shared.bo;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User extends BusinessObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private int id;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The email adress. */
	private String emailAdress;
	
	/** The nick name. */
	private String nickName;

	/* (non-Javadoc)
	 * @see de.hdm.rms.shared.bo.BusinessObject#getId()
	 */
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see de.hdm.rms.shared.bo.BusinessObject#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email adress.
	 *
	 * @return the email adress
	 */
	public String getEmailAdress() {
		return emailAdress;
	}

	/**
	 * Sets the email adress.
	 *
	 * @param emailAdress the new email adress
	 */
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	/**
	 * Gets the nick name.
	 *
	 * @return the nick name
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Sets the nick name.
	 *
	 * @param nickName the new nick name
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}