package com.rimdev.rimdevices.outputobject;

import java.util.Date;

public class Loginobject {
	
	String username;
	String password;
	String Tokean;
	boolean login;
	Date logintime;

	
	public Loginobject() {
		super();
	}
	
	
	public Date getLogintime() {
		return logintime;
	}


	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}


	public String getTokean() {
		return Tokean;
	}


	public void setTokean(String tokean) {
		Tokean = tokean;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	
	
	

}
