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
		throw new NullPointerException("E104");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("E104");
    }catch (ScriptException  se) {
		throw new NullPointerException("E104");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("E104");
    }
	
	if(emails == null || emails.size() <= 0) {
		
		throw new NoDataException("E108");
		
	}
	
		return emails;
	
		
	}



public void check_email(String email) {
	
	try {
		Optional<Email> flowid =emailRepo.findbyemail(email);
		 
		 if (flowid.isPresent()){
			  flowid.get();
		
				throw new DuplicationException("E105");

					}
			else{
			   // alternative processing....
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException("E104");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("E104");
    }catch (ScriptException  se) {
		throw new NullPointerException("E104");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("E104");
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
				throw new NoDataException("E109");
			}
	} catch (TransientDataAccessException  se) {
		throw new NullPointerException("E104");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("E104");
    }catch (ScriptException  se) {
		throw new NullPointerException("E104");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("E104");
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
			throw new NoDataException("E109");

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
			throw new NullPointerException("E104");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("E104");
	    }catch (ScriptException  se) {
			throw new NullPointerException("E104");
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new NullPointerException("E104");
	    }
		
	}else {
		
		throw new NoDataException("E107");
	
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
		throw new NullPointerException("E104");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("E104");
    }catch (ScriptException  se) {
		throw new NullPointerException("E104");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("E104");
    }
	
	
}



public void delete(Email input) {	
	
	try {
		userServ.checkuser(input.getUserID().getId());	
		emailRepo.delete(input);	
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException("E104");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("E104");
    }catch (ScriptException  se) {
		throw new NullPointerException("E104");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("E104");
    }
	
	
}



}
