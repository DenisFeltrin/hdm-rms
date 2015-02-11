package de.hdm.rms.shared.report;

import java.util.ArrayList;
import java.util.Date;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import de.hdm.rms.client.ClientSettings;
import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;
import de.hdm.rms.shared.report.Report;

/**
 * Die Klasse HTMLReportWriter erzeugt auf Basis der ausgewählten Parameter in der Client-Klasse ReportPanel eine
 * individuelle Übersicht aller Raumreservierungen über einen Raum, bzw. User in einem gewählten Zeitraum.
 * <p>Diese Übersicht wird mithilfe eines HTML-Widgets von GWT in HTML-Sprache in eine Variable geschrieben
 * und anschließend wird dieses Widget dem entsprechenden Panel zugeordnet. Als Ergebnis erhält der Nutzer
 * eine statische HTML-Seite, welche er sich bei Bedarf ausdrucken kann.
 * 
 * <p>Der Source-Code dieser Klasse lehnt sich in Teilen an die Vorgabe des Programmes BankProjekt 2.0 an.
 * 
 * @author Mario Theiler, Denis Feltrin, Björn Zimmermann
 * @version 1.0
 */
public class HTMLReportWriter {

	
	/** Eine Instanz von ReportServiceAsync wird erzeugt, welche aus den ClientSettings abgeleitet wird. */
	ReportServiceAsync reportAdministration = ClientSettings.getReportService();
	
	/**
	 * Da die Tabelle in beiden Reports identisch verwendet wird, werden diese Parameter global gesetzt.
	 */
	private String startDate  ;
	private String endDate  ;
	String test2;

	/** Die Überschrift der zu erzeugenden HTML-Seite ist bei beiden Reports identisch und wird daher an dieser
	 * Stelle übergreifend gesetzt.
	 */
	private String Title = "RMS Report";

	/** Mithilfe der Initialisierung eines Date-Objektes kann die Erstellungszeit des jeweiligen Reports angezeigt werden. */
	Date date = new Date();
	
	/** Ein Objekt von DateTimeFormat wird erstellt, welches in die gewünschte Anzeigesemantik formatiert wird. */
	private DateTimeFormat creationDate = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	
	/**
	 * Da die Übersicht des Report 2 nur einen bestimmten User anzeigen soll, muss dieser in einer Variable
	 * zwischengespeichert werden.
	 */
	private int selectedUserId;
	
	/** Analog des Report 2 wird für den Report 1 der ausgewählte Raum zwischengespeichert.*/
	private int selectedRoomId;
	
	/** Der Report setzt sich aus mehreren Einzelanweisungen zusammen. Da ein String dies nicht unterstützt,
	 * wird ein StringBuilder verwendet. Mit dessen Hilfe entsteht ein dynamischer String, welcher nach und nach
	 * mit den gewünschten HTML-Parametern befüllt wird.
	 */
	StringBuilder report1 = new StringBuilder();
	StringBuilder report2 = new StringBuilder();
	
	/**
	 * In der Methode loadMemReport1 wird mithilfe nachfolgender Parameter vom Server diejenigen User angefragt,
	 * welche zu einer bestimmten Reservierung eingeladen sind.
	 * 
	 * @param resId
	 * @param top
	 * @param startT
	 * @param endT
	 * @param counter
	 */
	public void loadMemReport1(int resId, String top, String startT, String endT, int counter){

		/**
		 * Die Parameter, die an diese Methode überliefert wurden, werden zu finalen Methoden-Parametern geschlüsselt.
		 */
		final String startTime = startT;
		final String endTime = endT;
		final String topic = top;
		final int zaehler = counter;

		reportAdministration.loadMembersForReservationReport1(resId, new AsyncCallback<ArrayList<String>>(){
			
			@Override
			public void onFailure(Throwable caught) {	
				System.out.println("Fehler beim Laden der Teilnehmer");
			}
			
			/**
			 * Liefert die Methode loadMembersForReservationReport1 ein Ergebnis, wird für jedes result-Set
			 * der zurückgelieferten ArrayList eine Zeile in den report1-String geschrieben und in der Ergebnis-Tabelle erstellt.
			 * 
			 * @param userWithInvitation
			 */
			@Override
			public void onSuccess(ArrayList<String> userWithInvitation) {
				
				StringBuffer memberStringBuffer = new StringBuffer();
 				report1.append("<tr class=\"ReportTable\">" + "<td>" + zaehler + "</td>" + "<td>" + topic + "</td>" + "<td>" +  startTime  +"</td>" + "<td>" + endTime + "</td>" + "<td> "); 

 				/**
 				 * Die ArrayList mit den gelieferten User-Namen wird mit einer for-Schleife zu einem StringBuffer
 				 * zusammengeführt.
 				 */
				for(int i=0; i<userWithInvitation.size();i++){
					memberStringBuffer.append(userWithInvitation.get(i)+", ");
				}
				report1.append( memberStringBuffer.toString()+  "</td>" + "</tr>");
				
				/**
				 * Im Anschluss aller abgelaufenen Methoden wird der zusammengesetze report1-StringBuffer mithilfe
				 * der toString-Methode in ein HTML-Widget von GWT geschrieben. Dieses HTML-Widget wird dem RootPanel
				 * zugeordnet. Das Widget interpretiert die im report1-String als html-Code und zeigt diesen entsprechend
				 * an.
				 */
				HTML html1 = new HTML(report1.toString());
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(html1);
				
			}
					
		});

	}

