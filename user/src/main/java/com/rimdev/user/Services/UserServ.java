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
import org.springframework.web.bind.annotation.RequestParam;
import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Repo.UserFileRepo;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserFile;
import com.rimdev.user.ouputobject.threevalues;

@Service
public class UserServ {
	
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	FileStorageService fileStorageService;
	
	
	@Autowired
	ComponentServ componentServ;
	
	

	
	@Autowired
	UserFileServ userFileServ;
	
	

	
	
public List<User> getall(String langcode) {
	
	try {
		return (List<User>) userRepo.findAll();
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


public void checkuser(int id,String langcode) {
	
	try {
		Optional<User> flowid =userRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 User  ouput = flowid.get();
		
			 
					}
			else{
				   // alternative processing....
							throw new NullPointerException(textConvertionServ.search("E108", langcode));
				}
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


public User getuser(int id,String langcode) {
	
	try {
		Optional<User> flowid =userRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 User  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NullPointerException(textConvertionServ.search("E108", langcode));
			}
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



public User getuserbyid(int id,String langcode) {
	
	try {
		Optional<User> flowid =userRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 User  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
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

public void check_user(String firstname,String middlename,String lastname,String langcode) {
	
	try {
		Optional<User> flowid =userRepo.findbyname( firstname, middlename, lastname);
		 
		 if (flowid.isPresent()){
			 User  ouput = flowid.get();
		
				throw new PopupException(textConvertionServ.search("E105", langcode));

					}
			else{
			   // alternative processing....
			}
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




public User Save(User input,String langcode) {
	
	try {	
		Date date = new Date();
		Generate gen=new Generate();
		String tokean=gen.token(10);
		input.setUseridnumber(tokean);
		input.setUsercreate(date);
		input.setUsermodify(date);
		User ouput =userRepo.save(input);	
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


public User update(User input,String langcode) {

	try {	
		Date date = new Date();
		input.setUsermodify(date);
		User ouput =userRepo.save(input);	
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


public FilesUpload savefile(threevalues input,String langcode) {

	try {
		//User user=getuser(Integer.parseInt(input.getValue1()), langcode);
		FilesUpload file= fileStorageService.getfilebyid(Integer.parseInt(input.getValue2()), langcode);
		Component com=componentServ.getComponentbyid(Integer.parseInt(input.getValue3()), langcode);
		
		
		userFileServ.Save(null, file,com, langcode);
		
		return file;
		
		
	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (Exception e) {
		// TODO: handle exception
    	throw new NullPointerException(textConvertionServ.search("E104", langcode));
	}
	
	
}



public FilesUpload deletefile(String fileid,String object, String componentid,String langcode) {
	
	User user=getuser(Integer.parseInt(object), langcode);
	FilesUpload file= fileStorageService.getfilebyid(Integer.parseInt(fileid), langcode);
	Component com=componentServ.getComponentbyid(Integer.parseInt(componentid), langcode);
	
	try {
		fileStorageService.deleteFile(file.getId(),langcode); 
		
		userFileServ.delete(user, file,com, langcode);
		
	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	
	return file;
}



public List<UserFile> getfile(String userid,String langcode) {
	

	try {
    User user=getuser(Integer.parseInt(userid), langcode);
    
    
	return	userFileServ.getfilebyuser(user, langcode);
		
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








}
