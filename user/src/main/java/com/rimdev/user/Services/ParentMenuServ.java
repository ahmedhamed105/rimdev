package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.MenuDisplayRepo;
import com.rimdev.user.Repo.ParentMenuRepo;
import com.rimdev.user.entities.MenuDisplay;
import com.rimdev.user.entities.ParentComponent;
import com.rimdev.user.entities.ParentMenu;
import com.rimdev.user.ouputobject.menu_object;
import com.rimdev.user.ouputobject.menuparsub;

@Service
public class ParentMenuServ {
	
	@Autowired
	ParentMenuRepo parentMenuRepo;
	
	@Autowired
	MenuDisplayRepo menuDisplayRepo;
	
	@Autowired
	MenuDisplayServ menuDisplayServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	public List<menu_object> getallmenus(String langcode) {
		
		List<menu_object> menu=new ArrayList<menu_object>();
		
		List<ParentMenu> com = (List<ParentMenu>) parentMenuRepo.findAll();

		if(com == null || com.size() <= 0) {
			
			throw new NoDataException(textConvertionServ.search("E109", langcode));
			
		}
		
		for (ParentMenu pmenu : com) {
			menu_object mm= new menu_object();
		
	     pmenu.setPmenu(textConvertionServ.search(pmenu.getPmenu(), langcode));	
		 List<MenuDisplay> a=	menuDisplayServ.getbycomponent(pmenu.getId(),langcode);
		 mm.setChild(a);
		 mm.setParent(pmenu);
		 menu.add(mm);
			
			
		}
		
		return menu;
		
	}
	
	
	
public menuparsub getmenus(String type,int menuid,String langcode) {
	
	menuparsub a= new menuparsub();

		if(type.equals("P")) {
			ParentMenu pa = parentMenuRepo.findById(menuid).get();
			pa.setPmenu(textConvertionServ.search(pa.getPmenu(), langcode));	
			a.setParent(pa);
		}
		if(type.equals("C")) {
			MenuDisplay pa = menuDisplayRepo.findById(menuid).get();
			pa.getParentmenuID().setPmenu(textConvertionServ.search(pa.getParentmenuID().getPmenu(), langcode));	
			a.setParent(pa.getParentmenuID());
			pa.setMenuname(textConvertionServ.search(pa.getMenuname(), langcode));	

			a.setChild(pa);		
		}
		
return a;
}

}
