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
import com.rimdev.user.Services.PagesServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Pages;
@Controller // This means that this class is a Controller
@RequestMapping(path="/Page") // 
public class Pagescontroller {
	
	@Autowired
	PagesServ pagesServ;
	
	
	 @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Pages>> getAll(@PathVariable("langcode") String langcode){
		return new ResponseEntity<List<Pages>>(pagesServ.getall(langcode), HttpStatus.OK);
	  }
	 

	  

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Pages>> saveorupdate(@PathVariable("langcode") String langcode,@RequestBody Pages teles) {
  // This returns a JSON or XML with the users

	System.out.println("hamed");
	if(teles.getId() !=null) {
				
		Pages found= pagesServ.getbyid(teles.getId(),langcode);
			
			if(found != null) {
              BeanUtils.copyProperties(teles, found, ObjectUtils.getNullPropertyNames(teles));
              if(!found.getPagename().equals(teles.getPagename())) {
            	  pagesServ.check_page(teles.getPagename(),langcode);
              }
  		       pagesServ.update(found,langcode);      
			}
	}else {
		
			 pagesServ.check_page(teles.getPagename(),langcode);
		      pagesServ.save(teles,langcode);

	}
		
	
	  return getAll(langcode);

}






@RequestMapping(value = "/delete/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Pages>> delete(@PathVariable("langcode") String langcode,@RequestBody Pages teles) {
  // This returns a JSON or XML with the users
	
	
	if(teles.getId() !=null  && teles.getPagename() != null) {
		
		
			Pages found= pagesServ.getbyid(teles.getId(),langcode);	
			if(found != null) {
				
				pagesServ.delete(teles,langcode);
			}
			
		

	}else {
		
		throw new NoDataException("ID not Found or user id not found");

	}
		
	  return getAll(langcode);

}



}