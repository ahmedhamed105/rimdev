package com.rimdev.RES.Input;

import java.util.List;

public class InDevice {
	
	
	private String IP;
	private String OS;
	private String MAC;
	private String Browser;
	private List<Devpara> DevPara;
	private int Apptype;
	private String Productkey;
	private String DeviceAPI;
	
	
	
	
	public InDevice() {
		super();
	}
	
	
	public InDevice(String iP, String oS, String mAC, String browser, List<Devpara> devPara, int apptype,
			String productkey, String deviceAPI) {
		super();
		IP = iP;
		OS = oS;
		MAC = mAC;
		Browser = browser;
		DevPara = devPara;
		Apptype = apptype;
		Productkey = productkey;
		DeviceAPI = deviceAPI;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getMAC() {
		return MAC;
	}
	public void setMAC(String mAC) {
		MAC = mAC;
	}
	public String getBrowser() {
		return Browser;
	}
	public void setBrowser(String browser) {
		Browser = browser;
	}
	public List<Devpara> getDevPara() {
		return DevPara;
	}
	public void setDevPara(List<Devpara> devPara) {
		DevPara = devPara;
	}
	public int getApptype() {
		return Apptype;
	}
	public void setApptype(int apptype) {
		Apptype = apptype;
	}
	public String getProductkey() {
		return Productkey;
	}
	public void setProductkey(String productkey) {
		Productkey = productkey;
	}
	public String getDeviceAPI() {
		return DeviceAPI;
	}
	public void setDeviceAPI(String deviceAPI) {
		DeviceAPI = deviceAPI;
	}
	
	
	
	
	
	

}
