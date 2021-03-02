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
@Table(name = "telephones", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Telephones.findAll", query = "SELECT t FROM Telephones t")
    , @NamedQuery(name = "Telephones.findById", query = "SELECT t FROM Telephones t WHERE t.id = :id")
    , @NamedQuery(name = "Telephones.findByPhoneNo", query = "SELECT t FROM Telephones t WHERE t.phoneNo = :phoneNo")
    , @NamedQuery(name = "Telephones.findByTelePrimary", query = "SELECT t FROM Telephones t WHERE t.telePrimary = :telePrimary")
    , @NamedQuery(name = "Telephones.findByTeleModify", query = "SELECT t FROM Telephones t WHERE t.teleModify = :teleModify")
    , @NamedQuery(name = "Telephones.findByTeleCreate", query = "SELECT t FROM Telephones t WHERE t.teleCreate = :teleCreate")})
public class Telephones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "phone_no", nullable = false, length = 45)
    private String phoneNo;
    @Basic(optional = false)
    @Column(name = "tele_primary", nullable = false)
    private Integer telePrimary;
    @Basic(optional = false)
    @Column(name = "tele_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date teleModify;
    @Basic(optional = false)
    @Column(name = "tele_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date teleCreate;
    @Column(name = "User_login_ID")
    private Integer userloginID;
    @Column(name = "Data_status_ID")
    private Integer datastatusID;

   

    public Telephones(String phoneNo, Integer telePrimary, Date teleModify, Date teleCreate, Integer userloginID,
			Integer datastatusID) {
		super();
		this.phoneNo = phoneNo;
		this.telePrimary = telePrimary;
		this.teleModify = teleModify;
		this.teleCreate = teleCreate;
		this.userloginID = userloginID;
		this.datastatusID = datastatusID;
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
        if (!(object instanceof Telephones)) {
            return false;
        }
        Telephones other = (Telephones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Telephones[ id=" + id + " ]";
    }
    
}
