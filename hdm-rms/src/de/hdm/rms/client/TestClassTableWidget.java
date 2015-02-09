package de.hdm.rms.client;
 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
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
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
 
public class TestClassTableWidget extends Showcase{

	@Override
	public String getHeadline() {
		// TODO Auto-generated method stub
		return null;
	}

	private Button editBtn = new Button("Eigene Reservierung bearbeiten");
	private Button statusBtn = new Button("Teilnehmerübersicht anzeigen");
	private Label resLabel = new Label("Meine Reservierungen   ");
	private Label resLabelText = new Label("Hier ist eine Übersicht über, die von Ihnen organisierten Veranstaltungen, dargestellt. Sie können jederzeit die Reservierung oder die Teilnehmerlisten einsehen und Änderungen vorhnemen. /n Bitte wählen Sie hierzu einfach die entsprechende Veranstaltung aus unf klicken Sie auf einen der unten eingeblendeten Button - Teilnehmerliste einstehen oder Reservierung bearbeiten./n/n");
	private Label memLabel = new Label("Meine Teilnahmen  " );
	private Label memLabelText = new Label("Hier ist eine Übersicht über, die von Ihnen organisierten Veranstaltungen, dargestellt. Sie können jederzeit die Reservierung oder die Teilnehmerlisten einsehen und Änderungen vorhnemen. /n Bitte wählen Sie hierzu einfach die entsprechende Veranstaltung aus unf klicken Sie auf einen der unten eingeblendeten Button - Teilnehmerliste einstehen oder Reservierung bearbeiten./n/n");

	private Button yBtn = new Button("Teilnahme bestätigens");
	private Button nBtn = new Button("Teilnahme ablehnen");
	private HorizontalPanel yNPanel = new HorizontalPanel();
 
	private static List<ReservationListObj> ReservationList;
	private static List<ReservationListObj> ReservationListMember;
	private static List<InvitationListObj> InvitationList;

	/*Deklaration von Cell-Table für alle Reservierungen*/
	CellTable<ReservationListObj> table = new CellTable<ReservationListObj>();
	
	/*Deklaration von Cell-Table für alle Reservierungen*/
	CellTable<ReservationListObj> table1 = new CellTable<ReservationListObj>();
	
	CellTable<InvitationListObj> InvitationTable = new CellTable<InvitationListObj>();

	private DialogBox dp= new DialogBox();
	
	private Button cancelBtn = new Button("Schließen");
	
	private VerticalPanel InvitationPanel = new VerticalPanel();
	private final Label DialogBoxHeadline = new Label("Einladungen anzeigen");
	
	private ReservationServiceAsync reservationAdministration =  GWT.create(ReservationService.class);
	
	/*Test Kommentar Listhandler ruft comperator auf und vergleicht solanfe einträge bis Liste geordnet ist
	ListHandler<ReservationListObj> columnSortHandler0 = new ListHandler<ReservationListObj>(ReservationList);
	ListHandler<ReservationListObj> columnSortHandler1 = new ListHandler<ReservationListObj>(ReservationList);
	ListHandler<ReservationListObj> columnSortHandler2 = new ListHandler<ReservationListObj>(ReservationList);
	ListHandler<ReservationListObj> columnSortHandler3 = new ListHandler<ReservationListObj>(ReservationList);
	ListHandler<ReservationListObj> columnSortHandler4 = new ListHandler<ReservationListObj>(ReservationList);
	ListHandler<ReservationListObj> columnSortHandler5 = new ListHandler<ReservationListObj>(ReservationList);
	*/

	public  List<InvitationListObj>  loadAllInvitationDataByOnReservationId(int reservationId){
			  
		  reservationAdministration.loadInvitationsById( new  AsyncCallback<ArrayList<InvitationListObj>>(){
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("fehler"  );
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
	
		  cancelBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					 
					dp.hide();
				}
		  });
		  
