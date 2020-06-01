package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Services.ConfigurationServ;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.DevicePage;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Config") // 
public class ConfigurationController {
	
	
	@Autowired
	ConfigurationServ configurationServ;
	
	
	  @RequestMapping(value = "/getacctkey", method = RequestMethod.GET)
	  public  ResponseEntity<String> getAllUsers(HttpServletRequest request){

		  return new ResponseEntity<String>(configurationServ.getbykey("account_tokean").getConfigvalue(), HttpStatus.OK);
	  }
	  

}
