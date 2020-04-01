package com.rimdev.user.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Exception.redirectlogin;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.DeviceServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.ouputobject.Loginobject;

@Controller // This means that this class is a Controller
@RequestMapping(path="/login") // 
public class loginController {
	
	
	
	@Autowired
	UserLoginServ userLoginServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	
	@Autowired
	DeviceServ deviceServ;
	
	

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Loginobject> saveorupdate(HttpServletRequest request,@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Loginobject info) {

	  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);
	  
	  if(a.getDeviceId().getLoginFail() >= 3 ) {
			deviceServ.blockdevice(a.getDeviceId());
			
			throw new redirectlogin("blocked");
	  }

	  System.out.println(request.getRequestURI());
	  Loginobject out;
	try {
		 out = userLoginServ.login(info, langcode);

	} catch (Exception e) {
		// TODO: handle exception
		deviceServ.addfailedlogin(a.getDeviceId());
		throw e;
	}

	deviceServ.sucesslogin(a.getDeviceId());
	return  new ResponseEntity<Loginobject>(out, HttpStatus.OK);

}


@RequestMapping(value = "/check/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Loginobject> check_tokean(HttpServletRequest request,@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Loginobject info) {
	
	//  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	System.out.println(request.getRequestURI()); 
	Loginobject out = userLoginServ.loginpage(info, langcode);

	return  new ResponseEntity<Loginobject>(out, HttpStatus.OK);

}

}
