package com.rimdev.rimlog.outputobject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class authobject {
	
	String requestip;
	String requestURL;
	String  Devicecode;
	String  username;
	String  usertokean;
	String  pagenum;
	String langcode;
	List<String> paramter;
	List<String> values;
	boolean info;
	boolean erorr;
	boolean warning;
	boolean fatal;
	boolean other;
	int logtype;
	String logException;
	String logtext;
	
	
	
	public authobject() {
		super();
	}
	
	
	

	public boolean isOther() {
		return other;
	}




	public void setOther(boolean other) {
		this.other = other;
	}




	public boolean isInfo() {
		return info;
	}




	public void setInfo(boolean info) {
		this.info = info;
	}




	public boolean isErorr() {
		return erorr;
	}




	public void setErorr(boolean erorr) {
		this.erorr = erorr;
	}




	public boolean isWarning() {
		return warning;
	}




	public void setWarning(boolean warning) {
		this.warning = warning;
	}




	public boolean isFatal() {
		return fatal;
	}




	public void setFatal(boolean fatal) {
		this.fatal = fatal;
	}




	public int getLogtype() {
		return logtype;
	}




	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}




	public String getLogException() {
		return logException;
	}




	public void setLogException(String logException) {
		this.logException = logException;
	}




	public String getLogtext() {
		return logtext;
	}


	public void setLogtext(String logtext) {
		this.logtext = logtext;
	}



	public List<String> getParamter() {
		return paramter;
	}


	public void setParamter(List<String> paramter) {
		this.paramter = paramter;
	}


	public List<String> getValues() {
		return values;
	}


	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getDevicecode() {
		return Devicecode;
	}
	public void setDevicecode(String devicecode) {
		Devicecode = devicecode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertokean() {
		return usertokean;
	}
	public void setUsertokean(String usertokean) {
		this.usertokean = usertokean;
	}
	public String getPagenum() {
		return pagenum;
	}
	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}
	public String getLangcode() {
		return langcode;
	}
	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}


	public String getRequestip() {
		return requestip;
	}


	public void setRequestip(String requestip) {
		this.requestip = requestip;
	}


	public String getRequestURL() {
		return requestURL;
	}


	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	


}
