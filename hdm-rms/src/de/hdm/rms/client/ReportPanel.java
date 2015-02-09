package de.hdm.rms.client;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;
import de.hdm.rms.shared.report.HTMLReportWriter;
import de.hdm.rms.shared.report.Report;

public class ReportPanel extends VerticalPanel {

	public class createReportOne extends Showcase {

			private ReportServiceAsync reportAdministration = ClientSettings.getReportService();

			private final VerticalPanel PanelReportOne = new VerticalPanel();
			private final HorizontalPanel InteractionPanel = new HorizontalPanel();
			private final VerticalPanel CreateReportOnePanel1 = new VerticalPanel();
			private final VerticalPanel CreateReportOnePanel2 = new VerticalPanel();
			private final VerticalPanel CreateReportOnePanel3 = new VerticalPanel();
			
			private final Button createReportOneBtn = new Button("Übersicht erstellen");

			private final Label HeadlineLabel = new Label("Report 1");
			private final Label DescriptionLabel = new Label("Dieser Report bietet Ihnen eine Übersicht über alle Raumbuchungen innerhalb eines Raumes im gewünschten Zeitraum. Um eine Übersicht zu generieren, wählen Sie zuerst einen Raum aus dem Dropdown und definieren anschließend den Start- bzw. Endzeitpunkt der Übersicht. Auf Grundlage dieser Informationen wird mit einem Klick auf Übersicht erstellen im nächsten Schritt eine Tabellenübersicht erstellt.");
			private final Label DateLabelFromReportOne = new Label("Zeitraum von:");
			private final Label DateLabelTwoReportOne = new Label("Zeitraum bis:");
			private final Label RoomLabel = new Label("Raum auswählen:");
			
			final ListBox ListOfRooms = new ListBox();
			private String selectedNickname2;
			private int selectedRoomName; 
private int   index;
			private DatePicker DatePickerFromReportOne = new DatePicker();
			private DatePicker DatePickerToReportOne = new DatePicker();
			
			@Override
			public String getHeadline() {
				return null;
			}

			public int getSelectedListBoxIndex(ListBox listOfRooms,int selectedIndex) {

				String selectedRoom = listOfRooms.getItemText(selectedIndex);
				 
				//Window.alert(""+selectedNickname + selectedIndex);
				reportAdministration.getOneRoomIdByName(selectedRoom,new AsyncCallback<Room>() {

																@Override
																public void onSuccess(Room result) {
																	selectedRoomName = result.getId();
 																}

																@Override
																public void onFailure(Throwable caught) {
																	Window.alert("Es ist ein Fehler aufgetreten.");
																}

															});
				
				return selectedRoomName;

			}

			void loadRooms() {

				ListOfRooms.setSize("180px", "35px");
				ListOfRooms.addStyleName("mainmenu-dropdown");
				
				reportAdministration.getAllRooms(new AsyncCallback<ArrayList<Room>>() {
					
					@Override
					public void onSuccess(ArrayList<Room> result) {

						for (int i = 0; i < result.size(); i++) {

							ListOfRooms.addItem(result.get(i).getName());

						}

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Konnte keine Räume finden");
					}

				});

			}

			@Override
			public void run() {

				InteractionPanel.getElement().setAttribute("cellpadding", "5");
				InteractionPanel.addStyleName("ReportPanel");
				createReportOneBtn.addStyleName("ReportBtn");
				HeadlineLabel.addStyleName("headline");
				DescriptionLabel.addStyleName("description");
 
				
				createReportOneBtn.addClickHandler(new ClickHandler() {

					public void onClick(ClickEvent event) {

 
						
						 DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
						    
					//	   Window.alert(fmt.format(dateTest));

						   String dateStringStart = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").format(DatePickerFromReportOne.getValue());
						   String dateStringEnd = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").format(DatePickerToReportOne.getValue());

						   
						    index = ListOfRooms.getSelectedIndex();
						
						String selectedNickname = ListOfRooms.getValue(index);
						int selectedRoomId = getSelectedListBoxIndex(ListOfRooms, ListOfRooms.getSelectedIndex());
						Window.alert("ReportPanel: From= "+dateStringStart+" To= "+dateStringEnd);
//						private DateTimeFormat creationDate = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss");
						
						HTMLReportWriter ReportOneToHTML = new HTMLReportWriter();
						ReportOneToHTML.getHeaderOfReportOne(selectedNickname, selectedRoomId, dateStringStart, dateStringEnd);

					}

				});

			}

			public createReportOne() {

				loadRooms();

				RootPanel.get("content_wrap").clear();
				
				CreateReportOnePanel1.add(RoomLabel);
				CreateReportOnePanel1.add(ListOfRooms);
				CreateReportOnePanel2.add(DateLabelFromReportOne);
				CreateReportOnePanel2.add(DatePickerFromReportOne);
				CreateReportOnePanel3.add(DateLabelTwoReportOne);
				CreateReportOnePanel3.add(DatePickerToReportOne);

				InteractionPanel.add(CreateReportOnePanel1);
				InteractionPanel.add(CreateReportOnePanel2);
				InteractionPanel.add(CreateReportOnePanel3);
				InteractionPanel.add(createReportOneBtn);

				PanelReportOne.add(HeadlineLabel);
				PanelReportOne.add(DescriptionLabel);
				PanelReportOne.add(InteractionPanel);

				RootPanel.get("content_wrap").add(PanelReportOne);

			}

		}

