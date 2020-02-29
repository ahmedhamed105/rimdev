package com.rimdev.user.Services;

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
import com.rimdev.user.Repo.EmailRepo;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.User;

@Service
public class EmailServ {

	@Autowired
	private EmailRepo emailRepo;
	
	@Autowired 
	private UserServ userServ;
	
	
public List<Email> getall() {
	
List<Email> emails;
	
	try {
		
		emails = (List<Email>) emailRepo.findAll();

	//    throw new NoDataException("no data found in users");

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NullPointerException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("NonTransientDataAccessException");
    }
	
	if(emails == null || emails.size() <= 0) {
		
		throw new NoDataException("no data found in "+ this.getClass().getName());
		
	}
	
		return emails;
	
		
	}



public void check_email(String email) {
	
	try {
		Optional<Email> flowid =emailRepo.findbyemail(email);
		 
		 if (flowid.isPresent()){
			 Email  ouput = flowid.get();
		
				throw new DuplicationException("Email duplicate");

					}
			else{
			   // alternative processing....
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NullPointerException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("NonTransientDataAccessException");
    }

	
}


public Email getbyid(int id) {
	
	try {
		Optional<Email> flowid =emailRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Email  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoDataException("no Email found in "+ this.getClass().getName());
			}
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




public List<Email> getbyuser(int userid) {
	

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
			
			userServ.checkuser(userid);	
			throw new NoDataException("no Email found in "+ this.getClass().getName());

		}
		

		return cu;
	
}





public void save(Email input) {
	
	
	if(input.getUserID() != null || input.getUserID().getId() != null) {
		User  usero = userServ.getuser(input.getUserID().getId());
		input.setUserID(usero);
		
		Date date = new Date();
		input.setEmailCreate(date);
		input.setEmailModify(date);
		
		try {
			emailRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException("TransientDataAccessException");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("RecoverableDataAccessException");
	    }catch (ScriptException  se) {
			throw new NullPointerException("ScriptException");
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new NullPointerException("NonTransientDataAccessException");
	    }
		
	}else {
		
		throw new NoDataException("No User found");
	
	}
	


	
}



public void update(Email input) {
	
	if(input.getUserID() != null || input.getUserID().getId() != null) {
		User  usero = userServ.getuser(input.getUserID().getId());
		input.setUserID(usero);
	}
	
	Date date = new Date();
	input.setEmailModify(date);
	
	try {
		emailRepo.save(input);	
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NullPointerException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("NonTransientDataAccessException");
    }
	
	
}



public void delete(Email input) {	
	
	try {
		userServ.checkuser(input.getUserID().getId());	
		emailRepo.delete(input);	
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NullPointerException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("NonTransientDataAccessException");
    }
	
	
}



}
