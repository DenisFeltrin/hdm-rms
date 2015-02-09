package de.hdm.rms.client;

import com.google.gwt.core.client.GWT;
import de.hdm.rms.shared.ReportService;
import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.shared.ReservationServiceAsync;

/**
 * In dieser Klasse sind clientseitige Dienste und Eigenschaften definiert. Diese werden in allen Klassen mit RPC´s
 * benötigt und sind deshalb hier ausgelagert.
 * 
 * @author Mario Theiler, Denis Feltrin, Björn Zimmermann
 * @version 1.0
 */
public class ClientSettings {
	
	/**
	 * Zur Verbindungsaufnahme über einen Remote-Service Proxy zur Verbindungsaufnahme mit den Server-seitgen Diensten
	 * namens <code>reservationService</code> und <code>ReportServiceAsync</code>.
	 */
	private static ReservationServiceAsync reservationService = null;
	private static ReportServiceAsync reportService = null;
	
	/**
	 * Im Folgenden wird eine eindeutige Instanz der Methode <code>getReservationService</code>, 
	 * sofern diese nicht exisitert, erstellt. 
	 * Falls diese bereits existiert, wird das bereits zuvor angelegte Objekt zurückgegeben.  
	 * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	 * <code>reservationServiceAsync reservationService = ClientSideSettings.getReservationService()</code>
	 * 
	 * @return eindeutige Instanz des Typs <code>reservationServiceAsync</code>
	 */
	public static ReservationServiceAsync getReservationService() {
		if (reservationService == null) {
			reservationService = GWT.create(ReservationService.class);
		}
		return reservationService;
	}
	
	/**
	 * Im Folgenden wird eine eindeutige Instanz der Methode <code>getReportService</code>,
	 *  sofern diese nicht exisitert, erstellt. 
	 * Falls diese bereits existiert, wird das bereits zuvor angelegte Objekt zurückgegeben.  
	 * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	 * <code>reportServiceAsync reportService = ClientSideSettings.getReportService()</code>  
	 * 
	 * @return eindeutige Instanz des Typs <code>reportServiceAsync</code>
	 */
	
	public static ReportServiceAsync getReportService() {
		if (reportService == null) {
			reportService = GWT.create(ReportService.class);
		}
		return reportService;
	}
}