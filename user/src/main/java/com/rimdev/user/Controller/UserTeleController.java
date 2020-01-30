package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Services.TelephonesServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.User;


@Controller // This means that this class is a Controller
@RequestMapping(path="/Tele") // 
public class UserTeleController {
	
	@Autowired
	UserServ userServ;
	
	@Autowired
	TelephonesServ telephonesServ;
	
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Telephones>> getAllUsers(){
		return new ResponseEntity<List<Telephones>>(telephonesServ.getall(), HttpStatus.OK);
	  }
	  
	  

@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Telephones>> saveorupdate(@RequestBody List<Telephones> telephones) {
  // This returns a JSON or XML with the users
	
	for (Telephones input : telephones) {
		
	
	
	if(input.getId()!=null) {
		try {
			Telephones found= telephonesServ.getbyid(input.getId());
			if(found == null) {
				
				telephonesServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				telephonesServ.update(found);

			}
		} catch (Exception e) {
			// TODO: handle exception
			
			telephonesServ.save(input);

		}

	}else {
		try {
			Telephones found= telephonesServ.check_tele(input.getPhoneNo());
			if(found == null) {
				
				telephonesServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				telephonesServ.update(found);

			}
		} catch (Exception e) {
			// TODO: handle exception
			
			telephonesServ.save(input);

		}


	}
		
	}
	
	
	return new ResponseEntity<List<Telephones>>(telephonesServ.getall(), HttpStatus.OK);

}



}
