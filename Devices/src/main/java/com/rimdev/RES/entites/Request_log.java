package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Request_log database table.
 * 
 */
@Entity
@Table(name="Request_log")
@NamedQuery(name="Request_log.findAll", query="SELECT r FROM Request_log r")
public class Request_log implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int device_request_ID;

	private int device_rule_ID;

	@Column(name="log_message", nullable=false, length=45)
	private String logMessage;

	@Column(nullable=false)
	private int status;

	public Request_log() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDevice_request_ID() {
		return this.device_request_ID;
	}

	public void setDevice_request_ID(int device_request_ID) {
		this.device_request_ID = device_request_ID;
	}

	public int getDevice_rule_ID() {
		return this.device_rule_ID;
	}

	public void setDevice_rule_ID(int device_rule_ID) {
		this.device_rule_ID = device_rule_ID;
	}

	public String getLogMessage() {
		return this.logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}