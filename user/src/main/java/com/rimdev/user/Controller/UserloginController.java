package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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
import com.rimdev.user.Services.GroupWebServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.UserLogin;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Userlogin") // 
public class UserloginController {
	
	
	@Autowired
	UserLoginServ userLoginServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	GroupWebServ groupWebServ;

	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<UserLogin>> getAllUsers(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
	
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	  
		  return new ResponseEntity<List<UserLogin>>(userLoginServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/user/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<UserLogin>> getbyuser( HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable("id") int userid){

		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  paramter.add("id");
	  values.add(String.valueOf(userid));
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	  
	  
		  return new ResponseEntity<List<UserLogin>>(userLoginServ.getbyuser(userid, langcode), HttpStatus.OK);
	  }
	  
	  
	  public  ResponseEntity<List<UserLogin>> getbyuser(String langcode, int userid){

		  return new ResponseEntity<List<UserLogin>>(userLoginServ.getbyuser(userid, langcode), HttpStatus.OK);
	  }
	  
	  

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<UserLogin>> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody UserLogin info) {
  // This returns a JSON or XML with the users

	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

//	System.out.println(info.getPasswordEncy());

	if(info.getId() !=null) {
		
	
		UserLogin found= userLoginServ.getbyid(info.getId(),langcode);
			
			if(found != null) {
              BeanUtils.copyProperties(info, found, ObjectUtils.getNullPropertyNames(info));
            
              if(!found.getUsername().equals(info.getUsername())) {
            	  userLoginServ.check_username(info.getUsername(),langcode);
              }

              userLoginServ.update(found,info,langcode);

			}
	}else {
	
		userLoginServ.check_username(info.getUsername(),langcode);
		userLoginServ.save(request,dg,info,langcode,Integer.parseInt(pagenum));
	}
		
	
	
	
	
		return getbyuser(langcode,info.getUserID().getId());
}



}
