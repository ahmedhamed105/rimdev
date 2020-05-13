package com.rimdev.user.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.FileStorageService;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.FilesUpload;

@Controller // This means that this class is a Controller
@RequestMapping(path="/file") // 
public class FilesController {
	
	
	
	

	    @Autowired
	    private FileStorageService fileStorageService;
	    
		@Autowired
		DevicePageServ devicePageServ;
	    

	    
	
	
	  @RequestMapping(value = "/uploadFile/{langcode}", method = RequestMethod.POST)
    public ResponseEntity<FilesUpload> uploadsingleFile(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestParam("file") MultipartFile file,@RequestParam("pageid") int pageid,@RequestParam("parentid") int parentid,@RequestParam("componentid") int componentid) {
		 System.out.println(pageid+" "+parentid+" "+componentid);
		 
	  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		return new ResponseEntity<FilesUpload>(fileStorageService.storeFile(file,pageid,parentid,componentid,langcode,a), HttpStatus.OK);
		 
		
    }

    
    
    @RequestMapping(value = "/downloadFile/{langcode}/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable("id") int fileid) {
    
	  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  paramter.add("id");
	  values.add(String.valueOf(fileid));
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
    	// Load file as Resource
        return fileStorageService.getresource(request,fileid,langcode);

    }

    
    
    @PostMapping("/deleteFile/{langcode}")
    public ResponseEntity<FilesUpload> deleteFile(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestParam("fileid") int fileid) {
	
    	List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
    	
    	FilesUpload result = new FilesUpload();
    	
    	try {
			fileStorageService.deleteFile(fileid,langcode); 
		} catch (Exception e) {
			// TODO: handle exception
			
		}
    	
    	return new ResponseEntity<FilesUpload>(result, HttpStatus.OK);

    }
}
