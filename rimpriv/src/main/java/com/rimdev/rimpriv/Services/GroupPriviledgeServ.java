package com.rimdev.rimpriv.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimpriv.Repo.GroupPriviledgeRepo;
import com.rimdev.rimpriv.entities.GroupPriviledge;

@Service
public class GroupPriviledgeServ {
	@Autowired
	GroupPriviledgeRepo groupPriviledgeRepo;
	
	@Autowired
	ExternalServ textConvertionServ;
	
	
	public List<GroupPriviledge> getall(String langcode) {
		try {
			return (List<GroupPriviledge>) groupPriviledgeRepo.findAll();
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

}
