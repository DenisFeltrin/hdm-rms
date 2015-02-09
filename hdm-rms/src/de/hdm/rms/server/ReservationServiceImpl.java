


package de.hdm.rms.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

  import de.hdm.rms.shared.LoginInfo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

 




import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.rms.server.db.InvitationMapper;
import de.hdm.rms.server.db.ReservationMapper;
import de.hdm.rms.server.db.RoomMapper;
import de.hdm.rms.server.db.UserMapper;
import de.hdm.rms.shared.LoginInfo;
import de.hdm.rms.shared.ReservationService;
import de.hdm.rms.shared.bo.Invitation;
import de.hdm.rms.shared.bo.InvitationListObj;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.ReservationListObj;
import de.hdm.rms.shared.bo.Room;
import de.hdm.rms.shared.bo.UserRms;
//import de.hdm.rms.shared.bo.User;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ReservationServiceImpl extends RemoteServiceServlet implements
		ReservationService {
	private UserMapper uMapper = null;
	private RoomMapper rMapper = null;
	private InvitationMapper iMapper = null;
	private ReservationMapper resMapper = null;

	private static Logger log = Logger.getLogger(ReservationServiceImpl.class.getCanonicalName());

	
	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.rMapper = RoomMapper.roomMapper();
		this.iMapper = InvitationMapper.invitationMapper();
		this.resMapper = ReservationMapper.reservationMapperv();
	}
	
	public ReservationServiceImpl() {
	}

 	public void insertUser(de.hdm.rms.shared.bo.UserRms u) {
		uMapper.insertUser(u);
	}

 	public void insertRoom(Room r) {
		rMapper.insertRoom(r);
	}

	@Override
	public Reservation insertReservation(Reservation re) {
		Reservation r = new Reservation();
	 
		resMapper.insertReservation(re);
	 r = resMapper.selectReservationId(re);	
		return r;
	}

	//@Override
