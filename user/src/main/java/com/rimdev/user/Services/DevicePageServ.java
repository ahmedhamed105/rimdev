package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NooauthException;
import com.rimdev.user.Repo.DevicePageRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.pagesdevice;

@Service
public class DevicePageServ {
	
	@Autowired
	DevicePageRepo devicePageRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	UserLoginServ userLoginServ;
	
	@Autowired
	LogServ logServ;
	
	public DevicePage check_tokean_page(String tokean,String pageid,String langcode) {
		
		try {
			int pagenumber =Integer.parseInt(pageid);
			Optional<DevicePage> flowid =devicePageRepo.findbytokeanpage(tokean, pagenumber);
			 
			 if (flowid.isPresent()){
				 return flowid.get();
			

						}
				else{
				   // alternative processing....
					throw new NooauthException(textConvertionServ.search("E103", langcode));
					
				}
		}  catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }

		
	}
	
	

public DevicePage savedevpag(HttpServletRequest request,Device dev,Pages pa,String username,String tokean,String langcode) {
	try {
		UserLogin userlogin = null;

	    userlogin =  userLoginServ.getusername(request,username, langcode,dev,2);
	    if(userlogin.getUsertokean().equals(tokean)) {
			String text= "Device auth with username : "+username+" or token : "+tokean;
			logServ.info(dev.getDeviceip(),request,text, dev, userlogin.getId(), 12, langcode," ");		
	    	
	    }else {
	    	String text= "not  auth (Device token wrong) with username : "+username+" or token : "+tokean;
			logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 3, langcode," ");			
			throw new NooauthException(textConvertionServ.search("E103", langcode));
	
	    	
	    }
	    
	    
	    Date visittime = new Date();
		DevicePage a=new DevicePage();
		a.setDeviceId(dev);
		a.setPagesID(pa);
		a.setVisittime(visittime);
		a.setUserloginID(userlogin);
		a.setPageTokean(dev.getDevicetokean());
		DevicePage out=devicePageRepo.save(a);
		return out;
	} catch (TransientDataAccessException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 2, langcode,se.getMessage());			
	
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}

	
}





public List<pagesdevice> getpagesbydevice(int id,String langcode) {
	List<DevicePage> p=new ArrayList<DevicePage>();
	try {
		 p= (List<DevicePage>)devicePageRepo.findbydeviceid(id);
	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}
	
	
	if(p.size() <= 0)
	{
		throw new NullPointerException("no pages");
		
	}
	
	
	
	List<pagesdevice> c= new ArrayList<pagesdevice>();
	for(DevicePage dev:p) {
		pagesdevice pa=new pagesdevice();
		pa.setPage_name(dev.getPagesID().getPagename());
		pa.setPage_Date(dev.getVisittime());
		c.add(pa);
		
	}
		
		return c;
		
	}



}
