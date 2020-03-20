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

import com.rimdev.user.Services.TextConvertionServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Languages;
import com.rimdev.user.ouputobject.lang_object;
import com.rimdev.user.ouputobject.singleString;



@Controller // This means that this class is a Controller
@RequestMapping(path="/text") // 
public class TextConvertionController {
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	  @RequestMapping(value = "/code/{langcode}/{txtcode}", method = RequestMethod.GET)
	  public @ResponseBody ResponseEntity<singleString>  convertbycode(@PathVariable("langcode") String langcode,@PathVariable("txtcode") String txtcode){ 
		  //exception handled	
		  singleString a =new singleString();
		  a.setMessage(textConvertionServ.search(txtcode,langcode));
		  return new ResponseEntity<singleString>(a, HttpStatus.OK);
		
	  }

	  
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<lang_object>> getAllUsers(@PathVariable("langcode") String langcode){ 
		  return new ResponseEntity<List<lang_object>>(textConvertionServ.getalllang(langcode), HttpStatus.OK);

	  
	  }
	  
	 
}
