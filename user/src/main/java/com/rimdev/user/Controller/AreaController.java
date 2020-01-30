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
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.Email;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Area") // 
public class AreaController {
	
	@Autowired
	AreaServ areaServ;

	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Area>> getAllUsers(){
		return new ResponseEntity<List<Area>>(areaServ.getall(), HttpStatus.OK);
	  }
	  
	  

@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Area>> saveorupdate(@RequestBody List<Area> Areas) {
  // This returns a JSON or XML with the users
	
	for (Area input : Areas) {
		
	
	
	if(input.getId()==null) {
		
		try {
			
			Area found= areaServ.getbyid(input.getId());
			
			if(found == null) {
				areaServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				areaServ.update(found);
				
			}
				
			} catch (Exception e) {
				// TODO: handle exception
				areaServ.save(input);
			}
			

	}else {
		try {
			
		Area found= areaServ.check_area(input.getAreaname());
		
		if(found == null) {
			areaServ.save(input);
		}else {
			  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

			areaServ.update(found);
			
		}
			
		} catch (Exception e) {
			// TODO: handle exception
			areaServ.save(input);
		}
		

	}
		
	}
	
	
	return new ResponseEntity<List<Area>>(areaServ.getall(), HttpStatus.OK);

}


}
