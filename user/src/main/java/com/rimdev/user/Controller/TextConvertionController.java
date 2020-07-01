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

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.LanguageMapServ;
import com.rimdev.user.Services.LogServ;
import com.rimdev.user.Services.TextConvertionServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.TextConvertion;
import com.rimdev.user.ouputobject.Lang_obj;
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
	  public  ResponseEntity<List<Lang_obj>> getAll(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){ 
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  
		  return new ResponseEntity<List<Lang_obj>>(textConvertionServ.getalllang(langcode), HttpStatus.OK);

	  
	  }
	  
	  @RequestMapping(value = "/save/{langcode}", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<List<Lang_obj>> save(HttpServletRequest request,
				@RequestHeader("Devicecode") String Devicecode, @RequestHeader("username") String username,
				@RequestHeader("usertokean") String usertokean, @RequestHeader("pageid") String pagenum,
				@PathVariable("langcode") String langcode, @RequestBody Lang_obj input) {
			// This returns a JSON or XML with the users

		  List<String> paramter =new ArrayList<String>();
		  List<String> values =new ArrayList<String>();
		  DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
			
				try {
					 TextConvertion txt = textConvertionServ.getbylangmaptxt(input.getTxtconv().getLanguagesID().getId(),input.getLangcode(), langcode);

					if (txt.getId() == null && input.getTxtconv().getLanguagesID().getId() != null) {
						// System.out.println("enter 3");
						textConvertionServ.Save(input, langcode);
			
					} else {
						throw new PopupException("error while insertion");
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new PopupException("error while insertion");
				}

				return getAll(request, Devicecode, username, usertokean, pagenum, langcode);


		}

	  @RequestMapping(value = "/update/{langcode}", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<List<Lang_obj>> update(HttpServletRequest request,
				@RequestHeader("Devicecode") String Devicecode, @RequestHeader("username") String username,
				@RequestHeader("usertokean") String usertokean, @RequestHeader("pageid") String pagenum,
				@PathVariable("langcode") String langcode, @RequestBody Lang_obj input) {
			// This returns a JSON or XML with the users

		  List<String> paramter =new ArrayList<String>();
		  List<String> values =new ArrayList<String>();
		  DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
			
				try {
					 TextConvertion txt = textConvertionServ.getbylangmaptxt(input.getTxtconv().getLanguagesID().getId(),input.getLangcode(), langcode);

					if (txt.getId() != null) {
						// System.out.println("enter 3");
						textConvertionServ.update(txt,input, langcode);
					} else {
						throw new PopupException("error while updating");
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new PopupException("error while updating");
				}

				return getAll(request, Devicecode, username, usertokean, pagenum, langcode);


		}
	  
	  
	 
}
