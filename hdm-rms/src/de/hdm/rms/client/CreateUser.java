package de.hdm.rms.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.User;

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
	private User u;
	private final ReservationServiceAsync AsyncObj = GWT
			.create(ReservationService.class);

	public CreateUser() {
	}

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
				if (firstName.getValue().isEmpty()
						|| lastName.getValue().isEmpty()
						|| nickName.getValue().isEmpty()
						|| email.getValue().isEmpty()) {
					Window.alert("Bitte Felder ausfüllen");
				} else {
					u = new User();
					u.setFirstName(firstName.getValue());
					u.setLastName(lastName.getValue());
					u.setEmailAdress(email.getValue());
					u.setNickName(nickName.getValue());

					AsyncObj.insertUser(u, new AsyncCallback<Void>() {

						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}

						public void onSuccess(Void result) {
							Window.alert("Benutzer registriert");

						}

					});
				}
			}
		});

	}

}
