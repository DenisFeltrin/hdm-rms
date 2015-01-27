package de.hdm.rms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hdm.rms.client.EditorCrudPanel.CreateUser;
 

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
 import de.hdm.rms.shared.LoginInfo;

class Hdm_rms implements EntryPoint, ClickHandler   {
	
	HorizontalPanel  menuPanel = new HorizontalPanel();
	Button reportBtn = new Button( );
   	Button profilBtn = new Button( );
	Button startBtn = new Button( );
	Button raumreservierung_btn = new Button( );
	Button einstellungen_btn = new Button( );
	
	private CreateUser createUserObj;
	    
	public Hdm_rms() {
 		menuPanel.add(startBtn);
		menuPanel.add(raumreservierung_btn);
		menuPanel.add(reportBtn);
		menuPanel.add(profilBtn);
		menuPanel.add(einstellungen_btn);
		
		startBtn.addClickHandler(this);
		raumreservierung_btn.addClickHandler(this);
		reportBtn.addClickHandler(this);
		profilBtn.addClickHandler(this);
		einstellungen_btn.addClickHandler(this);
 	  }
      
	public void onModuleLoad() {


		
		reportBtn.setHTML("<img border='0' src='img/report_btn.png' />");
		profilBtn.setHTML("<img border='0' src='img/profil_btn.png' />");
		startBtn.setHTML("<img border='0' src='img/startseite_btn.png' />");
		raumreservierung_btn.setHTML("<img border='0' src='img/raumreservierung_btn.png' />");
		einstellungen_btn.setHTML("<img border='0' src='img/einstellungen_btn.png' />");
			
		startBtn.setStylePrimaryName("startBtn");
		raumreservierung_btn.setStylePrimaryName("raumreservierung_btn");
		reportBtn.setStylePrimaryName("reportBtn");
		profilBtn.setStylePrimaryName("profilBtn");
		einstellungen_btn.setStylePrimaryName("einstellungen_btn");

		RootPanel.get("head_wrap_right").add(menuPanel);
		RootPanel.get("Impressum").add(new Impressum());
	}

	@Override
	public void onClick(ClickEvent event) {
		  // note that in general, events can have sources that are not Widgets.
	    Widget sender = (Widget) event.getSource();

	    if (sender == startBtn) {
	      // handle b1 being clicked
	    	RootPanel.get("content_wrap").clear();
	    	
	    } else if (sender == raumreservierung_btn) {
	    	RootPanel.get("content_wrap").clear();
			RootPanel.get("content_wrap").add( new EditorCrudPanel().new CreateReservation());
			//RootPanel.get("content_wrap").add( new EditorPanel().new EditReservation());

	    } else if (sender == reportBtn) {
	    	RootPanel.get("content_wrap").clear();
			//RootPanel.get("content_wrap").add( new EditorPanel().new CreateInvitation());
			//RootPanel.get("content_wrap").add( new EditorPanel().new EditInvitation());
	    	RootPanel.get("content_wrap").add(new ReportPanel().new createReportOne());
	    	RootPanel.get("content_wrap").add(new ReportPanel().new createReportTwo());
		     
		} else if (sender == profilBtn) {
			RootPanel.get("content_wrap").clear();
			RootPanel.get("content_wrap").add( new TestClassTableWIdget());

		//	RootPanel.get("content_wrap").add( new EditorPanel().new createUser());
		//	RootPanel.get("content_wrap").add( new EditorPanel().new EditUser());
			
		}else if (sender == einstellungen_btn) {
			RootPanel.get("content_wrap").clear();
			RootPanel.get("content_wrap").add(new EditorCrudPanel().new EditRoom());
			RootPanel.get("content_wrap").add(new EditorCrudPanel().new CreateRoom());
			RootPanel.get("content_wrap").add(new EditorCrudPanel().new EditUser());

			    }
		
	}
	
}