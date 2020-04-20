package com.rimdev.user.Controller;

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
import com.rimdev.user.Services.ParentMenuServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.menu_object;
import com.rimdev.user.ouputobject.menuparsub;

@Controller // This means that this class is a Controller
@RequestMapping(path="/menu") // 
public class MenuController {
	
	@Autowired
	ParentMenuServ parentMenuServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	UserLoginServ userLoginServ;
	
	
	
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<menu_object>> getUsersbyuser(HttpServletRequest request,@RequestHeader("pageid") String  pageid,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("devicetokean") String  devicetokean,@PathVariable("langcode") String langcode){ 
	
		  DevicePage devpage= devicePageServ.check_tokean_page(devicetokean, pageid, langcode);

		  UserLogin a= userLoginServ.getbyusernametokean(request,username, usertokean, langcode,devpage);

		  
		  return new ResponseEntity<List<menu_object>>(parentMenuServ.getallmenus(langcode,devpage), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/get/{langcode}/{type}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<menuparsub> getid(HttpServletRequest request,@RequestHeader("pageid") String  pageid,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("devicetokean") String  devicetokean,@PathVariable("langcode") String langcode,@PathVariable("type") String type,@PathVariable("id") int menuid){ 
		
		  DevicePage devpage= devicePageServ.check_tokean_page(devicetokean, pageid, langcode);

		  UserLogin a= userLoginServ.getbyusernametokean(request,username, usertokean, langcode,devpage);

		  
		  return new ResponseEntity<menuparsub>(parentMenuServ.getmenus(type,menuid,langcode), HttpStatus.OK);
	  }

}
