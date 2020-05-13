package com.rimdev.user.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.UserFileRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserFile;
import com.rimdev.user.entities.UserLogin;




@Service
public class UserFileServ {
	
	
	@Autowired
	UserFileRepo userFileRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	

	
	public UserFile Save(UserLogin user,FilesUpload file,Component com,String langcode) {
		
		try {	
			UserFile userfile=new UserFile();
			userfile.setFilesuploadID(file);
			userfile.setUserloginID(user);
			userfile.setComponentID(com);
			UserFile ouput =userFileRepo.save(userfile);	
			return ouput;

		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }			
			
		}
	
	
public void delete(User user,FilesUpload file,Component com,String langcode) {
		
		try {	
			UserFile userfile= getbyall(file.getId(),user.getId(),com.getId(),langcode);
			userFileRepo.delete(userfile);	
		

		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }			
			
		}
	
	
	
	public List<UserFile> getfilebyuser(User user,String langcode) {
		
		try {	
			List<UserFile> userfileList= (List<UserFile>)userFileRepo.findbyuser(user.getId());
			return userfileList;

		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }			
			
		}
	
	

public UserFile getbyall(int files_upload_ID,int User_ID,int Component_ID,String langcode) {
	// TODO Auto-generated method stub
	
	
	UserFile filedel=new UserFile();
	try {
		
		Optional<UserFile> filed= userFileRepo.findbyall(files_upload_ID, User_ID, Component_ID);
		 
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



}
