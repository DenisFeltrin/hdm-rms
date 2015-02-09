package de.hdm.rms.shared.bo;

public class Invitation extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private int acceptionStatus;
	private int memberId;
	private int reservationId;

	public int getAcceptionStatus() {
		return acceptionStatus;
	}

	public void setAcceptionStatus(int acceptionStatus) {
		this.acceptionStatus = acceptionStatus;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

}