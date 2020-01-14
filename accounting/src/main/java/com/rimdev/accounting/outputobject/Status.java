package com.rimdev.accounting.outputobject;

public class Status {
	int id;
	String status;
	
	

	

	public Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Status() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
