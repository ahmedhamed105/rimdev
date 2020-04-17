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
	GroupWebServ groupWebServ;
	
    @Autowired
    private FileStorageService fileStorageService;
    
    
    @Autowired
    PagesServ pagesServ;
	
    
	
	  @RequestMapping(value = "/page/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<parent_comp>> getUsersbyuser(HttpServletRequest request,@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable("id") int pageid){ 
		
		  
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pagenum, langcode);

		  List<String> paramter =new ArrayList<String>();
		  List<String> values =new ArrayList<String>();
		  paramter.add("langcode");
		  values.add(langcode);
		  paramter.add("id");
		  values.add(String.valueOf(pageid));
		  
		  groupWebServ.checkpriviledge(request, a,paramter,values);
		
		  return ResponseEntity.ok()
		  	      .cacheControl(CacheControl.maxAge(1, TimeUnit.SECONDS))
		  	      .body(parentComponentServ.getbypage(pageid,langcode,a));
	  }
	  
	  
	  
	  @RequestMapping(value = "/background/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<Resource> getbackground(HttpServletRequest request,@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable("id") int pageid){ 
		
		  
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pagenum, langcode);

		  List<String> paramter =new ArrayList<String>();
		  List<String> values =new ArrayList<String>();
		  paramter.add("langcode");
		  values.add(langcode);
		  paramter.add("id");
		  values.add(String.valueOf(pageid));
		  
		  groupWebServ.checkpriviledge(request, a,paramter,values);
		  int fileid= 1;
		 try {
			  Pages pa=pagesServ.getbyid(pageid);
			   fileid= pa.getFilesuploadID().getId();
			
		} catch (Exception e) {
			// TODO: handle exception
			fileid= 1;
		}
		
			  return fileStorageService.getresource(request,fileid,langcode);
		
		 
	  }
	  
	 
	  

}
