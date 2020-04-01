package com.rimdev.user.ouputobject;

public class GeoIP {
	
    private String ipAddress;
    private String country;
    private String city;
    private String state;
    private String latitude;
    private String longitude;
    private String subneting;
    private String timezone;
    
    
	public GeoIP() {
		super();
	}
	
	
	
	
	
	public String getTimezone() {
		return timezone;
	}





	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}





	public String getSubneting() {
		return subneting;
	}


	public void setSubneting(String subneting) {
		this.subneting = subneting;
	}


	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    
    
    
    

}
