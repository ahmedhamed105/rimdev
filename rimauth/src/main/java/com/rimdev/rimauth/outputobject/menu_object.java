package com.rimdev.rimauth.outputobject;

import java.util.List;

import com.rimdev.rimauth.entities.MenuDisplay;
import com.rimdev.rimauth.entities.ParentMenu;

public class menu_object {
	
	ParentMenu parent;
	List<MenuDisplay> child;
	
	
	
	
	public menu_object() {
		super();
	}
	public ParentMenu getParent() {
		return parent;
	}
	public void setParent(ParentMenu parent) {
		this.parent = parent;
	}
	public List<MenuDisplay> getChild() {
		return child;
	}
	public void setChild(List<MenuDisplay> child) {
		this.child = child;
	}
	
	
	

}
