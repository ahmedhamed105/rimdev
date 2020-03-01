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
import com.rimdev.user.Repo.TelephonesRepo;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.User;

@Service
public class TelephonesServ {
	
	@Autowired 
	private TelephonesRepo telephonesRepo;

	@Autowired 
	private UserServ userServ;
	
	
public List<Telephones> getall() {
	
	List<Telephones> teles;
	
	try {
		
		teles = (List<Telephones>) telephonesRepo.findAll();

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
	
	if(teles == null || teles.size() <= 0) {
		
		throw new NoDataException("E108");
		
	}
	
		return teles;
		
	}




public void check_tele(String tele) {
	

	try {
		Optional<Telephones> flowid =telephonesRepo.findbytele(tele);
		 
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


public Telephones getbyid(int id) {
	
	try {
		Optional<Telephones> flowid =telephonesRepo.findById(id);
		
		
		 
		 if (flowid.isPresent()){
			 Telephones  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoDataException("E107");
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



public List<Telephones> getbyuser(int userid) {
	List<Telephones> cu;
	try {
		
		cu=(List<Telephones>) telephonesRepo.findbyuser(userid);

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException("E104");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("E104");
    }catch (ScriptException  se) {
		throw new NullPointerException("E104");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("E104");
    }
	
	if(cu == null || cu.size() <= 0) {
		
		userServ.checkuser(userid);	
		throw new NoDataException("E107");

	}
	

	return cu;
}




public void save(Telephones input) {

	if(input.getUserID() != null || input.getUserID().getId() != null) {
		User  usero = userServ.getuser(input.getUserID().getId());
		input.setUserID(usero);
		
		Date date = new Date();
		input.setTeleCreate(date);
		input.setTeleModify(date);
		
		try {
			telephonesRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException("E104");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("E104");
	    }catch (ScriptException  se) {
			throw new NullPointerException("E104");
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException("E104");
	    }
		
	}else {
		
		throw new NoDataException("E107");
	
	}

	
	
	
	
}


public void update(Telephones input) {
	
	if(input.getUserID() != null || input.getUserID().getId() != null) {
		User  usero = userServ.getuser(input.getUserID().getId());
		input.setUserID(usero);
	}
	
	Date date = new Date();
	input.setTeleModify(date);
	
	try {
		telephonesRepo.save(input);	
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


public void delete(Telephones input) {	

	try {
		userServ.checkuser(input.getUserID().getId());	
		telephonesRepo.delete(input);	
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
