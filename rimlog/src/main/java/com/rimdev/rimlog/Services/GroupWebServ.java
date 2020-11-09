package com.rimdev.rimlog.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.Exception.PopupException;
import com.rimdev.rimlog.Repo.GroupWebRepo;
import com.rimdev.rimlog.entities.DevicePage;
import com.rimdev.rimlog.entities.GroupPriviledge;
import com.rimdev.rimlog.entities.GroupWeb;

@Repository
public class GroupWebServ {
	
	@Autowired
	GroupWebRepo groupWebRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	WebservicepriviledgeServ webservicepriviledgeServ;
	
	
	@Autowired
	LogServ logServ;
	
	
	public List<GroupWeb> getbygroup(int groupid,String langcode) {
		try {
			return (List<GroupWeb>) groupWebRepo.getbygroup(groupid);
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
		
		
	}
	
	
	public void checkpriviledge(HttpServletRequest request,DevicePage page,List<String> paramter,List<String> values)  {
	
		//System.out.println("begin");
		
		try {
			
			String langcode = values.get(paramter.indexOf("langcode"));
			
			
			 GroupPriviledge group=page.getUserloginID().getGrouppriviledgeID();
			  if(group.getGroupstatusID().getId() == 1) {
				  List<GroupWeb> webservices=  getbygroup(group.getId(), langcode);
				  
				
				String URL = webservicepriviledgeServ.makeUrl(request,paramter,values);
				//System.out.println("ahmed hamed"+ URL);
				
	
				
				boolean validsd=webservicepriviledgeServ.findwebservices(page,URL, webservices);
				//System.out.println(validsd);
				
				if(!validsd) {
					  String text= "webservice : "+ page.getPagesID().getPagename()+" No priviledge for user : "+page.getUserloginID().getUsername() +" group "+group.getGroupname()+" is closed";
					  logServ.errorlog(page.getDeviceId().getDeviceip(),request,text, page.getDeviceId(), page.getUserloginID().getId(), 15, langcode," ");					
						
				 throw new PopupException(textConvertionServ.search("E101", langcode));
				}else {
					
					String text= "webservice : "+ page.getPagesID().getPagename()+" have priviledge for user : "+page.getUserloginID().getUsername();
					logServ.info(page.getDeviceId().getDeviceip(),request,text, page.getDeviceId(), page.getUserloginID().getId(), 9, langcode," ");					
					
				}
				 
			  }else {
				  
				  String text= "webservice : "+ page.getPagesID().getPagename()+" No priviledge for user : "+page.getUserloginID().getUsername() +" group "+group.getGroupname()+" is closed";
				  logServ.errorlog(page.getDeviceId().getDeviceip(),request,text, page.getDeviceId(), page.getUserloginID().getId(), 13, langcode," ");					
					
				  throw new PopupException(textConvertionServ.search("E101", langcode));
			  }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		
		
	}
	
	
	public void checkpriviledgeexternal(HttpServletRequest request,String url,DevicePage page,List<String> paramter,List<String> values)  {
		
		//System.out.println("begin");
		
		try {
			
			String langcode = values.get(paramter.indexOf("langcode"));
			
			
			 GroupPriviledge group=page.getUserloginID().getGrouppriviledgeID();
			  if(group.getGroupstatusID().getId() == 1) {
				  List<GroupWeb> webservices=  getbygroup(group.getId(), langcode);
				  
				
				String URL = webservicepriviledgeServ.makeUrlexternal(url,paramter,values);
				//System.out.println("ahmed hamed"+ URL);
				
	
				
				boolean validsd=webservicepriviledgeServ.findwebservices(page,URL, webservices);
				//System.out.println(validsd);
				
				if(!validsd) {
					  String text= "webservice : "+ page.getPagesID().getPagename()+" No priviledge for user : "+page.getUserloginID().getUsername() +" group "+group.getGroupname();
					  logServ.errorlog(page.getDeviceId().getDeviceip(),request,text, page.getDeviceId(), page.getUserloginID().getId(), 15, langcode," ");					
						
				 throw new PopupException(textConvertionServ.search("E101", langcode));
				}else {
					
					String text= "webservice : "+ page.getPagesID().getPagename()+" have priviledge for user : "+page.getUserloginID().getUsername();
					logServ.info(page.getDeviceId().getDeviceip(),request,text, page.getDeviceId(), page.getUserloginID().getId(), 9, langcode," ");					
					
				}
				 
			  }else {
				  
				  String text= "webservice : "+ page.getPagesID().getPagename()+" No priviledge for user : "+page.getUserloginID().getUsername() +" group "+group.getGroupname()+" is closed";
				  logServ.errorlog(page.getDeviceId().getDeviceip(),request,text, page.getDeviceId(), page.getUserloginID().getId(), 13, langcode," ");					
					
				  throw new PopupException(textConvertionServ.search("E101", langcode));
			  }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		
		
	}


}
