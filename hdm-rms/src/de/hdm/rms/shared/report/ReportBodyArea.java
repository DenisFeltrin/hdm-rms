package de.hdm.rms.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

import de.hdm.rms.client.ClientSettings;
import de.hdm.rms.shared.ReportServiceAsync;

public class ReportBodyArea implements Serializable {

	private static final long serialVersionUID = 1L;

	private String users;
	
	ReportServiceAsync reportAdministration = ClientSettings.getReportService();

	public void onLoad() {

		getHeaderOfReportTwo(0);
		final HTML html = new HTML();
		html.setText(users.toString());

	}
	
	public String getHeaderOfReportTwo(int i) {
		
		String b="";
//		ArrayList<String> userWithInvitation = null;
		reportAdministration.loadMembersForReservation(i, new AsyncCallback<ArrayList<String>>(){
			
			@Override
			public void onFailure(Throwable caught) {		
			}
			
			@Override
			public void onSuccess(ArrayList<String> userWithInvitation) {
				StringBuffer memberStringBuffer = new StringBuffer();
				String b=""; 
				for(int i=0; i<userWithInvitation.size();i++){
					memberStringBuffer.append(userWithInvitation.get(i)+", ");
					b += userWithInvitation.get(i).toString();
//					setReturnString(userWithInvitation.get(i).toString());

				}
				
				System.out.println("return Wert für b innerhalb der Methode: " +b.toString());
			}
					
		});
		
		System.out.println("Ergebnis für b außerhalb Methode: "+b.toString());
		
		return users;
	}

}