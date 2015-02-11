package de.hdm.rms.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;

import de.hdm.rms.shared.LoginInfo;
import de.hdm.rms.shared.ReservationServiceAsync;
import de.hdm.rms.shared.bo.UserRms;

public class OAuthAppEngineSample implements EntryPoint {

	private static final Auth AUTH = Auth.get();
	private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private static final String GOOGLE_CLIENT_ID = "365116029161-mvl0s2d850n71gjivmijj8gmvka3blhq.apps.googleusercontent.com";
	private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
	private static final String PLUS_ME_SCOPE_EMAIL = "https://www.googleapis.com/auth/userinfo.email";
	UserRms u;
	private final HorizontalPanel loginPanel = new HorizontalPanel();
	private final Anchor signInLink = new Anchor("");
	private final Image loginImage = new Image();
	private final TextBox nameField = new TextBox();
	Hdm_rms h = new Hdm_rms();
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	// private final GreetingServiceAsync greetingService =
	// GWT.create(GreetingService.class);
	private ReservationServiceAsync reservationAdministration = ClientSettings
			.getReservationService();

	private void loadLogin(final LoginInfo loginInfo) {
		signInLink.setHref(loginInfo.getLoginUrl());
		signInLink.setText("Please, sign in with your Google Account");
		signInLink.setTitle("Sign in");
	}

	private void loadLogout(final LoginInfo loginInfo) {
		signInLink.setHref(loginInfo.getLogoutUrl());
		signInLink.setText(loginInfo.getName());
		signInLink.setTitle("Sign out");
	}

	public UserRms addGoogleAuthHelper() {
		final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL,
				GOOGLE_CLIENT_ID)
				.withScopes(PLUS_ME_SCOPE, PLUS_ME_SCOPE_EMAIL);
		AUTH.login(req, new Callback<String, Throwable>() {
			@Override
			public void onSuccess(final String token) {
				Window.alert("Login");

				if (!token.isEmpty()) {
					reservationAdministration.loginDetails(token,
							new AsyncCallback<LoginInfo>() {
								@Override
								public void onFailure(final Throwable caught) {
									Window.alert("fail2 Login");

									GWT.log("loginDetails -> onFailure");
								}

								@Override
								public void onSuccess(final LoginInfo loginInfo) {

									reservationAdministration.getUserDataFromEmail(
											loginInfo.getEmailAddress(),
											new AsyncCallback<UserRms>() {

												@Override
												public void onFailure(
														Throwable caught) {
													// TODO Auto-generated
													// method stub

												}

												@Override
												public void onSuccess(
														UserRms result) {
													if (result.getNickName() != null) {
														UserRms u = new UserRms();
														Cookies.setCookie(
																"SessionUserID",
																String.valueOf(result
																		.getId()));
														u.setLastName(result
																.getLastName());
														u.setFirstName(result
																.getFirstName());
														u.setNickName(result
																.getNickName());
														u.setEmailAdress(result
																.getEmailAdress());

														signInLink.setText(u
																.getEmailAdress());
														nameField.setText(u
																.getEmailAdress());
														RootPanel
																.get("head_wrap_right")
																.clear();
														RootPanel
																.get("head_wrap_right")
																.add(h.zeigePanel());
													} else {
														EditorCrudPanel CrudPanelCreateUser = new EditorCrudPanel();
														CrudPanelCreateUser.new CreateUser();
													}
												}

											});

									signInLink.setStyleName("login-area");
									// loginImage.setUrl(loginInfo.getPictureUrl());
									loginImage.setVisible(false);
									loginPanel.add(loginImage);
									loginImage
											.addLoadHandler(new LoadHandler() {
												@Override
												public void onLoad(
														final LoadEvent event) {
													final int newWidth = 24;
													final com.google.gwt.dom.client.Element element = event
															.getRelativeElement();
													if (element.equals(loginImage
															.getElement())) {
														final int originalHeight = loginImage
																.getOffsetHeight();
														final int originalWidth = loginImage
																.getOffsetWidth();
														if (originalHeight > originalWidth) {
															loginImage
																	.setHeight(newWidth
																			+ "px");
														} else {
															loginImage
																	.setWidth(newWidth
																			+ "px");
														}
														loginImage
																.setVisible(true);
													}
												}
											});
								}
							});
				}
			}

			@Override
			public void onFailure(final Throwable caught) {
				Window.alert("fail Login");

				GWT.log("Error -> loginDetails\n" + caught.getMessage());
			}
		});
		return u;
	}

	public void onModuleLoad() {

		signInLink.getElement().setClassName("login-area");

		loginImage.getElement().setClassName("login-area");
		loginPanel.add(signInLink);
		RootPanel.get("loginPanelContainer").add(loginPanel);

		reservationAdministration.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {

					@Override
					public void onFailure(final Throwable caught) {
						GWT.log("login -> onFailure");
						Window.alert("fehler login");
					}

					@Override
					public void onSuccess(final LoginInfo result) {

						Window.alert("login erfolgreich");

						if (result.getName() != null
								&& !result.getName().isEmpty()) {
							addGoogleAuthHelper();
							loadLogout(result);
							nameField.setEnabled(true);

						} else {
							loadLogin(result);

						}

					}
				});

		RootPanel.get("loginPanelContainer").add(signInLink);

	}
}