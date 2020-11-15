package com.rimdev.rimlang.Services;

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

import com.rimdev.rimlang.Exception.PopupException;
import com.rimdev.rimlang.Repo.EmailRepo;
import com.rimdev.rimlang.entities.Email;
import com.rimdev.rimlang.entities.User;
import com.rimdev.rimlang.entities.UserLogin;
import com.rimdev.rimlang.outputobject.Emailobj;

@Service
public class EmailServ {

	@Autowired
	private EmailRepo emailRepo;
	
	@Autowired 
	private UserServ userServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	@Autowired
	UserLoginServ userLoginServ;
	
	
public List<Emailobj> getall(String langcode) {
	List<Emailobj> out= new ArrayList<Emailobj>();
	List<Email>	emails; 
	try {
		
	emails = (List<Email>) emailRepo.findAll();
   if(emails == null || emails.size() <= 0) {
		
		throw new PopupException(textConvertionServ.search("E108", langcode));
		
	}
   
   for (Email email : emails) {
	   Emailobj em=new Emailobj();
	   em.setEmail(email);
	   em.setUserid(email.getUserloginID().getUserID().getUseridnumber());
	   out.add(em);
}
	

	//    throw new PopupException("no data found in users");

	} catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
	
	
	
		return out;
	
		
	}



public void check_email(String email,String langcode) {
	
	try {
		Optional<Email> flowid =emailRepo.findbyemail(email);
		 
		 if (flowid.isPresent()){
			  flowid.get();
		
				throw new PopupException(textConvertionServ.search("E105", langcode));

					}
			else{
			   // alternative processing....
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }

	
}



public Email getbyemail(String email,String langcode) {
	
	try {
		Optional<Email> flowid =emailRepo.findbyemail(email);
		 
		 if (flowid.isPresent()){
			 return	  flowid.get();
		
 
					}
			else{
			   // alternative processing....
				throw new PopupException(textConvertionServ.search("E109", langcode));
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }

	
}


public Email getbyid(int id,String langcode) {
	
	try {
		Optional<Email> flowid =emailRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Email  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new PopupException(textConvertionServ.search("E109", langcode));
			}
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




public List<Email> getbyuserlogin(String userid,String langcode) {
	

		List<Email> cu = null;
		try {
			
		//	cu=(List<Email>) emailRepo.findbyuser(userid);

		} catch (TransientDataAccessException  se) {
			throw new PopupException("TransientDataAccessException");
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException("RecoverableDataAccessException");
	    }catch (ScriptException  se) {
			throw new PopupException("ScriptException");
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException("NonTransientDataAccessException");
	    }
		
		if(cu == null || cu.size() <= 0) {
			
			//userServ.checkuser(userid, langcode);	
			throw new PopupException(textConvertionServ.search("E109", langcode));

		}
		

		return cu;
	
}




public String getbyuserloginchar(int userid,String langcode) {
	
String out = "";
		List<Email> cu;
		try {
			
			cu=(List<Email>) emailRepo.findbyuser(userid);

		} catch (TransientDataAccessException  se) {
			throw new PopupException("TransientDataAccessException");
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException("RecoverableDataAccessException");
	    }catch (ScriptException  se) {
			throw new PopupException("ScriptException");
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException("NonTransientDataAccessException");
	    }
		
		if(cu == null || cu.size() <= 0) {
	   return "no emails";

		}else {
			
			for (Email email : cu) {
				out = out+","+email.getEmailuser();
			}
			
			
			return out.substring(1);
		}
		

		
	
}



public void save(Emailobj input,String langcode) {
	List<UserLogin>  usero=	userLoginServ.getbyuserid(input.getUserid(), langcode);
	if(usero != null) {
		
		Date date = new Date();
	for (UserLogin userLogin : usero) {
		
		Email em=new Email();
		em =input.getEmail();
		em.setUserloginID(userLogin);
		em.setEmailCreate(date);
		em.setEmailModify(date);
		System.out.println(em.getEmailuser());
		try {
			emailRepo.save(em);	
		} catch (TransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }
		
	}
	}else {
		
		throw new PopupException("error while insertion");
	
	}

	
}



public void update(Email old,Emailobj input,String langcode) {
	
	List<UserLogin>  usero=	userLoginServ.getbyuserid(input.getUserid(), langcode);
	System.out.println(usero.size());
	if(usero != null) {
		
		Date date = new Date();
	for (UserLogin userLogin : usero) {
		
		Email em=new Email();
		em =old;
		em.setDatastatusID(input.getEmail().getDatastatusID());
		em.setEmailPrimary(input.getEmail().getEmailPrimary());
		em.setEmailuser(input.getEmail().getEmailuser());
		em.setUserloginID(userLogin);
		em.setEmailModify(date);
		try {
			emailRepo.save(em);	
		} catch (TransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }
		
	}
	}else {
		
		throw new PopupException("error while insertion");
	
	}
	
}



public void delete(Email input,String langcode) {	
	
	try {
		emailRepo.delete(input);	
	}  catch (TransientDataAccessException  se) {
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
