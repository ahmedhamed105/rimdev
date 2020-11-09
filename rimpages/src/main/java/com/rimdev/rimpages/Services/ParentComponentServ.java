package com.rimdev.rimpages.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.rimdev.rimpages.Exception.PopupException;
import com.rimdev.rimpages.Repo.ParentComponentRepo;
import com.rimdev.rimpages.entities.Component;
import com.rimdev.rimpages.entities.ComponentButton;
import com.rimdev.rimpages.entities.ComponentInput;
import com.rimdev.rimpages.entities.ComponentSelect;
import com.rimdev.rimpages.entities.DevicePage;
import com.rimdev.rimpages.entities.ParentComponent;
import com.rimdev.rimpages.outputobject.Component_object;
import com.rimdev.rimpages.outputobject.parent_comp;

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
	
	
	@Autowired
	ConfigurationServ configurationServ;
	

	public List<parent_comp> getbypage(int pageid,String langcode,DevicePage devpage){
		List<parent_comp> coms = new ArrayList<parent_comp>();
		try {
			
			List<ParentComponent> com = (List<ParentComponent>) parentComponentRepo.getbypage(pageid);
			
			
			
			if(com == null || com.size() <= 0) {
				
				throw new PopupException(textConvertionServ.search("E115", langcode));
				
			}

			
			for (ParentComponent component : com) {
				parent_comp a = new parent_comp();
				a.setParent(component);
				List<Component_object>	 select1=new ArrayList<Component_object>();
				
				//System.out.println("parent "+component.getId());
				
				if(component.getComTable() == 1 && component.getComFormid() != null) {
					
					List<Component_object>	 select =componentServ.getbyparent(component.getComFormid(), langcode);
			
					for (Component_object comp : select) {
					if(comp.getButton().getId() != null) {
				
					}else {
						 if(comp.getInput().getId() != null && configurationServ.getlist("input_comp_not_include_table").contains(comp.getInput().getInputtypeID().getId())) {	
							
						 }else {
								select1.add(comp);	
							}
					
					}
				}

					
				}
				
				component.setPcodeTittle(textConvertionServ.search(component.getPcodeTittle(), langcode));
			
					List<Component_object>	 select =componentServ.getbyparent(component.getId(), langcode);
					if(select.size() == 0) {
					
					  a.setChild(select1);
					  coms.add(a);	
						
						continue;
					}else {
						select1.addAll(select);	
						a.setChild(select1);
						coms.add(a);	
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
