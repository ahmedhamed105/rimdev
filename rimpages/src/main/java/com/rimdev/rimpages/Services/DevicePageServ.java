package com.rimdev.rimpages.Services;

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

import com.rimdev.rimpages.Exception.NoResultException;
import com.rimdev.rimpages.Repo.DevicePageRepo;
import com.rimdev.rimpages.Utils.Generate;
import com.rimdev.rimpages.entities.Device;
import com.rimdev.rimpages.entities.DevicePage;
import com.rimdev.rimpages.entities.Pages;
import com.rimdev.rimpages.entities.UserLogin;

@Service
public class DevicePageServ {
	
	@Autowired
	DevicePageRepo devicePageRepo;
	
	@Autowired
	ExternalServ textConvertionServ;
	
	
	
	@Autowired
	PagesServ pagesServ;
	
	


public List<DevicePage> getpagesbydevice(int id,String langcode) {
	System.out.println(id);
	List<DevicePage> p=new ArrayList<DevicePage>();
	try {
		 p= (List<DevicePage>)devicePageRepo.findbydeviceid(id);
	} catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
	} catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
	}catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
	}catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
	}
	
	
	if(p.size() <= 0)
	{
		throw new NoResultException(textConvertionServ.search("E400", langcode));
		
	}
	

		return p;
		
	}



}
