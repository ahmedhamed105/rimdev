package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.ComponentRepo;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.ComponentSelect;
import com.rimdev.user.ouputobject.Component_object;

@Service
public class ComponentServ {
	
	@Autowired
	ComponentRepo componentRepo; 
	
	@Autowired
	ComponentSelectServ  componentSelectServ;
	
	
	
	public List<Component_object> getbypage(int pageid){
		List<Component_object> coms = new ArrayList<Component_object>();
		try {
			
			List<Component> com = (List<Component>) componentRepo.getbypage(pageid);
			
			
			
			if(com == null || com.size() <= 0) {
				
				throw new NoDataException("E108");
				
			}
			
			for (Component component : com) {
				Component_object a = new Component_object();
				ComponentSelect select =componentSelectServ.getbycomponent(component.getId()).get(0);
				a.setComp(component);
				a.setSelect(select);
				coms.add(a);
				
			}

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
		
		if(coms == null || coms.size() <= 0) {
			
			throw new NoDataException("E108");
			
		}
		
			return coms;
		
		
	}

}
