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

import com.rimdev.user.Services.AdressServ;
import com.rimdev.user.Services.TelephonesServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Adress;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Address") // 
public class AddUserController {
	
	
	@Autowired
	UserServ userServ;
	
	@Autowired
	AdressServ adressServ;
	
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Adress>> getAllUsers(){
		return new ResponseEntity<List<Adress>>(adressServ.getall(), HttpStatus.OK);
	  }

	  


@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Adress>> saveorupdate(@RequestBody List<Adress> emails) {
  // This returns a JSON or XML with the users
	
	for (Adress input : emails) {
		
	
	
	if(input.getId() !=null) {
		
		try {
			Adress found= adressServ.getbyid(input.getId());
			if(found == null) {
				
				adressServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				  adressServ.update(found);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			adressServ.save(input);

		}

	}else {
		try {
			Adress found= adressServ.check_address(input.getAdname());
			if(found == null) {
				
				adressServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				  adressServ.update(found);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			adressServ.save(input);

		}



	}
		
	}
	
	
	return new ResponseEntity<List<Adress>>(adressServ.getall(), HttpStatus.OK);

}


}
