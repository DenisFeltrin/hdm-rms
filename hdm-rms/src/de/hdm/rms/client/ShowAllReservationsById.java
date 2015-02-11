package de.hdm.rms.client;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;
  
public class ShowAllReservationsById extends Showcase {
	
	  private ReservationServiceAsync reservationAdministration = ClientSettings.getReservationService();

	  private final VerticalPanel CreateRoomPanel = new VerticalPanel();
	  int temp_user_id = 1;
	  ArrayList<Reservation> arrayList = new ArrayList<Reservation>();
	  //private final Button roomRegisterBtn = new Button("Raum anlegen");
	  //private final TextBox name = new TextBox();
	  //private final TextBox capaticity = new TextBox();
	  //private final Label nameLabel = new Label("Name");
	  //private final Label capaticityLabel = new Label("Kapazität");
	 
	//  private Room r;
	
	 
public ArrayList<Reservation>  getArrayList(){
	
	
	
	return arrayList;
	
	
	
}
	
	public void loadAllReservationsByHostId(int temp_user_id){
		
		reservationAdministration.loadAllReservationsByHostId(temp_user_id, new  AsyncCallback<ArrayList<Reservation>>(){
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(ArrayList<Reservation> result) {

				
				arrayList = result;
				//for(int i = 0; i< result.size(); i++){
					
				
				//}
				
			
			
			}
			});	
	}
	@Override
	public String getHeadline() {
 		return null;
	}
	@Override
	public void run() {
 		
	}
}
	
	/*
	roomRegisterBtn.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			if (name.getValue().isEmpty()
					|| capaticity.getValue().isEmpty()) {
					
				Window.alert("Bitte Felder ausfüllen");
			} else {
				r = new Room();
				r.setName(name.getValue());
				r.setCapaticity(capaticity.getValue());

				reservationAdministration.insertRoom(r, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Raum angelegt");

					}

				});
			}
	}
	});*/
	
	
	 


