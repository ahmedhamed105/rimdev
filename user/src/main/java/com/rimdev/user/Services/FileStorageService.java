package com.rimdev.user.Services;

import java.io.File;
import java.io.FileInputStream;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rimdev.user.Config.FileStorageProperties;
import com.rimdev.user.Exception.DuplicationException;
import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.FileappTypeRepo;
import com.rimdev.user.Repo.FilesUploadRepo;
import com.rimdev.user.Repo.UserFileRepo;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.UserFile;
import com.rimdev.user.ouputobject.UploadFileResponse;

@Service
public class FileStorageService {
	
	@Autowired
	UserFileRepo userFileRepo;
	
	@Autowired
	UserRepo userRepo;
	
	
	@Autowired
	FilesUploadRepo filesUploadRepo;
	
	
	@Autowired
	FileappTypeRepo fileappTypeRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
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
	            throw new NoDataException("Could not create the directory where the uploaded files will be stored.", ex);
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
	  				            return null;
	  			        		    	     
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
			    	        System.out.println("Directory created successfully");
			    	    	
			    	      }else{
			    	    	  System.out.println("Directory Not created successfully");
			    	         // UploadFileResponse b= new UploadFileResponse("", "","", 0,3,"Directory Not created");
					        //    return b;
				        		 return null;   	     
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
	    	  
	    	  String filname=Generate.token(30)+"."+extension[extension.length-1];
	    
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

	    public FilesUpload storeFile(MultipartFile file,int pageid,int parentid,int componentid,String langcode) {

	   	  scomponentid=componentid;
		  spageid=pageid;
		  sparentid=parentid;
	    	try {
	    		Path main =	create_maindirectory() ;
		    	Path temp =	create_tempdirectory() ;
			    	
			    	
		    	Path filetemp =save_file(file, temp);
		    	
		   // 	  System.out.println(filetemp);
		    	
		    	boolean ind=true;
		    	try {
		    		 ind=fileEquals(filetemp.toFile(),main.toFile());
		    		  filetemp.toFile().delete();
				} catch (NullPointerException e) {
					// TODO: handle exception
					ind=false;
				}
		    	
		    	
		    	if(ind) {
		    		//System.out.println("duplicate");
		    		throw new DuplicationException(textConvertionServ.search("E105", langcode));
		    	}

		//  System.out.println(filetemp);
		    	
		  
		    	
		    Path maintemp =	save_file(file, main);

	       String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			                .path("file/downloadFile/")
			                .path(sfilename)
			                .toUriString();
	       
	       FilesUpload fileu=new FilesUpload();
	       fileu.setFilesName(sfilename);
	       fileu.setFilesUrl(fileDownloadUri);
	       fileu.setFilesSize(new BigDecimal(file.getSize()/1000));
	       fileu.setFilesType(file.getContentType());
	       fileu.setFilecomruntime(comtime);
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

	    public Resource loadFileAsResource(String fileName, int type,int userid) {
	    //	System.out.println("load");
	    	
	        try {
		     Path targetLocation1 = this.fileStorageLocation.resolve(userid+"\\"+type+"\\");

	            Path filePath = targetLocation1.resolve(fileName).normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            } else {
	            
	                throw new NoDataException("File not found " + fileName);
	            }
	        } catch (MalformedURLException ex) {
	        	ex.printStackTrace();
	            throw new NoDataException("File not found " + fileName, ex);
	        }
	    }
	    
	    
	    
	    public UploadFileResponse deleteFile(String fileName, int type,int userid) {
	    //	System.out.println("load");
	    	
	    	FilesUpload filedel=new FilesUpload();
	    	try {
	    		
	    		Optional<FilesUpload> filed= filesUploadRepo.findbyfilename(fileName);
	    		 
	    		 if (filed.isPresent()){
	    			 filedel=filed.get();
	    			}
	    			else{
	    			   // alternative processing....
	    				
			    		UploadFileResponse a= new UploadFileResponse("", "","",0,2,"file not found");	
			    		return a;
	    			}
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    		
	    		UploadFileResponse a= new UploadFileResponse("", "","",0,2,"file not found");	
	    		return a;
	    	}
	    	
	    	UserFile userf=new UserFile();
	    	try {
	    		
	    		Optional<UserFile> filed= userFileRepo.findbyusertypefile(userid,type,filedel.getId());
	    		 
	    		 if (filed.isPresent()){
	    			 userf=filed.get();
	    			}
	    			else{
	    			   // alternative processing....
	    				
			    		UploadFileResponse a= new UploadFileResponse("", "","",0,3,"file not found");	
			    		return a;
	    			}
	    	} catch (Exception e) {
	    		// TODO: handle exception
	    		
	    		UploadFileResponse a= new UploadFileResponse("", "","",0,3,"file not found");	
	    		return a;
	    	}
	    	
	    	try {
	    		userFileRepo.delete(userf);
	    		filesUploadRepo.delete(filedel);
			} catch (Exception e) {
				// TODO: handle exception
				UploadFileResponse a= new UploadFileResponse("", "","",0,4,"file not found");	
	    		return a;
			}
	    	
	      try {
	 	     Path targetLocation1 = this.fileStorageLocation.resolve(userid+"\\"+type+"\\");

	            Path filePath = targetLocation1.resolve(fileName).normalize();
	            if(!filePath.toFile().delete()) {
	            	UploadFileResponse a= new UploadFileResponse("", "","",0,1,"error while deleting");	
	        		return a;		
	            }
	            UploadFileResponse a= new UploadFileResponse("", "","",0,0,"Deleted");	
	    		return a;
		} catch (Exception e) {
			// TODO: handle exception
			UploadFileResponse a= new UploadFileResponse("", "","",0,1,"error while deleting");	
    		return a;
		}
	
	            
	       
	    }
	    
	    
	    
	    public  boolean fileEquals(File file1,File directory) {
	    	
	    	comtime=new BigDecimal(0);
	    	
	  //  System.out.println("path : "+file1.getParentFile());	
	    File[] files;
	    files=directory.listFiles();
	    File file2=null;
	    // For each pathname in the pathnames array
        for (File filec : files) {
            // Print the names of files and directories
        	System.out.println(filec.getAbsolutePath());
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
	                return true;
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
	            return true;
	        	
	        }
        }
        
        
		return false;


	    }
	    
	    
	    
	    public List<FilesUpload> getfile(int userid,int filetype) {
			// TODO Auto-generated method stub
	    	
	    	
	    	System.out.println(userid+" "+filetype);
	    	
	    	List<FilesUpload> files=new ArrayList<FilesUpload>();
	    	
	    	List<UserFile> userfiles=(List<UserFile>) userFileRepo.findbyusertype(userid, filetype);
	    	
	    	for (UserFile userFile : userfiles) {
	    		files.add(userFile.getFilesuploadID());
			}
	    	
	    	return files;
	    	
	    	
		}
	    
	    
}
