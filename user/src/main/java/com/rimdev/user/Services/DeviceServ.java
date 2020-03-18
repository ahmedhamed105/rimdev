package com.rimdev.user.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
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
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public List<Device> getall(String langcode) {
	try {
		return (List<Device>) deviceRepo.findAll();
} catch (TransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}
		
	}


public List<Device> checkdevice(String ip,DeviceOs os,DeviceType type,String browser) {
	
	return (List<Device>) deviceRepo.findbyiposbrowser( ip, os, type, browser);
	
}


public response_all Save(Device input) {
	
	response_all res=new response_all();	
	try {	
		Date date = new Date();
		Generate gen=new Generate();
		String tokean=gen.token(30);
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
		res.setDevice(ouput);
		return res;
	} catch (Exception e) {
		// TODO: handle exception
		res.setStatus(1);
		res.setMessage(e.getMessage());
		return res;
	}			
		
	}



}
