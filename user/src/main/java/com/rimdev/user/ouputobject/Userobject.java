package com.rimdev.user.ouputobject;

import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserLogin;

public class Userobject {
	
	User User;
	UserLogin login;
	
	
	
	public Userobject() {
		super();
	}
	public User getUser() {
		return User;
	}
	public void setUser(User user) {
		User = user;
	}
	public UserLogin getLogin() {
		return login;
	}
	public void setLogin(UserLogin login) {
		this.login = login;
	}
	
	
	

}
