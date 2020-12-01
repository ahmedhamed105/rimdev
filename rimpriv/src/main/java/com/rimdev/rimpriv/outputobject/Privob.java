package com.rimdev.rimpriv.outputobject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Privob {
	
	HttpServletRequest request;
	String tokean;
	int userid;
	int pageid;
	String langcode;
	String devicecode;
	List<String> paramter;
	List<String> values;
	
	
	
	
	public Privob() {
		super();
	}
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	/**
	 * @return the tokean
	 */
	public String getTokean() {
		return tokean;
	}
	/**
	 * @param tokean the tokean to set
	 */
	public void setTokean(String tokean) {
		this.tokean = tokean;
	}
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	 * @return the pageid
	 */
	public int getPageid() {
		return pageid;
	}
	/**
	 * @param pageid the pageid to set
	 */
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
	/**
	 * @return the langcode
	 */
	public String getLangcode() {
		return langcode;
	}
	/**
	 * @param langcode the langcode to set
	 */
	public void setLangcode(String langcode) {
		this.langcode = langcode;
	}
	/**
	 * @return the devicecode
	 */
	public String getDevicecode() {
		return devicecode;
	}
	/**
	 * @param devicecode the devicecode to set
	 */
	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}
	/**
	 * @return the paramter
	 */
	public List<String> getParamter() {
		return paramter;
	}
	/**
	 * @param paramter the paramter to set
	 */
	public void setParamter(List<String> paramter) {
		this.paramter = paramter;
	}
	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}
	
	
	

}
