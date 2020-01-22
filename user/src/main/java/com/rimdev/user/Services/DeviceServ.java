package com.rimdev.user.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Repo.DeviceRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Device;
import com.rimdev.user.ouputobject.response_all;

@Service
public class DeviceServ {
	
	@Autowired 
	private DeviceRepo deviceRepo;
	
	
public List<Device> getall() {
		
		return (List<Device>) deviceRepo.findAll();
		
	}


public List<Device> checkdevice(String name) {
	
	return (List<Device>) deviceRepo.findbyname(name);
	
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
