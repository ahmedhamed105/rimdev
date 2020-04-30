package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Exception.BlockedException;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.DeviceServ;
import com.rimdev.user.Services.GroupPagesServ;
import com.rimdev.user.Services.GroupWebServ;
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
	
	@Autowired
	GroupWebServ groupWebServ;
	
	@Autowired
	GroupPagesServ GroupPagesServ;
	
	

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Loginobject> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Loginobject info) {

	
	// System.out.println("begin 0");

	  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	  

	//  System.out.println(request.getRequestURI());
	  Loginobject out= userLoginServ.login(request,info, langcode,a);


	
    return ResponseEntity.ok()
  	      .cacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS))
  	      .body(out);
	
	//return  new ResponseEntity<Loginobject>(out, HttpStatus.OK);

}

}
