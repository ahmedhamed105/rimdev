package com.rimdev.user.ouputobject;

import java.util.List;

import com.rimdev.user.entities.MenuDisplay;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.entities.ParentMenu;

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