//	public void insertInvitation(ArrayList<Invitation> list) {
//		
//		for(int c=0; c<list.size();c++){
//			Invitation i = new Invitation();
//			i.setAcceptionStatus(list.get(c).getAcceptionStatus());
//			i.setReservationId(list.get(c).getReservationId());
//			i.setMemberId(list.get(c).getMemberId());
//			iMapper.insertInvitation(i);
//			String getEmailAdress = uMapper.getEmailByAdress(list.get(c).getId());
//			sendMail(getEmailAdress);
//
//			
//
//		}
		
	//}

	@Override
	public UserRms OneUserById(int userId) {
		de.hdm.rms.shared.bo.UserRms u = new de.hdm.rms.shared.bo.UserRms();
		u = uMapper.OneUserById(userId);
 		return u;
	}

	@Override
	public void deleteUserById(int userId) {
		
		uMapper.deleteUserById(userId);
	}

	@Override
	public void updateUserById(de.hdm.rms.shared.bo.UserRms u) {
		Boolean status =null;
		
		status = uMapper.updateUser(u);
	}

	@Override
	public Room OneRoomById(int roomId) {
		Room r = new Room();
		r = rMapper.OneRoomById(roomId);
 		return r;
	}

	@Override
	public void updateRoomById(Room r) {
		rMapper.updateRoomById(r);
	}

	@Override
	public void deleteRoomById(int roomId) {
		
		rMapper.deleteRoomById(roomId);
		
	}

	@Override
	public void deleteInvitationById(int invitationId) {
		
		iMapper.deleteInvitationById(invitationId);
		
	}

	@Override
	public void updateInvitationById(Invitation i) {
		Boolean status =null;
		
		status = iMapper.updateInvitation(i);
		
	}
	
	@Override
	public void deleteReservationById(int reservationId) {
		
		resMapper.deleteReservationById(reservationId);
		
	}

	@Override
	public void updateReservationById(Reservation r) {
		Boolean status =null;
		
		status = resMapper.updateReservation(r);
		
	}

	@Override
	public Reservation OneReservationById(int reservationId) {
		Reservation r = new Reservation();
		r = resMapper.OneReservationById(reservationId);
 		return r;		 
	}

	@Override
	public ArrayList<Reservation> loadAllReservationsByHostId(int temp_user_id) {
 
	 
		ArrayList<Reservation> reservationlist = resMapper.loadReservationsByID(temp_user_id);
		if (!reservationlist.isEmpty()) {
			return reservationlist;
		} else {
			return null;
		}
		
	}

	@Override
	public ArrayList<Reservation> loadAllReservations() {

		ArrayList<Reservation> reservationlist = resMapper.loadAllReservations();
		if (!reservationlist.isEmpty()) {
			return reservationlist;
		} else {
			return null;
		}
 	}

	@Override
	public ArrayList<ReservationListObj> loadAllReservationsAsList() {
		ArrayList<ReservationListObj> reservationlist = resMapper.loadAllReservationsAsList();
		if (!reservationlist.isEmpty()) {
			return reservationlist;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<InvitationListObj> loadInvitationsById() {
		ArrayList<InvitationListObj> invitationList = iMapper.loadAllInvitationsById();
 			return invitationList;
		 
	}

	@Override
	public ArrayList<de.hdm.rms.shared.bo.UserRms> getAllUsers() {
		ArrayList<de.hdm.rms.shared.bo.UserRms> userList = uMapper.loadAllUsers();
		return userList;
	}

	@Override
	public ArrayList<Room> getAllRooms() {
		ArrayList<Room> roomList = rMapper.loadAllRooms();
		return roomList;
	}

	@Override
	public de.hdm.rms.shared.bo.UserRms loadUserDateByNickname(String selectedNickname) {
		de.hdm.rms.shared.bo.UserRms u = new de.hdm.rms.shared.bo.UserRms();
		u = uMapper.getUserIdByUserNickname(selectedNickname);
		return u;
		
		
 	}

	@Override
	public Room getOneRoomIdByName(String selectedRoom) {

		Room ra = new Room();
		ra = rMapper.getOneRoomIdByName(selectedRoom);

		return ra;

	}


	
	
	
//	@Override
//	public LoginInfo login(final String requestUri) {
//		final UserService userService = UserServiceFactory.getUserService();
//		com.google.appengine.api.users.User user = userService.getCurrentUser();
//		
//		//final User user = userService.getCurrentUser();
//		final LoginInfo loginInfo = new LoginInfo();
//		if (user != null) {
//			loginInfo.setLoggedIn(true);
//			loginInfo.setName(user.getEmail());
//			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
//		} else {
//			loginInfo.setLoggedIn(false);
//			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
//		}
//		return loginInfo;
//	}
	
	
	
//	@Override
//	public LoginInfo loginDetails(final String token) {
//		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + token;
//
//		final StringBuffer r = new StringBuffer();
//		try {
//			final URL u = new URL(url);
//			final URLConnection uc = u.openConnection();
//			final int end = 1000;
//			InputStreamReader isr = null;
//			BufferedReader br = null;
//			try {
//				isr = new InputStreamReader(uc.getInputStream());
//				br = new BufferedReader(isr);
//				final int chk = 0;
//				while ((url = br.readLine()) != null) {
//					if ((chk >= 0) && ((chk < end))) {
//						r.append(url).append('\n');
//					}
//				}
//			} catch (final java.net.ConnectException cex) {
//				r.append(cex.getMessage());
//			} catch (final Exception ex) {
//			//	log.log(Level.SEVERE, ex.getMessage());
//				Window.alert("message1");
//			} finally {
//				try {
//					br.close();
//				} catch (final Exception ex) {
//					//log.log(Level.SEVERE, ex.getMessage());
//					Window.alert("message2");
//
//				}
//			}
//		} catch (final Exception e) {
//			//log.log(Level.SEVERE, e.getMessage());
//			Window.alert("message3");
//
//		}
//
//		final LoginInfo loginInfo = new LoginInfo();
//		try {
//			final JsonFactory f = new JsonFactory();
//			JsonParser jp;
//			jp = f.createJsonParser(r.toString());
//			jp.nextToken();
//			while (jp.nextToken() != JsonToken.END_OBJECT) {
//				final String fieldname = jp.getCurrentName();
//				jp.nextToken();
//				if ("picture".equals(fieldname)) {
//					loginInfo.setPictureUrl(jp.getText());
//				} else if ("name".equals(fieldname)) {
//					loginInfo.setName(jp.getText());
//				} else if ("email".equals(fieldname)) {
//					loginInfo.setEmailAddress(jp.getText());
//				}
//			}
//		} catch (final JsonParseException e) {
//			Window.alert("message4");
////	log.log(Level.SEVERE, e.getMessage());
//		} catch (final IOException e) {
//			Window.alert("message5");
////	log.log(Level.SEVERE, e.getMessage());
//		}
//		return loginInfo;
//	}

//	@Override
//	public String getUserEmail(String token, AsyncCallback<String> callback) {
//		 
//			final UserService userService = UserServiceFactory.getUserService();
//			com.google.appengine.api.users.User user = userService.getCurrentUser();
//
// 			if (null != user) {
//				return user.getEmail(); 
// 			} else {
//				Window.alert("message6");
//
//				//return " ---";
//			}
//			return null;
//				
//	}
	
	
	@Override
	public String getUserEmail(final String token) {
		  UserService userService = UserServiceFactory.getUserService();
		  User user = userService.getCurrentUser();
		if (null != user) {
			return user.getEmail();
		} else {
			return "noreply@sample.com";
		}
	}

	@Override
	public LoginInfo login(final String requestUri) {
		  UserService userService = UserServiceFactory.getUserService();
		  User user = userService.getCurrentUser();
		  LoginInfo loginInfo = new LoginInfo();
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setName(user.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

	@Override
	public LoginInfo loginDetails(final String token) {
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + token;

		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
				}
			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception ex) {
				log.log(Level.SEVERE, ex.getMessage());
			} finally {
				try {
					br.close();
				} catch (final Exception ex) {
					log.log(Level.SEVERE, ex.getMessage());
				}
			}
		} catch (final Exception e) {
//			log.log(Level.SEVERE, e.getMessage());
		}

		final LoginInfo loginInfo = new LoginInfo();
		try {
			final JsonFactory f = new JsonFactory();
			JsonParser jp;
			jp = f.createJsonParser(r.toString());
			jp.nextToken();
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				final String fieldname = jp.getCurrentName();
				jp.nextToken();
//				if ("picture".equals(fieldname)) {
//					loginInfo.setPictureUrl(jp.getText());
//				} else if ("name".equals(fieldname)) {
//					loginInfo.setName(jp.getText());
//				} else 
				if ("email".equals(fieldname)) {
					loginInfo.setEmailAddress(jp.getText());
				}
			}
		} catch (final JsonParseException e) {
			 log.log(Level.SEVERE, e.getMessage());
		} catch (final IOException e) {
		 	log.log(Level.SEVERE, e.getMessage());
		}
		return loginInfo;
	}

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void sendMail(  String toUserAdress) {
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		 
		String username ="Annemarie Marks"; 
		String hostname ="Max Hoster"; 
		String eventname ="Weihnachstfeier"; 
		String date ="24.12.2023";
		String time ="12.00 Uhr";
		String raum ="testraum";
		int ReservationId =123;
		String url ="www.google.de";

		String	from_address = "bjconcepts.de@gmail.com";
		String	to_address= "bjconcepts.de@gmail.com";
		String	subject= "Einladung zu Veranstaltung: " + eventname ;
		
		String	text_message= 
	    "Hallo: " + username  
		+ ", \n\n" + 
		"du wurdest von: "	+ hostname 
		+"\n"+
		"eigenladen an der Veranstaltung " + eventname
		+ " \n" + 
		"am " + date + "um " + time + "im Raum: " + raum + "teilzunehmen."
		
		+ " \n\n" + 
		"Die Veranstaltungs-ID ist: " + ReservationId
		+ " \n\n" + 
		"Bitte bestätige unter folgendem Link, ob du an der Veranstaltung teilnehmen kannst."
		
		+ " \n \n " + 

		"Bestätigungslink:" + url +
		"\n Vielen Dank.";

		
	/*	MailService.Message message = new MailService.Message();
		message.setSubject(subject);
		message.setTextBody(text_message);
		String application_id = SystemProperty.applicationId.get();
		String sender = "hdm-rms@" + application_id + ".appspotmail.com";
		message.setSender(sender);
		*/
	 	MailService.Message message = new MailService.Message(from_address, to_address, subject, text_message);
		try {
		 	MailServiceFactory.getMailService().sendToAdmins(message);

 		 //	MailServiceFactory.getMailService().send(message);
		} catch (IOException e) {
				e.printStackTrace();
		}	
		
	}

	@Override
	public UserRms getUserDataFromEmail(String eMailAdress) {
		UserRms u = new UserRms();
		u = uMapper.getOneUserIdByEmailAdress(eMailAdress);

		return u;

	}
	
	 

 
	
 //	@Override
//	public Reservation OneReservationById(int reservationId) {
//		Reservation r = new Reservation();
//		r = resMapper.OneReservationById(reservationId);
// 		return r;
//	}

}