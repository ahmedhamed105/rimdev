package com.rimdev.rimlog.Services;


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

import com.rimdev.rimlog.Exception.PopupException;
import com.rimdev.rimlog.Repo.TelephonesRepo;
import com.rimdev.rimlog.entities.Email;
import com.rimdev.rimlog.entities.Telephones;
import com.rimdev.rimlog.entities.User;
import com.rimdev.rimlog.entities.UserLogin;
import com.rimdev.rimlog.outputobject.Emailobj;

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
	
	
public List<Emailobj> getall(String langcode) {
	List<Emailobj> out= new ArrayList<Emailobj>();
	List<Telephones> teles;
	
	try {
		
		teles = (List<Telephones>) telephonesRepo.findAll();
		
		if(teles == null || teles.size() <= 0) {
			
			throw new PopupException(textConvertionServ.search("E108", langcode));
			
		}
	   
	   for (Telephones tele : teles) {
		   Emailobj em=new Emailobj();
		   em.setTele(tele);
		   em.setUserid(tele.getUserloginID().getUserID().getUseridnumber());
		   out.add(em);
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
	

	
		return out;
		
	}




public void check_tele(String tele,String langcode) {
	

	try {
		Optional<Telephones> flowid =telephonesRepo.findbytele(tele);
		 
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




public Telephones getbytele(String tele,String langcode) {
	

	try {
		Optional<Telephones> flowid =telephonesRepo.findbytele(tele);
		 
		 if (flowid.isPresent()){
			return flowid.get();
		
					}
			else{
			   // alternative processing....
				throw new PopupException(textConvertionServ.search("E107", langcode));


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


public Telephones getbyid(int id,String langcode) {
	
	try {
		Optional<Telephones> flowid =telephonesRepo.findById(id);
		
		
		 
		 if (flowid.isPresent()){
			 Telephones  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new PopupException(textConvertionServ.search("E107", langcode));
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



public List<Telephones> getbyuserlogin(int userid,String langcode) {
	List<Telephones> cu;
	try {
		
		cu=(List<Telephones>) telephonesRepo.findbyuser(userid);

	} catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
	
	if(cu == null || cu.size() <= 0) {
		
		userServ.checkuser(userid, langcode);	
		throw new PopupException(textConvertionServ.search("E107", langcode));

	}
	

	return cu;
}





public String getbyuserloginchar(int userid,String langcode) {
	String out = "";
	List<Telephones> cu;
	try {
		
		cu=(List<Telephones>) telephonesRepo.findbyuser(userid);

	} catch (TransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new PopupException(textConvertionServ.search("E104", langcode));
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



public void save(Emailobj input,String langcode) {
	List<UserLogin>  usero=	userLoginServ.getbyuserid(input.getUserid(), langcode);
	if(usero != null) {
		
		Date date = new Date();
	for (UserLogin userLogin : usero) {
		
		Telephones em=new Telephones();
		em =input.getTele();
		em.setUserloginID(userLogin);
		em.setTeleCreate(date);
		em.setTeleModify(date);
		try {
			telephonesRepo.save(em);	
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


public void update(Telephones old,Emailobj input,String langcode) {
	
	List<UserLogin>  usero=	userLoginServ.getbyuserid(input.getUserid(), langcode);
	if(usero != null) {
		
		Date date = new Date();
	for (UserLogin userLogin : usero) {
		
		Telephones em=new Telephones();
		em =old;
		em.setDatastatusID(input.getTele().getDatastatusID());
		em.setTelePrimary(input.getTele().getTelePrimary());
		em.setPhoneNo(input.getTele().getPhoneNo());
		em.setUserloginID(userLogin);
		em.setTeleModify(date);
		try {
			telephonesRepo.save(em);	
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



public void delete(Telephones input,String langcode) {	
	
	try {
		telephonesRepo.delete(input);	
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
