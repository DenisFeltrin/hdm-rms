package de.hdm.rms.shared.bo;

public class Invitation extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private boolean acceptionStatus;
	private int memberId;

	public boolean isAcceptionStatus() {
		return acceptionStatus;
	}

	public void setAcceptionStatus(boolean acceptionStatus) {
		this.acceptionStatus = acceptionStatus;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

}
