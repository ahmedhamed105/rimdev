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
import com.rimdev.user.Services.TelephonesServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Telephones;


@Controller // This means that this class is a Controller
@RequestMapping(path="/Tele") // 
public class UserTeleController {
	
	@Autowired
	UserServ userServ;
	
	@Autowired
	TelephonesServ telephonesServ;
	
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Telephones>> getAllUsers(){
		  //exception handled
		return new ResponseEntity<List<Telephones>>(telephonesServ.getall(), HttpStatus.OK);
	  }
	  

	  
	  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Telephones>> getUsersbyuser(@PathVariable("id") int userid){ 
		  //exception handled
	    return new ResponseEntity<List<Telephones>>(telephonesServ.getbyuser(userid), HttpStatus.OK);

		
	  }
	  
	  

	  

@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Telephones>> saveorupdate(@RequestBody Telephones teles) {
  // This returns a JSON or XML with the users

	
	if(teles.getId() !=null) {
				
			Telephones found= telephonesServ.getbyid(teles.getId());
			
			if(found != null) {
              BeanUtils.copyProperties(teles, found, ObjectUtils.getNullPropertyNames(teles));
               telephonesServ.check_tele(teles.getPhoneNo());
  		       telephonesServ.update(found);      
			}
	}else {
		
			 telephonesServ.check_tele(teles.getPhoneNo());
		      telephonesServ.save(teles);

	}
		
	
	  return getUsersbyuser(teles.getUserID().getId());

}






@RequestMapping(value = "/delete", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Telephones>> delete(@RequestBody Telephones teles) {
  // This returns a JSON or XML with the users
	
	
	if(teles.getId() !=null  && teles.getUserID() != null && teles.getUserID().getId() != null) {
		
		
			Telephones found= telephonesServ.getbyid(teles.getId());	
			if(found != null) {
				
				telephonesServ.delete(teles);
			}
			
		

	}else {
		
		throw new NoDataException("ID not Found or user id not found");

	}
		
	  return getUsersbyuser(teles.getUserID().getId());

}



}
