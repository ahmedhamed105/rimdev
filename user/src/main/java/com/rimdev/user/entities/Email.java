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
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "email", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
    , @NamedQuery(name = "Email.findById", query = "SELECT e FROM Email e WHERE e.id = :id")
    , @NamedQuery(name = "Email.findByEmailuser", query = "SELECT e FROM Email e WHERE e.emailuser = :emailuser")
    , @NamedQuery(name = "Email.findByEmailPrimary", query = "SELECT e FROM Email e WHERE e.emailPrimary = :emailPrimary")
    , @NamedQuery(name = "Email.findByEmailModify", query = "SELECT e FROM Email e WHERE e.emailModify = :emailModify")
    , @NamedQuery(name = "Email.findByEmailCreate", query = "SELECT e FROM Email e WHERE e.emailCreate = :emailCreate")})
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Email_user", nullable = false, length = 45)
    private String emailuser;
    @Basic(optional = false)
    @Column(name = "email_primary", nullable = false)
    private int emailPrimary;
    @Basic(optional = false)
    @Column(name = "email_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date emailModify;
    @Basic(optional = false)
    @Column(name = "email_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date emailCreate;
    @JoinColumn(name = "User_login_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private UserLogin userloginID;
    
    
    @JoinColumn(name = "Data_status_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DataStatus datastatusID;

    public Email() {
    }

    public Email(Integer id) {
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

    public String getEmailuser() {
        return emailuser;
    }

    public void setEmailuser(String emailuser) {
        this.emailuser = emailuser;
    }

    
    public int getEmailPrimary() {
		return emailPrimary;
	}

	public void setEmailPrimary(int emailPrimary) {
		this.emailPrimary = emailPrimary;
	}

	@XmlTransient
    @JsonIgnore
    public Date getEmailModify() {
        return emailModify;
    }

    public void setEmailModify(Date emailModify) {
        this.emailModify = emailModify;
    }
    @XmlTransient
    @JsonIgnore
    public Date getEmailCreate() {
        return emailCreate;
    }

    public void setEmailCreate(Date emailCreate) {
        this.emailCreate = emailCreate;
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
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Email[ id=" + id + " ]";
    }
    
}
