package com.rimdev.rimfile.outputobject;

public class authouput {	

     int deviceId;
     String deviceIP;
     int userloginID;
     String username;
     String logcode;
     
     
	public authouput() {
		super();
	}
	
	public String getLogcode() {
		return logcode;
	}

	public void setLogcode(String logcode) {
		this.logcode = logcode;
	}

	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceIP() {
		return deviceIP;
	}
	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP;
	}
	public int getUserloginID() {
		return userloginID;
	}
	public void setUserloginID(int userloginID) {
		this.userloginID = userloginID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
