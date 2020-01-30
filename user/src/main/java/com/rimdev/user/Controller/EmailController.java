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

import com.rimdev.user.Services.AreaServ;
import com.rimdev.user.Services.EmailServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.Email;
import com.rimdev.user.ouputobject.response_all;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Email") // 
public class EmailController {
	
	@Autowired
	EmailServ emailServ;

	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Email>> getAllUsers(){
		return new ResponseEntity<List<Email>>(emailServ.getall(), HttpStatus.OK);
	  }
	  
	  


@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Email>> saveorupdate(@RequestBody List<Email> emails) {
  // This returns a JSON or XML with the users
	
	for (Email input : emails) {
		
	
	
	if(input.getId() !=null) {
		
		try {
			Email found= emailServ.getbyid(input.getId());
			if(found == null) {
				
				emailServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				emailServ.update(found);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			emailServ.save(input);

		}

	}else {
		try {
			Email found= emailServ.check_email(input.getEmailuser());
			if(found == null) {
				
				emailServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				emailServ.update(found);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			emailServ.save(input);

		}



	}
		
	}
	
	
	return new ResponseEntity<List<Email>>(emailServ.getall(), HttpStatus.OK);

}


}
