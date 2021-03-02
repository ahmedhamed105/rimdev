package com.rimdev.gateway.Controller;

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

import com.rimdev.gateway.Services.DeviceExternalServ;
import com.rimdev.gateway.Services.PageExternalServ;
import com.rimdev.gateway.Services.UserExternalServ;
import com.rimdev.gateway.entities.Device;
import com.rimdev.gateway.entities.Pages;
import com.rimdev.gateway.entities.UserLogin;

@Controller // This means that this class is a Controller
@RequestMapping(path="/init") // 
public class GatewayController {
	@Autowired
	DeviceExternalServ deviceExternalServ;
	
	@Autowired
	PageExternalServ pageExternalServ;
	
	@Autowired
	UserExternalServ userExternalServ;
	

@RequestMapping(value = "/page/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<UserLogin> initpage(HttpServletRequest request,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@PathVariable("langcode") String langcode,@RequestBody Device input) 
		 {
	
		try {
			
			if(input == null) {
				 return new ResponseEntity<UserLogin>(new UserLogin(), HttpStatus.BAD_REQUEST);
				
			}
			
			input.setDeviceip(request.getRemoteAddr());
			
			//search device
		//	Device outdevice= deviceExternalServ.searchdevice(input, langcode);
			
			//save device 
			// Device outdevice= deviceExternalServ.saveorupdatedevice(input, langcode);
			 
			 System.out.println(input.toString());
			 
			 //get page
			// Pages page = pageExternalServ.getpagebyid(input.getPage(), langcode);
			 
			 //if not have user use public 
			// UserLogin user = userExternalServ.getuserloginbyusername(username, usertokean, langcode);
			 
			 //checkpriviledge for page 
			 
			 
			 //save devicepage
			 
			 UserLogin user=null; 
				
		     return new ResponseEntity<UserLogin>(user, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	    
	
}

}
