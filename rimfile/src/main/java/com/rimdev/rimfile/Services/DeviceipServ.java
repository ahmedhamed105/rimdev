package com.rimdev.rimfile.Services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimfile.Repo.DeviceipRepo;
import com.rimdev.rimfile.entities.Device;
import com.rimdev.rimfile.entities.Deviceip;

@Service
public class DeviceipServ {
	
	@Autowired
	DeviceipRepo deviceipRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	public void savebyip(Deviceip Geo,String langcode){
		
			Deviceip out = getDeviceip(Geo.getIpAddress(),Geo.getDeviceId().getId(),langcode);
			
			if(out == null)
			{
		     
				save(Geo, langcode);
				
		}else {
			
			
				update(out, langcode);
		}
	
		
		
		
	}
	
	
	
	

public Deviceip getDeviceip(String ip,int deviceid,String langcode) {
	
	try {
		Optional<Deviceip> flowid =deviceipRepo.findbyip(ip,deviceid);
		 
		 if (flowid.isPresent()){
			 return flowid.get();
		

					}
			else{
			   // alternative processing....
				
				return null;
				
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




public void save(Deviceip input,String langcode) {
	
		System.out.println(input.getIpAddress());
	    Date date = new Date();
		input.setDeviceipcreate(date);
		input.setDeviceipmodify(date);		
		
		try {
			deviceipRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
		
	
	


	
}



public void update(Deviceip input,String langcode) {
	

	
	Date date = new Date();
	input.setDeviceipmodify(date);
	
	try {
		deviceipRepo.save(input);	
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
