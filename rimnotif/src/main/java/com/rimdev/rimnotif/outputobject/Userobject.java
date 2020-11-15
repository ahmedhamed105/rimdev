package com.rimdev.rimnotif.outputobject;

import com.rimdev.rimnotif.entities.User;
import com.rimdev.rimnotif.entities.UserLogin;

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
	@Override
	public String toString() {
		return "Userobject [User=" + User.getId() + ", login=" + login.getId() + "]";
	}
	
	
	

}
