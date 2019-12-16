package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The persistent class for the Device_table database table.
 * 
 */
@Entity
@DynamicUpdate
@Table(name="Device_table")
@NamedQuery(name="Device_table.findAll", query="SELECT d FROM Device_table d")
public class Device_table implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=500)
	private String dtableculumn;

	@Column(length=45)
	private String dtableDesc;

	@Column(nullable=false, length=500)
	private String dtablename;

	@Column(nullable=false, length=500)
	private String requestparaname;

	public Device_table() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDtableculumn() {
		return this.dtableculumn;
	}

	public void setDtableculumn(String dtableculumn) {
		this.dtableculumn = dtableculumn;
	}

	public String getDtableDesc() {
		return this.dtableDesc;
	}

	public void setDtableDesc(String dtableDesc) {
		this.dtableDesc = dtableDesc;
	}

	public String getDtablename() {
		return this.dtablename;
	}

	public void setDtablename(String dtablename) {
		this.dtablename = dtablename;
	}

	public String getRequestparaname() {
		return this.requestparaname;
	}

	public void setRequestparaname(String requestparaname) {
		this.requestparaname = requestparaname;
	}

}