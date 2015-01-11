package de.hdm.rms.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

public class ReportPanel extends VerticalPanel {

	public class createReportOne extends Showcase {

		private ReportServiceAsync reportAdministration = ClientSettings
				.getReportService();
		private final VerticalPanel CreateReportOnePanel1 = new VerticalPanel();
		private final VerticalPanel CreateReportOnePanel2 = new VerticalPanel();
		private final VerticalPanel CreateReportOnePanel3 = new VerticalPanel();
		private final Button createReportOneBtn = new Button(
				"Report 1 erstellen");
private final Label HeadlineReportOne = new Label("Report1");
		private final TextBox Attribut2 = new TextBox();
		final ListBox ListOfRooms = new ListBox();
		private String selectedNickname2;
		private HorizontalPanel HorizontalPanelReportOne = new HorizontalPanel();
		
		private DatePicker DatePickerFromReportOne = new DatePicker();
		private DatePicker DatePickerToReportOne = new DatePicker();
		private final Label DateLabelFromReportOne = new Label("Von");
		private final Label DateLabelTwoReportOne = new Label("Bis");

		private final Label Attribut1Label = new Label(
				"Raum für den Report auswählen:");
		private final Label Attribut2Label = new Label("Zeitraum auswählen:");

		@Override
		public String getHeadline() {
			return null;
		}

		public String getSelectedListBoxIndex(ListBox listOfNicknames,
				int selectedIndex) {
			String selectedNickname = listOfNicknames
					.getItemText(selectedIndex);

			String s1 = "Eigene Pinnwand";
			// Window.alert(" " + selectedNickname);

			return selectedNickname;

		}

		void loadRooms() {
			// Dropdown aller vorhandenen User anzeigen

			// ListOfNicknames.addItem("Eigene Pinnwand:" );
			ListOfRooms.setSize("180px", "35px");
			ListOfRooms.addStyleName("mainmenu-dropdown");

			// Dropdown dem RootPanel zuordnen
			// RootPanel.get("content_wrap").add(ListOfNicknames);

			reportAdministration
					.getAllRooms(new AsyncCallback<ArrayList<Room>>() {

						public void onSuccess(ArrayList<Room> result) {

							for (int i = 0; i < result.size(); i++) {

								ListOfRooms.addItem(result.get(i).getName());

							}

							ListOfRooms.addChangeHandler(new ChangeHandler() {

								public void onChange(ChangeEvent event) {

									selectedNickname2 = getSelectedListBoxIndex(
											ListOfRooms,
											ListOfRooms.getSelectedIndex());
									// ShowUserFromSelectedItem(ListOfNicknames,
									// ListOfNicknames.getSelectedIndex());

								}

								public void ShowRoomFromSelectedItem(
										ListBox ListOfRooms, int selectedIndex) {
									// TODO Auto-generated method stub

									String selectedRoom = ListOfRooms
											.getItemText(selectedIndex);

									reportAdministration.getOneRoomIdByName(
											selectedRoom,
											new AsyncCallback<Room>() {

												@Override
												public void onSuccess(
														Room result) {

												}

												@Override
												public void onFailure(
														Throwable caught) {
													Window.alert("asdasd");
												}

											});

								}

							});

						}

						@Override
						public void onFailure(Throwable caught) {

							Window.alert("Konnte keine Räume finden");

						}
					});

		}

