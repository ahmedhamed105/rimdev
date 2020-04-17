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

import com.rimdev.user.Repo.DeviceRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.DeviceType;
import com.rimdev.user.entities.Deviceip;
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
	ConfigurationServ configurationServ;
	
	@Autowired
	LoginTypeServ loginTypeServ;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	

	
	public void addfailedlogin(Device dev){
		Date date = new Date();
		int a= dev.getLoginFail()+1;	
		dev.setDevicemodify(date);
		dev.setLoginFail(a);
		Device ouput =deviceRepo.save(dev);
		
	}
	
	
	public void sucesslogin(Device dev){
		Date date = new Date();
		dev.setDevicemodify(date);
		dev.setLoginFail(0);
		Device ouput =deviceRepo.save(dev);
		
	}
	
	
	public void blockdevice(Device dev){
		Date date = new Date();
		dev.setDevicemodify(date);
		dev.setDevicestatusID(deviceStatusServ.getbyid(2));
		dev.setLoginFail(0);
		Device ouput =deviceRepo.save(dev);
		

		
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
		Generate gen=new Generate();
		input.setDevicetokean(gen.token(30));
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
		input.setTokeantime(c.getTime());
		input.setDevicecreate(date);
		input.setDevicemodify(date);
		input.setLoginFail(0);
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
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}	
}






public DevicePage SaveDP(Device input,String username,String tokean,String langcode) {
		
	try {	
		
		if(input.getDeviceOSID()==null || input.getDeviceOSID().getId()==null) {
			input.setDeviceOSID(deviceOsServ.getbyname("Unknown"));
			
		}
		if(input.getDevicetypeID()==null || input.getDevicetypeID().getId()==null) {
			input.setDevicetypeID(deviceTypeServ.getbyname("Unknown"));		
		}
		
		if(input.getLogintypeID()==null || input.getLogintypeID().getId()==null) {
		
			input.setLogintypeID(loginTypeServ.getbyid(2));
	
		}
		
		input.setLogintypeID(loginTypeServ.getbyid(input.getLogintypeID().getId()));
		
		input.setDevicestatusID(deviceStatusServ.getbyid(1));
		
		
		Date date = new Date();
		Generate gen=new Generate();
		input.setDevicetokean(gen.token(30));
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if( configurationServ.getbykey("Tokean_Expiration_flag").getConfignum() == 1) {
         c.add(Calendar.HOUR, configurationServ.getbykey("Tokean_Expiration_hours").getConfignum()); //same with c.add(Calendar.DAY_OF_MONTH, 1);
         c.add(Calendar.MINUTE, configurationServ.getbykey("Tokean_Expiration_minutes").getConfignum()); //same with c.add(Calendar.DAY_OF_MONTH, 1);	
        }else {      	
      	  c.add(Calendar.MONTH, 12); //same with c.add(Calendar.DAY_OF_MONTH, 1);	
        }
          input.setTokeantime(c.getTime());
		input.setDevicecreate(date);
		input.setDevicemodify(date);
		Device ouput =deviceRepo.save(input);	
		
		
		Pages p= pagesServ.getbyid(input.getPage());
		DevicePage out=devicePageServ.savedevpag(ouput, p,username, tokean,langcode);
		
	
		
		return out;
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

public DevicePage updateDP(Device input,String username,String tokean,String langcode) {
	
	
	try {
	Generate gen=new Generate();
	input.setDevicetokean(gen.token(30));
    // convert date to calendar
	Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    if( configurationServ.getbykey("Tokean_Expiration_flag").getConfignum() == 1) {
        c.add(Calendar.HOUR, configurationServ.getbykey("Tokean_Expiration_hours").getConfignum()); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MINUTE, configurationServ.getbykey("Tokean_Expiration_minutes").getConfignum()); //same with c.add(Calendar.DAY_OF_MONTH, 1);	
       }else {      	
     	  c.add(Calendar.MONTH, 12); //same with c.add(Calendar.DAY_OF_MONTH, 1);	
       }
	input.setTokeantime(c.getTime());
	input.setDevicemodify(date);
	Device ouput =deviceRepo.save(input);	

	
	Pages p= pagesServ.getbyid(input.getPage());
	DevicePage out=devicePageServ.savedevpag(ouput, p,username, tokean,langcode);
	

	return out;
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
