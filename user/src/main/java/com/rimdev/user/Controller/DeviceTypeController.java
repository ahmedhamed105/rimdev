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

import com.rimdev.user.Services.DeviceOsServ;
import com.rimdev.user.Services.DeviceTypeServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.DeviceType;
import com.rimdev.user.entities.UserLogin;

@Controller // This means that this class is a Controller
@RequestMapping(path="/DeviceType") // 
public class DeviceTypeController {
	
	
	@Autowired
	DeviceTypeServ deviceTypeServ;
	
	@Autowired
	UserLoginServ userLoginServ;
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<DeviceType>> getAllUsers(@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@PathVariable("langcode") String langcode){
		  UserLogin a= userLoginServ.getbyusernametokean(username, usertokean, langcode);
		  
		  return new ResponseEntity<List<DeviceType>>(deviceTypeServ.getall(langcode), HttpStatus.OK);
	  }
	  

}
