/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.rimuser.entities;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "user_type", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "UserType.findAll", query = "SELECT u FROM UserType u")
    , @NamedQuery(name = "UserType.findById", query = "SELECT u FROM UserType u WHERE u.id = :id")
    , @NamedQuery(name = "UserType.findByUsertype", query = "SELECT u FROM UserType u WHERE u.usertype = :usertype")
    , @NamedQuery(name = "UserType.findByPublishnot", query = "SELECT u FROM UserType u WHERE u.publishnot = :publishnot")
    , @NamedQuery(name = "UserType.findByTypeModify", query = "SELECT u FROM UserType u WHERE u.typeModify = :typeModify")
    , @NamedQuery(name = "UserType.findByTypeCreate", query = "SELECT u FROM UserType u WHERE u.typeCreate = :typeCreate")})
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "User_type", nullable = false, length = 45)
    private String usertype;
    @Basic(optional = false)
    @Column(name = "Publish_not", nullable = false)
    private int publishnot;
    @Basic(optional = false)
    @Column(name = "type_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date typeModify;
    @Basic(optional = false)
    @Column(name = "type_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date typeCreate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usertypeID")
    private Collection<UserLogin> userLoginCollection;

    public UserType() {
    }

    public UserType(Integer id) {
        this.id = id;
    }

    public UserType(Integer id, String usertype, int publishnot, Date typeModify, Date typeCreate) {
        this.id = id;
        this.usertype = usertype;
        this.publishnot = publishnot;
        this.typeModify = typeModify;
        this.typeCreate = typeCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getPublishnot() {
        return publishnot;
    }

    public void setPublishnot(int publishnot) {
        this.publishnot = publishnot;
    }

    public Date getTypeModify() {
        return typeModify;
    }

    public void setTypeModify(Date typeModify) {
        this.typeModify = typeModify;
    }

    public Date getTypeCreate() {
        return typeCreate;
    }

    public void setTypeCreate(Date typeCreate) {
        this.typeCreate = typeCreate;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserLogin> getUserLoginCollection() {
        return userLoginCollection;
    }

    public void setUserLoginCollection(Collection<UserLogin> userLoginCollection) {
        this.userLoginCollection = userLoginCollection;
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
        if (!(object instanceof UserType)) {
            return false;
        }
        UserType other = (UserType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserType[ id=" + id + " ]";
    }
    
}
