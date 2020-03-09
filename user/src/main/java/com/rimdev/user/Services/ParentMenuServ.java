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
	
	
	public List<menu_object> getallmenus() {
		
		List<menu_object> menu=new ArrayList<menu_object>();
		
		List<ParentMenu> com = (List<ParentMenu>) parentMenuRepo.findAll();

		if(com == null || com.size() <= 0) {
			
			throw new NoDataException("E109");
			
		}
		
		for (ParentMenu pmenu : com) {
			menu_object mm= new menu_object();
			
		 List<MenuDisplay> a=	menuDisplayServ.getbycomponent(pmenu.getId());
		 mm.setChild(a);
		 mm.setParent(pmenu);
		 menu.add(mm);
			
			
		}
		
		return menu;
		
	}
	
	
	
public menuparsub getmenus(String type,int menuid) {
	
	menuparsub a= new menuparsub();

		if(type.equals("P")) {
			ParentMenu pa = parentMenuRepo.findById(menuid).get();
			a.setParent(pa);
		}
		if(type.equals("C")) {
			MenuDisplay pa = menuDisplayRepo.findById(menuid).get();
			a.setParent(pa.getParentmenuID());
			a.setChild(pa);		
		}
		
return a;
}

}
