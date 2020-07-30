package com.rimdev.product.Controller;

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

import com.rimdev.product.Exceptions.PopupException;
import com.rimdev.product.Services.ExternalServ;
import com.rimdev.product.Services.SubCatalogServ;
import com.rimdev.product.Services.TextConvertionServ;
import com.rimdev.product.entities.SubCatalog;

@Controller // This means that this class is a Controller
@RequestMapping(path="/subcatagory") // 
public class subcatalogController {
	
	
	@Autowired
	ExternalServ externalServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	SubCatalogServ subCatalogServ;
	
	
	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<SubCatalog>> getAll(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode) {
	    // This returns a JSON or XML with the users
		try {
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type in processing", "", 35, 0);
		if(result) {
			
			return new ResponseEntity<List<SubCatalog>>(new ArrayList<SubCatalog>(), HttpStatus.OK);

		}else {
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"product listing in processing", "while auth and log in first", 35, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));
		}
	} catch (Exception e) {
		e.printStackTrace();

		throw new PopupException(textConvertionServ.search("no_data", langcode));
	}
	
	}

	
	@RequestMapping(value = "/getcat/{langcode}", method = RequestMethod.POST)
	  public  ResponseEntity<List<SubCatalog>> getCatg(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody String maincat) {
	    // This returns a JSON or XML with the users
		try {
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type in processing", "", 35, 0);
		if(result) {
	
			return new ResponseEntity<List<SubCatalog>>(subCatalogServ.getbymain(Integer.parseInt(maincat),langcode), HttpStatus.OK);

		}else {
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"product listing in processing", "while auth and log in first", 35, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));
		}
	} catch (Exception e) {
		e.printStackTrace();

		throw new PopupException(textConvertionServ.search("no_data", langcode));
	}
	
	}
}
