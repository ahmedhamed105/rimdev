package com.rimdev.user.ouputobject;

import java.util.Date;


public class response_all {
	
	 int status;
	String message;
	String Tokean;
	Date expiretime;
	
	
	
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



	
	
	
			

}
