package com.rimdev.user.Services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import com.rimdev.user.Config.FileStorageProperties;
import com.rimdev.user.Excep.FileStorageException;
import com.rimdev.user.Excep.MyFileNotFoundException;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.ouputobject.UploadFileResponse;

import org.springframework.util.StringUtils;

@Service
public class FileStorageService {
	 private  Path fileStorageLocation;

	    @Autowired
	    public FileStorageService(FileStorageProperties fileStorageProperties) {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }

	    public UploadFileResponse storeFile(MultipartFile file,int type,int userid) {
	    	
	    	 Path targetLocation1 = this.fileStorageLocation.resolve(userid+"\\"+type+"\\");
	    	
	    	System.out.println(String.valueOf(targetLocation1.toAbsolutePath()));
	    	
	    	File directory = targetLocation1.toFile();
	    	
	    	System.out.println(directory.getAbsolutePath());
	    	
	    	try {
	    		
	    		 if(!directory.exists()){
		    		 
		    		 boolean bool = directory.mkdirs();
		    	      if(bool){
		    	   //      System.out.println("Directory created successfully");
		    	      }else{
		    	          UploadFileResponse b= new UploadFileResponse("", "","", 0,3,"Directory Not created");
				            return b;
			        		    	     
		    	      }

		    	             
		    	           
		    	}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}


	    	
	    	 String [] extension= file.getOriginalFilename().split("\\.");
	    	  
	    	  String filname=Generate.token(30)+"."+extension[extension.length-1];
	    
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(filname);

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
		            UploadFileResponse b= new UploadFileResponse("", "","", 0,2,"Sorry! Filename contains invalid path sequence");
		            return b;
	             //   throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }
	   
	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = targetLocation1.resolve(fileName);

	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		       
	            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("file/downloadFile/")
		                .path(fileName)
		                .toUriString();
		  

	            UploadFileResponse a= new UploadFileResponse(fileName, fileDownloadUri,
		                file.getContentType(), file.getSize(),0,"Sucess");
	            
	            
	            return a;
	        } catch (Exception ex) {    
	         //   throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	            UploadFileResponse b= new UploadFileResponse("", "","", 0,1,"Could not store file");
	            return b;
	        }
	    }

	    public Resource loadFileAsResource(String fileName, int type,int userid) {
	    	System.out.println("load");
	    	
	        try {
		     Path targetLocation1 = this.fileStorageLocation.resolve(userid+"\\"+type+"\\");

	            Path filePath = targetLocation1.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	            
	                throw new MyFileNotFoundException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	        	ex.printStackTrace();
	            throw new MyFileNotFoundException("File not found " + fileName, ex);
	        }
	    }
}
