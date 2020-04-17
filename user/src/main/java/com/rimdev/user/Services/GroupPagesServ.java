package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Repo.GroupPagesRepo;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.GroupPages;
import com.rimdev.user.entities.GroupParent;
import com.rimdev.user.entities.GroupPriviledge;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.entities.ParentMenu;
import com.rimdev.user.ouputobject.parent_comp;

@Service
public class GroupPagesServ {
	
	@Autowired
	PagesPriviledgeServ pagesPriviledgeServ;
	
	@Autowired
	GroupPagesRepo groupPagesRepo;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
	
	
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
	
	
	public void check_page(DevicePage devpage,String langcode) {
		
		
		GroupPriviledge group=devpage.getUserloginID().getGrouppriviledgeID();
		
		  if(group.getGroupstatusID().getId() == 1) {
			  List<GroupPages> parents=  getbygroup(group.getId(), langcode);
			  
			  boolean outcomp=  pagesPriviledgeServ.validpage(devpage.getPagesID(), parents,devpage);
			  if(!outcomp) {
					 throw new PopupException("no Priviledge to enter");	
				}
		  }else {
			  throw new PopupException("no Priviledge to enter ");
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
