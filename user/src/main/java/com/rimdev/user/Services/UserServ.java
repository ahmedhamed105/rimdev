package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Component;
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
	

	
	
public List<Userobject> getall(String langcode) {
	
	List<Userobject> ob=new ArrayList<Userobject>();
	
	try {
		List<UserLogin> user= (List<UserLogin>) userLoginServ.getalllogin(langcode);
		
		for (UserLogin user2 : user) {
			Userobject a= new Userobject();
			a.setUser(user2.getUserID());
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



public User getuserbyid(int id,String langcode) {
	
//	System.out.println("null 0");
	
	try {
		Optional<User> flowid =userRepo.findById(id);
		 
		 if (flowid.isPresent()){
			//	System.out.println("null g");
			 User  ouput = flowid.get();
		
			  return ouput;
					}
			else{
				
		//		System.out.println("null 1");
			   // alternative processing....
				return null;
			}
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NullPointerException e) {
    //	System.out.println("null 2");
		// TODO: handle exception
    	e.printStackTrace();
   // 	return null;
	}
	return null;
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
		FilesUpload file=	fileStorageService.getfilebyid(1, langcode);
		input.setFilesuploadID(file);
		Date date = new Date();
		Generate gen=new Generate();
		String tokean=gen.token(30);
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
		FilesUpload file= fileStorageService.getfilebyid(Integer.parseInt(input.getValue2()), langcode);
		Component com=componentServ.getComponentbyid(Integer.parseInt(input.getValue3()), langcode);
		
		UserLogin userlog=userLoginServ.getbyuser(Integer.parseInt(input.getValue1()), langcode).get(0);
	
		
		userFileServ.Save(userlog, file,com, langcode);
		
		return file;
		
	
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
