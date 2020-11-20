package com.rimdev.rimlang.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.rimlang.Services.TextConvertionServ;
import com.rimdev.rimlang.outputobject.langob;
@Controller // This means that this class is a Controller
@RequestMapping(path="/Lang") // 
public class LangController {
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	

@RequestMapping(value = "/translate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<langob> gettxt(HttpServletRequest req, @RequestBody langob txt,@PathVariable("langcode") String langcode) {

	try {
		langob out =new langob();
		out=txt;
		String txttr=textConvertionServ.search(txt.getLangtittle(), txt.getLangcode());
		out.setLangtittle(txttr);

return new ResponseEntity<langob>(out, HttpStatus.OK);
	} catch (Exception e) {
		throw e;
	}

}




}
