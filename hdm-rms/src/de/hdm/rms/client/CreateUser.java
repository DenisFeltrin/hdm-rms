package de.hdm.rms.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateUser extends VerticalPanel {

	private final TextBox lastName = new TextBox();
	private final VerticalPanel CreateUserPanel = new VerticalPanel();
	private final Button userRegisterBtn = new Button("Registrieren");

	public CreateUser() {}
	
	
	public void onLoad() {
		
		// set showcase Headline 
		CreateUserPanel.add(lastName);
		CreateUserPanel.add(userRegisterBtn);
		
		// Methode die aufgerufen wird bei Clickhandler 
		userRegisterBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("User wird angelegt.");
				
			}
		});
		

		lastName.setText("Nickname eingeben");
		RootPanel.get("content_wrap").clear();
		RootPanel.get("content_wrap").add(CreateUserPanel);
	
	}	

	
	
}
