package de.hdm.rms.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.User;

public class EditorCrudPanel extends VerticalPanel {
	
	  public class CreateUser extends Showcase  {
		  
		    private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
			private final VerticalPanel CreateUserPanel = new VerticalPanel();
			private final Button userRegisterBtn = new Button("Nutzer anlegen");
			private final TextBox firstName = new TextBox();
			private final TextBox lastName = new TextBox();
			private final TextBox nickName = new TextBox();
			private final TextBox email = new TextBox();
			private final Label firstNameLabel = new Label("Vorname");
			private final Label lastNameLabel = new Label("Nachname");
			private final Label nickNameLabel = new Label("Nickname");
			private final Label emailLabel = new Label("Email");
			private User u;
			
			  public void run() {
			   // bankVerwaltung.getCustomerById(11, new DeleteCustomerCallback(this));
			 	// Methode die aufgerufen wird bei Clickhandler
					userRegisterBtn.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							if (firstName.getValue().isEmpty()
									|| lastName.getValue().isEmpty()
									|| nickName.getValue().isEmpty()
									|| email.getValue().isEmpty()) {
								Window.alert("Bitte Felder ausf�llen");
							} else {
								u = new User();
								u.setFirstName(firstName.getValue());
								u.setLastName(lastName.getValue());
								u.setEmailAdress(email.getValue());
								u.setNickName(nickName.getValue());

								reservationAdministration.insertUser(u, new AsyncCallback<Void>() {
									public void onFailure(Throwable caught) {
										Window.alert("Benutzer konnte nicht registriert werden");
									}

									public void onSuccess(Void result) {
										Window.alert("Benutzer registriert");

									}

								});
							}
						}
					});
			     
			  }
			  
		  public CreateUser(){

						CreateUserPanel.add(firstNameLabel);
						CreateUserPanel.add(firstName);
						CreateUserPanel.add(lastNameLabel);
						CreateUserPanel.add(lastName);
						CreateUserPanel.add(nickNameLabel);
						CreateUserPanel.add(nickName);
						CreateUserPanel.add(emailLabel);
						CreateUserPanel.add(email);
						CreateUserPanel.add(userRegisterBtn);
						RootPanel.get("content_wrap").add(CreateUserPanel);

					}

		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}		 
				 
		  }
	  
	  public class EditUser extends Showcase {
		  
		  private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
		  private final VerticalPanel EditUserPanel = new VerticalPanel();
		  private final Button userDeleteBtn = new Button("Nutzer l�schen");
		  private final Button userEditBtn = new Button("Geänderte Nutzerdaten speichern."); 
		  private final TextBox firstName = new TextBox();
		  private final TextBox lastName = new TextBox();
		  private final TextBox nickName = new TextBox();
		  private final TextBox email = new TextBox();
		  private final Label firstNameLabel = new Label("Vorname");
		  private final Label lastNameLabel = new Label("Nachname");
		  private final Label nickNameLabel = new Label("Nickname");
		  private final Label emailLabel = new Label("Email");
		  private int userId = 1; 
		  
		  private final TextBox idBox = new TextBox();
		  private final Label iDLabel = new Label ("User ID");
		  protected boolean updateStatus;
		  
		  public void loadUserData(int userId){
 
				reservationAdministration.OneUserById (userId, new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("User konnte nicht geladen werden.");

					}

					@Override
					public void onSuccess(User result) {
						Window.alert("Folgender User wurde geladen: " +  result.getFirstName());

						firstName.setText(result.getFirstName());
						lastName.setText(result.getLastName());
						nickName.setText(result.getNickName());
						email.setText(result.getEmailAdress());
						
						idBox.setText("Test-ID " + result.getId());
						EditUserPanel.add(iDLabel);
						EditUserPanel.add(idBox);
						
						EditUserPanel.add(firstNameLabel);
						EditUserPanel.add(firstName);
						EditUserPanel.add(lastNameLabel);
						EditUserPanel.add(lastName);
						EditUserPanel.add(nickNameLabel);
						EditUserPanel.add(nickName);
						EditUserPanel.add(emailLabel);
						EditUserPanel.add(email);
						EditUserPanel.add(userDeleteBtn);
						EditUserPanel.add(userEditBtn);
						
						RootPanel.get("content_wrap").add(EditUserPanel);

					}

				});
		  
		  }
		  
		  public void deleteUser(int userId){
				reservationAdministration.deleteUserById (userId, new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("User konnte nicht gelöscht werden.");
					}

					@Override
					public void onSuccess(Void result) {
					}
			  
		  });
		}
		  
		  public Boolean updateUser(User u){
			  
				reservationAdministration.updateUserById (u, new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("User konnte nicht geändert werden.");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Die Nutzerdaten wurden geändert.");						
						
					}
			  
		  });
				return null;
		}
		  
		  public void run() {

			  userEditBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						
						if (firstName.getValue().isEmpty()
								|| lastName.getValue().isEmpty()
								|| nickName.getValue().isEmpty()
								|| email.getValue().isEmpty()) {
								
							Window.alert("Der User konnte nicht geladen werden.");
						} 
						
						else {
							User u = new User();
							firstName.getText();
							lastName.getText();
							nickName.getText();
							email.getText();
							 updateUser(u);
					
						}
					 
				}
				});
			  
			  
			  userDeleteBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
				 deleteUser(3);
						 
				}
				});
			  
		  }
		  
		  public EditUser() {
			 
			  loadUserData(userId);
			   
			}

		@Override
		public String getHeadline() {
			return null;
		}
		  
	  }
	
	  public class CreateRoom extends Showcase{
		  
		  private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
		  private final VerticalPanel CreateRoomPanel = new VerticalPanel();
		  private final Button roomRegisterBtn = new Button("Raum anlegen");
		  private final TextBox name = new TextBox();
		  private final TextBox capaticity = new TextBox();
		  private final Label nameLabel = new Label("Name");
		  private final Label capaticityLabel = new Label("Kapazität");
		  private Room r;

			  	public void run () {
			  		
			  		roomRegisterBtn.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							if (name.getValue().isEmpty()
									|| capaticity.getValue().isEmpty()) {
									
								Window.alert("Bitte Felder ausfüllen");
							} else {
								r = new Room();
								r.setName(name.getValue());
								r.setCapaticity(capaticity.getValue());

								reservationAdministration.insertRoom(r, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
									}

									@Override
									public void onSuccess(Void result) {
										Window.alert("Raum angelegt");

									}

								});
							}
					}
					});
			  	}
			  		  	
			  public CreateRoom(){
	 				 
					RootPanel.get("content_wrap").clear();
					CreateRoomPanel.add(nameLabel);
					CreateRoomPanel.add(name);
					CreateRoomPanel.add(capaticityLabel);
					CreateRoomPanel.add(capaticity);
					CreateRoomPanel.add(roomRegisterBtn);
					RootPanel.get("content_wrap").add(CreateRoomPanel);

				}

			@Override
			public String getHeadline() {
				return null;
			}

	  }
	    
	  public class EditRoom extends Showcase {
		  
		  	private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
		  
		  	private final VerticalPanel EditRoomPanel = new VerticalPanel();
			private final Button roomDeleteBtn = new Button("Raum löschen");
			private final Button roomEditBtn = new Button("Geänderte Raumdaten speichern.");
			private final TextBox name = new TextBox();
			private final TextBox capaticity = new TextBox();
			private final Label nameLabel = new Label("Name");
			private final Label capaticityLabel = new Label("Kapazität");
			private int roomId = 1; 

			private Room r;

			  public void loadRoomData(int roomId){
				  
					reservationAdministration.OneRoomById (roomId, new AsyncCallback<Room>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Raum konnte nicht geladen werden.");

						}

						@Override
						public void onSuccess(Room result) {
							Window.alert("Folgender Raum wurde geladen: " +  result.getName());

							capaticity.setText(result.getCapaticity());
							name.setText(result.getName());
							
							EditRoomPanel.add(nameLabel);
							EditRoomPanel.add(name);
							EditRoomPanel.add(capaticityLabel);
							EditRoomPanel.add(capaticity);

							EditRoomPanel.add(roomEditBtn);
							EditRoomPanel.add(roomDeleteBtn);
							
							  RootPanel.get("content_wrap").add(EditRoomPanel);

						}

					});
			  
			  }
			  
			  public void deleteRoom(int roomId){
					reservationAdministration.deleteRoomById (roomId, new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Raum konnte nicht gelöscht werden.");
						}

						@Override
						public void onSuccess(Void result) {
						}
				  
			  });
			}
			  
			  public Boolean updateRoom(Room r){
				  
					reservationAdministration.updateRoomById (r, new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Raum konnte nicht geändert werden.");
						}

						@Override
						public void onSuccess(Void result) {
							Window.alert("Die Raumdaten wurden geändert.");						
							
						}
				  
			  });
					return null;
			}
			
			  public void run() {

				  roomEditBtn.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							
							if (name.getValue().isEmpty()
									|| capaticity.getValue().isEmpty()) {
									
								Window.alert("Der Raum konnte nicht geladen werden.");
							} 
							
							else {
								Room r = new Room();
								name.getText();
								capaticity.getText();
								 updateRoom(r);
						
							}
						 
					}
					});
				  
				  
				  roomDeleteBtn.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
					 deleteRoom(3);
							 
					}
					});
				  
			  }
			
			  public EditRoom() {
					 
				  loadRoomData(roomId);
				   
				}

			@Override
			public String getHeadline() {
				return null;
			}
		  
	  }
	  
	  public class CreateInvitation extends Showcase {
			
		  	private final VerticalPanel CreateInvitationPanel = new VerticalPanel();
			private final Button sendInvitationBtn = new Button("Einladung verschicken");
			private final TextBox nickName = new TextBox();
			private final TextBox AcceptionStatus = new TextBox();
			private final Label nickNameLabel = new Label("Nickname");
			private int  memberId = 2;
			private String  accst = "2";
			private Invitation i;
			
		    private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();

			public CreateInvitation() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(CreateInvitationPanel);
				CreateInvitationPanel.add(nickNameLabel);
				CreateInvitationPanel.add(nickName);
				 
				CreateInvitationPanel.add(sendInvitationBtn);
			}
			
			public void run(){
				
				sendInvitationBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (nickName.getValue().isEmpty()){
							Window.alert("Bitte einen Nutzer auswählen");
						} else {
							i = new Invitation();
							i.setMemberId(memberId);
							i.setAcceptionStatus(Integer.parseInt(accst ) );
							 			
							reservationAdministration.insertInvitation(i, new AsyncCallback<Void>() {

								public void onFailure(Throwable caught) {
									Window.alert("Die Einladung wurde aufgrund eines Fehlers nicht verschickt.");

								}

								public void onSuccess(Void result) {
									Window.alert("Die Einladung wurde verschickt.");

								}

							});
																		
						}
					}
				});
						
			}

			@Override
			public String getHeadline() {
				return null;
			}
	  }
	  
	  public class EditInvitation extends Showcase {
		  
		  private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
		  
		  private final VerticalPanel EditInvitationPanel = new VerticalPanel();
		  private final Button deleteInvitationBtn = new Button("Einladung verschicken");
		  private final Button editInvitationBtn = new Button("Geänderte Einladungsdaten verschicken.");
		  private final TextBox nickName = new TextBox();
		  private final TextBox AcceptionStatus = new TextBox();
		  private final Label nickNameLabel = new Label("Nickname");
		  private int  memberId = 2;
		  private String  accst = "2";
		  
		  private Invitation i;
			
			public void run() {
				
				editInvitationBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (nickName.getValue().isEmpty()) {
							Window.alert("Die Einladung konnte nicht geladen werden.");
							
						} else {
							
							//Einladung editieren
							
						}
						
					}
				});
				
			}
		  
			public EditInvitation() {
				
				RootPanel.get("content_wrap").clear();
				
			}

			@Override
			public String getHeadline() {
				return null;
			}
			
	  }
	  
	  public class CreateReservation extends Showcase {
		 
		  private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
		  private final VerticalPanel CreateReservationPanel = new VerticalPanel();
		  private final VerticalPanel dataSmallPanelLeft = new VerticalPanel();
		  private final VerticalPanel dataSmallPanelRight = new VerticalPanel();
		  private final HorizontalPanel dataPanel = new HorizontalPanel();
		  private final HorizontalPanel userPanel = new HorizontalPanel();
		  private final VerticalPanel userPanelLabel = new VerticalPanel();

		  private final HorizontalPanel timeBarPanel = new HorizontalPanel();


		  private final Button userAddBtn = new Button("Nutzer zur Teilnehmerliste hinzufügen");
		  private final Button sendReservationBtn = new Button("Reservierung erstellen.");
		//  private final TextBox startTime = new TextBox();
		  private final ListBox startTimeHour = new ListBox();
		  private final ListBox startTimeMinutes = new ListBox();

		  private ListBox   endTimeHour = new ListBox();
		  private ListBox   endTimeMinutes = new ListBox();


		  private final ListBox roomDropdown = new ListBox();
		  private final ListBox nicknameDropdown = new ListBox();
		  private final TextBox topicBox = new TextBox();
		  private final Label startTimeLabel = new Label("Beginn der Reservierung");
		
		  private final Label lengthLabel = new Label("Dauer der Reservierung");
		  private final Label roomLabel = new Label("Raum auswählen");
		  private final Label nickNameLabel = new Label("Nutzer einladen");
		  private final Label topicLabel = new Label("Veranstaltungsbeschreibung");
		  private Reservation re;
		  
		  
		  public CreateReservation(){
			  	//super.get("content_wrap").clear();
			  DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
			    DateBox startDay = new DateBox();
			    startDay.setFormat(new DateBox.DefaultFormat(dateFormat));
			    startDay.getDatePicker().setYearArrowsVisible(true);
			    
				CreateReservationPanel.add(topicLabel);
				CreateReservationPanel.add(topicBox);
				
				CreateReservationPanel.add(roomLabel);
				CreateReservationPanel.add(roomDropdown);

				dataSmallPanelLeft.add(startTimeLabel);
				
				timeBarPanel.add(startTimeHour );
				timeBarPanel.add( startTimeMinutes);
				timeBarPanel.add(endTimeHour );
				timeBarPanel.add(endTimeMinutes );
				dataSmallPanelLeft.add(timeBarPanel);
				dataSmallPanelRight.add(startDay);

				
				dataPanel.add(dataSmallPanelRight);
				dataPanel.add(dataSmallPanelLeft);
				CreateReservationPanel.add(dataPanel);
			 
				CreateReservationPanel.add(nickNameLabel);
 				userPanel.add(nicknameDropdown);
				userPanel.add(userAddBtn);
				CreateReservationPanel.add(userPanel);

					
				CreateReservationPanel.add(sendReservationBtn);
				super.add(CreateReservationPanel);

		  }
		  
		  public void run() {
			  
			  topicBox.setHeight("20px");
			  topicBox.setWidth("700px");

			  roomDropdown.setHeight("35px");
			  roomDropdown.setWidth("700px");
			  
				sendReservationBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
				/*		if (startTime.getValue().isEmpty()
								|| length.getValue().isEmpty()
								|| roomDropdown.getValue().isEmpty()
								|| nicknameDropdown.getValue().isEmpty()
								|| topicBox.getValue().isEmpty()) {
							Window.alert("Bitte alle Felder ausfüllen.");
							
						} else {
							re = new Reservation();
							re.setStartTime(Integer.parseInt(startTime.getValue()));
							re.setLength(  Integer.parseInt(length.getValue()));
 							re.setHostId(Integer.parseInt(nicknameDropdown.getText()) );
							re.setRoomId( Integer.parseInt(roomDropdown.getText()) );
							re.setTopic(topicBox.getValue());

							reservationAdministration.insertReservation(re, new AsyncCallback<Void>() {

								public void onFailure(Throwable caught) {
									Window.alert("Die Einladung wurde aufgrund eines Fehlers nicht verschickt.");
								}

								public void onSuccess(Void result) {
									Window.alert("Die Einladung wurde verschickt.");

								}

							});
						}*/
					}
				});
			  
		  }

		@Override
		public String getHeadline() {
			return null;
		}
			
	  }  

  public class EditReservation extends Showcase {
	  
	 	private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
	 	private final VerticalPanel EditReservationPanel = new VerticalPanel();
	 	private final HorizontalPanel ButtonPanel = new HorizontalPanel();
		private final Button deleteReservationBtn = new Button("Reservierung löschen.");
		private final Button cancleReservationBtn = new Button("Abbrechen .");
		private final Button editReservationBtn = new Button("Reservierungsdaten werden geändert.");
		private final TextBox startTime = new TextBox();
		private final TextBox length = new TextBox();
		private final TextBox room = new TextBox();
		private final TextBox nickname = new TextBox();
		private final TextBox topic = new TextBox();
		private final Label startTimeLabel = new Label("Beginn der Reservierung");
		private final Label lengthLabel = new Label("Dauer der Reservierung");
		private final Label roomLabel = new Label("Raum auswählen");
		private final Label nickNameLabel = new Label("Nutzer einladen");
		private final Label topicLabel = new Label("Veranstaltungsbeschreibung");
		private final Label DialogBoxHeadline = new Label("Reservierung bearbeiten");
		private DialogBox dp= new DialogBox();
		private Reservation re;
 		
		  public void loadReservationData(int reservationId){
			
			  cancleReservationBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						 dp.hide();
						
					}
				});
				  
					EditReservationPanel.add(DialogBoxHeadline);
					ButtonPanel.add(cancleReservationBtn);
					EditReservationPanel.add(startTimeLabel);
					EditReservationPanel.add(startTime);
					EditReservationPanel.add(lengthLabel);
					EditReservationPanel.add(length);
					EditReservationPanel.add(nickNameLabel);
					EditReservationPanel.add(nickname);
					EditReservationPanel.add(roomLabel);
					EditReservationPanel.add(room);
					EditReservationPanel.add(topicLabel);
					EditReservationPanel.add(topic);
					
					 ButtonPanel.add(deleteReservationBtn);
						ButtonPanel.add(editReservationBtn);
						EditReservationPanel.add(ButtonPanel);

				reservationAdministration.OneReservationById (1, new AsyncCallback<Reservation>() {
//
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Reservierung konnte nicht geladen werden.");
//
					}
//
					@Override
					public void onSuccess(Reservation result) {
						Window.alert("Folgende Reservierung wurde geladen: " +  result.getTopic());
						

					 	RootPanel.get("content_wrap").add(EditReservationPanel);

//
					}
//
				});
			//	  setWidget(EditReservationPanel);

 		  
		  }
		
		public Boolean updateReservation(Reservation r){
			  
				reservationAdministration.updateReservationById (r, new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Reservierung konnte nicht ge�ndert werden.");
					}
//
					@Override
					public void onSuccess(Void result) {
						Window.alert("Die Reservierungsdaten wurden ge�ndert.");						
						
					}
			  
		  });
				return null;
			}
		
		  public void run() {
 			/*  editReservationBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						if (startTime.getValue().isEmpty()
								|| length.getValue().isEmpty()
								|| room.getValue().isEmpty()
								|| nickname.getValue().isEmpty()
								|| topic.getValue().isEmpty()) {
							Window.alert("Die Reservierung konnte nicht geladen werden.");
							
						} else {
							
							Reservation r = new Reservation();
							startTime.getText();
							length.getText();
							room.getText();
							nickname.getText();
							topic.getText();
							 updateReservation(r);
							
						}
						
					}
				});*/

		

			 

		  }
		
		public EditReservation() {
			int reservationId = 1;
		 	RootPanel.get( ).clear();
 				  loadReservationData(reservationId);
 				 dp.setWidget(EditReservationPanel);
 				 dp.center();
 				 dp.show();
 				 
 				 
		}
