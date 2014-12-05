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
import com.sun.java.swing.plaf.windows.resources.windows;

import java.lang.*;

import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.Room;

public class CreateRoom extends VerticalPanel {

	private final VerticalPanel CreateUserPanel = new VerticalPanel();
	private final Button roomRegisterBtn = new Button("Raum anlegen");
	private final TextBox name = new TextBox();
	private final TextBox capaticity = new TextBox();
	private final TextBox email = new TextBox();
	private final Label nameLabel = new Label("Name");
	private final Label capaticityLabel = new Label("Kapazität");

	private Room r;
	private final ReservationServiceAsync AsyncObj = GWT
			.create(ReservationService.class);

	public CreateRoom() {
	}

	public void onLoad() {

		RootPanel.get("content_wrap").add(CreateUserPanel);
		CreateUserPanel.add(nameLabel);
		CreateUserPanel.add(name);
		CreateUserPanel.add(capaticityLabel);
		CreateUserPanel.add(capaticity);
		

		// Methode die aufgerufen wird bei Clickhandler
		roomRegisterBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (name.getValue().isEmpty()
						|| capaticity.getValue().isEmpty()) {
						
					Window.alert("Bitte Felder ausfüllen");
				} else {
					r = new Room();
					r.setName(name.getValue());
					r.setCapaticity(capaticity.getValue());
					

					AsyncObj.insertRoom(r, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onSuccess(Void result) {
							Window.alert("Raum angelegt");

						}

					});
				}
		}
		});

	}

}
