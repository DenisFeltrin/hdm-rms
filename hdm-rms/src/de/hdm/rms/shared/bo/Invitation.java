package de.hdm.rms.shared.bo;

public class Invitation extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private int acceptionStatus;
	private int memberId;

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

}