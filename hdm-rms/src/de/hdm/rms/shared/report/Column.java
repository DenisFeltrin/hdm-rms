package de.hdm.rms.shared.report;

import java.io.Serializable;

public class Column implements Serializable {
	private static final long serialVersionUID = 1L;
	private String value = "";
	private int ColumnCounter;

	public Column(String s) {
		this.value = s;

	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return this.value;
	}
}
