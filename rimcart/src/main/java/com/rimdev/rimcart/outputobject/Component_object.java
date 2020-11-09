package com.rimdev.rimcart.outputobject;

import java.util.List;

import com.rimdev.rimcart.entities.Component;
import com.rimdev.rimcart.entities.ComponentButton;
import com.rimdev.rimcart.entities.ComponentInput;
import com.rimdev.rimcart.entities.ComponentSelect;
import com.rimdev.rimcart.entities.FileType;
import com.rimdev.rimcart.entities.RelationComp;

public class Component_object {
	
	Component comp;
	ComponentSelect select;
	ComponentInput input;
	ComponentButton button;
	List<FileType> allowfiletype;
	List<RelationComp> rel;
	 
	
	
	
	public List<RelationComp> getRel() {
		return rel;
	}
	public void setRel(List<RelationComp> rel) {
		this.rel = rel;
	}
	public List<FileType> getAllowfiletype() {
		return allowfiletype;
	}
	public void setAllowfiletype(List<FileType> allowfiletype) {
		this.allowfiletype = allowfiletype;
	}
	public Component_object() {
		super();
	}
	public Component getComp() {
		return comp;
	}
	public void setComp(Component comp) {
		this.comp = comp;
	}
	public ComponentSelect getSelect() {
		return select;
	}
	public void setSelect(ComponentSelect select) {
		this.select = select;
	}
	public ComponentInput getInput() {
		return input;
	}
	public void setInput(ComponentInput input) {
		this.input = input;
	}
	public ComponentButton getButton() {
		return button;
	}
	public void setButton(ComponentButton button) {
		this.button = button;
	}
	
	
	
	

}
