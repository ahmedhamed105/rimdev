package com.rimdev.rimuser.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rimdev.rimuser.Services.UserLoginServ;
import com.rimdev.rimuser.entities.UserLogin;
import com.rimdev.rimuser.outputobject.Deviceob;


@Controller // This means that this class is a Controller
@RequestMapping(path="/userlogin") // 
public class UserController {
	
	@Autowired
	UserLoginServ userLoginServ;
	


@RequestMapping(value = "/getuserlogin/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<UserLogin> getuserlogin(HttpServletRequest req,@PathVariable("langcode") String langcode,@RequestBody Deviceob login) {
	
	UserLogin user;
	
	try {
		
		
		
		if(login.getUserid().getUsername() != null && login.getUserid().getUsertokean() !=null) {
			
			 user= userLoginServ.getbyusernametokean(login.getUserid().getUsername(),login.getUserid().getUsertokean(), langcode);

		}else if(login.getUserid().getUsername() != null && login.getUserid().getUsertokean() ==null) {
			
			 user= userLoginServ.getbyusername(login.getUserid().getUsername(), langcode);
		}else {
			
			 user= userLoginServ.getbyid(1, langcode);
		}
		
		
		
	     return new ResponseEntity<UserLogin>(user, HttpStatus.OK);

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		user= userLoginServ.getbyid(1, langcode);
		
		 return new ResponseEntity<UserLogin>(user, HttpStatus.OK);
	}	

}





@RequestMapping(value = "/getuserloginbyuserid/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<UserLogin>> getuserloginbyuserid(HttpServletRequest req,@PathVariable("langcode") String langcode,@RequestBody Deviceob login) {
	try {
		List<UserLogin> user= userLoginServ.getbyuser(login.getUserid().getUserID().getUseridnumber(), langcode);
		
	     return new ResponseEntity<List<UserLogin>>(user, HttpStatus.OK);

	} catch (Exception e) {
		// TODO: handle exception
		throw e;
	}	

}




}