		@Override
		public void run() {
			// bankVerwaltung.getCustomerById(11, new
			// DeleteCustomerCallback(this));
			// Methode die aufgerufen wird bei Clickhandler
			createReportOneBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Window.alert("Report 1 wurde erstellt.");
					Window.open("http://www.google.com/", "_blank", "");

				}
			});
		}

		public createReportOne() {

			loadRooms();
			RootPanel.get("content_wrap").clear();
			CreateReportOnePanel1.add(Attribut1Label);
			CreateReportOnePanel1.add(ListOfRooms);
			CreateReportOnePanel2.add(DateLabelFromReportOne);
			CreateReportOnePanel2.add(DatePickerFromReportOne);
			CreateReportOnePanel3.add(DateLabelTwoReportOne);
			CreateReportOnePanel3.add(DatePickerToReportOne);
			HorizontalPanelReportOne.add(HeadlineReportOne);
			HorizontalPanelReportOne.add(CreateReportOnePanel1);
			HorizontalPanelReportOne.add(CreateReportOnePanel2);
			HorizontalPanelReportOne.add(CreateReportOnePanel3);
			HorizontalPanelReportOne.add(createReportOneBtn);
			RootPanel.get("content_wrap").add(HorizontalPanelReportOne);
			

		}

	}

	public class createReportTwo extends Showcase {

		private ReportServiceAsync reportAdministration = ClientSettings
				.getReportService();
		
		private final HorizontalPanel HorizontalPanelReportTwo = new HorizontalPanel();
		private ReservationServiceAsync reservationAdministration = ClientSettings
				.getReservationService();
		private final Button createReportTwoBtn = new Button(
				"Report 2 erstellen");
private final Label HeadlineReportTwo = new Label("Report2");
		private final TextBox Attribut4 = new TextBox();
		final ListBox ListOfNicknames = new ListBox();
		private String selectedNickname2;
		private DatePicker DatePickerFromReportTwo = new DatePicker();
		private DatePicker DatePickerToReportTwo = new DatePicker();
		private final Label DateLabelFromReportTwo = new Label("Von");
		private final Label DateLabelTwoReportTwo = new Label("Bis");
		private final VerticalPanel CreateReportTwoPanel1 = new VerticalPanel();
		private final VerticalPanel CreateReportTwoPanel2 = new VerticalPanel();
		private final VerticalPanel CreateReportTwoPanel3 = new VerticalPanel();

		private final Label Attribut3Label = new Label(
				"Nutzer für den Report auswählen:");
		private final Label Attribut4Label = new Label("Zeitraum auswählen:");

		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getSelectedListBoxIndex(ListBox listOfNicknames,
				int selectedIndex) {
			String selectedNickname = listOfNicknames
					.getItemText(selectedIndex);

			String s1 = "Eigene Pinnwand";
			// Window.alert(" " + selectedNickname);

			return selectedNickname;

		}

		void loadUsers() {
			// Dropdown aller vorhandenen User anzeigen

			// ListOfNicknames.addItem("Eigene Pinnwand:" );
			ListOfNicknames.setSize("180px", "35px");
			ListOfNicknames.addStyleName("mainmenu-dropdown");

			// Dropdown dem RootPanel zuordnen
			// RootPanel.get("content_wrap").add(ListOfNicknames);

			reportAdministration
					.getAllUsers(new AsyncCallback<ArrayList<User>>() {

						@Override
						public void onSuccess(ArrayList<User> result) {

							for (int i = 0; i < result.size(); i++) {

								ListOfNicknames.addItem(result.get(i)
										.getNickName());

							}

							ListOfNicknames
									.addChangeHandler(new ChangeHandler() {

										public void onChange(ChangeEvent event) {

											selectedNickname2 = getSelectedListBoxIndex(
													ListOfNicknames,
													ListOfNicknames
															.getSelectedIndex());
											// ShowUserFromSelectedItem(ListOfNicknames,
											// ListOfNicknames.getSelectedIndex());

										}

										public void ShowUserFromSelectedItem(
												ListBox listOfNicknames,
												int selectedIndex) {
											// TODO Auto-generated method stub

											String selectedNickname = listOfNicknames
													.getItemText(selectedIndex);

											reportAdministration
													.getOneUserIdByNickname(
															selectedNickname,
															new AsyncCallback<User>() {

																@Override
																public void onSuccess(
																		User result) {

																}

																@Override
																public void onFailure(
																		Throwable caught) {
																	Window.alert("asdasd");
																}

															});

										}

									});

						}

						@Override
						public void onFailure(Throwable caught) {

							Window.alert("Konnte keine User finden");

						}
					});

		}

		@Override
		public void run() {

			// bankVerwaltung.getCustomerById(11, new
			// DeleteCustomerCallback(this));
			// Methode die aufgerufen wird bei Clickhandler

			createReportTwoBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Window.alert("Report 2 wurde erstellt.");
					Window.open("http://www.google.com/", "_blank", "");

				}
			});

		}

		public createReportTwo() {
			loadUsers();
			CreateReportTwoPanel1.add(Attribut3Label);
			CreateReportTwoPanel1.add(ListOfNicknames);
			CreateReportTwoPanel2.add(DateLabelFromReportTwo);
			CreateReportTwoPanel2.add(DatePickerFromReportTwo);
			CreateReportTwoPanel3.add(DateLabelTwoReportTwo);
			CreateReportTwoPanel3.add(DatePickerToReportTwo);
			HorizontalPanelReportTwo.add(HeadlineReportTwo);
			HorizontalPanelReportTwo.add(CreateReportTwoPanel1);
			HorizontalPanelReportTwo.add(CreateReportTwoPanel2);
			HorizontalPanelReportTwo.add(CreateReportTwoPanel3);
			HorizontalPanelReportTwo.add(createReportTwoBtn);
			RootPanel.get("content_wrap").add(HorizontalPanelReportTwo);

		}

	}

}