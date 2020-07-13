package com.rimdev.user.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Services.FileStorageService;

@Controller // This means that this class is a Controller
@RequestMapping(path="/public") // 
public class publicController {
	
    @Autowired
    private FileStorageService fileStorageService;
	
	  @RequestMapping(value = "/image/{langcode}/{fileid}", method = RequestMethod.GET)
	  public  ResponseEntity<Resource> getbackground(HttpServletRequest request,@PathVariable("langcode") String langcode,@PathVariable("fileid") int fileid){ 
	
		
			  return fileStorageService.getresource(request,fileid,langcode,true);
		
		 
	  }

}
