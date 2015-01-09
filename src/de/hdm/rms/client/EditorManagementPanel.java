package de.hdm.rms.client;

 import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.User;

public class EditorManagementPanel extends Showcase {
	  private ReportServiceAsync reportAdministration = ClientSettings.getReportService();

		final VerticalPanel ManageReservationsPanel = new VerticalPanel();
		final Label reservationManagementLabel = new Label("In dieser Tabelle sehen Sie" +
				"alle Reservierungen, die mit Ihrem Account verbunden sind." +
				"In dieser Tabelle k?nnen Sie als Veranstalter der Reservierung diese editieren," +
				"oder falls Sie eingeladen sind, Zu- bzw. Absagen.");
		//Tabelle
		final Button editReservationBtn = new Button("Reservierung bearbeiten.");
		final Button deleteReservationBtn = new Button("Reservierung l?schen.");
		final Button newReservationBtn = new Button("Neue Reservierung erstellen.");
		final ListBox ListOfNicknames = new ListBox();
		final Button confirmInvitationBtn = new Button("Zusagen");
		final Button refuseInvitationBtn = new Button("Absagen");
	
		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}

		
		
		
		public void loadAllUserIntoListBox(){
			
			final String selectedNickname2; 

			//	ListOfNicknames.addItem("Eigene Pinnwand:" );
				ListOfNicknames.setSize("180px", "35px");
				ListOfNicknames.addStyleName("mainmenu-dropdown");
		 
				// Dropdown dem RootPanel zuordnen
//				RootPanel.get("content_wrap").add(ListOfNicknames);

				reportAdministration.getAllUsers(new AsyncCallback<ArrayList<User>>() {
					
					@Override
					public void onSuccess(ArrayList<User> result) {

						for (int i = 0; i < result.size(); i++) {

							ListOfNicknames.addItem(result.get(i).getNickName());

						}
						
						ListOfNicknames.addChangeHandler(new ChangeHandler() {
						 
					 		public void onChange(ChangeEvent event) {
							
							//	selectedNickname2=	getSelectedListBoxIndex(ListOfNicknames, ListOfNicknames.getSelectedIndex());
							//	ShowUserFromSelectedItem(ListOfNicknames, ListOfNicknames.getSelectedIndex());
								
								
		 					
								
							}
							
							public void ShowUserFromSelectedItem(ListBox listOfNicknames, int selectedIndex) {
								// TODO Auto-generated method stub
								
		 						String selectedNickname =  listOfNicknames.getItemText(selectedIndex);

		 						reportAdministration.getOneUserIdByNickname(  selectedNickname, new AsyncCallback<User>() {
									 
										@Override
										 public void onSuccess(User result) {
											
				 							Integer user_id = result.getId();
															 
										//Window.alert(""+user_id);
											
//				 							reportAdministration.getAllPostsObjectsByOneUserId(user_id,new AsyncCallback<ArrayList<Post>> () {
//										 			
//										 				@Override
//														public void onFailure(Throwable caught) {
	//
//														}
	//
//														@Override
//														public void onSuccess(ArrayList<Post> result) {
//														 	//Window.alert("22----"+result);
//															report6Table.setWidget(1, 0, new Label(String.valueOf(result)));
//														}
//											});  
										 							 	
										} 
																
										@Override
										public void onFailure(Throwable caught) {
											Window.alert("Fehler");
										}
											
								 }
										 );	
													
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
			
			newReservationBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

				}
			});
			
		}
		
		public void showReservationButtons(){
			
			editReservationBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

				}
			});
			
		}
		
		public void showInvitationButtons(){
			
			confirmInvitationBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

				}
			});
			
			refuseInvitationBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

				}
			});
			
		}
		
		public EditorManagementPanel(){
			
			ManageReservationsPanel.add(reservationManagementLabel);
			//ManageReservationsPanel.add(Tabelle);
			ManageReservationsPanel.add(newReservationBtn);
			RootPanel.get("content_wrap").add(ManageReservationsPanel);
			
		}
		
	}