package de.hdm.rms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.rms.client.CreateUser;

class Hdm_rms implements EntryPoint {
	
	
	
	public void onModuleLoad() {
		
		CreateUser CreateUserView = new CreateUser();
		
		RootPanel.get("content_wrap").add(CreateUserView);

		
	}
	
	
}