package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The persistent class for the Device_type database table.
 * 
 */
@Entity
@Table(name="Device_type")
@DynamicUpdate
@NamedQuery(name="Device_type.findAll", query="SELECT d FROM Device_type d")
public class Device_type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=45)
	private String devicedesc;

	@Column(nullable=false, length=45)
	private String devicetype;

	public Device_type() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDevicedesc() {
		return this.devicedesc;
	}

	public void setDevicedesc(String devicedesc) {
		this.devicedesc = devicedesc;
	}

	public String getDevicetype() {
		return this.devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

}