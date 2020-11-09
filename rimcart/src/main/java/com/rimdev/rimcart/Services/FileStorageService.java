package com.rimdev.rimcart.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rimdev.rimcart.Config.FileStorageProperties;
import com.rimdev.rimcart.Exception.PopupException;
import com.rimdev.rimcart.Repo.FileStatusRepo;
import com.rimdev.rimcart.Repo.FilesUploadRepo;
import com.rimdev.rimcart.Repo.UserFileRepo;
import com.rimdev.rimcart.Repo.UserRepo;
import com.rimdev.rimcart.Utils.Generate;
import com.rimdev.rimcart.entities.DevicePage;
import com.rimdev.rimcart.entities.FileStatus;
import com.rimdev.rimcart.entities.FileType;
import com.rimdev.rimcart.entities.FilesUpload;
import com.rimdev.rimcart.entities.UserFile;

@Service
public class FileStorageService {
	
	@Autowired
	UserFileRepo userFileRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	FileStatusRepo fileStatusRepo;
	
	
	@Autowired
	FilesUploadRepo filesUploadRepo;
	
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	FileTypeServ fileTypeServ;
	
	
	 private  Path fileStorageLocation;
	 
	 int scomponentid;
	 int spageid;
	 int sparentid;
	 String sfilename;
	 BigDecimal comtime;
	 
	 
	
		
		

	    @Autowired
	    public FileStorageService(FileStorageProperties fileStorageProperties) {
	        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	                .toAbsolutePath().normalize();

	        try {
	            Files.createDirectories(this.fileStorageLocation);
	        } catch (Exception ex) {
	            throw new PopupException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	    }
	    
	    Path create_maindirectory() {
	    	
	    	 Path targetLocation1 = this.fileStorageLocation.resolve(spageid+"\\"+sparentid+"\\"+scomponentid+"\\");

	  	   // 	System.out.println(String.valueOf(targetLocation1.toAbsolutePath()));
	  	    	
	  	    	File directory = targetLocation1.toFile();
	  	    	
	  	    //	System.out.println(directory.getAbsolutePath());
	  	    	
	  	    	try {
	  	    		
	  	    		 if(!directory.exists()){
	  		    		 
	  		    		 boolean bool = directory.mkdirs();
	  		    	      if(bool){
	  		    	   //      System.out.println("Directory created successfully");
	  		    	      }else{
	  				         //   return null;
	  			        		    	     
	  		    	      }

	  		    	             
	  		    	           
	  		    	}
	  				
	  			} catch (Exception e) {
	  				 return null;
	          		    	     
	  			}
	  	    	
	  	      return targetLocation1;
	    	
	    }
	    
	    Path create_tempdirectory() {
	    	
	    	Path targetLocationtemp = this.fileStorageLocation.resolve("temp\\"+spageid+"\\"+sparentid+"\\"+scomponentid+"\\");
	        File directorytemp = targetLocationtemp.toFile();
	
		    	try {
		    		
		    		 if(!directorytemp.exists()){
			    		 
			    		 boolean bool = directorytemp.mkdirs();
			    	      if(bool){
			    	    //    System.out.println("Directory created successfully");
			    	    	
			    	      }else{
			    	    //	  System.out.println("Directory Not created successfully");
			    	         // UploadFileResponse b= new UploadFileResponse("", "","", 0,3,"Directory Not created");
					        //    return b;
				        		// return null;   	     
			    	      }

			    	             
			    	           
			    	}
					
				} catch (Exception e) {
			    //    UploadFileResponse b= new UploadFileResponse("", "","", 0,3,"Directory Not created");
		        //    return b;
					 return null;    	     
				}
		    	
		    	  return targetLocationtemp;
	    	
	    }
	    
	    
	    Path  save_file(MultipartFile file,Path temp){
	    	
	    	 String [] extension= file.getOriginalFilename().split("\\.");
	    	 
	    	 Generate gen=new Generate();
	    	  
	    	  String filname=gen.token(30)+"."+extension[extension.length-1];
	    
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(filname);

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
		          return null;
	             //   throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }
	   
	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = temp.resolve(fileName);

	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	            sfilename=fileName;
	            return targetLocation;
	        } catch (Exception ex) {    
		         //   throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	        	  return null;
		        }
	    	
	    }
	    
	    public FilesUpload change_public(FilesUpload file,boolean publicf) {
	    	
	    if(publicf) {
	    file.setFilePublic(1);
	    }else {
	    file.setFilePublic(0);	
	    }
	    file = filesUploadRepo.save(file);
	    return file;
	    }


