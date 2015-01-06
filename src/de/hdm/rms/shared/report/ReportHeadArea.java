package de.hdm.rms.shared.report;

import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;



public class ReportHeadArea implements Serializable{
	 private static final long serialVersionUID = 1L;
	private String Title = "rms";
	private String UserName = "TestUser";
	private String firstName = "FIrstname";
	private String lastName = "LastName ";

	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	Date ReportDate = new Date();

	// System.out.println(dateFormat.format(date));
	public void onLoad() {
		final HTML html = new HTML("");
		html.setHTML("<h1>" + Title + "</h1></br>" 	
		+ "<h1>" + UserName	+ "</h1></br>" 
		+ "<h1>" + firstName + "</h1></br>"
		+ "<h1>" + lastName + "</h1></br>"
		+ "<h1>" + dateFormat.format(ReportDate) + "</h1>");

		RootPanel.get("content_wrap").add(html);
	}

}
