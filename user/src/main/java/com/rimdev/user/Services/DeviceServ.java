package com.rimdev.user.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.DeviceRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.DeviceType;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.response_all;

@Service
public class DeviceServ {
	
	@Autowired 
	private DeviceRepo deviceRepo;
	
	@Autowired
	PagesServ pagesServ;
	
	@Autowired
	DeviceOsServ deviceOsServ;
	
	@Autowired
	DeviceTypeServ deviceTypeServ;
	
	@Autowired
	DeviceStatusServ deviceStatusServ;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
public List<Device> getall(String langcode) {
	try {
		return (List<Device>) deviceRepo.findAll();
} catch (TransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}
		
	}
public Device checkdevicetwo(String Device_code) {
	try {
		Optional<Device> flowid =deviceRepo.findbydevicecode(Device_code);
		 
		 if (flowid.isPresent()){
			 Device  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
}

public Device checkdevice(String ip,DeviceOs os,DeviceType type,String browser) {
	try {
		List<Device> a= (List<Device>) deviceRepo.findbyiposbrowser( ip, os, type, browser);
	    return a.get(0);
	
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
	
}


public Device Save(Device input,String langcode) {
		
	try {	
		
		if(input.getDeviceOSID()==null || input.getDeviceOSID().getId()==null) {
			input.setDeviceOSID(deviceOsServ.getbyname("Unknown"));
			
		}
		if(input.getDevicetypeID()==null || input.getDevicetypeID().getId()==null) {
			input.setDevicetypeID(deviceTypeServ.getbyname("Unknown"));		
		}
		
		
		input.setDevicestatusID(deviceStatusServ.getbyid(1));
		
		
		Date date = new Date();
		Generate gen=new Generate();
		input.setDevicetokean(gen.token(30));
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
		input.setTokeantime(c.getTime());
		input.setDevicecreate(date);
		input.setDevicemodify(date);
		Device ouput =deviceRepo.save(input);	
		
		
		
		
		return ouput;
	} catch (TransientDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }			
		
	}

public Device update(Device input,String langcode) {
	
	
	try {
	Generate gen=new Generate();
	input.setDevicetokean(gen.token(30));
    // convert date to calendar
	Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
	input.setTokeantime(c.getTime());
	input.setDevicemodify(date);
	Device ouput =deviceRepo.save(input);	

	
	return ouput;
} catch (TransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}	
}






public Device SaveDP(Device input,String username,String tokean,String langcode) {
		
	try {	
		
		if(input.getDeviceOSID()==null || input.getDeviceOSID().getId()==null) {
			input.setDeviceOSID(deviceOsServ.getbyname("Unknown"));
			
		}
		if(input.getDevicetypeID()==null || input.getDevicetypeID().getId()==null) {
			input.setDevicetypeID(deviceTypeServ.getbyname("Unknown"));		
		}
		
		
		input.setDevicestatusID(deviceStatusServ.getbyid(1));
		
		
		Date date = new Date();
		Generate gen=new Generate();
		input.setDevicetokean(gen.token(30));
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
		input.setTokeantime(c.getTime());
		input.setDevicecreate(date);
		input.setDevicemodify(date);
		Device ouput =deviceRepo.save(input);	
		

		
		Pages p= pagesServ.getbyid(input.getPage());
		String pagetokean=pagesServ.savedevpag(ouput, p,username, tokean,langcode);
		
		ouput.setDevicetokean(pagetokean);
		
		return ouput;
	} catch (TransientDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }			
		
	}

public Device updateDP(Device input,String username,String tokean,String langcode) {
	
	
	try {
	Generate gen=new Generate();
	input.setDevicetokean(gen.token(30));
    // convert date to calendar
	Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
	input.setTokeantime(c.getTime());
	input.setDevicemodify(date);
	Device ouput =deviceRepo.save(input);	

	
	Pages p= pagesServ.getbyid(input.getPage());
	String pagetokean=pagesServ.savedevpag(ouput, p,username, tokean,langcode);
	
	ouput.setDevicetokean(pagetokean);
	
	return ouput;
} catch (TransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}	
}


}
