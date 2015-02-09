package de.hdm.rms.client;

import com.google.gwt.core.client.GWT;

import de.hdm.rms.shared.ReportService;
import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.shared.ReservationServiceAsync;

public class ClientSettings {

	  private static ReservationServiceAsync reservationService = null;
	  private static ReportServiceAsync reportService = null;
	  
	  
	  public ClientSettings(){}

	  public static ReservationServiceAsync getReservationService() {
		    // Gab es bislang noch keine BankAdministration-Instanz, dann...
		    if (reservationService == null) {
		      // Zunächst instantiieren wir BankAdministration
		    	reservationService = GWT.create(ReservationService.class);
		    }

		    // So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
		    return reservationService;
		  }

	public static ReportServiceAsync getReportService() {
	    // Gab es bislang noch keine BankAdministration-Instanz, dann...
	    if (reportService == null) {
	      // Zunächst instantiieren wir BankAdministration
	    	reportService = GWT.create(ReportService.class);
	    }

	    // So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
	    return reportService;
	}
	
}