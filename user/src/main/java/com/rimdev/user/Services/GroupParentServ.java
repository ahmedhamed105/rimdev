package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.redirectlogin;
import com.rimdev.user.Repo.GroupParentRepo;
import com.rimdev.user.Repo.ParentPriviledgeRepo;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.GroupParent;
import com.rimdev.user.entities.GroupPriviledge;
import com.rimdev.user.ouputobject.parent_comp;

@Service
public class GroupParentServ {
	
	
	@Autowired
	ParentPriviledgeServ  parentPriviledgeServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	@Autowired
	GroupParentRepo groupParentRepo;
	
	
	public List<GroupParent> getbygroup(int groupid,String langcode) {
		try {
			return (List<GroupParent>) groupParentRepo.getbygroup(groupid);
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
	
	
	public List<parent_comp> check_priviledge(List<parent_comp> comp,DevicePage devpage,String langcode) {
		
		
		GroupPriviledge group=devpage.getUserloginID().getGrouppriviledgeID();
		  if(group.getGroupstatusID().getId() == 1) {
			  List<GroupParent> parents=  getbygroup(group.getId(), langcode);
			  List<parent_comp> outcomp=  parentPriviledgeServ.removeparentnoprivil(comp, parents,devpage);
				return outcomp;
		  }else {
			  throw new redirectlogin("no Priviledge to enter ahmed");
		  }
		
	
		
	}

}
