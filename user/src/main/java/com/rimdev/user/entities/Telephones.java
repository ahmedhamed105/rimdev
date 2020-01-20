/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.user.entities;

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

import org.hibernate.annotations.DynamicUpdate;

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
    , @NamedQuery(name = "Telephones.findByPrimary", query = "SELECT t FROM Telephones t WHERE t.primary = :primary")
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
    @Column(name = "primary", nullable = false)
    private int primary;
    @Basic(optional = false)
    @Column(name = "tele_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date teleModify;
    @Basic(optional = false)
    @Column(name = "tele_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date teleCreate;
    @JoinColumn(name = "User_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private User userID;

    public Telephones() {
    }

    public Telephones(Integer id) {
        this.id = id;
    }

    public Telephones(Integer id, String phoneNo, int primary, Date teleModify, Date teleCreate) {
        this.id = id;
        this.phoneNo = phoneNo;
        this.primary = primary;
        this.teleModify = teleModify;
        this.teleCreate = teleCreate;
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

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public Date getTeleModify() {
        return teleModify;
    }

    public void setTeleModify(Date teleModify) {
        this.teleModify = teleModify;
    }

    public Date getTeleCreate() {
        return teleCreate;
    }

    public void setTeleCreate(Date teleCreate) {
        this.teleCreate = teleCreate;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
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
