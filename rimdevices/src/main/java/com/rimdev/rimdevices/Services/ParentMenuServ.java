package com.rimdev.rimdevices.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimdevices.Exception.PopupException;
import com.rimdev.rimdevices.Repo.MenuDisplayRepo;
import com.rimdev.rimdevices.Repo.ParentMenuRepo;
import com.rimdev.rimdevices.entities.DevicePage;
import com.rimdev.rimdevices.entities.MenuDisplay;
import com.rimdev.rimdevices.entities.ParentComponent;
import com.rimdev.rimdevices.entities.ParentMenu;
import com.rimdev.rimdevices.outputobject.menu_object;
import com.rimdev.rimdevices.outputobject.menuparsub;

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
	
	
	@Autowired
	GroupPagesServ GroupPagesServ;
	
	
	public List<menu_object> getallmenus(String langcode, DevicePage devpage) {
		
		List<menu_object> menu=new ArrayList<menu_object>();
		
		List<ParentMenu> com = (List<ParentMenu>) parentMenuRepo.findAll();

		if(com == null || com.size() <= 0) {
			
			throw new PopupException(textConvertionServ.search("E109", langcode));
			
		}
		
		for (ParentMenu pmenu : com) {
			
			menu_object mm= new menu_object();
	
	      boolean valid = GroupPagesServ.check_menu(pmenu.getPagesID(), devpage, langcode);
			
	
		
	     pmenu.setPmenu(textConvertionServ.search(pmenu.getPmenu(), langcode));	
		 List<MenuDisplay> menus=	menuDisplayServ.getbycomponent(pmenu.getId(),langcode);
		 List<MenuDisplay> menusout= new ArrayList<MenuDisplay>();
		 int count=0;
		 
		 for (MenuDisplay childs : menus) {
			 boolean valid1 = GroupPagesServ.check_menu(childs.getPagesID(), devpage, langcode);
				if(!valid1) {
					count ++;
					childs.setPagesID(null);
				}
				
				menusout.add(childs);
		}
		 
		 if( (menus.size() == count) && !valid) {
			 System.out.println("zero");
			 pmenu.setPagesID(null);
		 
		 }


		 mm.setChild(menusout);
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
