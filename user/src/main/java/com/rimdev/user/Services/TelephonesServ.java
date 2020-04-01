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
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserLogin;

@Service
public class TelephonesServ {
	
	@Autowired 
	private TelephonesRepo telephonesRepo;

	@Autowired 
	private UserServ userServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	@Autowired
	UserLoginServ userLoginServ;
	
	
public List<Telephones> getall(String langcode) {
	
	List<Telephones> teles;
	
	try {
		
		teles = (List<Telephones>) telephonesRepo.findAll();

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
	
	if(teles == null || teles.size() <= 0) {
		
		throw new NoDataException(textConvertionServ.search("E108", langcode));
		
	}
	
		return teles;
		
	}




public void check_tele(String tele,String langcode) {
	

	try {
		Optional<Telephones> flowid =telephonesRepo.findbytele(tele);
		 
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




public Telephones getbytele(String tele,String langcode) {
	

	try {
		Optional<Telephones> flowid =telephonesRepo.findbytele(tele);
		 
		 if (flowid.isPresent()){
			return flowid.get();
		
					}
			else{
			   // alternative processing....
				throw new NoDataException(textConvertionServ.search("E107", langcode));


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


public Telephones getbyid(int id,String langcode) {
	
	try {
		Optional<Telephones> flowid =telephonesRepo.findById(id);
		
		
		 
		 if (flowid.isPresent()){
			 Telephones  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoDataException(textConvertionServ.search("E107", langcode));
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



public List<Telephones> getbyuserlogin(int userid,String langcode) {
	List<Telephones> cu;
	try {
		
		cu=(List<Telephones>) telephonesRepo.findbyuser(userid);

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	
	if(cu == null || cu.size() <= 0) {
		
		userServ.checkuser(userid, langcode);	
		throw new NoDataException(textConvertionServ.search("E107", langcode));

	}
	

	return cu;
}





public String getbyuserloginchar(int userid,String langcode) {
	String out = "";
	List<Telephones> cu;
	try {
		
		cu=(List<Telephones>) telephonesRepo.findbyuser(userid);

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	
	if(cu == null || cu.size() <= 0) {
		   return "no telephone";

			}else {
				
				for (Telephones email : cu) {
					out = out+","+email.getPhoneNo();
				}
				
				
				return out.substring(1);
			}
	
}



public void save(Telephones input,String langcode) {

	if(input.getUserloginID() != null || input.getUserloginID().getId() != null) {
		UserLogin  usero = userLoginServ.getuserlogin(input.getUserloginID().getId(), langcode);
		input.setUserloginID(usero);
		
		Date date = new Date();
		input.setTeleCreate(date);
		input.setTeleModify(date);
		
		try {
			telephonesRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
		
	}else {
		
		throw new NoDataException(textConvertionServ.search("E107", langcode));
	
	}

	
	
	
	
}


public void update(Telephones input,String langcode) {
	
	if(input.getUserloginID() != null || input.getUserloginID().getId() != null) {
		UserLogin  usero = userLoginServ.getuserlogin(input.getUserloginID().getId(), langcode);
		input.setUserloginID(usero);
	}
	
	Date date = new Date();
	input.setTeleModify(date);
	
	try {
		telephonesRepo.save(input);	
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


public void delete(Telephones input,String langcode) {	

	try {
		userLoginServ.checkuserlogin(input.getUserloginID().getId(), langcode);	
		telephonesRepo.delete(input);	
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
