package de.hdm.rms.client;



import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.User;

public class ReportPanel extends VerticalPanel {

	public class createReportOne extends Showcase  {

		private ReportServiceAsync reportAdministration = ClientSettings.getReportService();
		private final VerticalPanel CreateReportOnePanel = new VerticalPanel();
		private final Button createReportOneBtn = new Button("Report 1 erstellen");
		
		private final TextBox Attribut1 = new TextBox();
		private final TextBox Attribut2 = new TextBox();
		final ListBox ListOfNicknames = new ListBox();

		private final Label Attribut1Label = new Label("Dropdown f�r Attribut 1");
		private final Label Attribut2Label = new Label("Dropdown f�r Attribut 2");
		//private User u;
		
	 
		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		  public void run() {
			   // bankVerwaltung.getCustomerById(11, new DeleteCustomerCallback(this));
			 	// Methode die aufgerufen wird bei Clickhandler
			createReportOneBtn.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {

							Window.alert("Report 1 wurde erstellt.");	
							
							}
					});
			
			  }
		
	
		  public createReportOne(){

			  RootPanel.get("content_wrap").clear();
			  CreateReportOnePanel.add(Attribut1Label);
				CreateReportOnePanel.add(Attribut1);
				CreateReportOnePanel.add(Attribut2Label);
				CreateReportOnePanel.add(Attribut2);
				CreateReportOnePanel.add(createReportOneBtn);
				RootPanel.get("content_wrap").add(CreateReportOnePanel);
				
			}
		
	}
	
	public class createReportTwo extends Showcase  {

		private ReportServiceAsync reportAdministration = ClientSettings.getReportService();
		private final VerticalPanel CreateReportTwoPanel = new VerticalPanel();
		private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();
		private final Button createReportTwoBtn = new Button("Report 2 erstellen");

		private final TextBox Attribut3 = new TextBox();
		private final TextBox Attribut4 = new TextBox();
		final ListBox ListOfNicknames = new ListBox();
		private    String selectedNickname2; 

		private final Label Attribut3Label = new Label("Nutzer für den Report auswählen:");
		private final Label Attribut4Label = new Label("Zeitraum auswählen:");
		//private User u;
		
		 

		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getSelectedListBoxIndex (ListBox listOfNicknames, int selectedIndex) {
	 		String selectedNickname =  listOfNicknames.getItemText(selectedIndex);
	 		
	 		String s1 ="Eigene Pinnwand";
		//	Window.alert(" " + selectedNickname);

			return selectedNickname;
	 		 
		}
		void loadUsers() {
			// Dropdown aller vorhandenen User anzeigen
			;
	 
		//	ListOfNicknames.addItem("Eigene Pinnwand:" );
			ListOfNicknames.setSize("180px", "35px");
			ListOfNicknames.addStyleName("mainmenu-dropdown");
	 
			// Dropdown dem RootPanel zuordnen
//			RootPanel.get("content_wrap").add(ListOfNicknames);

			reportAdministration.getAllUsers(new AsyncCallback<ArrayList<User>>() {
				
				@Override
				public void onSuccess(ArrayList<User> result) {

					for (int i = 0; i < result.size(); i++) {

						ListOfNicknames.addItem(result.get(i).getNickName());

					}
					
					ListOfNicknames.addChangeHandler(new ChangeHandler() {
					 
				 		public void onChange(ChangeEvent event) {
						
							selectedNickname2=	getSelectedListBoxIndex(ListOfNicknames, ListOfNicknames.getSelectedIndex());
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
										
//			 							reportAdministration.getAllPostsObjectsByOneUserId(user_id,new AsyncCallback<ArrayList<Post>> () {
//									 			
//									 				@Override
//													public void onFailure(Throwable caught) {
//
//													}
//
//													@Override
//													public void onSuccess(ArrayList<Post> result) {
//													 	//Window.alert("22----"+result);
//														report6Table.setWidget(1, 0, new Label(String.valueOf(result)));
//													}
//										});  
									 							 	
									} 
															
									@Override
									public void onFailure(Throwable caught) {
										Window.alert("asdasd");
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
			
			   // bankVerwaltung.getCustomerById(11, new DeleteCustomerCallback(this));
			 	// Methode die aufgerufen wird bei Clickhandler
			
			createReportTwoBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Window.alert("Report 2 wurde erstellt.");	
					
					}
			});
			
			  }
	
		
		  public createReportTwo(){
			  	loadUsers();
				CreateReportTwoPanel.add(Attribut3Label);
				CreateReportTwoPanel.add(ListOfNicknames);
//				CreateReportTwoPanel.add(Attribut3);
				CreateReportTwoPanel.add(Attribut4Label);
				CreateReportTwoPanel.add(Attribut4);
				CreateReportTwoPanel.add(createReportTwoBtn);
				RootPanel.get("content_wrap").add(CreateReportTwoPanel);

			}
		
	}
	
	
}