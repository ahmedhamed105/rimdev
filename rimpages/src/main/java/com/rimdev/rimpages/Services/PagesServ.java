package com.rimdev.rimpages.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimpages.Exception.NoResultException;
import com.rimdev.rimpages.Exception.NoResultException;
import com.rimdev.rimpages.Repo.DevicePageRepo;
import com.rimdev.rimpages.Repo.PagesRepo;
import com.rimdev.rimpages.entities.Pages;

@Service
public class PagesServ {
	
	@Autowired 
	private PagesRepo pagesRepo;
	
	@Autowired
	DevicePageRepo devicePageRepo;
	
	@Autowired
	ExternalServ textConvertionServ;
	

	
	
public List<Pages> getall(String langcode) {
		
try {	
		return (List<Pages>) pagesRepo.findAll();
} catch (TransientDataAccessException  se) {
	throw new NoResultException(textConvertionServ.search("E400", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NoResultException(textConvertionServ.search("E400", langcode));
}catch (ScriptException  se) {
	throw new NoResultException(textConvertionServ.search("E400", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NoResultException(textConvertionServ.search("E400", langcode));
}
		
	}




public void check_page(String pagename,String langcode) {
	

	try {
		Optional<Pages> flowid =pagesRepo.findbypagename(pagename);
		 
		 if (flowid.isPresent()){
			 flowid.get();
		
			throw new NoResultException(textConvertionServ.search("E105", langcode));
					}
			else{
			   // alternative processing....
				

			}
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }
	

	
}



public Pages getbyid(int id,String langcode) {
	
	try {
		Optional<Pages> flowid =pagesRepo.findById(id);
		
		
		 
		 if (flowid.isPresent()){
			 Pages  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new NoResultException(textConvertionServ.search("E400", langcode));
			}
	} catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }
	

	
}





public void save(Pages input,String langcode) {

	if(input.getPagename() != null) {	
		Date date = new Date();
		input.setDateModify(date);
		input.setDateCreate(date);
		
		try {
			pagesRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E400", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E400", langcode));
	    }catch (ScriptException  se) {
			throw new NoResultException(textConvertionServ.search("E400", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E400", langcode));
	    }
		
	}else {
		
		throw new NoResultException(textConvertionServ.search("E107", langcode));
	
	}

	
	
	
	
}


public void update(Pages old,Pages input,String langcode) {
	
	old.setPagemenu(input.getPagemenu());
	old.setPagename(input.getPagename());
	Date date = new Date();
	old.setDateModify(date);
	
	try {
		pagesRepo.save(old);	
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }
	
	

	
}


public void delete(Pages input,String langcode) {	

	try {
		pagesRepo.delete(input);	
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E400", langcode));
    }
	
	
}


	



}
