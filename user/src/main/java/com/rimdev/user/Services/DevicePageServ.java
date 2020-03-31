package com.rimdev.user.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.redirectlogin;
import com.rimdev.user.Repo.DevicePageRepo;
import com.rimdev.user.entities.DevicePage;

@Service
public class DevicePageServ {
	
	@Autowired
	DevicePageRepo devicePageRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	public DevicePage check_tokean_page(String tokean,String pageid,String langcode) {
		
		try {
			int pagenumber =Integer.parseInt(pageid);
			Optional<DevicePage> flowid =devicePageRepo.findbytokeanpage(tokean, pagenumber);
			 
			 if (flowid.isPresent()){
				 return flowid.get();
			

						}
				else{
				   // alternative processing....
					throw new redirectlogin(textConvertionServ.search("E105", langcode));
					
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

}
