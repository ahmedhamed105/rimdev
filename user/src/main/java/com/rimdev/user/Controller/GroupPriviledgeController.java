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

import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.GroupPriviledgeServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.GroupPriviledge;
import com.rimdev.user.entities.UserStatus;

@Controller // This means that this class is a Controller
@RequestMapping(path="/grouppriv") // 
public class GroupPriviledgeController {
	
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	GroupPriviledgeServ groupPriviledgeServ;
	
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<GroupPriviledge>> getAllUsers(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
	
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	  
		  return new ResponseEntity<List<GroupPriviledge>>(groupPriviledgeServ.getall(langcode), HttpStatus.OK);
	  }
	  

}