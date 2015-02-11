package de.hdm.rms.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Die Klasse Row dient als Business Object und definiert diverse globale Parameter.
 * 
 * @author Mario Theiler, Denis Feltrin, Björn Zimmermann
 * @version 1.0
 */
public class Row implements Serializable {

	private static final long serialVersionUID = 1L;

	private Vector<Column> columns = new Vector<Column>();

	public void addColumn(Column c) {
		this.columns.addElement(c);
	}

	public Vector<Column> getColumns() {
		return this.columns;
	}

	public int getNumColumns() {
		return this.columns.size();
	}

	public Column getColumnAt(int i) {
		return this.columns.elementAt(i);
	}

}