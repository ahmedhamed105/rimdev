package com.rimdev.rimnotif.outputobject;

import com.rimdev.rimnotif.entities.DataStatus;
import com.rimdev.rimnotif.entities.Email;
import com.rimdev.rimnotif.entities.Telephones;

public class Emailobj {
	
	Email email;
	String userid;
	Telephones tele;
	
	
	
	public Emailobj() {
		super();
	}
	
	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Telephones getTele() {
		return tele;
	}

	public void setTele(Telephones tele) {
		this.tele = tele;
	}
	
	
	
	

}
