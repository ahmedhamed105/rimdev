package com.rimdev.gateway.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.gateway.entities.Device;

@Service
public class DeviceCommonService {
	
	
	@Autowired
	DeviceExternalServ deviceExternalServ;
	
	
	public Device createorupdatedevice(int deviceid,String devicecode, String langcode) {
		
		try {
			 Device outdevice= deviceExternalServ.searchdevice(deviceid, devicecode, langcode);
			//update device
			 
			 return outdevice;
		} catch (Exception e) {
			// TODO: handle exception
			//create device
			throw e;
		}
		
	}
	

}
