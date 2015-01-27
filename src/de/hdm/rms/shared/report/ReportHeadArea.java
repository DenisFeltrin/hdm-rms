package de.hdm.rms.shared.report;

import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import de.hdm.rms.client.ClientSettings;
import de.hdm.rms.shared.ReportServiceAsync;
import de.hdm.rms.shared.bo.User;

public class ReportHeadArea implements Serializable {

	private static final long serialVersionUID = 1L;
	private ReportServiceAsync reportAdministration = ClientSettings.getReportService();

	private String Title = "rms";
	private String TitleOfReport = "Report";
	private User u;

	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	Date ReportDate = new Date();

	public User getHeaderOfReportTwo(String selectedNickname) {

		RootPanel.get("content_wrap").clear();
		reportAdministration.getOneUserIdByNickname(selectedNickname, new AsyncCallback<User>() {

					@Override
					public void onFailure(Throwable caught) {
						
					}

					@Override
					public void onSuccess(User result) {

						u.setFirstName(result.getFirstName());
						u.setLastName(result.getLastName());
						u.setNickName(result.getNickName());
						u.setId(result.getId());
						
						}

				});

		return u;
	}

	public void onLoad() {

		final HTML html = new HTML("");

		html.setHTML("<html><head><title>" + TitleOfReport
				+ "</title></head><body>" + "<h1>" + Title + "</h1></br>"
				+ "<h1>" + u.getNickName() + "</h1></br>" + "<h1>"
				+ u.getFirstName() + "</h1></br>" + "<h1>" + u.getLastName()
				+ "</h1></br>" + "<h1>" + dateFormat.format(ReportDate)
				+ "</h1>");

		RootPanel.get("content_wrap").add(html);

	}

}