package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import java.util.List;


/**
 * The persistent class for the opera_system database table.
 * 
 */
@Entity
@Table(name="opera_system")
@DynamicUpdate
@NamedQuery(name="OperaSystem.findAll", query="SELECT o FROM OperaSystem o")
public class OperaSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=45)
	private String operaDesc;

	@Column(nullable=false, length=45)
	private String operaLike;

	@Column(nullable=false, length=45)
	private String operaname;



	public OperaSystem() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperaDesc() {
		return this.operaDesc;
	}

	public void setOperaDesc(String operaDesc) {
		this.operaDesc = operaDesc;
	}

	public String getOperaLike() {
		return this.operaLike;
	}

	public void setOperaLike(String operaLike) {
		this.operaLike = operaLike;
	}

	public String getOperaname() {
		return this.operaname;
	}

	public void setOperaname(String operaname) {
		this.operaname = operaname;
	}


}