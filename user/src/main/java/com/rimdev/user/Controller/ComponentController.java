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
import com.rimdev.user.Services.ParentComponentServ;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.Email;
import com.rimdev.user.ouputobject.Component_object;
import com.rimdev.user.ouputobject.parent_comp;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Component") // 
public class ComponentController {
	
	@Autowired
	ParentComponentServ parentComponentServ;
	
	
	  @RequestMapping(value = "/page/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<parent_comp>> getUsersbyuser(@PathVariable("langcode") String langcode,@PathVariable("id") int pageid){ 
		  return new ResponseEntity<List<parent_comp>>(parentComponentServ.getbypage(pageid,langcode), HttpStatus.OK);
	  }
	  

}
