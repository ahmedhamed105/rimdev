package com.rimdev.rimlog.outputobject;

import com.rimdev.rimlog.entities.Device;
import com.rimdev.rimlog.entities.LogType;
import com.rimdev.rimlog.entities.User;

public class OutLogobject {

	String requestip;
	String requesturl;
	Device device;
	User user;
	String error_type;
	LogType logtype;
	String logexception;
	String logtext;

	public String getRequestip() {
		return requestip;
	}

	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}

	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getError_type() {
		return error_type;
	}

	public void setError_type(String error_type) {
		this.error_type = error_type;
	}

	public LogType getLogtype() {
		return logtype;
	}

	public void setLogtype(LogType logtype) {
		this.logtype = logtype;
	}

	public String getLogexception() {
		return logexception;
	}

	public void setLogexception(String logexception) {
		this.logexception = logexception;
	}

	public String getLogtext() {
		return logtext;
	}

	public void setLogtext(String logtext) {
		this.logtext = logtext;
	}

}
