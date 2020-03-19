package com.rimdev.accounting.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Exception.NoDataException;
import com.rimdev.accounting.Repo.FlowTypeRepo;

@Service
public class FlowTypeServ {
	
	@Autowired 
	private FlowTypeRepo flowTypeRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public List<FlowType> getall(String langcode) {
		try {
		return (List<FlowType>) flowTypeRepo.findAll();
} catch (TransientDataAccessException  se) {
	se.printStackTrace();
	throw new NoDataException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	se.printStackTrace();
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	se.printStackTrace();
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	se.printStackTrace();
	throw new NoDataException(textConvertionServ.search("E104", langcode));
}
		
	}





public FlowType getflow(int id,String langcode) {
	
	try {
		Optional<FlowType> flowid =flowTypeRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 FlowType  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }
}


	
public FlowType Save(FlowType input,String langcode){
	
	try {	
		
		Date date = new Date();
		input.setCreateDate(date);
		input.setEffectiveDate(date);
		FlowType ouput =flowTypeRepo.save(input);	
		return ouput;
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }
	}


public FlowType update(FlowType input,String langcode)  {
	
	
	try {	
		Date date = new Date();
		input.setEffectiveDate(date);
		FlowType ouput1 =flowTypeRepo.save(input);	
		return ouput1;
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
		throw new NoDataException(textConvertionServ.search("E104", langcode));
    }
	
	
	
}


}
