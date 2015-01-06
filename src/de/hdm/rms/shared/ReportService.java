package de.hdm.rms.shared;

import de.hdm.rms.shared.bo.User;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportservice")
public interface ReportService extends RemoteService {
	ArrayList<User> getAllUsers() throws IllegalArgumentException;
	User getOneUserIdByNickname(String selectedNickname);

}