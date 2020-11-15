package com.rimdev.rimnotif.outputobject;

import com.rimdev.rimnotif.entities.MenuDisplay;
import com.rimdev.rimnotif.entities.ParentMenu;

public class menuparsub {
	
	
	ParentMenu parent;
	MenuDisplay child;
	

	public menuparsub() {
		super();
	}


	public ParentMenu getParent() {
		return parent;
	}


	public void setParent(ParentMenu parent) {
		this.parent = parent;
	}


	public MenuDisplay getChild() {
		return child;
	}


	public void setChild(MenuDisplay child) {
		this.child = child;
	}


	
	
	
	
	
	

}
