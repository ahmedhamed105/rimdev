package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Repo.ComponentRepo;
import com.rimdev.user.Services.ComponentServ;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.FileStorageService;
import com.rimdev.user.Services.GroupWebServ;
import com.rimdev.user.Services.PagesServ;
import com.rimdev.user.Services.ParentComponentServ;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.ouputobject.Component_object;
import com.rimdev.user.ouputobject.parent_comp;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Component") // 
public class ComponentController {
	
	@Autowired
	ParentComponentServ parentComponentServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	

	
    @Autowired
    private FileStorageService fileStorageService;
    
    
    @Autowired
    PagesServ pagesServ;
	
    
	
	  @RequestMapping(value = "/page/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<parent_comp>> getcompbypage(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){ 
		
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  return ResponseEntity.ok()
		  	      .cacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS))
		  	      .body(parentComponentServ.getbypage(Integer.parseInt(pagenum),langcode,a));
	  }
	  
	  
	  
	  @RequestMapping(value = "/background/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<Resource> getbackground(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){ 
		
		  
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  int fileid= 1;
		 try {
			  Pages pa=pagesServ.getbyid(Integer.parseInt(pagenum));
			   fileid= pa.getFilesuploadID().getId();
			
		} catch (Exception e) {
			// TODO: handle exception
			fileid= 1;
		}
		
			  return fileStorageService.getresource(request,fileid,langcode,true);
		
		 
	  }
	  
	  
	  @RequestMapping(value = "/image/{langcode}/{imageid}", method = RequestMethod.GET)
	  public  ResponseEntity<Resource> getimage(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable("imageid") int imageid){ 
		
		  
	  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  paramter.add("imageid");
	  values.add(String.valueOf(imageid));
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		
		
			  return fileStorageService.getresource(request,imageid,langcode,false);
		
		 
	  }
	  
	  

}
