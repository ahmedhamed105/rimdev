package com.rimdev.rimlog.outputobject;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rimdev.rimlog.entities.Device;
import com.rimdev.rimlog.entities.User;


public class response_all {
	
	 int status;
	String message;
	String Tokean;
	Date expiretime;
	Device device;
	User user;
	
	
	
	public response_all() {
		super();
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getTokean() {
		return Tokean;
	}


	public void setTokean(String tokean) {
		Tokean = tokean;
	}


	public Date getExpiretime() {
		return expiretime;
	}


	public void setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
	}

    @XmlTransient
    @JsonIgnore
	public Device getDevice() {
		return device;
	}


	public void setDevice(Device device) {
		this.device = device;
	}

    @XmlTransient
    @JsonIgnore
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	



	
	
	
			

}