		public class createReportTwo extends Showcase {

			private ReportServiceAsync reportAdministration = ClientSettings.getReportService();

			private final VerticalPanel PanelReportTwo = new VerticalPanel();
			private final HorizontalPanel InteractionPanel = new HorizontalPanel();
			private final VerticalPanel CreateReportTwoPanel1 = new VerticalPanel();
			private final VerticalPanel CreateReportTwoPanel2 = new VerticalPanel();
			private final VerticalPanel CreateReportTwoPanel3 = new VerticalPanel();

			private final Button createReportTwoBtn = new Button("Übersicht erstellen");

			private final Label HeadlineLabel = new Label("Report 2");
			private final Label DescriptionLabel = new Label("Dieser Report bietet Ihnen eine Übersicht über alle Raumbuchungen eines Nutzers im gewünschten Zeitraum. Um eine Übersicht zu generieren, wählen Sie zuerst einen Nutzer aus dem Dropdown und definieren anschließend den Start- bzw. Endzeitpunkt der Übersicht. Auf Grundlage dieser Informationen wird mit einem Klick auf Übersicht erstellen im nächsten Schritt eine Tabellenübersicht erstellt.");

			final ListBox ListOfNicknames = new ListBox();
			private String selectedNickname2;
			private int selectedUserID; 
			
			private DatePicker DatePickerFromReportTwo = new DatePicker();
			private DatePicker DatePickerToReportTwo = new DatePicker();

			private final Label UserLabel = new Label("Nutzer auswählen:");
			private final Label DateLabelFromReportTwo = new Label("Von");
			private final Label DateLabelTwoReportTwo = new Label("Bis");

			private Report r;

			@Override
			public String getHeadline() {
				return null;
			}

			public int getSelectedListBoxIndex(ListBox listOfNicknames,int selectedIndex) {

				String selectedNickname = listOfNicknames.getItemText(selectedIndex);
				 
				//Window.alert(""+selectedNickname + selectedIndex);
				reportAdministration.getOneUserIdByNickname(selectedNickname,new AsyncCallback<UserRms>() {

																@Override
																public void onSuccess(UserRms result) {
																	selectedUserID = result.getId();
 																}

																@Override
																public void onFailure(Throwable caught) {
																	Window.alert("Es ist ein Fehler aufgetreten.");
																}

															});
				
				return selectedUserID;

			}

			void loadUsers() {

				ListOfNicknames.setSize("180px", "35px");
				ListOfNicknames.addStyleName("mainmenu-dropdown");
				
				reportAdministration.getAllUsers(new AsyncCallback<ArrayList<UserRms>>() {
					
					@Override
					public void onSuccess(ArrayList<UserRms> result) {

						for (int i = 0; i < result.size(); i++) {

							ListOfNicknames.addItem(result.get(i).getNickName());

						}

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Konnte keine User finden");
					}

				});

			}

			@Override
			public void run() {

				InteractionPanel.getElement().setAttribute("cellpadding", "5");
				InteractionPanel.addStyleName("ReportPanel");
				createReportTwoBtn.addStyleName("ReportBtn");
				HeadlineLabel.addStyleName("headline");
				DescriptionLabel.addStyleName("description");

				createReportTwoBtn.addClickHandler(new ClickHandler() {

					public void onClick(ClickEvent event) {

						int index = ListOfNicknames.getSelectedIndex();
						String selectedNickname = ListOfNicknames.getValue(index);
						int selectedUserID = getSelectedListBoxIndex(ListOfNicknames, ListOfNicknames.getSelectedIndex());
						
						HTMLReportWriter ReportTwoToHTML = new HTMLReportWriter();
						ReportTwoToHTML.getHeaderOfReportTwo(selectedNickname, selectedUserID);

					}

				});

			}

			public createReportTwo() {

				loadUsers();

				//RootPanel.get("content_wrap").clear();
				
				CreateReportTwoPanel1.add(UserLabel);
				CreateReportTwoPanel1.add(ListOfNicknames);
				CreateReportTwoPanel2.add(DateLabelFromReportTwo);
				CreateReportTwoPanel2.add(DatePickerFromReportTwo);
				CreateReportTwoPanel3.add(DateLabelTwoReportTwo);
				CreateReportTwoPanel3.add(DatePickerToReportTwo);

				InteractionPanel.add(CreateReportTwoPanel1);
				InteractionPanel.add(CreateReportTwoPanel2);
				InteractionPanel.add(CreateReportTwoPanel3);
				InteractionPanel.add(createReportTwoBtn);

				PanelReportTwo.add(HeadlineLabel);
				PanelReportTwo.add(DescriptionLabel);
				PanelReportTwo.add(InteractionPanel);

				RootPanel.get("content_wrap").add(PanelReportTwo);

			}
			
		}

}