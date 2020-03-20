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
import com.rimdev.user.Services.LanguagesServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Languages;


@Controller // This means that this class is a Controller
@RequestMapping(path="/langs") // 
public class LanguagesController {
	
	@Autowired
	LanguagesServ languagesServ;
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Languages>> getAllUsers(@PathVariable("langcode") String langcode){ 
		  return new ResponseEntity<List<Languages>>(languagesServ.getalllang(langcode), HttpStatus.OK);
	  }
	  
	  @RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<Languages>> saveorupdate(@PathVariable("langcode") String langcode,@RequestBody Languages input) {
	    // This returns a JSON or XML with the users
		  Languages user=null;
		try {
			 user= languagesServ.getlang(input.getId(),langcode);
		//	 System.out.println("enter 2");

			if(user == null ) {
			//	System.out.println("enter 3");
				user=languagesServ.Save(input,langcode);
			
			}else {
		//		System.out.println("enter 4");
		    BeanUtils.copyProperties(input, user, ObjectUtils.getNullPropertyNames(input));
		 //   System.out.println(user.getFirstName());
		    user=languagesServ.update(user,langcode);
				
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("enter 5");
			user= languagesServ.Save(input,langcode);

		}

			return getAllUsers(langcode);
			
		
		  }

}