	    public FilesUpload storeFile(MultipartFile file,int pageid,int parentid,int componentid,String langcode,DevicePage devpag) {

	   	  scomponentid=componentid;
		  spageid=pageid;
		  sparentid=parentid;
		  
		  FileType ftype= null;
		  
		  try {
			  ftype=  fileTypeServ.getbymime(file.getContentType(), langcode);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		  
		  FilesUpload fileu=new FilesUpload();
	    	
	    		Path main =	create_maindirectory() ;
		    	Path temp =	create_tempdirectory() ;
			    	
			    	
		    	Path filetemp =save_file(file, temp);
		    	
		 	 // System.out.println(main.toFile().getAbsolutePath());
		    	
		    
		    	try {
		    		File samefile=fileEquals(filetemp.toFile(),main.toFile());
		    		//System.out.println(samefile);	
		    		if(samefile != null) {
		    	
		    		//	System.out.println(samefile.getName());		
		    		//	System.out.println(samefile.getAbsolutePath());	
		    			
		    			   try {
		    		    		
		    Optional<FilesUpload> fileupload = filesUploadRepo.findbyfile(samefile.getName(), samefile.getParent());
		    		    		 
		    		    		 if (fileupload.isPresent()){
		    		    			 fileu=fileupload.get();
		    		    			  filetemp.toFile().delete();
		    		    			  return fileu;
		    		    			}
		    		    			else{
		    		    			   // alternative processing....
		    		   
		    		    			}
		    		    	} catch (Exception e) {
		    		    		// TODO: handle exception
		    		 
		    		    	}
		    			
		    			
		    		}	    		
		    		
				} catch (NullPointerException e) {
					// TODO: handle exception
					e.printStackTrace();
					//ind=false;
				}
		    	
		    	
		   // 	boolean ind=true;
		    	
		   // 	if(ind) {
		   // 		//System.out.println("duplicate");
		   // 		throw new PopupException(textConvertionServ.search("E118", langcode));
		  //  	}

		//  System.out.println(filetemp);
		    	
		  
		    	try {
		    	    Path maintemp =	save_file(file, main);
				    
				    sfilename=maintemp.getFileName().getFileName().toString();

			       String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					                .path("file/downloadFile/")
					                .path(sfilename)
					                .toUriString();
				
		
	       FileStatus  filst;
	       try {
	    		
	    		Optional<FileStatus> typereq =fileStatusRepo.findById(1);
	    		 
	    		 if (typereq.isPresent()){
	    			 filst=typereq.get();
	    			}
	    			else{
	    			   // alternative processing....
	    				
	      throw new NullPointerException("Error no file status");

	    			}
	    	} catch (Exception e) {
	    		// TODO: handle exception
	  	      throw new PopupException("Error ",e);
	
	    		
	    	}
	       
	
	       
	      
	       fileu.setFilesName(sfilename);
	       fileu.setFilesUrl(fileDownloadUri);
	       fileu.setFilesSize(new BigDecimal(file.getSize()/1000));
	       fileu.setFiletypeID(ftype);
	       fileu.setFilecomruntime(comtime);
	       fileu.setFilestatusID(filst);
	       fileu.setFilePath(maintemp.getParent().toString());
	       filesUploadRepo.save(fileu);

		            return fileu;
			}catch (TransientDataAccessException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    } catch (RecoverableDataAccessException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    }catch (ScriptException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    }catch (NonTransientDataAccessException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    }
	    	
	    
	    }

	    public Resource loadFileAsResource(int fileid,String langcode,Boolean publicfile) {
	    //	System.out.println("load");
	    	FilesUpload filedel=getfilebyid(fileid,langcode);
	    	if(publicfile && filedel.getFilePublic() != 1) {
	    		return null;
	    	}
	        try {
		     Path targetLocation1 = this.fileStorageLocation.resolve(filedel.getFilePath());

	            Path filePath = targetLocation1.resolve(filedel.getFilesName()).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	            	return null;
	               // throw new PopupException("File not found " + fileid);
	            }
	        } catch (MalformedURLException ex) {
	        	ex.printStackTrace();
	        	return null;
	          //  throw new PopupException("File not found " + fileid, ex);
	        }
	    }
	    
	    
	    
	    public void changestatustoclose(int fileid,String langcode) {
	    //	System.out.println("load");
	    	
	    	FilesUpload filedel=getfilebyid(fileid,langcode);
	    	
	    	
	    	 FileStatus  filst;
		       try {
		    		
		    		Optional<FileStatus> typereq =fileStatusRepo.findById(2);
		    		 
		    		 if (typereq.isPresent()){
		    			 filst=typereq.get();
		    			}
		    			else{
		    			   // alternative processing....
		    				
		      throw new NullPointerException("Error no file status");

		    			}
		    	} catch (Exception e) {
		    		// TODO: handle exception
		  	      throw new PopupException("Error ",e);
		
		    		
		    	}
	    
	    	filedel.setFilestatusID(filst);
	    	try {
	    		
	    		filesUploadRepo.save(filedel);
			} catch (Exception e) {
				// TODO: handle exception
			    throw new PopupException("Error ",e);
			}
      
	       
	    }
	    
	    
	    
