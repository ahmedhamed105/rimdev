package com.rimdev.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rimdev.user.Services.FileStorageService;
import com.rimdev.user.entities.User;
import com.rimdev.user.ouputobject.UploadFileResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller // This means that this class is a Controller
@RequestMapping(path="/file") // 
public class FilesController {
	
	

	    @Autowired
	    private FileStorageService fileStorageService;
	    
	    
	    public UploadFileResponse uploadFile(MultipartFile file) {
			  UploadFileResponse a=null;
		        String fileName = fileStorageService.storeFile(file);
		        

		        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("file/downloadFile/")
		                .path(fileName)
		                .toUriString();
		  
		    	    a= new UploadFileResponse(fileName, fileDownloadUri,
			                file.getContentType(), file.getSize());
			
				return a;


	    }
	
	
	  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<UploadFileResponse> uploadsingleFile(@RequestParam("file") MultipartFile file) {
		 UploadFileResponse a=uploadFile(file);
		return new ResponseEntity<UploadFileResponse>(a, HttpStatus.OK);

    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
       
    	List<UploadFileResponse>  a = Arrays.asList(files)
        .stream()
        .map(file -> uploadFile(file))
        .collect(Collectors.toList());
    	
    	return new ResponseEntity<List<UploadFileResponse>>(a, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
          System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
