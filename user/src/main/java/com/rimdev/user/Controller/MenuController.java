package com.rimdev.user.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.rimdev.user.Services.ParentMenuServ;
import com.rimdev.user.ouputobject.menu_object;
import com.rimdev.user.ouputobject.menuparsub;

@Controller // This means that this class is a Controller
@RequestMapping(path="/menu") // 
public class MenuController {
	
	@Autowired
	ParentMenuServ parentMenuServ;
	
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<menu_object>> getUsersbyuser(@PathVariable("langcode") String langcode){ 
		  return new ResponseEntity<List<menu_object>>(parentMenuServ.getallmenus(langcode), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/get/{langcode}/{type}/{menuid}", method = RequestMethod.GET)
	  public  ResponseEntity<menuparsub> getid(@PathVariable("langcode") String langcode,@PathVariable("type") String type,@PathVariable("menuid") int menuid){ 
		  return new ResponseEntity<menuparsub>(parentMenuServ.getmenus(type,menuid,langcode), HttpStatus.OK);
	  }

}
