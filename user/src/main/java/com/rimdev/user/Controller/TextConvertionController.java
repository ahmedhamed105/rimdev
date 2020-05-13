package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.LanguageMapServ;
import com.rimdev.user.Services.TextConvertionServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.LanguageMap;
import com.rimdev.user.ouputobject.lang_object;
import com.rimdev.user.ouputobject.singleString;



@Controller // This means that this class is a Controller
@RequestMapping(path="/text") // 
public class TextConvertionController {
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	LanguageMapServ languageMapServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	
	  @RequestMapping(value = "/code/{langcode}/{txtcode}", method = RequestMethod.GET)
	  public @ResponseBody ResponseEntity<singleString>  convertbycode(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable("txtcode") String txtcode){ 

		  
	  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  paramter.add("txtcode");
	  values.add(txtcode);
	  DevicePage devpag= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  
		  //exception handled	
		  singleString a =new singleString();
		  a.setMessage(textConvertionServ.search(txtcode,langcode));
		  return new ResponseEntity<singleString>(a, HttpStatus.OK);
		
	  }

	  
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<lang_object>> getAllUsers(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){ 
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  
		  return new ResponseEntity<List<lang_object>>(textConvertionServ.getalllang(langcode), HttpStatus.OK);

	  
	  }
	  
	  
	  public  ResponseEntity<List<lang_object>> getAllUsers(String langcode){ 
		  return new ResponseEntity<List<lang_object>>(textConvertionServ.getalllang(langcode), HttpStatus.OK);  
	  }
	  
	  
	  @RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<lang_object>> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody lang_object input) {
	
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  // This returns a JSON or XML with the users
		  LanguageMap langmap;
		try {
			langmap= languageMapServ.getbycode(input.getLangaugecode(),langcode);
		//	 System.out.println("enter 2");

			if(langmap == null ) {
			//	System.out.println("enter 3");
				textConvertionServ.Save(input,langcode);
			
			}else {
		//		System.out.println("enter 4");
		//		System.out.println("ahmed "+input.getText2());

		    textConvertionServ.update(input,langmap,langcode);
				
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			
			textConvertionServ.Save(input,langcode);

		}

			return getAllUsers(langcode);
			
		
		  }
	  
	  
	  
	  @RequestMapping(value = "/delete/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<lang_object>> delete(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody lang_object input) {

		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  // This returns a JSON or XML with the users
		  LanguageMap langmap;
		try {
			langmap= languageMapServ.getbycode(input.getLangaugecode(),langcode);
		//	 System.out.println("enter 2");

			if(langmap == null ) {
			throw new NullPointerException("no data found");
			
			}else {
		//		System.out.println("enter 4");
		//		System.out.println("ahmed "+input.getText2());

		    textConvertionServ.delete(input,langmap,langcode);
				
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			
			
			throw new NullPointerException("no data found");
		}

			return getAllUsers(langcode);
			
		
		  }
	 
}
