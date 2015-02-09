package de.hdm.rms.shared.bo;

/**
 * Die Klasse Invitation wird für jeden eingeladenen Nutzer erstellt. Hierbei stellt sie eine Verbindung zwischen
 * Reservierung und User dar. Beide müssen in einem Invitation-Objekt vorhanden sein.
 * 
 * @author Mario Theiler, Denis Feltrin, Björn Zimmermann
 * @version 1.0
 */
public class Invitation extends BusinessObject {

	 /** Diese ID wird benötigt, damit die Klasse serialisierbar ist. */
	private static final long serialVersionUID = 1L;
	
	/** Eine Einladung muss einen Status haben, damit dieser jederzeit geändert werden kann. */
	private int acceptionStatus;
	
	/**
	 * Die Member-Id enthält die identische Id, wie das Business-Objekt User.
	 * Dies wird in der Datenbank durch eine Fremdschlüsselbeziehung realisiert.
	 * Eine Invitation kann nur mit einer einzigen Member-Id verknüpft sein.
	 */
	private int memberId;
	
	/**
	 * Die Reservierungs-Id enthält die identische Id, wie das Business-Objekt Reservierung.
	 * Dies wird in der Datenbank durch eine Fremdschlüsselbeziehung realisiert.
	 * Eine Invitation kann nur mit einer einzigen Reservierung verknüpft sein.
	 */
	private int reservationId;

	/**
	 * 
	 *
	 * @return the acception status
	 */
	public int getAcceptionStatus() {
		return acceptionStatus;
	}

	/**
	 * Sets the acception status.
	 *
	 * @param acceptionStatus the new acception status
	 */
	public void setAcceptionStatus(int acceptionStatus) {
		this.acceptionStatus = acceptionStatus;
	}

	/**
	 * Gets the member id.
	 *
	 * @return the member id
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * Sets the member id.
	 *
	 * @param memberId the new member id
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	/**
	 * Gets the reservation id.
	 *
	 * @return the reservation id
	 */
	public int getReservationId() {
		return reservationId;
	}

	/**
	 * Sets the reservation id.
	 *
	 * @param reservationId the new reservation id
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

}