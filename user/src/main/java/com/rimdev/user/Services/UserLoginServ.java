package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.DuplicationException;
import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Exception.redirectlogin;
import com.rimdev.user.Repo.UserLoginRepo;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.AES;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.Loginobject;

@Service
public class UserLoginServ {
	
	@Autowired 
	private UserLoginRepo userLoginRepo;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired 
	private UserServ userServ;
	
	
	@Autowired
	EmailServ emailServ;
	
	@Autowired
	TelephonesServ telephonesServ;
	
	
public List<UserLogin> getalllogin(String langcode) {
		try {

		return (List<UserLogin>) userLoginRepo.findAll();
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


public List<UserLogin> getall(String langcode) {
	try {
	List<UserLogin> loginlist= getalllogin(langcode);
	return loginlist;
	} catch (TransientDataAccessException  se) {
		throw new NullPointerException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NullPointerException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("NonTransientDataAccessException");
    }
	
}


public List<UserLogin> getbyuser(int userid,String langcode) {
	

	
	try {
		List<UserLogin> loginlistout=new ArrayList<UserLogin>();
		List<UserLogin> loginlist= (List<UserLogin>) userLoginRepo.findbyuser(userid);
		
		for (UserLogin userLogin : loginlist) {
			userLogin.setPasswordEncy("eneter a new password");
			loginlistout.add(userLogin);
		}
		
		if(loginlist == null || loginlist.size() <= 0) {
			
			throw new NoDataException(textConvertionServ.search("E109", langcode));

		}
		
		return loginlistout;
		
	

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NullPointerException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("NonTransientDataAccessException");
    }
	
	

	

}




public void check_username(String username,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findbyusername(username);
		 
		 if (flowid.isPresent()){
			  flowid.get();
		
				throw new DuplicationException(textConvertionServ.search("E105", langcode));

					}
			else{
			   // alternative processing....
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }

	
}




public UserLogin getbyusername(String username,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findbyusername(username);
		 
		 if (flowid.isPresent()){
			 UserLogin a= flowid.get();
		
			return a;

					}
			else{
			   // alternative processing....
				throw new NoDataException("please enter correct username");

				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }

	
}



public UserLogin getbyid(int id,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 UserLogin  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoDataException(textConvertionServ.search("E109", langcode));
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




public void save(UserLogin input,String langcode) {
	
	
	if(input.getUserID() != null || input.getUserID().getId() != null) {
		User  usero = userServ.getuser(input.getUserID().getId(), langcode);
		input.setUserID(usero);
		
		Date date = new Date();
		input.setCreatedate(date);
		input.setLoginCreate(date);
		input.setLoginModfiy(date);
		try {
			userLoginRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
		
	}else {
		
		throw new NoDataException(textConvertionServ.search("E107", langcode));
	
	}
	


	
}



public void update(UserLogin input,String langcode) {
	
	if(input.getUserID() != null || input.getUserID().getId() != null) {
		User  usero = userServ.getuser(input.getUserID().getId(), langcode);
		input.setUserID(usero);
	}
	
	Date date = new Date();
	input.setLoginModfiy(date);
	
	try {
		userLoginRepo.save(input);	
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	
	
}



public Loginobject loginpage(Loginobject input,String langcode) {
	Loginobject out=new Loginobject();
	
	out.setUsername(input.getUsername());
	UserLogin userlog;
	try {
		 userlog=getbyusername(input.getUsername(), langcode);
	} catch (Exception e) {
		// TODO: handle exception
		throw new redirectlogin("please enter good username");
	}
	
	
	if(! userlog.getUsertokean().equals(input.getTokean())) {
		throw new redirectlogin("please enter good tokean");
		
	}
   

    Calendar cal = Calendar.getInstance(); 
   
	Generate gen=new Generate();
	String tokean=gen.token(30);
	out.setTokean(tokean);
	userlog.setUsertokean(tokean);
	userlog.setLoginModfiy(cal.getTime());
	cal.add(Calendar.MONTH, 1);
	userlog.setExpiredate(cal.getTime());
	out.setLogin(true);

	try {
		userLoginRepo.save(userlog);	
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	return out;
	
}



public Loginobject login(Loginobject input,String langcode) {
	Loginobject out=new Loginobject();
	
	out.setUsername(input.getUsername());
	
	UserLogin userlog=getbyusername(input.getUsername(), langcode);
	
    String password = dencryp(input.getPassword());
    
    String origpassword = dencryp(userlog.getPasswordEncy()+userlog.getLoginkey());
        
    System.out.println(password + " "+origpassword);
    
    if(! password.equals(origpassword)) {
    	
    	 throw new NoDataException("please eneter correct password");
    }


    Calendar cal = Calendar.getInstance(); 
   
	Generate gen=new Generate();
	String tokean=gen.token(30);
	out.setTokean(tokean);
	userlog.setUsertokean(tokean);
	userlog.setLoginModfiy(cal.getTime());
	 cal.add(Calendar.MONTH, 1);
	userlog.setExpiredate(cal.getTime());
	out.setLogin(true);

	try {
		userLoginRepo.save(userlog);	
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	return out;
	
}


public String dencryp(String password) {
	
	try {
		String key = getkey(password);
		String pass = getencpassword(password);

		
		//decrypt password
		
		String decryptedPassword =  new String(Base64.getDecoder().decode(pass));
	     AES aesUtil = new AES(128, 1000);
	    return aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0], key, decryptedPassword.split("::")[2]);
	    
	} catch (Exception e) {
		// TODO: handle exception
		throw new NoDataException("have a enc problem");
	}

	
	
	
}

public String getkey(String password) {
	try {
	return password.substring(password.length() - 16);
	} catch (Exception e) {
		// TODO: handle exception
		throw new NoDataException("have a enc problem");
	}
}

public String getencpassword(String password) {
	try {
	return password.substring(0,password.length() - 16);
	} catch (Exception e) {
		// TODO: handle exception
		throw new NoDataException("have a enc problem");
	}
}

}
