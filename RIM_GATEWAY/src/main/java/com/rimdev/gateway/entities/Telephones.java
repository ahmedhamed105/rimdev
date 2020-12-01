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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "telephones", catalog = "rim_user", schema = "")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "phone_no", nullable = false, length = 45)
    private String phoneNo;
    @Basic(optional = false)
    @Column(name = "tele_primary", nullable = false)
    private int telePrimary;
    @Basic(optional = false)
    @Column(name = "tele_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date teleModify;
    @Basic(optional = false)
    @Column(name = "tele_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date teleCreate;
    @JoinColumn(name = "User_login_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private UserLogin userloginID;

    
    @JoinColumn(name = "Data_status_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DataStatus datastatusID;

    public Telephones() {
    }

    public Telephones(Integer id) {
        this.id = id;
    }
    
    public DataStatus getDatastatusID() {
        return datastatusID;
    }

    public void setDatastatusID(DataStatus datastatusID) {
        this.datastatusID = datastatusID;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    

    public int getTelePrimary() {
		return telePrimary;
	}

	public void setTelePrimary(int telePrimary) {
		this.telePrimary = telePrimary;
	}

	@XmlTransient
    @JsonIgnore
    public Date getTeleModify() {
        return teleModify;
    }

    public void setTeleModify(Date teleModify) {
        this.teleModify = teleModify;
    }

    @XmlTransient
    @JsonIgnore
    public Date getTeleCreate() {
        return teleCreate;
    }

    public void setTeleCreate(Date teleCreate) {
        this.teleCreate = teleCreate;
    }

    public UserLogin getUserloginID() {
        return userloginID;
    }

    public void setUserloginID(UserLogin userloginID) {
        this.userloginID = userloginID;
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
