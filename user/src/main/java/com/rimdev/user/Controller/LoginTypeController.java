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
import com.rimdev.user.Services.LoginTypeServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.DeviceType;
import com.rimdev.user.entities.LoginType;
import com.rimdev.user.entities.UserLogin;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Logintype") // 
public class LoginTypeController {
	
	
	@Autowired
	LoginTypeServ loginTypeServ;
	
	@Autowired
	UserLoginServ userLoginServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<LoginType>> getAllUsers(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);
		  
		  return new ResponseEntity<List<LoginType>>(loginTypeServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/dataall/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<LoginType>> getAll(@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@PathVariable("langcode") String langcode){
		  UserLogin a= userLoginServ.getbyusernametokean(username, usertokean, langcode);
		  
		  return new ResponseEntity<List<LoginType>>(loginTypeServ.getall(langcode), HttpStatus.OK);
	  }
}