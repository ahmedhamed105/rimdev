package com.rimdev.language.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.language.Entities.TextConvertion;
import com.rimdev.language.Object.inputsearch;
import com.rimdev.language.Services.TextConvertionServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/lang") // 
public class TextConvertionController {
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	  @RequestMapping(value = "/code", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<TextConvertion>  convertbycode(@RequestBody inputsearch search){ 
		  //exception handled	
		  return new ResponseEntity<TextConvertion>(textConvertionServ.search(search), HttpStatus.OK);
		
	  }


}
