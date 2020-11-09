package com.rimdev.rimlog.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimlog.Exception.PopupException;
import com.rimdev.rimlog.Repo.ComponentRepo;
import com.rimdev.rimlog.entities.Component;
import com.rimdev.rimlog.entities.ComponentButton;
import com.rimdev.rimlog.entities.ComponentFile;
import com.rimdev.rimlog.entities.ComponentInput;
import com.rimdev.rimlog.entities.ComponentSelect;
import com.rimdev.rimlog.entities.FileType;
import com.rimdev.rimlog.entities.RelationComp;
import com.rimdev.rimlog.entities.RelationType;
import com.rimdev.rimlog.outputobject.Component_object;

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
	
	@Autowired
	RelationCompServ relationCompServ;
	

	
	
	
	
	
	public List<Component_object> getbyparent(int parentid,String langcode){
		List<Component_object> coms = new ArrayList<Component_object>();
		try {
			
			List<Component> com = (List<Component>) componentRepo.getbyparent(parentid);
			
			
			
			if(com == null || com.size() <= 0) {
				
				return new ArrayList<>();
				
			}
			
			for (Component component : com) {
				Component_object a = new Component_object();
				ComponentSelect select = new ComponentSelect();
				ComponentInput input=new ComponentInput();
				ComponentButton button=new ComponentButton();
				List<FileType> filetype=new ArrayList<>();
				List<RelationComp> rel=new ArrayList<>();
				
				
				component.setCcode(textConvertionServ.search(component.getCcode(), langcode));
				if(component.getRequiredError() != null) {
					component.setRequiredError(textConvertionServ.search(component.getRequiredError(), langcode));
				}
				if(component.getPaternError() != null) {
				 component.setPaternError(textConvertionServ.search(component.getPaternError(), langcode));	
				}
				
				try {
					rel=relationCompServ.getbycomponent(component.getId(), langcode);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				

				try {
					 select =componentSelectServ.getbycomponent(component.getId(), langcode).get(0);

				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					 input =componentInputServ.getbycomponent(component.getId(), langcode).get(0);
					 List<ComponentFile> files =componentFileServ.getbyinputcomp(input, langcode);
				if(input.getFileSizeerr() != null) {
					input.setFileSizeerr(textConvertionServ.search(input.getFileSizeerr(), langcode));	
				}
				if(input.getFileCounterr() != null) {
					input.setFileCounterr(textConvertionServ.search(input.getFileCounterr(), langcode));	
				}
				if(input.getFileTypeerror() != null) {
					input.setFileTypeerror(textConvertionServ.search(input.getFileTypeerror(), langcode));	
				}
				
				
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
				a.setRel(rel);
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
