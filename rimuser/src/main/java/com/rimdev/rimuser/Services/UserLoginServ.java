package com.rimdev.rimuser.Services;

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

import com.rimdev.rimuser.Exception.BlockedException;
import com.rimdev.rimuser.Exception.NoResultException;
import com.rimdev.rimuser.Exception.NooauthException;
import com.rimdev.rimuser.Exception.NoResultException;
import com.rimdev.rimuser.Exception.RedirectException;
import com.rimdev.rimuser.Repo.UserLoginRepo;
import com.rimdev.rimuser.Utils.AES;
import com.rimdev.rimuser.Utils.Generate;
import com.rimdev.rimuser.entities.Device;
import com.rimdev.rimuser.entities.DevicePage;
import com.rimdev.rimuser.entities.Email;
import com.rimdev.rimuser.entities.Pages;
import com.rimdev.rimuser.entities.Telephones;
import com.rimdev.rimuser.entities.User;
import com.rimdev.rimuser.entities.UserLogin;
import com.rimdev.rimuser.entities.UserStatus;

@Service
public class UserLoginServ {
	
	@Autowired 
	private UserLoginRepo userLoginRepo;
	
	
	@Autowired
	LangExternalServ textConvertionServ;
	
	@Autowired 
	private UserServ userServ;
	
	@Autowired 
	private UserStatusServ userStatusServ;
	
	
	@Autowired
	EmailServ emailServ;
	
	@Autowired
	TelephonesServ telephonesServ;
	
	


public UserLogin getbyid(int id,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 UserLogin  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoResultException(textConvertionServ.search("E108", langcode));
			}
	} catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
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
							throw new NoResultException(textConvertionServ.search("E108", langcode));
				}
				} catch (TransientDataAccessException  se) {
					throw new NoResultException(textConvertionServ.search("E104", langcode));
			    } catch (RecoverableDataAccessException  se) {
					throw new NoResultException(textConvertionServ.search("E104", langcode));
			    }catch (ScriptException  se) {
					throw new NoResultException(textConvertionServ.search("E104", langcode));
			    }catch (NonTransientDataAccessException  se) {
					throw new NoResultException(textConvertionServ.search("E104", langcode));
			    }
		
}
	
	
	
public List<UserLogin> getalllogin(String langcode) {
		try {

		return (List<UserLogin>) userLoginRepo.findAll();
		} catch (TransientDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NoResultException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E104", langcode));
	    }
		
	}


public List<UserLogin> getall(String langcode) {
	try {
	List<UserLogin> loginlist= getalllogin(langcode);
	return loginlist;
	} catch (TransientDataAccessException  se) {
		throw new NoResultException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NoResultException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException("NonTransientDataAccessException");
    }
	
}


public List<UserLogin> getbyuser(String userid,String langcode) {

	try {
		List<UserLogin> loginlistout=new ArrayList<UserLogin>();
		List<UserLogin> loginlist= (List<UserLogin>) userLoginRepo.findbyuserid(userid);
		
		for (UserLogin userLogin : loginlist) {
			userLogin.setPasswordEncy("eneter a new password");
			loginlistout.add(userLogin);
		}
		
		if(loginlist == null || loginlist.size() <= 0) {
			
			throw new NoResultException(textConvertionServ.search("E109", langcode));

		}
		
		return loginlistout;
		
	

	} catch (TransientDataAccessException  se) {
		throw new NoResultException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NoResultException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException("NonTransientDataAccessException");
    }
	


}




public void check_username(String username,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findbyusername(username);
		 
		 if (flowid.isPresent()){
			  flowid.get();
		
				throw new NoResultException(textConvertionServ.search("E105", langcode));

					}
			else{
			   // alternative processing....
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
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
				throw new NoResultException(textConvertionServ.search("E104", langcode));

				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }

	
}


public UserLogin getbyusernametokean(String username,String tokean,String langcode) {
	
	try {
		Optional<UserLogin> flowid =userLoginRepo.findbyusernametokean(username,tokean);
		 
		 if (flowid.isPresent()){
			 UserLogin a= flowid.get();
			 
				return a;

					}
			else{
			   // your device is not authorised
				throw new NoResultException(textConvertionServ.search("E103", langcode));
				
			}
	}  catch (TransientDataAccessException  se) {
		
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E104", langcode));
    }

	
}




}
