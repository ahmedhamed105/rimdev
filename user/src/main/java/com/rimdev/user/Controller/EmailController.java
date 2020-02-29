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

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Services.AreaServ;
import com.rimdev.user.Services.EmailServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
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
	  
	  
	  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Email>> getUsersbyuser(@PathVariable("id") int userid){ 
		  return new ResponseEntity<List<Email>>(emailServ.getbyuser(userid), HttpStatus.OK);
	  }
	  
	  


@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Email>> saveorupdate(@RequestBody Email emails) {
  // This returns a JSON or XML with the users
	
	
	
	if(emails.getId() !=null) {
		
	
			Email found= emailServ.getbyid(emails.getId());
			
			if(found != null) {
              BeanUtils.copyProperties(emails, found, ObjectUtils.getNullPropertyNames(emails));
            
              if(!found.getEmailuser().equals(emails.getEmailuser())) {
            	  emailServ.check_email(emails.getEmailuser());
              }
              
  			
              emailServ.update(found);

			}
	}else {
	
			 emailServ.check_email(emails.getEmailuser());
		     emailServ.save(emails);
	}
		
	
	
	
		return getUsersbyuser(emails.getUserID().getId());
}






@RequestMapping(value = "/delete", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Email>> delete(@RequestBody Email emails) {
  // This returns a JSON or XML with the users
	
	
	if(emails.getId() !=null  && emails.getUserID() != null && emails.getUserID().getId() != null) {
		
		
		Email found= emailServ.getbyid(emails.getId());	
		if(found != null) {
			
			emailServ.delete(emails);
		}
		
	

}else {
	
	throw new NoDataException("ID not Found or user id not found");

}
	
	
	return getUsersbyuser(emails.getUserID().getId());
}


}
