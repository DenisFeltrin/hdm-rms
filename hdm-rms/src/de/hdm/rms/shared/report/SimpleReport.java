package de.hdm.rms.shared.report;

import java.util.Vector;

public class SimpleReport extends Report {

	private Vector<Row> table = new Vector<Row>();

	public void addRow(Row r) {
		this.table.addElement(r);
	}

	public Vector<Row> getRows() {
		return this.table;
	}

}
