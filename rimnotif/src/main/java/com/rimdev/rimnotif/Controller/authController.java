package com.rimdev.rimnotif.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.rimnotif.Services.DevicePageServ;
import com.rimdev.rimnotif.Services.LogServ;
import com.rimdev.rimnotif.entities.DevicePage;
import com.rimdev.rimnotif.outputobject.authobject;
import com.rimdev.rimnotif.outputobject.authouput;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Auth") // 
public class authController {
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	LogServ logServ;
	
	
	

@RequestMapping(value = "/get/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<authouput> savefile(HttpServletRequest req,@RequestBody authobject auth) {

	try {
		DevicePage dg= devicePageServ.check_webservice_external(req,auth.getRequestURL(), auth.getUsertokean(), auth.getUsername(), auth.getPagenum(), auth.getLangcode(),auth.getDevicecode(),auth.getParamter(),auth.getValues());
		authouput out= new authouput();
		out.setDeviceId(dg.getDeviceId().getId());
		out.setDeviceIP(dg.getDeviceId().getDeviceip());
		out.setUserloginID(dg.getUserloginID().getId());
		out.setUsername(dg.getUserloginID().getUsername());

return new ResponseEntity<authouput>(out, HttpStatus.OK);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		authouput out= new authouput();
		return new ResponseEntity<authouput>(out, HttpStatus.UNAUTHORIZED);
	}

}



@RequestMapping(value = "/log/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<authouput> log_text(HttpServletRequest req,@RequestBody authobject auth) {

	try {
		DevicePage dg= devicePageServ.check_webservice_external(req,auth.getRequestURL(), auth.getUsertokean(), auth.getUsername(), auth.getPagenum(), auth.getLangcode(),auth.getDevicecode(),auth.getParamter(),auth.getValues());
		authouput out= new authouput();
		out.setDeviceId(dg.getDeviceId().getId());
		out.setDeviceIP(dg.getDeviceId().getDeviceip());
		out.setUserloginID(dg.getUserloginID().getId());
		out.setUsername(dg.getUserloginID().getUsername());
		
		if(auth.isInfo()) {
			logServ.infoexternal(dg.getDeviceId().getDeviceip(), auth.getRequestURL(), auth.getLogtext(), dg.getDeviceId(),dg.getUserloginID().getId(), auth.getLogtype(), auth.getLangcode(), auth.getLogException());
		}else if(auth.isErorr()) {
			logServ.errorlogexternal(dg.getDeviceId().getDeviceip(), auth.getRequestURL(), auth.getLogtext(), dg.getDeviceId(),dg.getUserloginID().getId(), auth.getLogtype(), auth.getLangcode(), auth.getLogException());

		}else if(auth.isWarning()) {
			logServ.warningexternal(dg.getDeviceId().getDeviceip(), auth.getRequestURL(), auth.getLogtext(), dg.getDeviceId(),dg.getUserloginID().getId(), auth.getLogtype(), auth.getLangcode(), auth.getLogException());

		}else if(auth.isFatal()) {
			logServ.fatalerrorexternal(dg.getDeviceId().getDeviceip(), auth.getRequestURL(), auth.getLogtext(), dg.getDeviceId(),dg.getUserloginID().getId(), auth.getLogtype(), auth.getLangcode(), auth.getLogException());

		}else if(auth.isOther()) {
			logServ.logotherexternal(dg.getDeviceId().getDeviceip(), auth.getRequestURL(), auth.getLogtext(), dg.getDeviceId(),dg.getUserloginID().getId(), auth.getLogtype(), auth.getLangcode(), auth.getLogException());

		}

return new ResponseEntity<authouput>(out, HttpStatus.OK);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		authouput out= new authouput();
		return new ResponseEntity<authouput>(out, HttpStatus.UNAUTHORIZED);
	}

}


}
