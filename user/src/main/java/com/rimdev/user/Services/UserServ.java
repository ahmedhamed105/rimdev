package com.rimdev.user.Services;

import java.util.ArrayList;
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

import com.rimdev.user.Exception.NoResultException;
import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserFile;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.Userobject;
import com.rimdev.user.ouputobject.threevalues;

@Service
public class UserServ {
	
	
	@Autowired 
	 UserRepo userRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	FileStorageService fileStorageService;
	
	
	@Autowired
	ComponentServ componentServ;

	@Autowired
	UserFileServ userFileServ;
	
	@Autowired
	UserLoginServ userLoginServ;
	
	
	@Autowired
	LogServ logServ;
	

	
	
public List<Userobject> getall(String langcode) {
	
	List<Userobject> ob=new ArrayList<Userobject>();
	
	try {
		List<UserLogin> user= (List<UserLogin>) userLoginServ.getalllogin(langcode);
		
		for (UserLogin user2 : user) {
			Userobject a= new Userobject();
			a.setUser(user2.getUserID());
			user2.setPasswordEncy("Enter password");
			a.setLogin(user2);
			ob.add(a);
		}
		
		return ob;
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

public User getuserbyid(int id, String langcode) {

	try {
		Optional<User> flowid = userRepo.findById(id);

		if (flowid.isPresent()) {
			User ouput = flowid.get();
			return ouput;
		} else {

			throw new NoResultException(textConvertionServ.search("E500", langcode));
		}
	} catch (TransientDataAccessException se) {
		throw new NoResultException(textConvertionServ.search("E500", langcode));
	} catch (RecoverableDataAccessException se) {
		throw new NoResultException(textConvertionServ.search("E500", langcode));
	} catch (ScriptException se) {
		throw new NoResultException(textConvertionServ.search("E500", langcode));
	} catch (NonTransientDataAccessException se) {
		throw new NoResultException(textConvertionServ.search("E500", langcode));
	} catch (Exception e) {
		throw new NoResultException(textConvertionServ.search("E500", langcode));
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



public User Save(HttpServletRequest request,DevicePage devpag,User input,String langcode) {
	
	try {
		FilesUpload file=	fileStorageService.getfilebyid(1, langcode);
		input.setFilesuploadID(file);
		Date date = new Date();
		Generate gen=new Generate();
		String tokean=gen.token(30);
		input.setUseridnumber(tokean);
		input.setUsercreate(date);
		input.setUsermodify(date);
		User ouput =userRepo.save(input);	
		
		String text= "user entity "+input.getFirstName()+" created by "+devpag.getUserloginID().getUsername();
		logServ.info(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode," ");		
    	
		
		return ouput;

	} catch (TransientDataAccessException  se) {
		String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
   
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
   
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
   
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }			
		
	}


public User update(HttpServletRequest request,DevicePage devpag,User old,User input,String langcode) {

	try {	
		input.setId(old.getId());
		input.setUsercreate(old.getUsercreate());
		if(input.getFilesuploadID() == null || input.getFilesuploadID().getId() == null) {
			input.setFilesuploadID(old.getFilesuploadID());			
		}
		Date date = new Date();
		input.setUsermodify(date);
		User ouput =userRepo.save(input);	
		
		String text= "user entity "+input.getFirstName()+" updated by "+devpag.getUserloginID().getUsername();
		logServ.info(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode," ");		
    	
		
		return ouput;
	} catch (TransientDataAccessException  se) {
		String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }		
		
	}


public FilesUpload savefile(HttpServletRequest request,DevicePage devpag,threevalues input,String langcode) {

	try {
		FilesUpload file = fileStorageService.getfilebyid(input.getFileid().getId(), langcode);
		Component com =componentServ.getComponentbyid(Integer.parseInt(input.getCompid()), langcode);
		
		UserLogin userlog=userLoginServ.getbyusername(input.getUser().getLogin().getUsername(), langcode);
	
		userFileServ.Save(request,devpag,userlog, file,com, langcode);
		
		return file;
		
	
	} catch (TransientDataAccessException  se) {
		String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
	
	
}



public FilesUpload saveprofilefile(HttpServletRequest request,DevicePage devpag,threevalues input,String langcode) {

	try {
		FilesUpload file = fileStorageService.getfilebyid(input.getFileid().getId(), langcode);
		Component com =componentServ.getComponentbyid(Integer.parseInt(input.getCompid()), langcode);
		
		UserLogin userlog=userLoginServ.getbyusername(input.getUser().getLogin().getUsername(), langcode);
	
		
		userlog.getUserID().setFilesuploadID(file);
		System.out.println("here");
		userFileServ.delete(request,devpag,userlog,com, langcode);
		System.out.println("here1");
		userFileServ.Save(request,devpag,userlog, file,com, langcode);
		System.out.println("here2");
		userRepo.save(userlog.getUserID());
		
		return file;
		
	
	} catch (TransientDataAccessException  se) {
		String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	
    	String text= "user entity "+input.toString()+" not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  32, langcode,se.getMessage());		
    	
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
	
	
}




public FilesUpload deletefile(HttpServletRequest request,DevicePage devpag,String fileid,String object, String componentid,String langcode) {
	
	UserLogin user=userLoginServ.getuserlogin(Integer.parseInt(object), langcode);
	FilesUpload file= fileStorageService.getfilebyid(Integer.parseInt(fileid), langcode);
	Component com=componentServ.getComponentbyid(Integer.parseInt(componentid), langcode);
	
	try {
		fileStorageService.changestatustoclose(file.getId(),langcode); 
		
		userFileServ.deletefile(request,devpag,user, file,com, langcode);
		
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












}
