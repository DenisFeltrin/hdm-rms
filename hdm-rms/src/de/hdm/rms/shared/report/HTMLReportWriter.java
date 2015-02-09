package de.hdm.rms.shared.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.rms.client.ClientSettings;
import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.bo.Invitation;
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
	private String startDate  ;
	private String endDate  ;

	/** Eine Instanz von reportAdministration wird erzeugt, welche aus den ClientSettings abgeleitet wird. */
	private ReportServiceAsync reportAdministration = ClientSettings.getReportService();

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
	
	private int tempResId;
private String tempResTopic;
private String startTime;
private String endTime;

	//Test
//	private Date fromDate;
//	private Date toDate;
	
	/** Der Report setzt sich aus mehreren Einzelanweisungen zusammen. Da ein String dies nicht unterstützt,
	 * wird ein StringBuffer verwendet. Mit dessen Hilfe entsteht ein dynamischer String, welcher nach und nach
	 * mit den gewünschten HTML-Parametern befüllt wird.
	 */
	StringBuffer report1 = new StringBuffer();
	StringBuffer report2 = new StringBuffer();
	StringBuffer memberStringBuffer = new StringBuffer();

	ArrayList<Reservation> reservationList = new ArrayList<Reservation> ();
	
	/**
	 * Diese Methode befüllt den bereits genannten StringBuffer für Report 1.
	 * Dabei wird zum Einen ein RaumObjekt erstellt, das mit den gewünschten ReportHeader-Informationen befüllt wird.
	 * Diese Informationen über den ausgewählten Raum werden im Kopfbereich des Reports angezeigt, um kenntlich zu machen,
	 * über welchen Raum die Buchungsinformationen dargestellt werden.
	 * Zum Anderen wird daraufhin die HTML-Tabelle "zusammengebaut". Falls der ausgewählte Raum Raumreservierungen 
	 * enthält, durchläuft eine for-Schleife alle Ergebnisse, die vom Server kommen und schreibt sie in die Tabelle.
	 * Sollte der Raum keine Raumreservierungen enthalten, wird dies ebenfalls in HTML angezeigt.
	 * Dies alles geschieht, sobald der User auf den Button "Übersicht erstellen" geklickt hat.
	 *
	 * @param selectedRoom the selected room
	 * @param roomId the room id
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return the header of report one
	 */
	public void getHeaderOfReportOne(String selectedRoom, int roomId, String fromDate, String toDate) {
		Window.alert("HTMLReportWriter: From= "+fromDate+" To= "+toDate);
		selectedRoomId = roomId;
		startDate = fromDate;
		endDate = toDate;
		
		Window.alert("HTMLReportWriter2: From= "+startDate+" To= "+endDate);

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
				RootPanel.get("content_wrap").clear();

				report1.append("<html><head><title></title></head><body>" + "<h2>" + Title + "</h2>"
						+ "<h3>Alle Raumreservierungen für folgenden Raum:</h3>"
						+ "Raumname: <i>" + r.getRoomName() + "</i></br>" + "Kapazität: <i>" + r.getCapacity()
						+ "</i></br>" + "</p>"
						+ "Der Report wurde erstellt: <i>" + creationDate.format(date).toString() + "</i></br>" + "</p>"
						);
				createReportOneBody(selectedRoomId, startDate, endDate);

 				HTML html2 = new HTML(report1.toString());
				RootPanel.get("content_wrap").add(html2);

			}
			
			/**
			 * Der Content-Bereich des Reports wird mithilfe dieser Methode befüllt.
			 */
			private void createReportOneBody(int selectedRoomId, String selectFromDate, String selectToDate) {

				
				Window.alert("joooo" + selectedRoomId);

//				this.fromDate = selectFromDate;
//				this.toDate = selectToDate;
				Window.alert("HTMLReportWriter: From= "+selectFromDate+" To= "+selectToDate);
				reportAdministration.getReservationByRoomIdWithDate(selectedRoomId, selectFromDate, selectToDate, new AsyncCallback<ArrayList<Reservation>>() {
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("nooo222");
						System.out.println("Es ist ein Fehler aufgetreten.");
					}

					@Override
					public void onSuccess(ArrayList<Reservation> res) {
 
						reservationList = res;
						/**
						 * Hat der Raum keine Raumreservierungen im gewählten Zeitraum zugeordnet, wird folgender Text
						 * angezeigt. Im Anschluss daran wird noch das Impressum hinzugefügt.
						 */
//						if(res.isEmpty()){
//							
//							Window.alert("res leer");
//							report1.append("<b>Für diesen Raum sind im gewählten Zeitraum keine Reservierungen erstellt.</b>"
//									+ "</br>" + "</p>"
//									);
//							
//							Impressum imp = new Impressum();
//							report1.append(imp.setImpressum());
//							
//						/**
//						 * Enthält der Raum im gewählten Zeitraum Raumreservierungen , wird Reihe um Reihe eine
//						 * HTML-Tabelle mit den entsprechenden Parametern befüllt. Im Anschluss daran wird noch das
//						 * Impressum hinzugefügt.
//						 */
//						} else{
							
						getMemberIdOfInvitation(res);
						
						
							Window.alert("res inhalt vorhanden" + res.get(0).getId());
							report1.append("<table class=\"ReportTable\"><tr>");

							report1.append(
								"<tr class=\"ReportTable\">" + "<th>" + "Nr." + "</th>" + "<th>" + "Thema" + "</th>" + "<th>" + "Von" + "</th>" + "<th>" + "Bis" + "</th>" + "<th>" + "Teilnehmer" + "</th>" +"</tr>"
									);
							// +++ 
				 
							
							
							
							


							report1.append("</table>"
									+ "</p>"
									);
							
							Impressum imp = new Impressum();
							report1.append(imp.setImpressum());
						//}
						
						/**
						 * Nachdem der StringBuffer abschließend mit entsprechenden Parametern befüllt wurde,
						 * wird nun ein HTML-Widget erstellt und der StringBuffer mithilfe der toString()-Methode
						 * dort hinein geschrieben. Sobald dies geschehen ist, wird das Widget dem RootPanel hinzugefügt.
						 * Das HTML-Widget ist in der Lage, die HTML-Sprache zu interpretieren und dementsprechend
						 * anzuzeigen.
						 */
						HTML html1 = new HTML(report1.toString());
						RootPanel.get("content_wrap").clear();
						RootPanel.get("content_wrap").add(html1);
						
					}

				});
				
			}

		});
		
		//Hier StringBuffer mit Strings aus den Subklassen zusammenfügen
		//ReportHeadArea rha = new ReportHeadArea();
		//report1.append(rha.setReportHeadArea());
		//ReportBodyArea rba = new ReportBodyArea();
		//report1.append(rba.setReportBodyArea());
		//Impressum imp = new Impressum();
		//report1.append(imp.setImpressum());

	}
	
	
	
	
