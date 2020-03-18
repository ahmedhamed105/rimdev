package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Services.DeviceOsServ;
import com.rimdev.user.Services.DeviceServ;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;

@Controller // This means that this class is a Controller
@RequestMapping(path="/DeviceOS") // 
public class DeviceOsController {
	
	
	
	@Autowired
	DeviceOsServ deviceOsServ;
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<DeviceOs>> getAllUsers(@PathVariable("langcode") String langcode){
		return new ResponseEntity<List<DeviceOs>>(deviceOsServ.getall(langcode), HttpStatus.OK);
	  }
	  

}
