package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.BlockedException;
import com.rimdev.user.Exception.NooauthException;
import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Exception.RedirectException;
import com.rimdev.user.Repo.UserLoginRepo;
import com.rimdev.user.Utils.AES;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
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
	
	@Autowired
	ConfigurationServ configurationServ;
	
	@Autowired
	LogServ logServ;
	
	

public UserLogin getuserlogin(int id,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 UserLogin  ouput = flowid.get();
		
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


public void checkuserlogin(int id,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 UserLogin  ouput = flowid.get();
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
			
			throw new NullPointerException(textConvertionServ.search("E109", langcode));

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
		
				throw new PopupException(textConvertionServ.search("E105", langcode));

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
				throw new NullPointerException("please enter correct username");

				
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
				throw new NullPointerException(textConvertionServ.search("E109", langcode));
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
		
		throw new NullPointerException(textConvertionServ.search("E107", langcode));
	
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
	UserLogin userlog=null;//getusername(input.getUsername(), langcode,0);
	
	if(userlog == null) {
		 throw new NullPointerException("Not permit to login");
		
	}

	
	if(! userlog.getUsertokean().equals(input.getTokean())) {
		throw new RedirectException("please enter good tokean");
		
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
String emailRegex = "[a-zA-Z0-9.!#$%&amp;’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)+"; 

String teleRegex = "^[0-9]{1,12}$"; 


public static boolean isValid(String text,String pattern) 
{ 
    Pattern pat = Pattern.compile(pattern); 
    if (text == null) 
        return false; 
    return pat.matcher(text).matches(); 
} 



public UserLogin getusername(HttpServletRequest request,String username,String langcode,Device dev,int login){

	
	UserLogin userlog = null;
	
	if(isValid(username, emailRegex) && configurationServ.getbykey("Login_email").getConfigboolean() == 1) {
	//	email ;
		
	Email em= emailServ.getbyemail(username, langcode);
	
	if(em.getUserloginID().getUserID().getUserstatusID().getUserstatus().equals("Active")) {

		// user is  Active
		
		
	if(em.getDatastatusID().getDstatus().equals("Active")) {
		
		// Email is  Active
		
		try {
		userlog=em.getUserloginID();
		} catch (Exception e) {
			String text= "email : "+username+" is wrong";
			logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 16, langcode," ");
			
			if(login == 2)
			// TODO: handle exception
		    		throw new NooauthException(textConvertionServ.search("E105", langcode));		
			else if(login == 1)
	    		throw new PopupException(textConvertionServ.search("E105", langcode));	
			else
			    throw new RedirectException(textConvertionServ.search("E105", langcode));
		
		}
	}else if(em.getDatastatusID().getDstatus().equals("Blocked"))  {
		
		// Email is  blocked
		
	   	String text= "blocked email : "+username;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 17, langcode," ");			
	
		
			throw new BlockedException(textConvertionServ.search("E106", langcode));					

		
	}else{
		
		// Email is  not Active
		
	   	String text= "check email status : "+username;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 18, langcode," ");	
		
		if(login == 2) 
			throw new NooauthException(textConvertionServ.search("E108", langcode));			
		else if(login == 1) 
			throw new PopupException(textConvertionServ.search("E108", langcode));					
		else
			throw new RedirectException(textConvertionServ.search("E108", langcode));
		
	}
	
	
	
}else if(em.getUserloginID().getUserID().getUserstatusID().getUserstatus().equals("Blocked"))  {
	
	
	String text= "blocked user : "+username;
	logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 19, langcode," ");			

	// user is blocked
	throw new BlockedException(textConvertionServ.search("E106", langcode));					

	
}else{
	
	// user is not Active
	String text= "not Active user : "+username;
	logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 20, langcode," ");			

	
	if(login == 2)
	   		throw new NooauthException(textConvertionServ.search("E107", langcode));	
	else if(login == 1)
   		throw new PopupException(textConvertionServ.search("E107", langcode));	
		else
		throw new RedirectException(textConvertionServ.search("E107", langcode));
	
}

		
	}else if(isValid(username, teleRegex)  && configurationServ.getbykey("login_telephone").getConfigboolean() == 1) {
		//tellphone
		
	Telephones tele=telephonesServ.getbytele(username, langcode);	
	
	
	if(tele.getUserloginID().getUserID().getUserstatusID().getUserstatus().equals("Active")) {
		// User is  Active
	
	if(tele.getDatastatusID().getDstatus().equals("Active")) {
	try {
		userlog=tele.getUserloginID();
	} catch (Exception e) {
		String text= "telephone : "+username+" is wrong";
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 23, langcode," ");
		
		if(login == 2)
			// TODO: handle exception
		    		throw new NooauthException(textConvertionServ.search("E105", langcode));		
			else if(login == 1)
	    		throw new PopupException(textConvertionServ.search("E105", langcode));	
			else
			    throw new RedirectException(textConvertionServ.search("E105", langcode));

	}
	
	}else if(tele.getDatastatusID().getDstatus().equals("Blocked"))  {
		
	 	String text= "blocked telephone : "+username;
			logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 21, langcode," ");
		
			throw new BlockedException(textConvertionServ.search("E106", langcode));					

		
	}else{
		
	   	String text= "check telephone status : "+username;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 22, langcode," ");	
		
		if(login == 2) 
			throw new NooauthException(textConvertionServ.search("E109", langcode));			
		else if(login == 1) 
			throw new PopupException(textConvertionServ.search("E109", langcode));					
		else
			throw new RedirectException(textConvertionServ.search("E109", langcode));
		
	}
	
	}else if(tele.getUserloginID().getUserID().getUserstatusID().getUserstatus().equals("Blocked"))  {
	
		String text= "blocked user : "+username;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 19, langcode," ");	
		
		throw new BlockedException(textConvertionServ.search("E106", langcode));					

		
	}else{
		
		// user is not Active
		String text= "not Active user : "+username;
		logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 20, langcode," ");	
		
		if(login == 2)
	   		throw new NooauthException(textConvertionServ.search("E107", langcode));	
	else if(login == 1)
   		throw new PopupException(textConvertionServ.search("E107", langcode));	
		else
		throw new RedirectException(textConvertionServ.search("E107", langcode));
		
	}	
	
	}else {
		
		if( configurationServ.getbykey("login_username").getConfigboolean() == 1)
		{
		//username login
		try {
		 userlog=getbyusername(username, langcode);
		} catch (Exception e) {
			
			String text= "username : "+username+" is wrong";
			logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 24, langcode," ");
			
			if(login == 2)
				// TODO: handle exception
			    		throw new NooauthException(textConvertionServ.search("E105", langcode));		
				else if(login == 1)
		    		throw new PopupException(textConvertionServ.search("E105", langcode));	
				else
				    throw new RedirectException(textConvertionServ.search("E105", langcode));

		}
		
		if(userlog.getUserID().getUserstatusID().getUserstatus().equals("Active")) {

		
		}else if(userlog.getUserID().getUserstatusID().getUserstatus().equals("Blocked"))  {
			
			String text= "blocked user : "+username;
			logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 17, langcode," ");	
			
			throw new BlockedException(textConvertionServ.search("E106", langcode));					

			
		}else{
			
			// user is not Active
			String text= "not Active user : "+username;
			logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 20, langcode," ");	
			
			if(login == 2)
		   		throw new NooauthException(textConvertionServ.search("E107", langcode));	
		else if(login == 1)
	   		throw new PopupException(textConvertionServ.search("E107", langcode));	
			else
			throw new RedirectException(textConvertionServ.search("E107", langcode));
			
			
		}
		
		}else {
			
			String text= "email or telphone or username : "+username;
			logServ.errorlog(dev.getDeviceip(),request,text, dev, 0, 25, langcode," ");
			
			if(login == 2)
				// TODO: handle exception
			    		throw new NooauthException(textConvertionServ.search("E110", langcode));		
				else if(login == 1)
		    		throw new PopupException(textConvertionServ.search("E110", langcode));	
				else
				    throw new RedirectException(textConvertionServ.search("E110", langcode));

			
		}
		
	}
	
	
	return userlog;
	

}


public Loginobject login(Loginobject input,String langcode) {
	
	
	Loginobject out=new Loginobject();


	UserLogin userlog = null; //getusername(input.getUsername(), langcode,1);
	
	if(userlog == null) {
		 throw new NullPointerException("Not permit to login");
		
	}
	
	
	out.setUsername(userlog.getUsername());
	
    String password = dencryp(input.getPassword());
    
    String origpassword = dencryp(userlog.getPasswordEncy()+userlog.getLoginkey());
        
    System.out.println(password + " "+origpassword);
    
    if(! password.equals(origpassword)) {
    	
    	 throw new NullPointerException("please eneter correct password");
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
		throw new NullPointerException("have a enc problem");
	}

	
	
	
}

public String getkey(String password) {
	try {
	return password.substring(password.length() - 16);
	} catch (Exception e) {
		// TODO: handle exception
		throw new NullPointerException("have a enc problem");
	}
}

public String getencpassword(String password) {
	try {
	return password.substring(0,password.length() - 16);
	} catch (Exception e) {
		// TODO: handle exception
		throw new NullPointerException("have a enc problem");
	}
}

}
