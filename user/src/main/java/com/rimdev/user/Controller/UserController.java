package com.rimdev.user.Controller;

import java.util.List;

import javax.validation.constraints.NotNull;

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

import com.rimdev.user.Services.FileStorageService;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.entities.User;
import com.rimdev.user.ouputobject.response_all;

@Controller // This means that this class is a Controller
@RequestMapping(path="/User") // 
public class UserController {
	

	@Autowired
	UserServ userServ;
	

	
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userServ.getall(), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	  public ResponseEntity<User> getuser(@PathVariable @NotNull int id){
		  User a = new User();
		  try {
			 a = userServ.getuser(id);
			 if(a == null) {
			  return new ResponseEntity<User>(a, HttpStatus.NOT_FOUND); 	 
			 }
			return new ResponseEntity<User>(a, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<User>(a, HttpStatus.NOT_FOUND);
		}
	  }
	  
	  

@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<User> saveorupdate(@RequestBody User input) {
  // This returns a JSON or XML with the users

	User user= new User();
	try {
		 user= userServ.getuser(input.getId());
		

		if(user == null ) {
			user=userServ.Save(input);
			  
		}else {

	    BeanUtils.copyProperties(input, user, ObjectUtils.getNullPropertyNames(input));
	    user=userServ.update(user);
			
		}


	} catch (Exception e) {
		// TODO: handle exception
	
		user=userServ.Save(input);

	}
	
	
	return new ResponseEntity<User>(user, HttpStatus.OK);

	
}



	  
	  


}
