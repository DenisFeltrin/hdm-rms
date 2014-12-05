package de.hdm.rms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.rms.client.CreateUser;

class Hdm_rms implements EntryPoint {

	
	public void onModuleLoad() {
		
		Command GeneralProfile = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};
			
		/*UserMainMenuGeneralAdminSettings*/ 
		Command roomcreate = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};
			
		Command roomedit = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};

		/*UserMainMenuGeneralAdminSettings*/ 
		Command profilEdit = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};
		
		Command invitation = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};
		
		/*UserMainMenuReservationGeneral*/ 
		Command createEvent = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};
			
		Command editEvent = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};
		
		/*UserMainMenuReportGeneral*/ 
		Command report1 = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};
		
		Command report2 = new Command() {
			public void execute() {
				RootPanel.get("content_wrap").clear();
				RootPanel.get("content_wrap").add(new CreateUser());
			}
		};		
		
		MenuBar UserMainMenu = new MenuBar( );
			
		MenuBar UserMainMenuGeneralAdminSettings = new MenuBar(true); 
		UserMainMenuGeneralAdminSettings.addItem("Raum anlegen", roomcreate );
		UserMainMenuGeneralAdminSettings.addItem("Raum bearbeiten", roomedit );

		MenuBar UserMainMenuGeneralProfile = new MenuBar(true); 
		UserMainMenuGeneralProfile.addItem("Profil bearbeiten", profilEdit );
		UserMainMenuGeneralProfile.addItem("Meine Einladungen", invitation );

		MenuBar UserMainMenuReservationGeneral = new MenuBar(true); 
		UserMainMenuReservationGeneral.addItem("Veranstaltungen erstellen", createEvent );
		UserMainMenuReservationGeneral.addItem("Veranstaltung bearbeiten", editEvent );
		
		MenuBar UserMainMenuReportGeneral = new MenuBar(true); 
		UserMainMenuReportGeneral.addItem("Report 1", report1 );
		UserMainMenuReportGeneral.addItem("Report 2", report2 );

		UserMainMenu.addItem("Administration", UserMainMenuGeneralAdminSettings);
		UserMainMenu.addItem("Mein Profil", UserMainMenuGeneralProfile);
		UserMainMenu.addItem("Veranstaltungsservice", UserMainMenuReservationGeneral);
		UserMainMenu.addItem("Reportservice", UserMainMenuReportGeneral);
			
		RootPanel.get("head_wrap_right").add(UserMainMenu);
		
	     //CreateUser CreateUserView = new CreateUser();
		 DeleteUser DeleteUserView = new DeleteUser();
		
	 	 //RootPanel.get("content_wrap").add(CreateUserView);
		 RootPanel.get("content_wrap").add(DeleteUserView);
		 
		 UserMainMenu.setStylePrimaryName("menuListStyle");
		 
		 UserMainMenuGeneralAdminSettings.setStylePrimaryName("menuListStyle2");
		 UserMainMenuGeneralProfile.setStylePrimaryName("menuListStyle2");
		 UserMainMenuReservationGeneral.setStylePrimaryName("menuListStyle2");
		 UserMainMenuReportGeneral.setStylePrimaryName("menuListStyle2");

	}
	
}