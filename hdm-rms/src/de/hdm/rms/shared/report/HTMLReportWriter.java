package de.hdm.rms.shared.report;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.rms.client.ClientSettings;
import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;
import de.hdm.rms.shared.report.Report;

public class HTMLReportWriter {
	private ReportServiceAsync reportAdministration = ClientSettings
			.getReportService();

	private String Title = "RMS";
	Date date = new Date();
	private DateTimeFormat creationDate = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
	private String reportText;

	// DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	// Date ReportDate = new Date();

	public void getHeaderOfReportTwo(String selectedNickname) {

		reportAdministration.getOneUserIdByNickname(selectedNickname,
				new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(User result) {

						Report r = new Report();
						r.setNickName(result.getNickName());
						r.setLastName(result.getLastName());
						r.setFirstName(result.getFirstName());
						r.setId(result.getId());

						RootPanel.get("content_wrap").clear();
						StringBuffer rs = new StringBuffer();

						rs.append("<html><head><title></title></head><body>" + "<h2>" + Title + "</h2>"
								+ "Nickname: " + r.getNickName() + "</br>" + "Vorname: " + r.getFirstName()
								+ "</br>" + "Nachname: " + r.getLastName() + "</br>" + "</p>"
								+ "Erstellt: " + creationDate.format(date).toString() + "</br>" + "</p>"
								+ "<table>"
								+ "<tr>" + "<th>" + "Benutzer" + "</th>" + "<th>" + "Von" + "</th>" + "<th>" + "Bis" + "</th>" +"</tr>" 
								/* 
								 * Hier for-Schleife einbauen:
								 * Für jedes Reservierungsobjekt, das vom Server zurückgeliefert wird,
								 * ein neues "tr"-Element in die Tabelle einfügen und mit den Objekt-Variablen befüllen.
								 */
								+ "<tr>" + "<td>" + r.getNickName() + "</td>" + "<td>" + "08.30 Uhr" + "</td>" + "<td>" + "12.30 Uhr" + "</td>" + "</tr>"
								+ "</table>");

						HTML html1 = new HTML(rs.toString());
						RootPanel.get("content_wrap").add(html1);

					}

				});

	}
	public void getHeaderOfReportOne(String selectedRoom) {

		reportAdministration.getOneRoomIdByName(selectedRoom,
				new AsyncCallback<Room>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Room result) {

						Report r = new Report();
						r.setRoomName(result.getName());
						r.setCapacity(result.getCapaticity());
						r.setId(result.getId());

						RootPanel.get("content_wrap").clear();
						StringBuffer rs = new StringBuffer();

						rs.append("<html><head><title></title></head><body>" + "<h2>" + Title + "</h2>"
								+ "Nickname: IrgendEinNickname</br>" + "Vorname: IrgendEinVorname</br>" + "Nachname: IrgendEinNickname</br>" + "Raumname: " + r.getRoomName()+ "</p>"
								+ "Erstellt: " + creationDate.format(date).toString() + "</br>" + "</p>"
								+ "<table>"
								+ "<tr>" + "<th>" + "Benutzer" + "</th>" + "<th>" + "Von" + "</th>" + "<th>" + "Bis" + "</th>" +"</tr>" 
								/* 
								 * Hier for-Schleife einbauen:
								 * Für jedes Reservierungsobjekt, das vom Server zurückgeliefert wird,
								 * ein neues "tr"-Element in die Tabelle einfügen und mit den Objekt-Variablen befüllen.
								 */
								+ "<tr>" + "<td>" + r.getNickName() + "</td>" + "<td>" + "08.30 Uhr" + "</td>" + "<td>" + "12.30 Uhr" + "</td>" + "</tr>"
								+ "</table>");

						HTML html1 = new HTML(rs.toString());
						RootPanel.get("content_wrap").add(html1);

					}

				});

	}

	public String getReportText() {
		return this.reportText;
	}

}
