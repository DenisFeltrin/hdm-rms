package de.hdm.rms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import de.hdm.rms.client.AdministationPanel.createUser;

class Hdm_rms implements EntryPoint, ClickHandler   {
	
	HorizontalPanel  menuPanel = new HorizontalPanel();
	Button reportBtn = new Button( );
   	Button profilBtn = new Button( );
	Button startBtn = new Button( );
	Button raumreservierung_btn = new Button( );
	Button einstellungen_btn = new Button( );
	
	private createUser createUserObj;
	    
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
		
	}

	@Override
	public void onClick(ClickEvent event) {
		  // note that in general, events can have sources that are not Widgets.
	    Widget sender = (Widget) event.getSource();

	    if (sender == startBtn) {
	      // handle b1 being clicked
	    } else if (sender == raumreservierung_btn) {
	    	RootPanel.get("content_wrap").clear();
			RootPanel.get("content_wrap").add( new AdministationPanel().new CreateReservation());

	    } else if (sender == reportBtn) {
			RootPanel.get("content_wrap").add( new AdministationPanel().new CreateInvitation());
		     
		} else if (sender == profilBtn) {
			RootPanel.get("content_wrap").clear();
			//RootPanel.get("content_wrap").add( new AdministationPanel().new createUser());
			RootPanel.get("content_wrap").add( new AdministationPanel().new EditUser());
			
		}else if (sender == einstellungen_btn) {
			RootPanel.get("content_wrap").clear();
			//RootPanel.get("content_wrap").add( new AdministationPanel().new createRoom());
			RootPanel.get("content_wrap").add( new AdministationPanel().new EditRoom());

			    }
		
	}
	
}