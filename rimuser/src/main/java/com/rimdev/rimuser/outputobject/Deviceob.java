package com.rimdev.rimuser.outputobject;

import com.rimdev.rimuser.entities.Device;
import com.rimdev.rimuser.entities.DevicePage;
import com.rimdev.rimuser.entities.Pages;
import com.rimdev.rimuser.entities.User;
import com.rimdev.rimuser.entities.UserLogin;

public class Deviceob {
	
Pages pageid;
UserLogin userid;
Device data;
DevicePage devpgdata;
	
	
	
	public Deviceob() {
		super();
	}




	/**
	 * @return the pageid
	 */
	public Pages getPageid() {
		return pageid;
	}



	/**
	 * @param pageid the pageid to set
	 */
	public void setPageid(Pages pageid) {
		this.pageid = pageid;
	}



	/**
	 * @return the userid
	 */
	public UserLogin getUserid() {
		return userid;
	}



	/**
	 * @param userid the userid to set
	 */
	public void setUserid(UserLogin userid) {
		this.userid = userid;
	}




	/**
	 * @return the data
	 */
	public Device getData() {
		return data;
	}



	/**
	 * @param data the data to set
	 */
	public void setData(Device data) {
		this.data = data;
	}



	/**
	 * @return the devpgdata
	 */
	public DevicePage getDevpgdata() {
		return devpgdata;
	}



	/**
	 * @param devpgdata the devpgdata to set
	 */
	public void setDevpgdata(DevicePage devpgdata) {
		this.devpgdata = devpgdata;
	}
	


	
	


}
