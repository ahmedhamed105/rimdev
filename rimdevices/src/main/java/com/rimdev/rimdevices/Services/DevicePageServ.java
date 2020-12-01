package com.rimdev.rimdevices.Services;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimdevices.Repo.DevicePageRepo;
import com.rimdev.rimdevices.entities.Device;
import com.rimdev.rimdevices.entities.DevicePage;
import com.rimdev.rimdevices.entities.Pages;


@Service
public class DevicePageServ {
	
	@Autowired
	DevicePageRepo devicePageRepo;
	
	@Autowired
	LangExternalServ textConvertionServ;
	
	
	

public DevicePage savedevicepage(Device dev,Pages pa,String langcode) {
	try {

	    Date visittime = new Date();
		DevicePage a=new DevicePage();
		a.setDeviceId(dev);
		a.setPagesID(pa);
		a.setVisittime(visittime);
	//	a.setUserloginID(user);
	//	a.setPageTokean(user.getUsertokean());
		DevicePage out=devicePageRepo.save(a);
		return out;
	} catch (TransientDataAccessException  se) {
		String text= "sql error"+se.getMessage();
	//	logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());			
	
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		String text= "sql error"+se.getMessage();
	//	logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		String text= "sql error"+se.getMessage();
		//logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		String text= "sql error"+se.getMessage();
		//logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}

	
}

	
	


}
