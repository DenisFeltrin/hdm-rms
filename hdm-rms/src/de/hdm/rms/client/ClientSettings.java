package de.hdm.rms.client;

import com.google.gwt.core.client.GWT;
import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.shared.ReservationServiceAsync;

public class ClientSettings {

	  private static ReservationServiceAsync reservationService = null;

	  public static ReservationServiceAsync getReservationService() {
		    // Gab es bislang noch keine BankAdministration-Instanz, dann...
		    if (reservationService == null) {
		      // Zunächst instantiieren wir BankAdministration
		    	reservationService = GWT.create(ReservationService.class);
		    }

		    // So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
		    return reservationService;
		  }
	
}