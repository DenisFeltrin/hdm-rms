package de.hdm.rms.shared.report;

/**
 * Das Impressum muss in jedem Fall (Raumreservierungen vorhanden oder nicht vorhanden) angezeigt werden.
 * Es wird zum Abschluss im HTMLReportWriter dem StringBuffer hinzugefügt.
 * 
 * @author Mario Theiler, Denis Feltrin, Björn Zimmermann
 * @version 1.0
 */
public class ImpressumReport {
	
	public String setImpressum(){
		
		String reportImpressum = new String("<b>Impressum</b></br>"
				+ "<b>(Angaben gemäß § 5 TMG)</b><br/>"
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