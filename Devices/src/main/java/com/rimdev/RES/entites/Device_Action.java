package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Device_Action database table.
 * 
 */
@Entity
@Table(name="Device_Action")
@NamedQuery(name="Device_Action.findAll", query="SELECT d FROM Device_Action d")
public class Device_Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int ALL_flag;

	@Column(nullable=false, length=45)
	private String allow_block;

	@Column(nullable=false)
	private int device_table_ID;

	@Column(nullable=false, length=500)
	private String paramname;

	@Column(length=45)
	private String paramValue;

	public Device_Action() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getALL_flag() {
		return this.ALL_flag;
	}

	public void setALL_flag(int ALL_flag) {
		this.ALL_flag = ALL_flag;
	}

	public String getAllow_block() {
		return this.allow_block;
	}

	public void setAllow_block(String allow_block) {
		this.allow_block = allow_block;
	}

	public int getDevice_table_ID() {
		return this.device_table_ID;
	}

	public void setDevice_table_ID(int device_table_ID) {
		this.device_table_ID = device_table_ID;
	}

	public String getParamname() {
		return this.paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}