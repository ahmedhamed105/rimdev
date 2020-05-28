package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Repo.ComponentRepo;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.ComponentButton;
import com.rimdev.user.entities.ComponentFile;
import com.rimdev.user.entities.ComponentInput;
import com.rimdev.user.entities.ComponentSelect;
import com.rimdev.user.entities.FileType;
import com.rimdev.user.ouputobject.Component_object;

@Service
public class ComponentServ {
	
	@Autowired
	ComponentRepo componentRepo; 
	
	@Autowired
	ComponentSelectServ  componentSelectServ;
	
	@Autowired
	ComponentInputServ componentInputServ;
	
	@Autowired
	ComponentButtonServ componentButtonServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	ComponentFileServ componentFileServ;
	

	
	
	
	
	
	public List<Component_object> getbyparent(int parentid,String langcode){
		List<Component_object> coms = new ArrayList<Component_object>();
		try {
			
			List<Component> com = (List<Component>) componentRepo.getbyparent(parentid);
			
			
			
			if(com == null || com.size() <= 0) {
				
				throw new PopupException(textConvertionServ.search("E116", langcode));
				
			}
			
			for (Component component : com) {
				Component_object a = new Component_object();
				ComponentSelect select = new ComponentSelect();
				ComponentInput input=new ComponentInput();
				ComponentButton button=new ComponentButton();
				List<FileType> filetype=new ArrayList<>();
				
				
				component.setCcode(textConvertionServ.search(component.getCcode(), langcode));
				
				try {
					 select =componentSelectServ.getbycomponent(component.getId(), langcode).get(0);

				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					 input =componentInputServ.getbycomponent(component.getId(), langcode).get(0);
					 List<ComponentFile> files =componentFileServ.getbyinputcomp(input, langcode);
				
				if(files.size() > 0) {
					for (ComponentFile componentFile : files) {
						filetype.add(componentFile.getFiletypeID());
					}
					
				}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					button =componentButtonServ.getbycomponent(component.getId(), langcode).get(0);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			

				a.setComp(component);
				a.setSelect(select);
				a.setInput(input);
				a.setButton(button);
				a.setAllowfiletype(filetype);
				coms.add(a);
				
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
		
		if(coms == null || coms.size() <= 0) {
			
			throw new NullPointerException(textConvertionServ.search("E109", langcode));
			
		}
		
			return coms;
		
		
	}
	
	
	
	public Component getComponentbyid(int id,String langcode) {
		
		try {
			Optional<Component> flowid =componentRepo.findById(id);
			 
			 if (flowid.isPresent()){
				 Component  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					throw new NullPointerException(textConvertionServ.search("E108", langcode));
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

}
