package com.rimdev.rimdevices.outputobject;

import com.rimdev.rimdevices.entities.Device;
import com.rimdev.rimdevices.entities.DevicePage;
import com.rimdev.rimdevices.entities.Pages;
import com.rimdev.rimdevices.entities.User;

public class Deviceob {
	
Pages pageid;
User userid;
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
	public User getUserid() {
		return userid;
	}



	/**
	 * @param userid the userid to set
	 */
	public void setUserid(User userid) {
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
