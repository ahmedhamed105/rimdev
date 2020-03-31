package com.rimdev.user.Controller;

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

import com.rimdev.user.Services.DevicePageServ;
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
	
	

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Loginobject> saveorupdate(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Loginobject info) {

	  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	
	
	Loginobject out = userLoginServ.login(info, langcode);

	return  new ResponseEntity<Loginobject>(out, HttpStatus.OK);

}


@RequestMapping(value = "/check/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Loginobject> check_tokean(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Loginobject info) {
	
	  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	
	Loginobject out = userLoginServ.loginpage(info, langcode);

	return  new ResponseEntity<Loginobject>(out, HttpStatus.OK);

}

}
