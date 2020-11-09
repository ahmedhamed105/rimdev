package com.rimdev.rimfile.outputobject;

import com.rimdev.rimfile.entities.TextConvertion;

public class Lang_obj {
	
	String langcode;
	TextConvertion txtconv;
	
	public Lang_obj() {
		super();
	}

	public String getLangcode() {
		return langcode;
	}

	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}

	public TextConvertion getTxtconv() {
		return txtconv;
	}

	public void setTxtconv(TextConvertion txtconv) {
		this.txtconv = txtconv;
	}


}
