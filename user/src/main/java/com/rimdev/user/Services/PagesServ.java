package com.rimdev.user.Services;

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

import com.rimdev.user.Exception.DuplicationException;
import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.DevicePageRepo;
import com.rimdev.user.Repo.PagesRepo;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.ouputobject.pagesdevice;

@Service
public class PagesServ {
	
	@Autowired 
	private PagesRepo pagesRepo;
	
	@Autowired
	DevicePageRepo devicePageRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public List<Pages> getall(String langcode) {
		
try {	
		return (List<Pages>) pagesRepo.findAll();
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




public void check_page(String pagename,String langcode) {
	

	try {
		Optional<Pages> flowid =pagesRepo.findbypagename(pagename);
		 
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



public Pages getbyid(int id,String langcode) {
	
	try {
		Optional<Pages> flowid =pagesRepo.findById(id);
		
		
		 
		 if (flowid.isPresent()){
			 Pages  ouput = flowid.get();
		
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





public void save(Pages input,String langcode) {

	if(input.getPagename() != null) {	
		Date date = new Date();
		input.setDateModify(date);
		input.setDateCreate(date);
		
		try {
			pagesRepo.save(input);	
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


public void update(Pages input,String langcode) {
	

	Date date = new Date();
	input.setDateModify(date);
	
	
	try {
		pagesRepo.save(input);	
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


public void delete(Pages input,String langcode) {	

	try {
		pagesRepo.delete(input);	
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


	

	
public List<pagesdevice> getpagesbydevice(int id) {
	
	List<DevicePage> p= (List<DevicePage>)devicePageRepo.findbydeviceid(id);
	List<pagesdevice> c= new ArrayList<pagesdevice>();
	for(DevicePage dev:p) {
		pagesdevice pa=new pagesdevice();
		pa.setPage_name(dev.getPagesID().getPagename());
		pa.setPage_Date(dev.getVisittime());
		c.add(pa);
		
	}
		
		return c;
		
	}


public void savedevpag(Device dev,Pages pa) {
	Date visittime = new Date();
	DevicePage a=new DevicePage();
	a.setDeviceId(dev);
	a.setPagesID(pa);
	a.setVisittime(visittime);
	devicePageRepo.save(a);
	
}


public Pages getbyid(int id) {
	
	
	try {
		Optional<Pages> flowid =pagesRepo.findbyid(id);
		 
		 if (flowid.isPresent()){
			 Pages  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
	

	
	
	
}

}