	/**
	 * Diese Methode befüllt den bereits genannten StringBuilder für Report 1.
	 * Dabei wird zum Einen ein RaumObjekt erstellt, das mit den gewünschten ReportHeader-Informationen befüllt wird.
	 * Diese Informationen über den ausgewählten Raum werden im Kopfbereich des Reports angezeigt, um kenntlich zu machen,
	 * über welchen Raum die Buchungsinformationen dargestellt werden.
	 * Zum Anderen wird daraufhin die HTML-Tabelle "zusammengebaut". Falls der ausgewählte Raum Raumreservierungen 
	 * enthält, durchläuft eine for-Schleife alle Ergebnisse, die vom Server kommen und schreibt sie in die Tabelle.
	 * Sollte der Raum keine Raumreservierungen enthalten, wird dies ebenfalls in HTML angezeigt.
	 * Dies alles geschieht, sobald der User auf den Button "Übersicht erstellen" geklickt hat.
	 * 
	 * @param selectedRoom
	 * @param roomId
	 * @param fromDate
	 * @param toDate
	 */
	public void getHeaderOfReportOne(String selectedRoom, int roomId, String fromDate, String toDate) {

		selectedRoomId = roomId;
		startDate = fromDate;
		endDate = toDate;

		/**
		 * Um den Raum inkl. seiner zugehörigen Parameter anzeigen zu können, wird die Raum-Id aus dem DropDown
		 * im Report-Panel an die Methode getOneRoomIdByName geschickt.
		 */
		reportAdministration.getOneRoomIdByName(selectedRoom, new AsyncCallback<Room>() {
			
			@Override
			public void onFailure(Throwable caught) {
			}	

			@Override
			public void onSuccess(Room result) {
				
				RootPanel.get("content_wrap").clear();
				
				selectedRoomId = result.getId();
				Report r = new Report();
				r.setRoomName(result.getName());
				r.setCapacity(result.getCapaticity());
				r.setId(result.getId());
				
				/**
				 * Dieser Abschnitt sammelt die Rauminformationen über den ausgewählten Raum und stellt ihn
				 * im Kopfbereich des Reports dar.
				 */
				report1.append("<html><head><title></title></head><body>" + "<h2>" + Title + "</h2>"
						+ "<h3>Alle Raumreservierungen für folgenden Raum:</h3>"
						+ "Raumname: <i>" + r.getRoomName() + "</i></br>" + "Kapazität: <i>" + r.getCapacity()
						+ "</i></br>" + "</p>"
						+ "Der Report wurde erstellt: <i>" + creationDate.format(date).toString() + "</i></br>" + "</p>"
						);

				createReportOneBody(selectedRoomId, startDate, endDate);
				
				report1.append("</table>" + "</p>");
				
				/**
				 * Eine Instanz der Klasse Impressum wird erstellt und an dieser Stelle dem report1-String hinzugefügt.
				 */
				ImpressumReport imp = new ImpressumReport();
				report1.append(imp.setImpressum());
				
			}
			
			/**
			 * Der Content-Bereich des Reports wird mithilfe dieser Methode befüllt.
			 * Dafür wird die Room-Id und die beiden Start- und Enddaten des gewünschten Zeitraumes an die Methode
			 * getReservationByRoomIdWithDate geschickt.
			 * <p>Wenn die Methode ein leeres Ergebnis liefert und somit keine Reservierungen vorhanden sind,
			 * wird dies mithilfe einer Meldung angezeigt und das HTML-Widget wird ohne Tabelle erstellt und
			 * dem RootPanel hinzugefügt.
			 * <p> Sind Reservierungen vorhanden, wird dynamisch eine Tabelle erstellt und die teilnehmenden User
			 * werden über die Methode loadMemReport1 angefügt.
			 */
			private void createReportOneBody(int selectedRoomId, String selectFromDate, String selectToDate) {

				reportAdministration.getReservationByRoomIdWithDate(selectedRoomId, selectFromDate, selectToDate, new AsyncCallback<ArrayList<Reservation>>() {
					
					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Es ist ein Fehler aufgetreten.");
					}

					@Override
					public void onSuccess(ArrayList<Reservation> res) {
						
						if(res.isEmpty()){
							report1.append("<b style=\"color:red;\">Für diesen Raum sind im gewählten Zeitraum keine Reservierungen erstellt.</b>"
									+ "</br>" + "</p>");
							
							HTML html1 = new HTML(report1.toString());
							RootPanel.get("content_wrap").clear();
							RootPanel.get("content_wrap").add(html1);
							
						} else{
						
							report1.append("<table class=\"ReportTable\"><tr>");
							
							report1.append("<tr class=\"ReportTable\">" + "<th>" + "Nr." + "</th>" + "<th>" + "Thema" + "</th>" + "<th>" + "Von" + "</th>" + "<th>" + "Bis" + "</th>" + "<th>" + "Teilnehmer" + "</th>" +"</tr>"
									);	
							
							for(int i=0, zaehler= res.size(); i < res.size(); i++, zaehler--){
								loadMemReport1(res.get(i).getId(), res.get(i).getTopic(),res.get(i).getStartTime().toString(),res.get(i).getEndTime().toString(), zaehler );
							 		report1.append("</td>" + "</tr>");
							}
							
						}
					}

				});
				
			}

		});
			
	}
	
	/**
	 * In der Methode loadMemReport1 wird mithilfe nachfolgender Parameter vom Server diejenigen User angefragt,
	 * welche zu einer bestimmten Reservierung eingeladen sind.
	 * 
	 * @param resId
	 * @param top
	 * @param startT
	 * @param endT
	 * @param counter
	 */
	public void loadMemReport2(int resId, String top, String startT, String endT, int counter){

		/**
		 * Die Parameter, die an diese Methode überliefert wurden, werden zu finalen Methoden-Parametern geschlüsselt.
		 */
		final String startTime = startT;
		final String endTime = endT;
		final String topic = top;
		final int zaehler = counter;

		reportAdministration.loadMembersForReservationReport2(resId, new AsyncCallback<ArrayList<String>>(){
			
			@Override
			public void onFailure(Throwable caught) {	
				System.out.println("Fehler beim Laden der Teilnehmer");
			}
			
			/**
			 * Liefert die Methode loadMembersForReservationReport2 ein Ergebnis, wird für jedes result-Set
			 * der zurückgelieferten ArrayList eine Zeile in den report2-String geschrieben und in der Ergebnis-Tabelle erstellt.
			 * 
			 * @param userWithInvitation
			 */
			@Override
			public void onSuccess(ArrayList<String> userWithInvitation) {
				
				StringBuffer memberStringBuffer = new StringBuffer();
 				report2.append("<tr class=\"ReportTable\">" + "<td>" + zaehler + "</td>" + "<td>" + topic + "</td>" + "<td>" +  startTime  +"</td>" + "<td>" + endTime + "</td>" + "<td> "); 

 				/**
 				 * Die ArrayList mit den gelieferten User-Namen wird mit einer for-Schleife zu einem StringBuffer
 				 * zusammengeführt.
 				 */
				for(int i=0; i<userWithInvitation.size();i++){
					memberStringBuffer.append(userWithInvitation.get(i)+", ");
				}
				report2.append( memberStringBuffer.toString()+  "</td>" + "</tr>");
				
				/**
				 * Im Anschluss aller abgelaufenen Methoden wird der zusammengesetze report1-StringBuffer mithilfe
				 * der toString-Methode in ein HTML-Widget von GWT geschrieben. Dieses HTML-Widget wird dem RootPanel
				 * zugeordnet. Das Widget interpretiert die im report1-String als html-Code und zeigt diesen entsprechend
				 * an.
				 */
				HTML html2 = new HTML(report2.toString());
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(html2);
				
			}
					
		});

	}
	
	/**
	 * Diese Methode befüllt den bereits genannten StringBuilder für Report 2.
	 * Dabei wird zum Einen ein UserObjekt erstellt, das mit den gewünschten ReportHeader-Informationen befüllt wird.
	 * Diese Informationen über den ausgewählten User werden im Kopfbereich des Reports angezeigt, um kenntlich zu machen,
	 * über welchen User die Buchungsinformationen dargestellt werden.
	 * Zum Anderen wird daraufhin die HTML-Tabelle "zusammengebaut". Falls der ausgewählte User Raumreservierungen 
	 * erstellt hat, durchläuft eine for-Schleife alle Ergebnisse, die vom Server kommen und schreibt sie in die Tabelle.
	 * Sollte der User keine Raumreservierungen erstellt haben, wird dies ebenfalls in HTML angezeigt.
	 * Dies alles geschieht, sobald der User auf den Button "Übersicht erstellen" geklickt hat.
	 * 
	 * @param selectedNickname the selected nickname
	 * @param userId the user id
	 * @return the header of report two
	 */
	public void getHeaderOfReportTwo(String selectedNickname, int userId, String fromDate, String toDate) {

		selectedUserId = userId;
		startDate = fromDate;
		endDate = toDate;

		/**
		 * Um den User inkl. seiner zugehörigen Parameter anzeigen zu können, wird die User-Id aus dem DropDown
		 * im Report-Panel an die Methode getOneUserIdByName geschickt.
		 */
		reportAdministration.getOneUserIdByNickname(selectedNickname, new AsyncCallback<UserRms>() {
			
			@Override
			public void onFailure(Throwable caught) {
			}	

			@Override
			public void onSuccess(UserRms result) {
				
				RootPanel.get("content_wrap").clear();
				
				selectedUserId = result.getId();
				Report r = new Report();
				r.setNickName(result.getNickName());
				r.setLastName(result.getLastName());
				r.setFirstName(result.getFirstName());
				r.setId(result.getId());
				RootPanel.get("content_wrap").clear();
				
				/**
				 * Dieser Abschnitt sammelt die Rauminformationen über den ausgewählten User und stellt ihn
				 * im Kopfbereich des Reports dar.
				 */
				report2.append("<html><head><title></title></head><body>" + "<h2>" + Title + "</h2>"
						+ "<h3>Alle Raumreservierungen für folgenden Nutzer:</h3>"
						+ "Nickname: <i>" + r.getNickName() + "</i></br>" + "Vorname: <i>" + r.getFirstName()
						+ "</i></br>" + "Nachname: <i>" + r.getLastName() + "</i></br>" + "</p>"
						+ "Der Report wurde erstellt: <i>" + creationDate.format(date).toString() + "</i></br>" + "</p>"
						);

				createReportTwoBody(selectedUserId, startDate, endDate);
				
				report1.append("</table>" + "</p>");
				
				/**
				 * Eine Instanz der Klasse Impressum wird erstellt und an dieser Stelle dem report2-String hinzugefügt.
				 */
				ImpressumReport imp = new ImpressumReport();
				report2.append(imp.setImpressum());
				
			}
			
			/**
			 * Der Content-Bereich des Reports wird mithilfe dieser Methode befüllt.
			 * Dafür wird die Room-Id und die beiden Start- und Enddaten des gewünschten Zeitraumes an die Methode
			 * getReservationByRoomIdWithDate geschickt.
			 * <p>Wenn die Methode ein leeres Ergebnis liefert und somit keine Reservierungen vorhanden sind,
			 * wird dies mithilfe einer Meldung angezeigt und das HTML-Widget wird ohne Tabelle erstellt und
			 * dem RootPanel hinzugefügt.
			 * <p> Sind Reservierungen vorhanden, wird dynamisch eine Tabelle erstellt und die teilnehmenden User
			 * werden über die Methode loadMemReport1 angefügt.
			 */
			private void createReportTwoBody(int selectedUserId, String selectFromDate, String selectToDate) {

				reportAdministration.getReservationByHostIdWithDate(selectedUserId, selectFromDate, selectToDate, new AsyncCallback<ArrayList<Reservation>>() {
					
					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Es ist ein Fehler aufgetreten.");
					}

					@Override
					public void onSuccess(ArrayList<Reservation> res) {
						
						if(res.isEmpty()){
							report2.append("<b style=\"color:red;\">Von diesem User sind im gewählten Zeitraum keine Reservierungen vorhanden.</b>"
									+ "</br>" + "</p>");
							
							HTML html2 = new HTML(report2.toString());
							RootPanel.get("content_wrap").clear();
							RootPanel.get("content_wrap").add(html2);
							
						} else{
						
							report2.append("<table class=\"ReportTable\"><tr>");
							
							report2.append("<tr class=\"ReportTable\">" + "<th>" + "Nr." + "</th>" + "<th>" + "Thema" + "</th>" + "<th>" + "Von" + "</th>" + "<th>" + "Bis" + "</th>" + "<th>" + "Teilnehmer" + "</th>" +"</tr>"
									);	
							
							for(int i=0, zaehler= res.size(); i < res.size(); i++, zaehler--){
								loadMemReport2(res.get(i).getId(), res.get(i).getTopic(),res.get(i).getStartTime().toString(),res.get(i).getEndTime().toString(), zaehler );
							 		report2.append("</td>" + "</tr>");
							}
							
						}
					}

				});
				
			}
			
		});
		
	}
	
}	