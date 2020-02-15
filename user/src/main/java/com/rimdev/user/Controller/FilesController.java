package com.rimdev.user.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rimdev.user.Services.FileStorageService;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.ouputobject.UploadFileResponse;
import com.rimdev.user.ouputobject.filesearching;

@Controller // This means that this class is a Controller
@RequestMapping(path="/file") // 
public class FilesController {
	
	
	
	

	    @Autowired
	    private FileStorageService fileStorageService;
	    
	    
	    public UploadFileResponse deleteFile(String fileName,int type,int userid) {
	    	 UploadFileResponse a =  fileStorageService.deleteFile(fileName,type,userid);
				return a;
	    }
	    
	    
	    public UploadFileResponse uploadFile(MultipartFile file,int type,int userid) {
			  UploadFileResponse a = fileStorageService.storeFile(file,type,userid);
				return a;
	    }
	    
	    
		
		  @RequestMapping(value = "/all/{Userid}/{Filetype}", method = RequestMethod.GET)
		  public  ResponseEntity<List<FilesUpload>> getAllfiles(@PathVariable("Userid") int Userid,@PathVariable("Filetype") int Filetype){
		
			  
			  return new ResponseEntity<List<FilesUpload>>(fileStorageService.getfile(Userid,Filetype), HttpStatus.OK);
		  }
		  
	
	
	  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<UploadFileResponse> uploadsingleFile(@RequestParam("file") MultipartFile file,@RequestParam("type") int type,@RequestParam("userid") int userid) {
		 System.out.println(type+" "+userid);
		  
		  UploadFileResponse a=uploadFile(file,type,userid);
		 
		 if (a.getError() > 0) {
		return new ResponseEntity<UploadFileResponse>(a, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<UploadFileResponse>(a, HttpStatus.OK);

    }

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("type") int type,@RequestParam("userid") int userid) {
       
    	List<UploadFileResponse>  a = Arrays.asList(files)
        .stream()
        .map(file -> uploadFile(file,type,userid))
        .collect(Collectors.toList());
    	
    	return new ResponseEntity<List<UploadFileResponse>>(a, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/downloadFile/{Userid}/{Filetype}/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request,@PathVariable("Userid") int Userid,@PathVariable("Filetype") int Filetype,@PathVariable("fileName") String fileName) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName,Filetype,Userid);

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

    
    
    @PostMapping("/deleteFile/{fileName:.+}")
    public ResponseEntity<UploadFileResponse> deleteFile(@PathVariable String fileName, HttpServletRequest request,@RequestParam("type") int type,@RequestParam("userid") int userid) {
      UploadFileResponse a=deleteFile(fileName,type,userid);
		 
		 if (a.getError() > 0) {
		return new ResponseEntity<UploadFileResponse>(a, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UploadFileResponse>(a, HttpStatus.OK);
   

 	

    }
}