void  getMemberIdOfInvitation(ArrayList<Reservation> resArray){
	
	
	for(int i=0; i < resArray.size();  ){
		
		 tempResId = resArray.get(i).getId();
		 tempResTopic = resArray.get(i).getTopic();
		Window.alert("resId" + resArray.get(i).getId());
		
		
		tempResId  = 37;
		reportAdministration.getMemberIdOfInvitation(tempResId, new AsyncCallback<ArrayList<Invitation>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Es ist ein Fehler aufgetreten2.");
				
			}

			@Override
			public void onSuccess(ArrayList<Invitation> result) {
				
				
				for(int i=0;     i < result.size();   ){

					Window.alert("getmemberID2: " +result.get(i).getMemberId() );

					reportAdministration.getMembersOfReservation(result.get(i).getMemberId(), new AsyncCallback<ArrayList<UserRms>>() {

						@Override
						public void onFailure(
								Throwable caught) {
							Window.alert("Fehler Invitation");

						}

						@Override
						public void onSuccess(ArrayList<UserRms> result) {

							for(int i=0, zaehler=1; i < result.size(); i++, zaehler++){
Window.alert("Fehler Invitation2" +result.get(i).getLastName() );
							memberStringBuffer.append(result.get(i).getLastName());
						  report1.append("<tr class=\"ReportTable\">" + "<td>" + zaehler + "</td>" + "<td>" + tempResTopic + "</td>" + "<td>" + startTime + "</td>" + "<td>" + endTime + "</td>" + "<td>" + memberStringBuffer + "</td>" + "</tr>"
									);
							i++;

							}
						} 
						 
						
						
					});
					i++;

				}
				
 
				
			}
 
		});
 
		i++;

		
	}}
	
	
	
	
	
	
	
	/**
	 * Diese Methode befüllt den oben genannten StringBuffer für Report 2.
	 * Dabei wird zum Einen ein UserObjekt erstellt, das mit den gewünschten ReportHeader-Informationen befüllt wird.
	 * Diese Informationen über den ausgewählten User werden im Kopfbereich des Reports angezeigt, um kenntlich zu machen,
	 * über welchen User die Buchungsinformationen dargestellt werden.
	 * Zum Anderen wird daraufhin die HTML-Tabelle "zusammengebaut". Falls der ausgewählte User Raumreservierungen erstellt
	 * hat, durchläuft eine for-Schleife alle Ergebnisse, die vom Server kommen und schreibt sie in die Tabelle.
	 * Sollte der User keine Raumreservierungen erstellt haben, wird dies ebenfalls in HTML angezeigt.
	 * Dies alles geschieht, sobald der User auf den Button "Übersicht erstellen" geklickt hat.
	 *
	 * @param selectedNickname the selected nickname
	 * @param userId the user id
	 * @return the header of report two
	 */
	public void getHeaderOfReportTwo(String selectedNickname, int userId) {
		
		selectedUserId = userId;
		reportAdministration.getOneUserIdByNickname(selectedNickname, new AsyncCallback<UserRms>() {

			@Override
			public void onFailure(Throwable caught) {

			}	

			@Override
			public void onSuccess(UserRms result) {

				selectedUserId = result.getId();
				Report r = new Report();
				r.setNickName(result.getNickName());
				r.setLastName(result.getLastName());
				r.setFirstName(result.getFirstName());
				r.setId(result.getId());
				RootPanel.get("content_wrap").clear();

				report2.append("<html><head><title></title></head><body>" + "<h2>" + Title + "</h2>"
						+ "<h3>Alle Raumreservierungen für folgenden Nutzer:</h3>"
						+ "Nickname: <i>" + r.getNickName() + "</i></br>" + "Vorname: <i>" + r.getFirstName()
						+ "</i></br>" + "Nachname: <i>" + r.getLastName() + "</i></br>" + "</p>"
						+ "Der Report wurde erstellt: <i>" + creationDate.format(date).toString() + "</i></br>" + "</p>"
						);
				createReportTwoBody(selectedUserId);

			}

			/**
			 * Der Content-Bereich des Reports wird mithilfe dieser Methode befüllt.
			 */
			private void createReportTwoBody(int selectedUserId) {

				reportAdministration.getReservationByHostId(selectedUserId, new AsyncCallback<ArrayList<Reservation>>() {
					
					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Es ist ein Fehler aufgetreten.");
					}

					@Override
					public void onSuccess(ArrayList<Reservation> res) {

						/**
						 * Hat der User keine Raumreservierungen im gewählten Zeitraum erstellt, wird folgender Text
						 * angezeigt. Im Anschluss daran wird noch das Impressum hinzugefügt.
						 */
						if(res.isEmpty()){
							
							report2.append("<b>Dieser Nutzer hat im gewählten Zeitraum keine Reservierungen erstellt.</b>"
									+ "</br>" + "</p>"
									);
							
							Impressum imp = new Impressum();
							report2.append(imp.setImpressum());
							
						/**
						 * Hat der User Raumreservierungen im gewählten Zeitraum erstellt, wird Reihe um Reihe eine
						 * HTML-Tabelle mit den entsprechenden Parametern befüllt. Im Anschluss daran wird noch das
						 * Impressum hinzugefügt.
						 */
						} else{

							
							report2.append("<table class=\"ReportTable\"><tr>");

							report2.append(
								"<tr class=\"ReportTable\">" + "<th>" + "Nr." + "</th>" + "<th>" + "Thema" + "</th>" + "<th>" + "Von" + "</th>" + "<th>" + "Bis" + "</th>" + "<th>" + "Teilnehmer" + "</th>" +"</tr>"
									);
							
							for(int i=0, zaehler=1; i < res.size(); i++, zaehler++){
								report2.append("<tr class=\"ReportTable\">" + "<td>" + zaehler + "</td>" + "<td>" + res.get(i).getTopic() + "</td>" + "<td>" + res.get(i).getStartTime().toString() + "</td>" + "<td>" + res.get(i).getEndTime().toString() + "</td>" + "<td>" + "Teilnehmer hier aufzählen" + "</td>" + "</tr>"
										);
							}
							
							report2.append("</table>"
									+ "</p>"
									);
							
							Impressum imp = new Impressum();
							report2.append(imp.setImpressum());
							
							
						}
						
						/**
						 * Nachdem der StringBuffer abschließend mit entsprechenden Parametern befüllt wurde,
						 * wird nun ein HTML-Widget erstellt und der StringBuffer mithilfe der toString()-Methode
						 * dort hinein geschrieben. Sobald dies geschehen ist, wird das Widget dem RootPanel hinzugefügt.
						 * Das HTML-Widget ist in der Lage, die HTML-Sprache zu interpretieren und dementsprechend
						 * anzuzeigen.
						 */
						HTML html2 = new HTML(report2.toString());
						RootPanel.get("content_wrap").clear();
						RootPanel.get("content_wrap").add(html2);
						
					}

				});
							
			}
			
		});
		
	}

}