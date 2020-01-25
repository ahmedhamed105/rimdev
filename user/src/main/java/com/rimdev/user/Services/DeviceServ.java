package com.rimdev.user.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.rimdev.user.Repo.DeviceRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.DeviceType;
import com.rimdev.user.ouputobject.response_all;

@Service
public class DeviceServ {
	
	@Autowired 
	private DeviceRepo deviceRepo;
	
	
public List<Device> getall() {
		
		return (List<Device>) deviceRepo.findAll();
		
	}


public List<Device> checkdevice(String ip,DeviceOs os,DeviceType type,String browser) {
	
	return (List<Device>) deviceRepo.findbyiposbrowser( ip, os, type, browser);
	
}


public response_all Save(Device input) {
	
	response_all res=new response_all();	
	try {	
		Date date = new Date();
		String tokean=Generate.token(30);
		input.setDevicetokean(tokean);
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
		input.setTokeantime(c.getTime());
		input.setDevicecreate(date);
		input.setDevicemodify(date);
		Device ouput =deviceRepo.save(input);	
		res.setStatus(0);
		res.setMessage("ok");
		res.setTokean(tokean);
		res.setExpiretime(c.getTime());
		return res;
	} catch (Exception e) {
		// TODO: handle exception
		res.setStatus(1);
		res.setMessage(e.getMessage());
		return res;
	}			
		
	}



}
