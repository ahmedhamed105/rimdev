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

import com.rimdev.user.Services.TelephonesServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
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
	  
	  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Telephones>> getUsersbyuserd(@PathVariable("id") int userid){ 
	
		  try {
			  return new ResponseEntity<List<Telephones>>(telephonesServ.getbyuser(userid), HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			
			  return new ResponseEntity<List<Telephones>>(telephonesServ.getbyuser(userid), HttpStatus.BAD_REQUEST);

		}
	  }
	  
	  
	  public  List<Telephones> getUsersbyuser(int userid){ 
		  return telephonesServ.getbyuser(userid);
	  }
	  
	  
	  

@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Telephones>> saveorupdate(@RequestBody Telephones teles) {
  // This returns a JSON or XML with the users
	

	
	if(teles.getId() !=null) {
		
		try {
			Telephones found= telephonesServ.getbyid(teles.getId());
			if(found != null) {
              BeanUtils.copyProperties(teles, found, ObjectUtils.getNullPropertyNames(teles));
              Telephones as= telephonesServ.check_tele(teles.getPhoneNo());
  			if(as == null) {	

  				telephonesServ.update(found);


  			}else {
  				
  			  return new ResponseEntity<List<Telephones>>(getUsersbyuser(teles.getUserID().getId()), HttpStatus.CONFLICT);

  			}
              
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			  return new ResponseEntity<List<Telephones>>(getUsersbyuser(teles.getUserID().getId()), HttpStatus.BAD_REQUEST);

		}

	}else {
		try {
			Telephones found= telephonesServ.check_tele(teles.getPhoneNo());
			if(found == null) {
				

				telephonesServ.save(teles);

			}else {
				
			return new ResponseEntity<List<Telephones>>(getUsersbyuser(teles.getUserID().getId()), HttpStatus.CONFLICT);

			}
			
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
			
			  return new ResponseEntity<List<Telephones>>(getUsersbyuser(teles.getUserID().getId()), HttpStatus.BAD_REQUEST);

		}



	}
		
	
	  return new ResponseEntity<List<Telephones>>(getUsersbyuser(teles.getUserID().getId()), HttpStatus.OK);

}






@RequestMapping(value = "/delete", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Telephones>> delete(@RequestBody Telephones teles) {
  // This returns a JSON or XML with the users
	
	
	if(teles.getId() !=null) {
		
		try {
			Telephones found= telephonesServ.getbyid(teles.getId());	
			if(found != null) {
				
				telephonesServ.delete(teles);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			  return new ResponseEntity<List<Telephones>>(getUsersbyuser(teles.getUserID().getId()), HttpStatus.BAD_REQUEST);


		}

	}
		
	  return new ResponseEntity<List<Telephones>>(getUsersbyuser(teles.getUserID().getId()), HttpStatus.OK);

}



}
