package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Repo.ComponentRepo;
import com.rimdev.user.Services.ComponentServ;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.Email;
import com.rimdev.user.ouputobject.Component_object;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Component") // 
public class ComponentController {
	
	@Autowired
	ComponentServ componentServ;
	
	
	  @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Component_object>> getUsersbyuser(@PathVariable("id") int pageid){ 
		  return new ResponseEntity<List<Component_object>>(componentServ.getbypage(pageid), HttpStatus.OK);
	  }

}
