package com.rimdev.rimfile.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimfile.Exception.NooauthException;
import com.rimdev.rimfile.Exception.PopupException;
import com.rimdev.rimfile.Exception.RedirectException;
import com.rimdev.rimfile.Repo.GroupPagesRepo;
import com.rimdev.rimfile.entities.DevicePage;
import com.rimdev.rimfile.entities.GroupPages;
import com.rimdev.rimfile.entities.GroupParent;
import com.rimdev.rimfile.entities.GroupPriviledge;
import com.rimdev.rimfile.entities.Pages;
import com.rimdev.rimfile.entities.ParentMenu;
import com.rimdev.rimfile.outputobject.parent_comp;

@Service
public class GroupPagesServ {
	
	@Autowired
	PagesPriviledgeServ pagesPriviledgeServ;
	
	@Autowired
	GroupPagesRepo groupPagesRepo;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	@Autowired
	LogServ logServ;
	
	
	
	
	
	public List<GroupPages> getbygroup(int groupid,String langcode) {
		try {
			return (List<GroupPages>) groupPagesRepo.getbygroup(groupid);
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
	
	
	public void check_page(HttpServletRequest request,DevicePage devpage,String langcode) {
		
		
		GroupPriviledge group=devpage.getUserloginID().getGrouppriviledgeID();
		
		  if(group.getGroupstatusID().getId() == 1) {
			  List<GroupPages> parents=  getbygroup(group.getId(), langcode);
			  
			  boolean outcomp=  pagesPriviledgeServ.validpage(devpage.getPagesID(), parents,devpage);
			  if(!outcomp) {
				  String text= "page : "+ devpage.getPagesID().getPagename()+" No priviledge for user : "+devpage.getUserloginID().getUsername();
				  logServ.errorlog(devpage.getDeviceId().getDeviceip(),request,text, devpage.getDeviceId(), devpage.getUserloginID().getId(), 14, langcode," ");
				  
					 throw new RedirectException(textConvertionServ.search("E101", langcode));	
				}else {
					
					String text= "page : "+ devpage.getPagesID().getPagename()+" have priviledge for user : "+devpage.getUserloginID().getUsername();
					logServ.info(devpage.getDeviceId().getDeviceip(),request,text, devpage.getDeviceId(), devpage.getUserloginID().getId(), 8, langcode," ");					
					
					
				}
		  }else {
			  
			  String text= "page : "+ devpage.getPagesID().getPagename()+" No priviledge for user : "+devpage.getUserloginID().getUsername() +" group "+group.getGroupname()+" is closed";
			  logServ.errorlog(devpage.getDeviceId().getDeviceip(),request,text, devpage.getDeviceId(), devpage.getUserloginID().getId(), 13, langcode," ");					
				
			  throw new RedirectException(textConvertionServ.search("E101", langcode));
		  }
		
	
		
	}
	
	
	public boolean check_menu(Pages page,DevicePage devpage,String langcode) {
		
		
		GroupPriviledge group=devpage.getUserloginID().getGrouppriviledgeID();
		  if(group.getGroupstatusID().getId() == 1) {
			  List<GroupPages> parents=  getbygroup(group.getId(), langcode);
			  
			  boolean outcomp=  pagesPriviledgeServ.validpage(page, parents,devpage);
				return outcomp;
		  }else {
			  throw new PopupException("no Priviledge to enter ahmed");
		  }
		
	
		
	}
}
