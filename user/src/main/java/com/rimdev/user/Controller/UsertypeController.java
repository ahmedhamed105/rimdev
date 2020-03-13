package com.rimdev.user.Controller;

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

import com.rimdev.user.Services.UserTypeServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.UserType;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Usertype") // 
public class UsertypeController {
	
	

	@Autowired
	UserTypeServ userTypeServ;

	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<UserType>> getAllUsers(@PathVariable("langcode") String langcode){
		return new ResponseEntity<List<UserType>>(userTypeServ.getall(langcode), HttpStatus.OK);
	  }

	  

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<UserType>> saveorupdate(@PathVariable("langcode") String langcode,@RequestBody List<UserType> UserTypes) {
  // This returns a JSON or XML with the users
	
	for (UserType input : UserTypes) {
		
	System.out.println(input.getUsertype());
	
	if(input.getId() != null) {
try {
	UserType found= userTypeServ.getbyid(input.getId());
	
	if(found == null) {
		
		userTypeServ.save(input);
		}else {
			  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

			userTypeServ.update(found);

		}

} catch (Exception e) {
	// TODO: handle exception
	
	userTypeServ.save(input);
}
	}else {
		try {
			UserType found= userTypeServ.check_type(input.getUsertype());
			if(found == null) {
				
			userTypeServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				userTypeServ.update(found);

			}
		} catch (Exception e) {
			// TODO: handle exception
			
			userTypeServ.save(input);

		}


	}
		
	}
	
	
	return new ResponseEntity<List<UserType>>(userTypeServ.getall(langcode), HttpStatus.OK);

}


}
