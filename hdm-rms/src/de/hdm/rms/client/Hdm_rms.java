package de.hdm.rms.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.rms.client.CreateUser;

class Hdm_rms implements EntryPoint {
	
	
	CreateUser Affe = new CreateUser();
	
	
	public void onModuleLoad() {
		
		
		RootPanel.get("content_wrap").add(Affe);
		
	}
	
	
}