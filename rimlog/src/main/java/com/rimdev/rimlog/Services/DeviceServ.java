package com.rimdev.rimlog.Services;

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

import com.rimdev.rimlog.Exception.BlockedException;
import com.rimdev.rimlog.Exception.NooauthException;
import com.rimdev.rimlog.Repo.DeviceRepo;
import com.rimdev.rimlog.Utils.Generate;
import com.rimdev.rimlog.entities.Application;
import com.rimdev.rimlog.entities.Device;
import com.rimdev.rimlog.entities.DeviceOs;
import com.rimdev.rimlog.entities.DevicePage;
import com.rimdev.rimlog.entities.DeviceType;
import com.rimdev.rimlog.entities.Pages;
import com.rimdev.rimlog.entities.UserLogin;

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
	ConfigurationServ configurationServ;
	
	@Autowired
	ApplicationServ applicationServ;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	LogServ logServ;

	

	
	

	

public Device getbyid(int id,String langcode) {
	try {
		Optional<Device> flowid =deviceRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Device  ouput = flowid.get();
		
			  return ouput;
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

	
public List<Device> getall(String langcode) {
	try {
		return (List<Device>) deviceRepo.findAll();
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



public Device checkdevicetwo(String Device_code,String langcode) {
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

public Device checkdevice(String ip,DeviceOs os,DeviceType type,String browser,String langcode) {
	try {
		List<Device> a= (List<Device>) deviceRepo.findbyiposbrowser( ip, os, type, browser);
	    return a.get(0);
	
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
	
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
		input.setDevicecreate(date);
		input.setDevicemodify(date);
		Device ouput =deviceRepo.save(input);	
		
		
		
		
		return ouput;
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

public Device update(Device old,Device input,String langcode) {
	
	
	try {
		old.setDevicestatusID(input.getDevicestatusID());
    // convert date to calendar
	Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
    old.setDevicemodify(date);
	Device ouput =deviceRepo.save(old);	

	
	return ouput;
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






public DevicePage SaveDP(HttpServletRequest request,Device input,String username,String tokean,String langcode) {
		
	try {	
		
		System.out.println(input.getDeviceOSID().getDeviceOS());
		
		DeviceOs deviceos=deviceOsServ.getbyname(input.getDeviceOSID().getDeviceOS());
		
		if(deviceos==null) {
			input.setDeviceOSID(deviceOsServ.getbyname("Unknown"));
			
		}else {
			
			input.setDeviceOSID(deviceos);
		}
		
		System.out.println(input.getDevicetypeID().getDevtype());
		DeviceType devicetype=deviceTypeServ.getbyname(input.getDevicetypeID().getDevtype());
		
		if(devicetype==null) {
			input.setDevicetypeID(deviceTypeServ.getbyname("Unknown"));		
		}else {
			input.setDevicetypeID(devicetype);
		}
		
		
		Application application =applicationServ.getbytype(input.getApplicationID().getAppname());
		if(application == null) {
		
			throw new NooauthException(textConvertionServ.search("E104", langcode));
	
		}else {
			 input.setApplicationID(application);
		}
		
	
		input.setDevicestatusID(deviceStatusServ.getbyid(1));
		
		
		Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
		input.setDevicecreate(date);
		input.setDevicemodify(date);
		Device ouput =deviceRepo.save(input);	
		
		
		Pages p= pagesServ.getbyid(input.getPage());
		DevicePage out=devicePageServ.savedevpag(request,ouput, p,username, tokean,langcode);
		
	
		
		return out;
	} catch (TransientDataAccessException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());	
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());	
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());	
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		String text= "sql error"+tokean;
		logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());	
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }			
		
	}





public DevicePage updateDP(HttpServletRequest request,Device input,Device update,String username,String tokean,String langcode) {
	
	
	if(input.getDevicestatusID().getId() == 2) {
		
	   	String text= "Device is blocked";
		logServ.errorlog(input.getDeviceip(),request,text, input, 0, 26, langcode," ");			
	
	    throw new BlockedException(textConvertionServ.search("E111", langcode));			
		
		
	}
	
	System.out.println(input.getDeviceOSID().getDeviceOS());
	
	DeviceOs deviceos=deviceOsServ.getbyname(update.getDeviceOSID().getDeviceOS());
	
	if(deviceos==null) {
		input.setDeviceOSID(deviceOsServ.getbyname("Unknown"));
		
	}else {
		
		input.setDeviceOSID(deviceos);
	}
	
	System.out.println(input.getDevicetypeID().getDevtype());
	DeviceType devicetype=deviceTypeServ.getbyname(update.getDevicetypeID().getDevtype());
	
	if(devicetype==null) {
		input.setDevicetypeID(deviceTypeServ.getbyname("Unknown"));		
	}else {
		input.setDevicetypeID(devicetype);
	}
	
	
	Application application =applicationServ.getbytype(update.getApplicationID().getAppname());
	if(application == null) {
	
		throw new NooauthException(textConvertionServ.search("E104", langcode));

	}else {
		 input.setApplicationID(application);
	}
	
	Device ouput ;
	
	try {
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

	
} catch (TransientDataAccessException  se) {
	String text= "sql error"+tokean;
	logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	String text= "sql error"+tokean;
	logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	String text= "sql error"+tokean;
	logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	se.printStackTrace();
	String text= "sql error"+tokean;
	logServ.errorlog(input.getDeviceip(),request,text, input, 0, 2, langcode,se.getMessage());
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}
	
	
	//get page entity
		Pages p= pagesServ.getbyid(input.getPage());
		
		
		DevicePage out=devicePageServ.savedevpag(request,ouput, p,username, tokean,langcode);
		

		return out;
	
}


}
