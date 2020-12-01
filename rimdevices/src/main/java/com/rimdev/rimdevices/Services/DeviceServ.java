package com.rimdev.rimdevices.Services;

import java.io.File;
import java.net.InetAddress;
import java.util.Calendar;
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

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.rimdev.rimdevices.Exception.BlockedException;
import com.rimdev.rimdevices.Exception.NoResultException;
import com.rimdev.rimdevices.Exception.NooauthException;
import com.rimdev.rimdevices.Repo.DeviceRepo;
import com.rimdev.rimdevices.entities.Application;
import com.rimdev.rimdevices.entities.Device;
import com.rimdev.rimdevices.entities.DeviceOs;
import com.rimdev.rimdevices.entities.DevicePage;
import com.rimdev.rimdevices.entities.DeviceType;
import com.rimdev.rimdevices.entities.Pages;

@Service
public class DeviceServ {
	
	@Autowired 
	 DeviceRepo deviceRepo;
	
	
	@Autowired
	DeviceOsServ deviceOsServ;
	
	@Autowired
	DeviceTypeServ deviceTypeServ;
	
	@Autowired
	DeviceStatusServ deviceStatusServ;
	
	@Autowired
	ApplicationServ applicationServ;
	
	
	@Autowired
	LangExternalServ textConvertionServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	DeviceipServ deviceipServ;
	

	

	
	

	

public Device getbyid(int id,String langcode) {
	try {
		Optional<Device> flowid =deviceRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Device  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoResultException(textConvertionServ.search("E104", langcode));
			}
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }
	
}

	



public Device getbycode(String Device_code,String langcode) {
	try {
		Optional<Device> flowid =deviceRepo.findbydevicecode(Device_code);
		 
		 if (flowid.isPresent()){
			 Device  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoResultException(textConvertionServ.search("E104", langcode));
			}
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }
	
}





public List<Device> getall(String langcode) {
	try {
		return (List<Device>) deviceRepo.findAll();
} catch (TransientDataAccessException  se) {
	throw new NoResultException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NoResultException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	throw new NoResultException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NoResultException(textConvertionServ.search("E104", langcode));
}
		
	}







public boolean check_device(Device input,String langcode){
	
	if(input.getDevicestatusID().getId() == 2) {

		 throw new BlockedException(textConvertionServ.search("E201", langcode));				
		
	}else {
		
	    return true;				

	}
	
}





public Device saveDevice(Device newdev,String langcode) {
	
	
	try {
		DeviceType defaultdeviceType = deviceTypeServ.getbyname("Unknown");
		DeviceOs defaultdeviceOS = deviceOsServ.getbyname("Unknown");


		DeviceOs deviceos = deviceOsServ.getbyname(newdev.getDeviceOSID().getDeviceOS());

		if (deviceos == null) {
			newdev.setDeviceOSID(defaultdeviceOS);

		} else {

			newdev.setDeviceOSID(deviceos);
		}

		DeviceType devicetype = deviceTypeServ.getbyname(newdev.getDevicetypeID().getDevtype());

		if (devicetype == null) {
			newdev.setDevicetypeID(defaultdeviceType);
		} else {
			newdev.setDevicetypeID(devicetype);
		}

		Application application = applicationServ.getbytype(newdev.getApplicationID().getAppname(),langcode);
		if (application != null) {
			newdev.setApplicationID(application);
		}

	} catch (Exception e) {

		throw e;

	}
	
	Device ouput ;
	
	
    // convert date to calendar
	Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);

    newdev.setDevicemodify(date);

    ouput =deviceRepo.save(newdev);	

    deviceipServ.savebyip(ouput, langcode);
	
	return ouput;
	
}


public Device updateDevice(Device input,Device update,String langcode) {
	
	
	try {
		DeviceType defaultdeviceType = deviceTypeServ.getbyname("Unknown");
		DeviceOs defaultdeviceOS = deviceOsServ.getbyname("Unknown");

		check_device(input, langcode);

		DeviceOs deviceos = deviceOsServ.getbyname(update.getDeviceOSID().getDeviceOS());

		if (deviceos == null) {
			input.setDeviceOSID(defaultdeviceOS);

		} else {

			input.setDeviceOSID(deviceos);
		}

		DeviceType devicetype = deviceTypeServ.getbyname(update.getDevicetypeID().getDevtype());

		if (devicetype == null) {
			input.setDevicetypeID(defaultdeviceType);
		} else {
			input.setDevicetypeID(devicetype);
		}

		Application application = applicationServ.getbytype(update.getApplicationID().getAppname(),langcode);
		if (application != null) {
			input.setApplicationID(application);
		}

	} catch (Exception e) {

		throw e;

	}
	
	Device ouput ;
	
	
    // convert date to calendar
	Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    input.setDeviceip(update.getDeviceip());
	input.setDevicemodify(date);
	input.setDevicebrowser(update.getDevicebrowser());
	input.setDevicename(update.getDevicename());
	input.setDeviceosunknow(update.getDeviceosunknow());
	input.setDeviceosversion(update.getDeviceosversion());
	input.setDevicemac(update.getDevicemac());
	input.setDeviceinfo(update.getDeviceinfo());
	input.setDesktopDevice(update.isDesktopDevice());
	input.setTablet(update.isTablet());
	input.setMobile(update.isMobile());
	input.setDeviceBVersion(update.getDeviceBVersion());
	input.setDevicelatitude(update.getDevicelatitude());
	input.setDevicelong(update.getDevicelong());
	input.setPage(update.getPage());
	
    ouput =deviceRepo.save(input);	
    
    deviceipServ.savebyip(ouput, langcode);

	
	return ouput;
	
}




}
