package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.UserStatusServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.UserStatus;

@Controller // This means that this class is a Controller
@RequestMapping(path="/userstatus") // 
public class UserStatusController {
	
	
	
	@Autowired
	UserStatusServ userStatusServ;
	
	@Autowired
	DevicePageServ devicePageServ;

	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<UserStatus>> getAllUsers(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){
	
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);
  
		  return new ResponseEntity<List<UserStatus>>(userStatusServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  


}
