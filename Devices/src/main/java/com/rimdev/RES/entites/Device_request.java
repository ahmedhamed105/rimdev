package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Device_request database table.
 * 
 */
@Entity
@Table(name="Device_request")
@NamedQuery(name="Device_request.findAll", query="SELECT d FROM Device_request d")
public class Device_request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int error_map_ID;

	@Column(length=45)
	private String ip;

	@Column(length=45)
	private String mac;

	@Column(length=45)
	private String other;

	@Column(length=45)
	private String processor;

	@Column(length=45)
	private String ram;

	@Column(name="request_status", nullable=false)
	private int requestStatus;

	@Column(name="web_browser", length=45)
	private String webBrowser;

	public Device_request() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getError_map_ID() {
		return this.error_map_ID;
	}

	public void setError_map_ID(int error_map_ID) {
		this.error_map_ID = error_map_ID;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getProcessor() {
		return this.processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public String getRam() {
		return this.ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public int getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getWebBrowser() {
		return this.webBrowser;
	}

	public void setWebBrowser(String webBrowser) {
		this.webBrowser = webBrowser;
	}

}