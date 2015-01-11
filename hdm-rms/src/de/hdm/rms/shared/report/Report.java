package de.hdm.rms.shared.report;

import java.io.Serializable;

public class Report implements Serializable {
	private String title = "rms";
	private String UserName = "TestUser";
	private String firstName = "FIrstname";
	private String lastName = "LastName ";
	private String impressum = "";
	private String headerData = "";
	private String creationDate = "";

	private String getImpressum() {
		return this.impressum;
	}

	private String getHeaderData() {
		return this.headerData;
	}

	private String getTitle() {
		return this.title;
	}

	private String getCreationDate() {
		return this.creationDate;
	}

	public void setImpressum(String impressum) {
		this.impressum = impressum;
	}

	public void setHeaderData(String headerData) {
		this.headerData = headerData;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCreationDate(String creationdate) {
		this.creationDate = creationdate;
	}

}
