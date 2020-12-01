package com.rimdev.rimpriv.outputobject;

import java.util.List;

import com.rimdev.rimpriv.entities.ParentComponent;

public class parent_comp {
	
	ParentComponent parent;
	List<Component_object> child;

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

	



}
