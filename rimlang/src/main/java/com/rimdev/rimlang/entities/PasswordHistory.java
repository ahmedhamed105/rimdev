/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.rimlang.entities;

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
@Table(name = "password_history", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "PasswordHistory.findAll", query = "SELECT p FROM PasswordHistory p")
    , @NamedQuery(name = "PasswordHistory.findById", query = "SELECT p FROM PasswordHistory p WHERE p.id = :id")
    , @NamedQuery(name = "PasswordHistory.findByPasswordHist", query = "SELECT p FROM PasswordHistory p WHERE p.passwordHist = :passwordHist")
    , @NamedQuery(name = "PasswordHistory.findByPassModify", query = "SELECT p FROM PasswordHistory p WHERE p.passModify = :passModify")
    , @NamedQuery(name = "PasswordHistory.findByPassCreate", query = "SELECT p FROM PasswordHistory p WHERE p.passCreate = :passCreate")})
public class PasswordHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "password_hist", nullable = false, length = 45)
    private String passwordHist;
    @Basic(optional = false)
    @Column(name = "pass_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date passModify;
    @Basic(optional = false)
    @Column(name = "pass_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date passCreate;
    @JoinColumn(name = "User_login_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private UserLogin userloginID;


    public PasswordHistory() {
    }

    public PasswordHistory(Integer id) {
        this.id = id;
    }

    public PasswordHistory(Integer id, String passwordHist, Date passModify, Date passCreate) {
        this.id = id;
        this.passwordHist = passwordHist;
        this.passModify = passModify;
        this.passCreate = passCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPasswordHist() {
        return passwordHist;
    }

    public void setPasswordHist(String passwordHist) {
        this.passwordHist = passwordHist;
    }

    public Date getPassModify() {
        return passModify;
    }

    public void setPassModify(Date passModify) {
        this.passModify = passModify;
    }
	@XmlTransient
    @JsonIgnore
    public Date getPassCreate() {
        return passCreate;
    }

    public void setPassCreate(Date passCreate) {
        this.passCreate = passCreate;
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
        if (!(object instanceof PasswordHistory)) {
            return false;
        }
        PasswordHistory other = (PasswordHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PasswordHistory[ id=" + id + " ]";
    }
    
}
