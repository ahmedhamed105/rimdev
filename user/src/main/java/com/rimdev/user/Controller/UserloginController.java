package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Services.UserLogServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.entities.UserLog;
import com.rimdev.user.entities.UserLogin;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Userlogin") // 
public class UserloginController {
	
	
	@Autowired
	UserLoginServ userLoginServ;

	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<UserLogin>> getAllUsers(){
		return new ResponseEntity<List<UserLogin>>(userLoginServ.getall(), HttpStatus.OK);
	  }

}
