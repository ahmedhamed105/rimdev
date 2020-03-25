package com.rimdev.user.Controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
import com.rimdev.user.Utils.AES;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.UserLogin;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Userlogin") // 
public class UserloginController {
	
	
	@Autowired
	UserLoginServ userLoginServ;

	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<UserLogin>> getAllUsers(@PathVariable("langcode") String langcode){
		return new ResponseEntity<List<UserLogin>>(userLoginServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/user/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<UserLogin>> getbyuser(@PathVariable("langcode") String langcode,@PathVariable("id") int userid){
		return new ResponseEntity<List<UserLogin>>(userLoginServ.getbyuser(userid, langcode), HttpStatus.OK);
	  }
	  
	  

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<UserLogin>> saveorupdate(@PathVariable("langcode") String langcode,@RequestBody UserLogin info) {
  // This returns a JSON or XML with the users

	
	System.out.println(info.getPasswordEncy());
	String key = info.getPasswordEncy().substring(info.getPasswordEncy().length() - 16);
	String pass = info.getPasswordEncy().substring(0,info.getPasswordEncy().length() - 16);
//	System.out.println(key);
	info.setLoginkey(key);
	info.setPasswordEncy(pass);
//	System.out.println(pass);
	
	//decrypt password
	
	String decryptedPassword =  new String(Base64.getDecoder().decode(pass));
     AES aesUtil = new AES(128, 1000);
    String password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], key, decryptedPassword.split("::")[2]);
    System.out.println(password);
		
	if(info.getId() !=null) {
		
	
		UserLogin found= userLoginServ.getbyid(info.getId(),langcode);
			
			if(found != null) {
              BeanUtils.copyProperties(info, found, ObjectUtils.getNullPropertyNames(info));
            
              if(!found.getUsername().equals(info.getUsername())) {
            	  userLoginServ.check_username(info.getUsername(),langcode);
              }

              userLoginServ.update(found,langcode);

			}
	}else {
	
		userLoginServ.check_username(info.getUsername(),langcode);
		userLoginServ.save(info,langcode);
	}
		
	
	
	
	
		return getbyuser(langcode,info.getUserID().getId());
}



}