	 		 dp.center();
			 dp.show();
			 return InvitationList;

	 }  

		public void loadAllReservationsByHostId(int temp_user_id){
			
		 	reservationAdministration.loadAllReservationsAsList( new  AsyncCallback<ArrayList<ReservationListObj>>(){
				
		 		@Override
				public void onFailure(Throwable caught) {
					Window.alert("Es ist ein Fehler aufgetreten");

				}
				@Override
				public void onSuccess(ArrayList<ReservationListObj> result) {
					  
					ReservationList = result;
					table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					  
	 				      // In diesem Deklarations-Block erstellen wir die 6 Spalten der Tabelle.
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
					    		  return String.valueOf(object.getNickname());
					    	  }
					      };
					      
					      table.addColumn(topicColumn, "Name der Veranstaltung");

	  				      TextColumn<ReservationListObj> dateColumn   = new TextColumn<ReservationListObj>() {
					        
	  				    	  @Override
	  				    	  public String getValue(ReservationListObj object) {
	  				    		  return object.getTopic();
	  				    	  }
	  				      };
	  				      
					      table.addColumn(dateColumn, "Startdatum");

					      TextColumn<ReservationListObj> lenghColumn = new TextColumn<ReservationListObj>() {
					         
					    	  @Override
					    	  public String getValue(ReservationListObj object) {
					    		  return String.valueOf(object.getNickname()); // lenght
					    	  }
					      };
					      
					      table.addColumn(lenghColumn, "Dauer (in Minuten)");
					      
					      TextColumn<ReservationListObj> roomColumn =  new TextColumn<ReservationListObj>() {
					        
					    	  @Override
					    	  public String getValue(ReservationListObj object) {
					    		  return String.valueOf(object.getRoomName());
					    	  }
					      };
					      
					      table.addColumn(roomColumn, "Raumbezeichnung");
					      
					      TextColumn<ReservationListObj> hosterColumn = new TextColumn<ReservationListObj>() {
					        
					    	  @Override
					    	  public String getValue(ReservationListObj object) {
					    		  return String.valueOf(object.getNickname());
					    	  } 
					      };
					      
					      table.addColumn(hosterColumn, "Veranstalter");
				
					      /*Test Kommentar DatenProvider wird ausgewählt wird später ersetzt durch Methoden Aufruf */
					        
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
					    			  public void onSelectionChange(SelectionChangeEvent event) {
					    				  final ReservationListObj selected = selectionModel.getSelectedObject();
					    				  if (selected != null) {
					    					  Window.alert("You selected: " + selected.getId());
					    					  editBtn.setEnabled(true);
					    					  statusBtn.setEnabled(true);
								      
					    					  editBtn.addClickHandler(new ClickHandler() {
					    						  public void onClick(ClickEvent event) { 
					    							  RootPanel.get().add( new EditorCrudPanel().new EditReservation(selected.getId()));
					    						  }
					    					  });
								      
					    					  statusBtn.addClickHandler(new ClickHandler() {
					    						  public void onClick(ClickEvent event) { 
					    							  
					    							  //dp.clear();
					    							  dp.clear();
					    							  InvitationPanel.clear();
							    					//RootPanel.get().add( new EditorCrudPanel().new ShowInvitationStatus(selected.getId()));
							    					//Window.alert("a" + selectReservationId);
								    			     
					    							  loadAllInvitationDataByOnReservationId( 2);

					    							  final Label DialogBoxHeadline = new Label("Einladungen anzeigen");
 
	 							    				//	RootPanel.get( ).clear();
								    					  
					    							  InvitationTable.setRowCount(20, true);   
					    							  InvitationTable.setWidth("100%");
								   					//InvitationTable.setRowData(0, InvitationList);
								   					  InvitationPanel.add(DialogBoxHeadline);
								   					  InvitationPanel.add(InvitationTable);
								   					  InvitationPanel.add(cancelBtn);
								   					  dp.setWidget(InvitationPanel);

								    			//	}
								    			
											}
										});

					            }
					         }
					      });
					  
				}
				}); 
 		}

		 public void setColumsInvitationTable(){
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
			
			InvitationTable.addColumn(fnColumn, "Vorname");

			TextColumn<InvitationListObj> lnColumn = new TextColumn<InvitationListObj>() {
			
				@Override
				public String getValue(InvitationListObj object) {
					return String.valueOf(object.getLastName());  
				}
			};
			
			InvitationTable.addColumn(lnColumn, "Nachname");

			 TextColumn<InvitationListObj> emColumn = new TextColumn<InvitationListObj>() {
			
				 @Override
				 public String getValue(InvitationListObj object) {
					 return String.valueOf(object.getEMail());
				 }
			};
			
			InvitationTable.addColumn(emColumn, "E-Mail");

			TextColumn<InvitationListObj> statusColumn = new TextColumn<InvitationListObj>() {
			
				@Override
				public String getValue(InvitationListObj object) {
					return String.valueOf(object.getAcceptionStatus());
				}
			};
			
			InvitationTable.addColumn(statusColumn, "Teilnahmestatus");
			
		 }
		 
			public void loadAllMemberDataById(int temp_user_id){
				
			 	reservationAdministration.loadAllReservationsAsList( new  AsyncCallback<ArrayList<ReservationListObj>>(){
					
			 		@Override
					public void onFailure(Throwable caught) {
						Window.alert("fehler"  );

					}
			 		
					@Override
					public void onSuccess(ArrayList<ReservationListObj> result) {
						  ReservationListMember = result;
						  
							table1.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
						     
		 				      // In diesem Deklarations-Block erstellen wir die 6 Spalten der Tabelle.
		 				      TextColumn<ReservationListObj> idColumn = new TextColumn<ReservationListObj>() {
						         
		 				    	  @Override
		 				    	  public String getValue(ReservationListObj object) {
		 				    		  return String.valueOf(object.getId());
						            
		 				    	  }
						      };
						      
						      table1.addColumn(idColumn, "Reservation-Id");

						      TextColumn<ReservationListObj> topicColumn = new TextColumn<ReservationListObj>() {
						     
						    	  @Override
						    	  public String getValue(ReservationListObj object) {
						    		  return String.valueOf(object.getNickname()); // lenght
						    	  }
						      };
						      
						      table1.addColumn(topicColumn, "Name der Veranstaltung");

		  				      TextColumn<ReservationListObj> dateColumn   = new TextColumn<ReservationListObj>() {
						       
		  				    	  @Override
		  				    	  public String getValue(ReservationListObj object) {
		  				    		  return object.getTopic();
		  				    	  }
						      };
						      
						      table1.addColumn(dateColumn, "Startdatum");

						      TextColumn<ReservationListObj> lenghColumn = new TextColumn<ReservationListObj>() {
						        
						    	  @Override
						    	  public String getValue(ReservationListObj object) {
						    		  return String.valueOf(object.getNickname()); // lenght
						    	  }
						      };
						      
						      table1.addColumn(lenghColumn, "Dauer (in Minuten)");
						      
						      TextColumn<ReservationListObj> roomColumn =  new TextColumn<ReservationListObj>() {
						        
						    	  @Override
						    	  public String getValue(ReservationListObj object) {
						    		  return String.valueOf(object.getRoomName());
						    	  }
						      };
						      
						      table1.addColumn(roomColumn, "Raumbezeichnung");
						      
						      TextColumn<ReservationListObj> hosterColumn = new TextColumn<ReservationListObj>() {
						        
						    	  @Override
						    	  public String getValue(ReservationListObj object) {
						    		  return String.valueOf(object.getNickname());
						    	  } 
						      };
						      
						      table1.addColumn(hosterColumn, "Veranstalter");
						      
						      /*Test Kommentar DatenProvider wird ausgewählt wird später ersetzt durch Methoden Aufruf */
						        
							  ListDataProvider<ReservationListObj> dataProvider = new ListDataProvider<ReservationListObj>();
							  dataProvider.addDataDisplay(table1);
							  List<ReservationListObj> list = dataProvider.getList();
						 	  dataProvider.setList(ReservationListMember);
		  
							     for (ReservationListObj reservation : ReservationListMember) {
							       list.add(reservation);
							    }
						      
						      // Add a selection model to handle user selection.
						      final SingleSelectionModel<ReservationListObj> selectionModel2 
						      = new SingleSelectionModel<ReservationListObj>();
						      table1.setSelectionModel(selectionModel2);
						      selectionModel2.addSelectionChangeHandler(
						      
						    		  new SelectionChangeEvent.Handler() {
						    			  public void onSelectionChange(SelectionChangeEvent event) {
						    				  final ReservationListObj selected = selectionModel2.getSelectedObject();
						    				  if (selected != null) {
						    					  //  Window.alert("You selected: " + selected.reservationId);
						    					  yBtn.setEnabled(true);
						    					  nBtn.setEnabled(true);
						            	
						    					  yBtn.addClickHandler(new ClickHandler() {
						    						  public void onClick(ClickEvent event) { 
						    							  nBtn.setEnabled(false);
						    						  }
						    					  });
									      
						    					  nBtn.addClickHandler(new ClickHandler() {
						    						  public void onClick(ClickEvent event) { 
									    			
									    			
						    						  }
						    					  });

						    				  }
						    			  }
						    		  });
						      
					}
			 	}); 
			}
		 
		@Override
		public void run() {
			
		 			loadAllReservationsByHostId(1);
		 			setColumsInvitationTable();
		 			 loadAllMemberDataById(1);
		 			
			   	//    table.setRowData(0, ReservationList);
		 		//    table.setRowCount(20, true);
		 	
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