//

		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}
	 
	  
  }

  
  
  
  
  public   class ShowInvitationStatus extends Showcase {
 
		private DialogBox dp= new DialogBox();
		private Button cancleBtn = new Button();

		private VerticalPanel InvitationPanel = new VerticalPanel();
		private final Label DialogBoxHeadline = new Label("Einladungen anzeigen");
		private Reservation re;
		private int selectReservationId;
		private        List<InvitationListObj> InvitationList;
 
		  private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();

		  CellTable<InvitationListObj> InvitationTable = new CellTable<InvitationListObj>();


		
		public ShowInvitationStatus( int selectReservationId){
			RootPanel.get( ).clear();
			
			  loadAllInvitationDataByOnReservationId(selectReservationId);
			// Window.alert("a" + selectReservationId);
			 dp.setWidget(InvitationPanel);
			 dp.center();
			 dp.show();
			

		} 
		
		
		
		  public  List<InvitationListObj>  loadAllInvitationDataByOnReservationId(int reservationId){
 			
			  
				reservationAdministration.loadInvitationsById( new  AsyncCallback<ArrayList<InvitationListObj>>(){
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fehler"  );
					}
					@Override
					public void onSuccess(ArrayList<InvitationListObj> result) {
						
 						 InvitationList = result;
 						 
 						 
 						 for(int i =0; i < result.size(); i++){
 							 System.out.println(result.toString());
 						 }
 						//InvitationList.addAll(result);
 						
 						// Window.alert("a" + selectReservationId);
 					TextColumn<InvitationListObj> idColumn = new TextColumn<InvitationListObj>() {
 			       @Override
 			       public String getValue(InvitationListObj object) {
 			          return String.valueOf(object.getId());
 			          
 			       }
 			    };
 			    InvitationTable.addColumn(idColumn, "Teilnehmer");
 			    
 			    

 			     TextColumn<InvitationListObj> fnColumn = new TextColumn<InvitationListObj>() {
 			   @Override
 			   public String getValue(InvitationListObj object) {
 			      return String.valueOf(object.getFirstName());
 			      
 			   }
 			};
 			InvitationTable.addColumn(idColumn, "Vorname");



 			TextColumn<InvitationListObj> lnColumn = new TextColumn<InvitationListObj>() {
 			@Override
 			public String getValue(InvitationListObj object) {
 			  return String.valueOf(object.getLastName());
 			  
 			}
 			};
 			InvitationTable.addColumn(idColumn, "Nachname");


 			 TextColumn<InvitationListObj> emColumn = new TextColumn<InvitationListObj>() {
 			@Override
 			public String getValue(InvitationListObj object) {
 			return String.valueOf(object.getEMail());

 			}
 			};
 			InvitationTable.addColumn(idColumn, "E-Mail");



 			TextColumn<InvitationListObj> statusColumn = new TextColumn<InvitationListObj>() {
 			@Override
 			public String getValue(InvitationListObj object) {
 			return String.valueOf(object.getAcceptionStatus());

 			}
 			};
 			InvitationTable.addColumn(idColumn, "Teilnahmestatus");
 			
 			
 		     
		   
 				      
 				      ListDataProvider<InvitationListObj> dataProvider = new ListDataProvider<InvitationListObj>();
 					   
 					   List<InvitationListObj> list = dataProvider.getList();
  				 	   dataProvider.setList(InvitationList);


 					     for (InvitationListObj invitation : InvitationList) {
 					       list.add(invitation);
 					    }
  					    dataProvider.addDataDisplay(InvitationTable);

 					     
 					     InvitationTable.setRowCount(20, true);
 					      
 					     InvitationTable.setWidth("100%");
 					     InvitationTable.setRowData(0, InvitationList);
 					     
 					    InvitationPanel.add(DialogBoxHeadline);
 						  InvitationPanel.add(InvitationTable);
 						  InvitationPanel.add(cancleBtn);
 					     
 						
					}
					});
 			
			  
			  

			  cancleBtn.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						 dp.hide();
						
					}
				});
			return InvitationList;
			  
			  
			  
 
		  }  
		 
	 
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
	  
	@Override
	public String getHeadline() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void run() {
	//	 loadAllInvitationDataByOnReservationId(selectReservationId);
	
	      		
	}
	   
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  }  
  
  
  
}