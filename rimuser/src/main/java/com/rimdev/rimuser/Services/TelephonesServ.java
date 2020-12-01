package com.rimdev.rimuser.Services;


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

import com.rimdev.rimuser.Exception.PopupException;
import com.rimdev.rimuser.Repo.TelephonesRepo;
import com.rimdev.rimuser.entities.Email;
import com.rimdev.rimuser.entities.Telephones;
import com.rimdev.rimuser.entities.User;
import com.rimdev.rimuser.entities.UserLogin;

@Service
public class TelephonesServ {
	
	@Autowired 
	private TelephonesRepo telephonesRepo;

	@Autowired 
	private UserServ userServ;
	
	@Autowired
	LangExternalServ textConvertionServ;
	
	
	@Autowired
	UserLoginServ userLoginServ;
	
	

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




}
