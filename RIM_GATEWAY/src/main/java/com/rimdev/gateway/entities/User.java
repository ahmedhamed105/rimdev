/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.gateway.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "user", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore all null fields
@DynamicUpdate
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
		@NamedQuery(name = "User.findByMiddleName", query = "SELECT u FROM User u WHERE u.middleName = :middleName"),
		@NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
		@NamedQuery(name = "User.findByBirthdate", query = "SELECT u FROM User u WHERE u.birthdate = :birthdate"),
		@NamedQuery(name = "User.findByUseridnumber", query = "SELECT u FROM User u WHERE u.useridnumber = :useridnumber"),
		@NamedQuery(name = "User.findByPassportnumber", query = "SELECT u FROM User u WHERE u.passportnumber = :passportnumber"),
		@NamedQuery(name = "User.findByIDnumber", query = "SELECT u FROM User u WHERE u.iDnumber = :iDnumber"),
		@NamedQuery(name = "User.findByUsermodify", query = "SELECT u FROM User u WHERE u.usermodify = :usermodify"),
		@NamedQuery(name = "User.findByUsercreate", query = "SELECT u FROM User u WHERE u.usercreate = :usercreate") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;
	@Column(name = "first_name", length = 45)
	private String firstName;
	@Column(name = "middle_name", length = 45)
	private String middleName;
	@Column(name = "Last_name", length = 45)
	private String lastname;
	@Column(name = "birthdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdate;
	@Column(name = "User_id_number", length = 450)
	private String useridnumber;
	@Column(name = "Passport_number", length = 450)
	private String passportnumber;
	@Column(name = "ID_number", length = 45)
	private String iDnumber;
	@Basic(optional = false)
	@Column(name = "User_modify", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date usermodify;
	@Basic(optional = false)
	@Column(name = "User_create", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date usercreate;

	@Column(name = "files_upload_ID")
	private Integer filesuploadID;

	public User(String firstName, String middleName, String lastname, Date birthdate, String useridnumber,
			String passportnumber, String iDnumber, Date usermodify, Date usercreate, Integer filesuploadID) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.useridnumber = useridnumber;
		this.passportnumber = passportnumber;
		this.iDnumber = iDnumber;
		this.usermodify = usermodify;
		this.usercreate = usercreate;
		this.filesuploadID = filesuploadID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastname=" + lastname
				+ ", birthdate=" + birthdate + ", useridnumber=" + useridnumber + ", passportnumber=" + passportnumber
				+ ", iDnumber=" + iDnumber + ", usermodify=" + usermodify + ", usercreate=" + usercreate
				+ ", filesuploadID=" + filesuploadID + "]";
	}

	

}
