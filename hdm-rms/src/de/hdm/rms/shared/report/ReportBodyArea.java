package de.hdm.rms.shared.report;

import java.io.Serializable;

import com.google.gwt.user.client.ui.HTML;

public class ReportBodyArea implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ColumnHeadline1;
	private String ColumnHeadline2;
	private String ColumnHeadline3;
	private int RowCount;
	private int ColumnCount;

	public void onLoad() {
		final HTML html = new HTML("");
		html.setHTML("<table width=800>" +

		"<tr><td>" + ColumnCount + "</td></tr>"

		+ "<tr><td>" + ColumnHeadline1 + "</td>/tr>"

		+ "<tr><td>" + ColumnHeadline2 + "</td></tr>"

		+ "<tr><td>" + ColumnHeadline3 + "</td></tr>" +

		"</table>"

		);
	}

	public void setFirstRow(String ColumnHeadline1, String ColumnHeadline2) {

	}

	public void setParagraph(int RowCount, int ColumnCount) {

	}

}
