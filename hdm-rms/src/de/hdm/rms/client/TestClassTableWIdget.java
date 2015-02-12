package de.hdm.rms.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import de.hdm.rms.client.EditorCrudPanel.CreateReservation;
import de.hdm.rms.client.EditorCrudPanel.CreateRoom;
import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;

public class TestClassTableWIdget extends Showcase {

	@Override
	public String getHeadline() {
		// TODO Auto-generated method stub
		return null;
	}

	private Button editBtn = new Button("Eigene Reservierung bearbeiten");
	private Button statusBtn = new Button("Teilnehmerübersicht anzeigen");
	private Label resLabel = new Label("Meine Reservierungen  ");
	private Label resLabelText = new Label(" Hier ist eine Übersicht über die von Ihnen organisierten Veranstaltungen dargestellt. Sie können jederzeit die Reservierung oder die Teilnehmerlisten einsehen und Änderungen vornehmen. Bitte wählen Sie hierzu einfach die entsprechende Veranstaltung aus und klicken Sie auf einen der unten eingeblendeten Buttons - Teilnehmerliste einsehen oder Reservierung bearbeiten.");

	private Label memLabel = new Label("Meine Teilnahmen ");
	private Label memLabelText = new Label("Hier ist eine Übersicht über alle Veranstaltungen dargestellt, zu denen Sie eingeladen wurden. Sie können diese Einladungen entweder annehmen oder ablehnen. Bitte wählen Sie hierzu einfach die entsprechende Veranstaltung aus und klicken Sie auf einen der unten eingeblendeten Buttons - Annehmen oder Ablehnen.");

	private Button yBtn = new Button("Teilnahme bestätigen");
	private Button nBtn = new Button("Teilnahme ablehnen");
	private HorizontalPanel yNPanel = new HorizontalPanel();
private int acceptionStatus = 0; 
	private static List<ReservationListObj> ReservationList;
	private static List<ReservationListObj> ReservationListMember;
	private static List<InvitationListObj> InvitationListMember;

	private static List<InvitationListObj> InvitationList;

	/* Deklaration von Cell-Table für alle Reservierungen */
	CellTable<ReservationListObj> table = new CellTable<ReservationListObj>();

	/* Deklaration von Cell-Table für alle Reservierungen */
	CellTable<InvitationListObj> table1 = new CellTable<InvitationListObj>();

	CellTable<InvitationListObj> InvitationTable = new CellTable<InvitationListObj>();

	private DialogBox dp = new DialogBox();

	private Button cancleBtn = new Button("Schließen");

	private VerticalPanel InvitationPanel = new VerticalPanel();
	private final Label DialogBoxHeadline = new Label("Einladungen anzeigen");

	private ReservationServiceAsync reservationAdministration = GWT.create(ReservationService.class);

	/*
	 * Test Kommentar Listhandler ruft comperator auf und vergleicht solanfe
	 * einträge bis Liste geordnet ist ListHandler<ReservationListObj>
	 * columnSortHandler0 = new
	 * ListHandler<ReservationListObj>(ReservationList);
	 * ListHandler<ReservationListObj> columnSortHandler1 = new
	 * ListHandler<ReservationListObj>(ReservationList);
	 * ListHandler<ReservationListObj> columnSortHandler2 = new
	 * ListHandler<ReservationListObj>(ReservationList);
	 * ListHandler<ReservationListObj> columnSortHandler3 = new
	 * ListHandler<ReservationListObj>(ReservationList);
	 * ListHandler<ReservationListObj> columnSortHandler4 = new
	 * ListHandler<ReservationListObj>(ReservationList);
	 * ListHandler<ReservationListObj> columnSortHandler5 = new
	 * ListHandler<ReservationListObj>(ReservationList);
	 */

	public int getAcceptionStatus(){
 		System.out.println("acceptionStatus getter  : "+acceptionStatus);

		return acceptionStatus;
		
	}
	public void setAcceptionStatus(int acceptionS){
		this.acceptionStatus= acceptionS;
 		System.out.println("acceptionStatus setter  : "+acceptionStatus);

	}
	
