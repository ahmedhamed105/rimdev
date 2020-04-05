package com.rimdev.user.Services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Repository;

import com.rimdev.user.Exception.redirectlogin;
import com.rimdev.user.Repo.GroupWebRepo;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.GroupPriviledge;
import com.rimdev.user.entities.GroupWeb;

import javassist.NotFoundException;

@Repository
public class GroupWebServ {
	
	@Autowired
	GroupWebRepo groupWebRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	WebservicepriviledgeServ webservicepriviledgeServ;
	
	
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
	
		System.out.println("begin");
		
		try {
			
			String langcode = values.get(paramter.indexOf("langcode"));
			
			
			 GroupPriviledge group=page.getUserloginID().getGrouppriviledgeID();
			  if(group.getGroupstatusID().getId() == 1) {
				  List<GroupWeb> webservices=  getbygroup(group.getId(), langcode);
				  
				  
				  
				boolean found=false;
				
				String URL = webservicepriviledgeServ.makeUrl(request,paramter,values);
				System.out.println("ahmed hamed"+ URL);
				
				boolean validsd=webservicepriviledgeServ.findwebservices(page,URL, webservices);
				System.out.println(validsd);
				
				if(!validsd) {
					
					 throw new redirectlogin("no Priviledge to enter");	
				}
				 
			  }else {
				  throw new redirectlogin("no Priviledge to enter");
			  }
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw e;
		}
		
		
	}

}
