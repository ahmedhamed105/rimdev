package com.rimdev.user.ouputobject;

import java.util.List;

import com.rimdev.user.entities.ParentComponent;

public class parent_comp {
	
	ParentComponent parent;
	List<Component_object> child;
	String pagename;
	String menuname;
	String parentname;

	public parent_comp() {
		super();
	}

	public ParentComponent getParent() {
		return parent;
	}

	public void setParent(ParentComponent parent) {
		this.parent = parent;
	}

	public List<Component_object> getChild() {
		return child;
	}

	public void setChild(List<Component_object> child) {
		this.child = child;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	
	



}
