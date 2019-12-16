package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Device_rule database table.
 * 
 */
@Entity
@Table(name="Device_rule")
@NamedQuery(name="Device_rule.findAll", query="SELECT d FROM Device_rule d")
public class Device_rule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int device_Action_ID;

	@Column(nullable=false)
	private int dtype_opera_ID;

	public Device_rule() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDevice_Action_ID() {
		return this.device_Action_ID;
	}

	public void setDevice_Action_ID(int device_Action_ID) {
		this.device_Action_ID = device_Action_ID;
	}

	public int getDtype_opera_ID() {
		return this.dtype_opera_ID;
	}

	public void setDtype_opera_ID(int dtype_opera_ID) {
		this.dtype_opera_ID = dtype_opera_ID;
	}

}