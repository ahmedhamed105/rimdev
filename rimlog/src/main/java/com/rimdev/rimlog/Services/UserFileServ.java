package com.rimdev.rimlog.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimlog.Exception.PopupException;
import com.rimdev.rimlog.Repo.UserFileRepo;
import com.rimdev.rimlog.Utils.Generate;
import com.rimdev.rimlog.entities.Component;
import com.rimdev.rimlog.entities.DevicePage;
import com.rimdev.rimlog.entities.FilesUpload;
import com.rimdev.rimlog.entities.User;
import com.rimdev.rimlog.entities.UserFile;
import com.rimdev.rimlog.entities.UserLogin;




@Service
public class UserFileServ {
	
	
	@Autowired
	UserFileRepo userFileRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;

	@Autowired
	LogServ logServ;
	
	@Autowired
	FileStorageService fileStorageService;

	
	public UserFile Save(HttpServletRequest request,DevicePage devpag,UserLogin user,FilesUpload file,Component com,String langcode) {
		
		try {
			
			file = fileStorageService.change_public(file, false);
			UserFile userfile=new UserFile();
			userfile.setFilesuploadID(file);
			userfile.setUserloginID(user);
			userfile.setComponentID(com);
			UserFile ouput =userFileRepo.save(userfile);	
			return ouput;

		} catch (TransientDataAccessException  se) {
			String text= "file save "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			String text= "file save "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			String text= "file save "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			String text= "file save "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }			
			
		}
	
	
	

public void deletefile(HttpServletRequest request,DevicePage devpag,UserLogin user,FilesUpload file,Component com,String langcode) {
		
		try {	
			UserFile userfile= getbyall(file.getId(),user.getId(),com.getId(),langcode);
			userFileRepo.delete(userfile);	
		

		} catch (TransientDataAccessException  se) {
			String text= "file delete "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			String text= "file delete "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			String text= "file delete "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			String text= "file delete "+user.getUsername() +" file "+file.getId()+" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }			
			
		}
	
	
public void delete(HttpServletRequest request,DevicePage devpag,UserLogin user,Component com,String langcode) {
		
		try {	
			UserFile userfile= getbycompoent(user.getId(),com.getId(),langcode);
			System.out.println(userfile);
			if(userfile != null) {
				userFileRepo.delete(userfile);		
			}

		

		} catch (TransientDataAccessException  se) {
			String text= "file delete "+user.getUsername() +" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			String text= "file delete "+user.getUsername() +" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			String text= "file delete "+user.getUsername() +" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			String text= "file delete "+user.getUsername() +" comp "+com.getId()+" not save file by "+devpag.getUserloginID().getUsername();
			logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
	    	se.printStackTrace();
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }			
			
		}
	
	
	
	public List<UserFile> getfilebyuser(UserLogin user,String langcode) {
		
		try {	
			List<UserFile> userfileList= (List<UserFile>)userFileRepo.findbyuser(user.getId());
			return userfileList;

		} catch (TransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }			
			
		}
	
	

public UserFile getbyall(int files_upload_ID,int User_ID,int Component_ID,String langcode) {
	// TODO Auto-generated method stub
	
	
	UserFile filedel=new UserFile();
	try {
		
		Optional<UserFile> filed= userFileRepo.findbyall(files_upload_ID, User_ID, Component_ID);
		 
		 if (filed.isPresent()){
			 return filed.get();
			}
			else{
			   // alternative processing....
				
	  return null;
			}
	}catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
}



public UserFile getbycompoent(int User_ID,int Component_ID,String langcode) {
	// TODO Auto-generated method stub
	

	try {
		
		Optional<UserFile> filed= userFileRepo.findbycomp(User_ID, Component_ID);
		 
		 if (filed.isPresent()){
			 return filed.get();
			}
			else{
			   // alternative processing....
				
	   return null;
			}
	}catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
}



}
