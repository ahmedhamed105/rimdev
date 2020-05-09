package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Repo.ParentComponentRepo;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.ComponentButton;
import com.rimdev.user.entities.ComponentInput;
import com.rimdev.user.entities.ComponentSelect;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.ParentComponent;
import com.rimdev.user.ouputobject.Component_object;
import com.rimdev.user.ouputobject.parent_comp;

@Service
public class ParentComponentServ {
	@Autowired
	ComponentServ componentServ;
	
   @Autowired
   ParentComponentRepo parentComponentRepo;
   
   
   @Autowired
   ParentMenuServ parentMenuServ;
   
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	@Autowired
	GroupParentServ groupParentServ;
	

	public List<parent_comp> getbypage(int pageid,String langcode,DevicePage devpage){
		List<parent_comp> coms = new ArrayList<parent_comp>();
		try {
			
			List<ParentComponent> com = (List<ParentComponent>) parentComponentRepo.getbypage(pageid);
			
			
			
			if(com == null || com.size() <= 0) {
				
				throw new PopupException(textConvertionServ.search("E115", langcode));
				
			}
			
			for (ParentComponent component : com) {
				
				
				component.setPcodeTittle(textConvertionServ.search(component.getPcodeTittle(), langcode));
				
				parent_comp a = new parent_comp();
			
					List<Component_object>	 select =componentServ.getbyparent(component.getId(), langcode);
					if(select.size() > 0 ) {
						a.setParent(component);
						a.setChild(select);
						coms.add(a);							
					}else {
						if(!component.getParentType().equals("form")) {
							a.setParent(component);
					//		a.setChild(select);
							coms.add(a);									
						}
						
					}
				
	
				
			}

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
		
		coms = groupParentServ.check_priviledge(coms,devpage,langcode);
		
		if(coms == null || coms.size() <= 0) {
			
			throw new PopupException(textConvertionServ.search("E117", langcode));
			
		}
		
		
		
			return coms;
		
		
	}

}
