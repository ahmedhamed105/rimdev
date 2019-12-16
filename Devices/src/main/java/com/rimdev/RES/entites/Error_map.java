package com.rimdev.RES.entites;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Error_map database table.
 * 
 */
@Entity
@Table(name="Error_map")
@NamedQuery(name="Error_map.findAll", query="SELECT e FROM Error_map e")
public class Error_map implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String error_code;

	@Column(nullable=false, length=45)
	private String error_desc;

	public Error_map() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getError_code() {
		return this.error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_desc() {
		return this.error_desc;
	}

	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}

}