	public void loadAcceptionStatus(int resId, int memId){
		
 		reservationAdministration.loadAcceptionStatus(resId, memId, new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Integer result) {
				    setAcceptionStatus(result);
			}
		});
 		Window.alert("acceptionStatus vor return: "+acceptionStatus);
 		System.out.println("acceptionStatus vor return: "+acceptionStatus);
 	}
	
	public List<InvitationListObj> loadAllInvitationDataByOnReservationListObj(
			int userId) {

		reservationAdministration.loadInvitationsById(new AsyncCallback<ArrayList<InvitationListObj>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fehler");
					}

					@Override
					public void onSuccess(ArrayList<InvitationListObj> result) {

						InvitationList = result;

						ListDataProvider<InvitationListObj> dataProvider = new ListDataProvider<InvitationListObj>();
						dataProvider.addDataDisplay(InvitationTable);
						List<InvitationListObj> list = dataProvider.getList();
						dataProvider.setList(InvitationList);

						for (InvitationListObj invitation : InvitationList) {
							list.add(invitation);
						}

					}
				});

		cancleBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dp.hide();

			}
		});
		dp.center();
		dp.show();
		return InvitationList;

	}

	public void loadAllReservationsByHostId(int temp_user_id) {

		reservationAdministration.loadAllReservationsAsList(temp_user_id, new AsyncCallback<ArrayList<ReservationListObj>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fehler");

					}

					@Override
					public void onSuccess(ArrayList<ReservationListObj> result) {
						ReservationList = result;

						table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

						// In diesem Deklarations-Block erstellen wir die 6
						// Spalten der Tabelle.
						TextColumn<ReservationListObj> idColumn = new TextColumn<ReservationListObj>() {
							@Override
							public String getValue(ReservationListObj object) {
								return String.valueOf(object.getId());

							}
						};
						table.addColumn(idColumn, "Reservation-Id");

						TextColumn<ReservationListObj> topicColumn = new TextColumn<ReservationListObj>() {
							@Override
							public String getValue(ReservationListObj object) {
								return String.valueOf(object.getTopic());
							}
						};
						table.addColumn(topicColumn, "Name der Veranstaltung");

						TextColumn<ReservationListObj> dateColumn = new TextColumn<ReservationListObj>() {
							@Override
							public String getValue(ReservationListObj object) {
								return object.getStartTime();
							}
						};
						table.addColumn(dateColumn, "Startzeit");

						TextColumn<ReservationListObj> lenghColumn = new TextColumn<ReservationListObj>() {
							@Override
							public String getValue(ReservationListObj object) {
								return String.valueOf(object.getEndTime()); // lenght
							}
						};
						table.addColumn(lenghColumn, "Endzeit");

						TextColumn<ReservationListObj> roomColumn = new TextColumn<ReservationListObj>() {
							@Override
							public String getValue(ReservationListObj object) {
								return String.valueOf(object.getRoomName());
							}
						};
						table.addColumn(roomColumn, "Raum");

						TextColumn<ReservationListObj> hosterColumn = new TextColumn<ReservationListObj>() {
							@Override
							public String getValue(ReservationListObj object) {
								return String.valueOf(object.getNickname() +" / " + object.getEMail());
  							}
						};
						table.addColumn(hosterColumn, "Veranstalter [Nickname / E-Mail]");

						/*
						 * Test Kommentar DatenProvider wird ausgewählt wird
						 * später ersetzt durch Methoden Aufruf
						 */

						ListDataProvider<ReservationListObj> dataProvider = new ListDataProvider<ReservationListObj>();
						dataProvider.addDataDisplay(table);
						List<ReservationListObj> list = dataProvider.getList();

						dataProvider.setList(ReservationList);

						for (ReservationListObj reservation : ReservationList) {
							list.add(reservation);
						}

						// Add a selection model to handle user selection.
						final SingleSelectionModel<ReservationListObj> selectionModel = new SingleSelectionModel<ReservationListObj>();
						table.setSelectionModel(selectionModel);
						selectionModel.addSelectionChangeHandler(

						new SelectionChangeEvent.Handler() {
							public void onSelectionChange(
									SelectionChangeEvent event) {
								final ReservationListObj selected = selectionModel
										.getSelectedObject();
								if (selected != null) {
									Window.alert("You selected: "
											+ selected.getId());
									editBtn.setEnabled(true);
									statusBtn.setEnabled(true);

									editBtn.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											RootPanel
													.get()
													.add(new EditorCrudPanel().new EditReservation(
															selected.getId()));
										}
									});

									statusBtn
											.addClickHandler(new ClickHandler() {
												public void onClick(
														ClickEvent event) {
													// dp.clear();
													dp.clear();
													InvitationPanel.clear();
													// RootPanel.get().add( new
													// EditorCrudPanel().new
													// ShowInvitationStatus(selected.getId()));

													// Window.alert("a" +
													// selectReservationId);

													loadAllInvitationDataByOnReservationListObj(selected.getId());

													final Label DialogBoxHeadline = new Label(
															"Einladungen anzeigen");

													// RootPanel.get( ).clear();

													InvitationTable
															.setRowCount(20,
																	true);

													InvitationTable
															.setWidth("100%");
													// InvitationTable.setRowData(0,
													// InvitationList);

													InvitationPanel
															.add(DialogBoxHeadline);
													InvitationPanel
															.add(InvitationTable);
													InvitationPanel
															.add(cancleBtn);
													dp.setWidget(InvitationPanel);

													// }

												}
											});

								}
							}
						});

					}
				});
	}

	public void setColumsInvitationTable() {
		
		TextColumn<InvitationListObj> namecol = new TextColumn<InvitationListObj>() {
			@Override
			public String getValue(InvitationListObj object) {
				return String.valueOf(object.getNickname());

			}
		};
		InvitationTable.addColumn(namecol,  "Teilnehmer");
		
		TextColumn<InvitationListObj> resStatus = new TextColumn<InvitationListObj>() {
			@Override
			public String getValue(InvitationListObj object) {
				
				if(object.getAcceptionStatus()==1){
					return String.valueOf("Einladung angenommen.");
				}
				if(object.getAcceptionStatus()==2){
					return String.valueOf("Einladung abgelehnt.");
				}else{
					return String.valueOf("Einladung wurde verschickt.");
				}
				
			}
		};
		InvitationTable.addColumn(resStatus,  "Status der Einladung");

	}

	public void loadAllInvitationData(int temp_user_id) {

		reservationAdministration.loadAllInvitationData(temp_user_id, new AsyncCallback<ArrayList<InvitationListObj>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fehler");

					}

					@Override
					public void onSuccess(ArrayList<InvitationListObj> result) {
						InvitationListMember = result;
 						table1.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

						// In diesem Deklarations-Block erstellen wir die 6
						// Spalten der Tabelle.
						TextColumn<InvitationListObj> idColumn = new TextColumn<InvitationListObj>() {
							@Override
							public String getValue(InvitationListObj object) {
								return String.valueOf(object.getId() );

							}
						};
						table1.addColumn(idColumn, "Reservation-Id");

						TextColumn<InvitationListObj> topicColumn = new TextColumn<InvitationListObj>() {
							@Override
							public String getValue(InvitationListObj object) {
								return String.valueOf(object.getTopic()); 
							}
						};
						table1.addColumn(topicColumn, "Titel der Veranstaltung");

						TextColumn<InvitationListObj> dateColumn = new TextColumn<InvitationListObj>() {
							@Override
							public String getValue(InvitationListObj object) {
								return object.getStartTime();
							}
						};
						table1.addColumn(dateColumn, "Startzeit");

						TextColumn<InvitationListObj> lenghColumn = new TextColumn<InvitationListObj>() {
							@Override
							public String getValue(InvitationListObj object) {
								return String.valueOf(object.getEndTime()); // lenght
							}
						};
						table1.addColumn(lenghColumn, "Endzeit");

						TextColumn<InvitationListObj> roomColumn = new TextColumn<InvitationListObj>() {
							@Override
							public String getValue(InvitationListObj object) {
								return String.valueOf(object.getRoomName());
							}
						};
						table1.addColumn(roomColumn, "Raum");

						TextColumn<InvitationListObj> hosterColumn = new TextColumn<InvitationListObj>() {
							@Override
							public String getValue(InvitationListObj object) {
								return String.valueOf(object.getNickname() +" / " +object.getEMail());
							}
						};
						table1.addColumn(hosterColumn, "Veranstalter [Nickname / E-Mail]");
						

					 

						/*
						 * Test Kommentar DatenProvider wird ausgewählt wird
						 * später ersetzt durch Methoden Aufruf
						 */

						ListDataProvider<InvitationListObj> dataProvider = new ListDataProvider<InvitationListObj>();
						dataProvider.addDataDisplay(table1);
						List<InvitationListObj> list = dataProvider.getList();

						dataProvider.setList(InvitationListMember);

						for (InvitationListObj reservation : InvitationListMember) {
							list.add(reservation);
						}

						// Add a selection model to handle user selection.
						final SingleSelectionModel<InvitationListObj> selectionModel2 = new SingleSelectionModel<InvitationListObj>();
						table1.setSelectionModel(selectionModel2);
						selectionModel2.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
							public void onSelectionChange(SelectionChangeEvent event) {
								final InvitationListObj selected = selectionModel2.getSelectedObject();
								int invStatus =0;
								int accStatusTemp = 0;
								   loadAcceptionStatus(selected.getId(), Integer.parseInt(Cookies.getCookie("SessionUserID")));
								   accStatusTemp=  getAcceptionStatus();
								   
								   Window.alert("accStatusTemp Inhalt vor erster if: "+accStatusTemp);
								
 								if(accStatusTemp == 4){
 	 								Window.alert("accStatusTemp Iin neuer If Status 4 : "+accStatusTemp);

 								}
 								else if (selected != null && accStatusTemp == 0 ) {
									invStatus = 0;

									yBtn.setEnabled(true);
									nBtn.setEnabled(true);
								}else if (accStatusTemp == 1){
									
									nBtn.setEnabled(true);
									yBtn.setEnabled(false);

									nBtn.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											int invStatus=2;
											nBtn.setEnabled(false);
											yBtn.setEnabled(true);
											reservationAdministration.setInviteStatus(invStatus, selected.getId(), Integer.parseInt(Cookies.getCookie("SessionUserID")), new AsyncCallback<Invitation>(){

												@Override
												public void onFailure(Throwable caught) {
													System.out.println("Der Status der Reservierung konnte nicht bearbeitet werden.");
												}

												@Override
												public void onSuccess(Invitation result) {
													Window.alert("Sie haben dieser Reservierung erfolgreich abgesagt:"+selected.getId());

													RootPanel.get("content_wrap").clear();
													RootPanel.get("content_wrap").add(new TestClassTableWIdget());
												}
												
											});
//											System.out.println(selected.getId());
											
										}
									});
									
								}else if (accStatusTemp == 2){
									
									nBtn.setEnabled(false);
									yBtn.setEnabled(true);
									yBtn.addClickHandler(new ClickHandler() {
										public void onClick(ClickEvent event) {
											yBtn.setEnabled(true);
											nBtn.setEnabled(false);
											int invStatus=1;
											reservationAdministration.setInviteStatus(invStatus, selected.getId(), Integer.parseInt(Cookies.getCookie("SessionUserID")), new AsyncCallback<Invitation>(){

												@Override
												public void onFailure(Throwable caught) {
													System.out.println("Der Status der Reservierung konnte nicht bearbeitet werden.");
												}

												@Override
												public void onSuccess(Invitation result) {
													Window.alert("Sie haben dieser Reservierung erfolgreich zugesagt: "+selected.getId());
													
													RootPanel.get("content_wrap").clear();
													RootPanel.get("content_wrap").add(new TestClassTableWIdget());
												}
												
											});
//											System.out.println(selected.getId());
											
										}
									});
								} 
	
					

							

							 
							}
						});

					}
				});
	}

	
	public String getStatusInWord(String status ){
		System.out.println(status + "STATUS-");

		int intZahl =0;
		intZahl = Integer.parseInt(status);
		
		if(intZahl == 0){
			return String.valueOf("Ausbleibend");

		}else if(intZahl == 1){
			return String.valueOf("Angenommen");

		}
		else if(intZahl == 2){
			return String.valueOf("Abgelehnt");

		}
		return null;
		
	}
	@Override
	public void run() {
int tempUserId = Integer.parseInt(Cookies
	     .getCookie("SessionUserID"));
		loadAllReservationsByHostId(tempUserId);
		setColumsInvitationTable();
		loadAllInvitationData(tempUserId);
		// table.setRowData(0, ReservationList);
		// table.setRowCount(20, true);

		table.setWidth("100%");
		table1.setWidth("100%");

		VerticalPanel panel = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();

		editBtn.setEnabled(false);
		statusBtn.setEnabled(false);
		yBtn.setEnabled(false);
		nBtn.setEnabled(false);

		panel.setBorderWidth(1);
		panel.setWidth("100%");
		panel.add(resLabel);
		panel.add(resLabelText);
		panel.add(table);
		hPanel.add(editBtn);
		hPanel.add(statusBtn);
		panel.add(hPanel);

		panel.add(memLabel);
		panel.add(memLabelText);
		panel.add(table1);
		yNPanel.add(yBtn);
		yNPanel.add(nBtn);
		panel.add(yNPanel);

		CreateReservation a = new EditorCrudPanel().new CreateReservation();

		panel.add(a);

		// Add the widgets to the root panel.
		RootPanel.get("content_wrap").add(panel);
		// super.add(panel);

	}

}
