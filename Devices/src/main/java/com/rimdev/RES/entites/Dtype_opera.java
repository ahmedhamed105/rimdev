package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Dtype_opera database table.
 * 
 */
@Entity
@Table(name="Dtype_opera")
@NamedQuery(name="Dtype_opera.findAll", query="SELECT d FROM Dtype_opera d")
public class Dtype_opera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int device_type_ID;

	//bi-directional many-to-one association to OperaSystem
	@ManyToOne
	@JoinColumn(name="opera_system_ID", nullable=false)
	private OperaSystem operaSystem;

	public Dtype_opera() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDevice_type_ID() {
		return this.device_type_ID;
	}

	public void setDevice_type_ID(int device_type_ID) {
		this.device_type_ID = device_type_ID;
	}

	public OperaSystem getOperaSystem() {
		return this.operaSystem;
	}

	public void setOperaSystem(OperaSystem operaSystem) {
		this.operaSystem = operaSystem;
	}

}