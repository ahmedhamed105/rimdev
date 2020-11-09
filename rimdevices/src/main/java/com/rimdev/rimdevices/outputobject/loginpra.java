package com.rimdev.rimdevices.outputobject;

import java.util.List;

import com.rimdev.rimdevices.entities.Application;
import com.rimdev.rimdevices.entities.Notification;
import com.rimdev.rimdevices.entities.User;
import com.rimdev.rimdevices.entities.UserLogin;

public class loginpra {
	
	String usertokean;
	String username;
	UserLogin userlogin;
	User userid;
	Application app;
	List<Notification> notif;
	
	
	
	
	public loginpra() {
		super();
	}
	
	
	
	
	public List<Notification> getNotif() {
		return notif;
	}




	public void setNotif(List<Notification> notif) {
		this.notif = notif;
	}




	public Application getApp() {
		return app;
	}


	public void setApp(Application app) {
		this.app = app;
	}


	public String getUsertokean() {
		return usertokean;
	}
	public void setUsertokean(String usertokean) {
		this.usertokean = usertokean;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}




	public UserLogin getUserlogin() {
		return userlogin;
	}




	public void setUserlogin(UserLogin userlogin) {
		this.userlogin = userlogin;
	}




	public User getUserid() {
		return userid;
	}




	public void setUserid(User userid) {
		this.userid = userid;
	}
	
	

}
