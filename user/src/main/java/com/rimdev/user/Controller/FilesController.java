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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Services.FileStorageService;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.ouputobject.UploadFileResponse;
import com.rimdev.user.ouputobject.filesearching;
import com.rimdev.user.ouputobject.parent_comp;

@Controller // This means that this class is a Controller
@RequestMapping(path="/file") // 
public class FilesController {
	
	
	
	

	    @Autowired
	    private FileStorageService fileStorageService;
	    
	    

	    
	
	
	  @RequestMapping(value = "/uploadFile/{langcode}", method = RequestMethod.POST)
    public ResponseEntity<FilesUpload> uploadsingleFile(@PathVariable("langcode") String langcode,@RequestParam("file") MultipartFile file,@RequestParam("pageid") int pageid,@RequestParam("parentid") int parentid,@RequestParam("componentid") int componentid) {
		 System.out.println(pageid+" "+parentid+" "+componentid);

		return new ResponseEntity<FilesUpload>(fileStorageService.storeFile(file,pageid,parentid,componentid,langcode), HttpStatus.OK);
		 
		
    }

    
    
    @RequestMapping(value = "/downloadFile/{langcode}/{fileid}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request,@PathVariable("langcode") String langcode,@PathVariable("fileid") int fileid) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileid,langcode);

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

    
    
    @PostMapping("/deleteFile/{langcode}")
    public ResponseEntity<FilesUpload> deleteFile(HttpServletRequest request,@RequestParam("fileid") int fileid,@PathVariable("langcode") String langcode) {
    	FilesUpload result = new FilesUpload();
    	
    	try {
			fileStorageService.deleteFile(fileid,langcode); 
		} catch (Exception e) {
			// TODO: handle exception
			
		}
    	
    	return new ResponseEntity<FilesUpload>(result, HttpStatus.OK);

    }
}
