package de.hdm.rms.shared.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.appengine.labs.repackaged.com.google.common.base.Joiner;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.rms.client.ClientSettings;
import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
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
	
//	public class memberToString{
	public String returnString = "" ;

	public String getReturnString () {
		return returnString;		
	}
	
	public void setReturnString (String a) {
		returnString = a;
	}
	
	ReportServiceAsync reportAdministration = ClientSettings.getReportService();
	String b=""; 
	
	public void loadMem(int resId, String top, String startT, String endT, int counter){

		b=""; 
		final String startTime = startT;
		final String endTime = endT;
		final String topic = top;
		final int zaehler = counter;

		reportAdministration.loadMembersForReservation(resId, new AsyncCallback<ArrayList<String>>(){
			
			@Override
			public void onFailure(Throwable caught) {	
				System.out.println("Fehler beim Laden der Teilnehmer "  );

			}
			
			@Override
			public void onSuccess(ArrayList<String> userWithInvitation) {
				
				
				StringBuffer memberStringBuffer = new StringBuffer();
 				report1.append("<tr class=\"ReportTable\">" + "<td>" + zaehler + "</td>" + "<td>" + topic + "</td>" + "<td>" +  startTime  +"</td>" + "<td>" + endTime + "</td>" + "<td> "); 

				for(int i=0; i<userWithInvitation.size();i++){
					memberStringBuffer.append(userWithInvitation.get(i)+", ");
				}
				report1.append( memberStringBuffer.toString()+  "</td>" + "</tr>");
				System.out.println("report1 nach loadMembersForInvitation innerhalb: "+report1);
				
				HTML html1 = new HTML(report1.toString());
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(html1);
				
//				System.out.println("returnString memberStringBuffer.toString()-Methode Inhalt: "+returnString + " B: " + b );
				// returnString = "test1, test2";
//				System.out.println("Inhalt returnString nach harter Definition: "+returnString);
//				System.out.println("return Wert innerhalb der Methode: " +b.toString());
			}
					
		});
//		System.out.println("return Wert aus Klasse: "+returnString);
//		System.out.println("return Wert aus for-Schleife außerhalb der Methode: " +b.toString());
		System.out.println("report1 nach loadMembersForInvitation außerhalb: "+report1);
		
	}
//	}
	
	private String startDate  ;
	private String endDate  ;
	private String Topic;
	String test2;

	/** Eine Instanz von reportAdministration wird erzeugt, welche aus den ClientSettings abgeleitet wird. */
//	private ReportServiceAsync reportAdministration = ClientSettings.getReportService();

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
	 * wird ein StringBuffer verwendet. Mit dessen Hilfe entsteht ein dynamischer String, welcher nach und nach
	 * mit den gewünschten HTML-Parametern befüllt wird.
	 */
	StringBuilder report1 = new StringBuilder();
	StringBuffer report1b = new StringBuffer();

	StringBuffer report2 = new StringBuffer();
	StringBuffer report1Users = new StringBuffer();
	String nameString;
	String memberUserTemp;
	String concatonateNameString;
	ArrayList<String> userNameArray = new ArrayList<>();
	ArrayList<Invitation> invitationMemberIdArray = new ArrayList<>();
	Label memberLabel;

	public void getHeaderOfReportOne(String selectedRoom, int roomId, String fromDate, String toDate) {

		selectedRoomId = roomId;
		startDate = fromDate;
		endDate = toDate;

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
				report1.append("</table>"
						+ "</p>");
				Impressum imp = new Impressum();
				report1.append(imp.setImpressum());
				System.out.println("report1 nach Impressum setzen: "+report1);

			}
			
			/**
			 * Der Content-Bereich des Reports wird mithilfe dieser Methode befüllt.
			 */
			private void createReportOneBody(int selectedRoomId, String selectFromDate, String selectToDate) {

				reportAdministration.getReservationByRoomIdWithDate(selectedRoomId, selectFromDate, selectToDate, new AsyncCallback<ArrayList<Reservation>>() {
					
					@Override
					public void onFailure(Throwable caught) {
						System.out.println("Es ist ein Fehler aufgetreten.");
					}

					@Override
					public void onSuccess(ArrayList<Reservation> res) {
						
							report1.append("<table class=\"ReportTable\"><tr>");
							report1.append(
									"<tr class=\"ReportTable\">" + "<th>" + "Nr." + "</th>" + "<th>" + "Thema" + "</th>" + "<th>" + "Von" + "</th>" + "<th>" + "Bis" + "</th>" + "<th>" + "Teilnehmer" + "</th>" +"</tr>"
									);	
							
							System.out.println("report1 nach createReportOneBody onSuccess: "+report1);
							
							for(int i=0, zaehler= res.lastIndexOf(res); i < res.size(); i++, zaehler--){
							  loadMem(res.get(i).getId(), res.get(i).getTopic(),res.get(i).getStartTime().toString(),res.get(i).getEndTime().toString(), zaehler );
							 		report1.append("</td>" + "</tr>");
									System.out.println("report1 nach loadMem: "+report1);

							}
						System.out.println("report1 vor HTML erzeugen: "+report1);
//						HTML html1 = new HTML(report1.toString());
//						RootPanel.get("content_wrap").clear();
//						RootPanel.get("content_wrap").add(html1);
						
					}

				});
				
			}

		});
		
		;
			
	}	 
	public void loadUserstoArray(ArrayList<Invitation> invitationMemberIdArray){
		for(int i = 0; i < invitationMemberIdArray.size(); i++){
			reportAdministration.getMembersOfReservation(invitationMemberIdArray.get(i).getMemberId(), new AsyncCallback<ArrayList<User>>(){
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fehler memberUsersArray");			
				}
				
				@Override
				public void onSuccess(ArrayList<User> result) {
					Window.alert("getMembersOfReservation Inhalt Array: "+result.get(0).getLastName());
					for(int i = 0; i<5; i++){
//						Window.alert("getMembersOfReservation Inhalt Array: "+result.get(i).getLastName());
						userNameArray.add(result.get(i).getLastName()+", ");
					}
			
				}
		
			});
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

	public void getHeaderOfReportTwo(String selectedNickname, int userId, String fromDate, String toDate) {
		
		selectedUserId = userId;
		startDate= fromDate;
		endDate = toDate;
		
		reportAdministration.getOneUserIdByNickname(selectedNickname, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
			}	

			@Override
			public void onSuccess(User result) {

				RootPanel.get("content_wrap").clear();
				
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
 
			}
		});

		}
	}	