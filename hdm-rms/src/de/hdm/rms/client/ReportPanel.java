package de.hdm.rms.client;

import java.util.Spliterator;
import java.util.function.Consumer;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.User;

public class ReportPanel extends VerticalPanel {

	public class createReportOne extends Showcase  {

		private ReportServiceAsync reportAdministration = ClientSettings.getReportService();
		private final VerticalPanel CreateReportOnePanel = new VerticalPanel();
		private final Button createReportOneBtn = new Button("Report 1 erstellen");
		
		private final TextBox Attribut1 = new TextBox();
		private final TextBox Attribut2 = new TextBox();

		private final Label Attribut1Label = new Label("Dropdown für Attribut 1");
		private final Label Attribut2Label = new Label("Dropdown für Attribut 2");
		//private User u;
		
	 
		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		  public void run() {
			   // bankVerwaltung.getCustomerById(11, new DeleteCustomerCallback(this));
			 	// Methode die aufgerufen wird bei Clickhandler
			createReportOneBtn.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {

							Window.alert("Report 1 wurde erstellt.");	
							
							}
					});
			
			  }
	
		  public createReportOne(){

			  RootPanel.get("content_wrap").clear();
			  CreateReportOnePanel.add(Attribut1Label);
				CreateReportOnePanel.add(Attribut1);
				CreateReportOnePanel.add(Attribut2Label);
				CreateReportOnePanel.add(Attribut2);
				CreateReportOnePanel.add(createReportOneBtn);
				RootPanel.get("content_wrap").add(CreateReportOnePanel);
				
			}
		
	}
	
	public class createReportTwo extends Showcase  {

		private ReportServiceAsync reportAdministration = ClientSettings.getReportService();
		private final VerticalPanel CreateReportTwoPanel = new VerticalPanel();
		private final Button createReportTwoBtn = new Button("Report 2 erstellen");

		private final TextBox Attribut3 = new TextBox();
		private final TextBox Attribut4 = new TextBox();

		private final Label Attribut3Label = new Label("Dropdown für Attribut 3");
		private final Label Attribut4Label = new Label("Dropdown für Attribut 4");
		//private User u;
		
		 

		@Override
		public String getHeadline() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		  public void run() {
			   // bankVerwaltung.getCustomerById(11, new DeleteCustomerCallback(this));
			 	// Methode die aufgerufen wird bei Clickhandler
			
			createReportTwoBtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {

					Window.alert("Report 2 wurde erstellt.");	
					
					}
			});
			
			  }
	
		
		  public createReportTwo(){

				CreateReportTwoPanel.add(Attribut3Label);
				CreateReportTwoPanel.add(Attribut3);
				CreateReportTwoPanel.add(Attribut4Label);
				CreateReportTwoPanel.add(Attribut4);
				CreateReportTwoPanel.add(createReportTwoBtn);
				RootPanel.get("content_wrap").add(CreateReportTwoPanel);

			}
		
	}
	
}