package com.rimdev.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.ouputobject.Loginobject;

@Controller // This means that this class is a Controller
@RequestMapping(path="/login") // 
public class loginController {
	
	
	
	@Autowired
	UserLoginServ userLoginServ;
	
	

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Loginobject> saveorupdate(@PathVariable("langcode") String langcode,@RequestBody Loginobject info) {
	System.out.println(info.getUsername());
	System.out.println(info.getPassword());
	
	Loginobject out = userLoginServ.login(info, langcode);

	return  new ResponseEntity<Loginobject>(out, HttpStatus.OK);

}


@RequestMapping(value = "/check/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Loginobject> check_tokean(@PathVariable("langcode") String langcode,@RequestBody Loginobject info) {
	System.out.println(info.getTokean());
	System.out.println(info.getUsername());
	
	Loginobject out = userLoginServ.loginpage(info, langcode);

	return  new ResponseEntity<Loginobject>(out, HttpStatus.OK);

}

}