	    public  File fileEquals(File file1,File directory) {
	    	
	    	comtime=new BigDecimal(0);
	    	int ident=0;
	    	File same=null;
	    	
	   //System.out.println("path : "+directory.getAbsolutePath());	
	    File[] files;
	    files=directory.listFiles();
	   // System.out.println("path : "+files.length);	
	    File file2=null;
	    // For each pathname in the pathnames array
        for (File filec : files) {
            // Print the names of files and directories
        //	System.out.println(filec.getAbsolutePath());
        	file2=filec;
        	

	    	try {
	            if (!file1.exists() || !file2.exists()) {
	            	 continue;
	            }
	            if (file1.length() != file2.length()) {
	                continue;
	            }

	            long f1Length = file1.length();
	            long f2Length = file2.length();

	            System.out.println("Check Digest method:");
	            FileInputStream fis1 = new FileInputStream(file1);
	            DigestInputStream dgStream1 = new DigestInputStream(fis1,
	                    MessageDigest.getInstance("MD5"));
	            FileInputStream fis2 = new FileInputStream(file2);
	            DigestInputStream dgStream2 = new DigestInputStream(fis2,
	                    MessageDigest.getInstance("MD5"));
	            // most expensive is dgStream1.getMessageDigest() so do it only at last read
	            dgStream1.on(false);
	            dgStream2.on(false);

	            long f1ReadTotal = 0;
	            long f2ReadTotal = 0;

	            long start = System.nanoTime();

	            int read = 0;
	            byte[] buff = new byte[1024 * 128];
	            do {
	                if ((f1Length - f1ReadTotal) < (1024 * 128)) {
	                    // last read 
	                    dgStream1.on(true);
	                }
	                read = dgStream1.read(buff);
	                f1ReadTotal += read > 0 ? read : 0;
	            } while (read > 0);

	            read = 0;
	            do {
	                if ((f2Length - f2ReadTotal) < (1024 * 128)) {
	                    // last read
	                    dgStream2.on(true);
	                }
	                read = dgStream2.read(buff);
	                f2ReadTotal += read > 0 ? read : 0;
	            } while (read > 0);

	            long runTime = System.nanoTime() - start;
	            if (Arrays.equals(dgStream1.getMessageDigest().digest(), dgStream2
	                    .getMessageDigest().digest())) {
	            	BigDecimal a=new BigDecimal(runTime / 1000000);
	    	       comtime=comtime.add(a);
	                System.out.println("Files are identical. completed in "
	                        + (runTime / 1000000) + " ms. [" + runTime + " ns.]");
	                fis1.close();
		            fis2.close();
		            ident ++;
		            same=file2;
		            break;
	            } else {
	            	BigDecimal a=new BigDecimal(runTime / 1000000);
	            	comtime=comtime.add(a);
	                System.out.println("Files are not identical. completed in "
	                        + (runTime / 1000000) + " ms. [" + runTime + " ns.]");
	                fis1.close();
		            fis2.close();
		           
	                
	            }

	     

	        } catch (Exception e) {
	            e.printStackTrace();
	            ident ++;
	            same=file2;
	            break;
	        }
        }
        
        if(ident >= 1) {
        return	same;  	
        }else {
		return null;
        }

	    }
	    
	    
	    

	    
	    
	    
	    public FilesUpload getfilebyid(int fileid,String langcode) {
			// TODO Auto-generated method stub
	    	
	    	
	    	FilesUpload filedel=new FilesUpload();
	    	try {
	    		
	    		Optional<FilesUpload> filed= filesUploadRepo.findById(fileid);
	    		 
	    		 if (filed.isPresent()){
	    			 filedel=filed.get();
	    			}
	    			else{
	    			   // alternative processing....
	    				
			   throw new NullPointerException("file not found");
	    			}
	    	}catch (TransientDataAccessException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    } catch (RecoverableDataAccessException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    }catch (ScriptException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    }catch (NonTransientDataAccessException  se) {
				throw new NullPointerException(textConvertionServ.search("E104", langcode));
		    }
			return filedel;
		}
	    
	    
  
  
  
	    
	    public ResponseEntity<Resource> getresource(HttpServletRequest request,int fileid,String langcode,Boolean publicfile) {
	    	
	    	
	    	Resource resource  = loadFileAsResource(fileid,langcode,publicfile);   		

	        String contentType = null;
	        if(resource == null) {
	        	 contentType = "application/octet-stream";
	        	return ResponseEntity.ok()
		                .contentType(MediaType.parseMediaType(contentType))
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "no" + "\"")
		                .body(resource);
	        	
	        }

	        // Try to determine file's content type
	       
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
