package com.rimdev.rimauth.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.rimdev.rimauth.Utils.Generate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimauth.Exception.NooauthException;
import com.rimdev.rimauth.Exception.PopupException;
import com.rimdev.rimauth.Repo.DevicePageRepo;
import com.rimdev.rimauth.Utils.Generate;
import com.rimdev.rimauth.entities.Device;
import com.rimdev.rimauth.entities.DevicePage;
import com.rimdev.rimauth.entities.Pages;
import com.rimdev.rimauth.entities.UserLogin;
import com.rimdev.rimauth.outputobject.pagesdevice;

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
	
	@Autowired
	GroupWebServ groupWebServ;
	
	@Autowired
	PagesServ pagesServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	DeviceServ deviceServ;
	
	@Autowired
	GroupPagesServ groupPagesServ;
	
public DevicePage check_webservice_external(HttpServletRequest request,String url,String tokean,String username,String pageid,String langcode,String devicecode,List<String> paramter,List<String> values) {
		
		DevicePage devpage=getdevicepage(request, tokean, username, pageid, langcode,devicecode);
		
		userLoginServ.check_userlogin_without(request, devpage.getDeviceId(), devpage.getPagesID(), username, tokean, langcode);
		
		groupPagesServ.check_page(request,devpage, langcode);
		
		  paramter.add("langcode");
		  values.add(langcode);
		  
		  groupWebServ.checkpriviledgeexternal(request,url ,devpage,paramter,values);
		  
		  return devpage;
	}
	
	
	public DevicePage check_webservice(HttpServletRequest request,String tokean,String username,String pageid,String langcode,String devicecode,List<String> paramter,List<String> values) {
		
		DevicePage devpage=getdevicepage(request, tokean, username, pageid, langcode,devicecode);
		
		userLoginServ.check_userlogin_without(request, devpage.getDeviceId(), devpage.getPagesID(), username, tokean, langcode);
		
		groupPagesServ.check_page(request,devpage, langcode);
		
		  paramter.add("langcode");
		  values.add(langcode);
		  
		  groupWebServ.checkpriviledge(request, devpage,paramter,values);
		  
		  return devpage;
	}
	
	
	
	
public DevicePage getdevicepage(HttpServletRequest request,String tokean,String username,String pageid,String langcode,String devicecode) {
		
		int pagenumber =Integer.parseInt(pageid);
		try {
			
			Optional<DevicePage> flowid =devicePageRepo.findbytokeanpage(tokean, pagenumber);
			 
			 if (flowid.isPresent()){
				 return flowid.get();
			

						}
				else{
				   // alternative processing....
				UserLogin userlogin = userLoginServ.getbyid(1, langcode); 
				Pages p= pagesServ.getbyid(pagenumber);
				Device dev=deviceServ.checkdevicetwo(devicecode, langcode);
				DevicePage out=devicePageServ.savedevicepagewithout(request,dev, p,userlogin,langcode);
				return  out;
					
					
				}
		}  catch (Exception  se) {
			UserLogin userlogin = userLoginServ.getbyid(1, langcode); 
			Pages p= pagesServ.getbyid(pagenumber);
			Device dev=deviceServ.checkdevicetwo(devicecode, langcode);
			DevicePage out=devicePageServ.savedevicepagewithout(request,dev, p,userlogin,langcode);
			return  out;
			
	    }

		
	}
	

	

public DevicePage savedevpag(HttpServletRequest request,Device dev,Pages pa,String username,String tokean,String langcode) {
	try {
		UserLogin userlogin = null;
		DevicePage a=new DevicePage();
	    userlogin =  userLoginServ.check_userlogin(request, dev, pa, username, tokean, langcode);
	    if(userlogin == null || userlogin.getId() == 1) {
	    	userlogin = userLoginServ.getbyid(1, langcode);  
	    	Generate gen=new Generate();
	    	String publictokean = gen.token(30);
			a.setPageTokean(publictokean);
	    }else {
			a.setPageTokean(userlogin.getUsertokean());	
	    }
	    
	    Date visittime = new Date();
	
		a.setDeviceId(dev);
		a.setPagesID(pa);
		a.setVisittime(visittime);
		a.setUserloginID(userlogin);

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



public DevicePage savedevicepagewithout(HttpServletRequest request,Device dev,Pages pa,UserLogin user,String langcode) {
	try {

	    Date visittime = new Date();
		DevicePage a=new DevicePage();
		a.setDeviceId(dev);
		a.setPagesID(pa);
		a.setVisittime(visittime);
		a.setUserloginID(user);
		a.setPageTokean(user.getUsertokean());
		DevicePage out=devicePageRepo.save(a);
		return out;
	} catch (TransientDataAccessException  se) {
		String text= "sql error"+se.getMessage();
		logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());			
	
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		String text= "sql error"+se.getMessage();
		logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		String text= "sql error"+se.getMessage();
		logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		String text= "sql error"+se.getMessage();
		logServ.errorlog(dev.getDeviceip(),request,text, dev, user.getId(), 2, langcode,se.getMessage());	
		
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}

	
}




public List<pagesdevice> getpagesbydevice(int id,String langcode) {
	System.out.println(id);
	List<DevicePage> p=new ArrayList<DevicePage>();
	try {
		 p= (List<DevicePage>)devicePageRepo.findbydeviceid(id);
	} catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}
	
	
	if(p.size() <= 0)
	{
		throw new PopupException("no pages");
		
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
