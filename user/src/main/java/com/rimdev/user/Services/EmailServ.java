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

import com.rimdev.user.Exception.DuplicationException;
import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.EmailRepo;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserLogin;

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
	
	
public List<Email> getall(String langcode) {
	
List<Email> emails;
	
	try {
		
		emails = (List<Email>) emailRepo.findAll();

	//    throw new NoDataException("no data found in users");

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	
	if(emails == null || emails.size() <= 0) {
		
		throw new NoDataException(textConvertionServ.search("E108", langcode));
		
	}
	
		return emails;
	
		
	}



public void check_email(String email,String langcode) {
	
	try {
		Optional<Email> flowid =emailRepo.findbyemail(email);
		 
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



public Email getbyemail(String email,String langcode) {
	
	try {
		Optional<Email> flowid =emailRepo.findbyemail(email);
		 
		 if (flowid.isPresent()){
			 return	  flowid.get();
		
 
					}
			else{
			   // alternative processing....
				throw new NoDataException(textConvertionServ.search("E109", langcode));
				
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


public Email getbyid(int id,String langcode) {
	
	try {
		Optional<Email> flowid =emailRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Email  ouput = flowid.get();
		
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




public List<Email> getbyuserlogin(int userid,String langcode) {
	

		List<Email> cu;
		try {
			
			cu=(List<Email>) emailRepo.findbyuser(userid);

		} catch (TransientDataAccessException  se) {
			throw new NullPointerException("TransientDataAccessException");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("RecoverableDataAccessException");
	    }catch (ScriptException  se) {
			throw new NullPointerException("ScriptException");
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException("NonTransientDataAccessException");
	    }
		
		if(cu == null || cu.size() <= 0) {
			
			userServ.checkuser(userid, langcode);	
			throw new NoDataException(textConvertionServ.search("E109", langcode));

		}
		

		return cu;
	
}




public String getbyuserloginchar(int userid,String langcode) {
	
String out = "";
		List<Email> cu;
		try {
			
			cu=(List<Email>) emailRepo.findbyuser(userid);

		} catch (TransientDataAccessException  se) {
			throw new NullPointerException("TransientDataAccessException");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("RecoverableDataAccessException");
	    }catch (ScriptException  se) {
			throw new NullPointerException("ScriptException");
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException("NonTransientDataAccessException");
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



public void save(Email input,String langcode) {
	
	
	if(input.getUserloginID() != null || input.getUserloginID().getId() != null) {
		UserLogin  usero =  userLoginServ.getuserlogin(input.getUserloginID().getId(), langcode);
		input.setUserloginID(usero);
		
		Date date = new Date();
		input.setEmailCreate(date);
		input.setEmailModify(date);
		
		try {
			emailRepo.save(input);	
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



public void update(Email input,String langcode) {
	
	if(input.getUserloginID() != null || input.getUserloginID().getId() != null) {
		UserLogin  usero =  userLoginServ.getuserlogin(input.getUserloginID().getId(), langcode);
		input.setUserloginID(usero);
	}
	
	Date date = new Date();
	input.setEmailModify(date);
	
	try {
		emailRepo.save(input);	
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



public void delete(Email input,String langcode) {	
	
	try {
		userLoginServ.checkuserlogin(input.getUserloginID().getId(), langcode);	
		emailRepo.delete(input);	
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



}
