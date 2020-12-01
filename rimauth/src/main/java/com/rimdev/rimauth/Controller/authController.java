package com.rimdev.rimauth.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.rimauth.Services.DevicePageServ;
import com.rimdev.rimauth.Services.LogServ;
import com.rimdev.rimauth.outputobject.authouput;
import com.rimdev.rimauth.outputobject.authreqob;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Auth") // 
public class authController {
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	LogServ logServ;
	
	
	

@RequestMapping(value = "/page/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<authouput> gettokenbydevice(HttpServletRequest req,@RequestBody authreqob auth) {
	return null;


	

}




}
