package de.hdm.rms.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateUser extends VerticalPanel {

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
	
	
	
	public CreateUser() {}
	
	
	public void onLoad() {
		
		
		RootPanel.get("content_wrap").add(CreateUserPanel);
		CreateUserPanel.add(firstNameLabel);
		CreateUserPanel.add(firstName);
		CreateUserPanel.add(lastNameLabel);
		CreateUserPanel.add(lastName);
		CreateUserPanel.add(nickNameLabel);
		CreateUserPanel.add(nickName);
		CreateUserPanel.add(emailLabel);
		CreateUserPanel.add(email);
		CreateUserPanel.add(userRegisterBtn);
		


		// Methode die aufgerufen wird bei Clickhandler 
		userRegisterBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Ich tu so als würde ich nen User anlegen.");
				
			}
		});
 	
	}	

	
	
}
