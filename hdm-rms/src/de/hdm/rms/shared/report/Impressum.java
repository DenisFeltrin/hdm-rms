package de.hdm.rms.shared.report;

import com.google.gwt.user.client.Window;

/**
 * Das Impressum muss in jedem Fall (Raumreservierungen vorhanden oder nicht vorhanden) angezeigt werden.
 * Es wird zum Abschluss im HTMLReportWriter dem StringBuffer hinzugefügt.
 */
public class Impressum {
	
	public String setImpressum(){
		
		String reportImpressum = new String("<h3>Impressum</h3>"
				+ "<b>Angaben gemäß § 5 TMG:</b><br/>"
				+ "Hochschule der Medien<br/>"
				+ "Mario Theiler, Björn Zimmermann, Denis Feltrin<br/>"
				+ "Nobelstraße 10<br/>"
				+ "70569 Stuttgart<br/>"
				+ "</p>"
				+ "</body>"
				);		

	return reportImpressum;
	
	}

}