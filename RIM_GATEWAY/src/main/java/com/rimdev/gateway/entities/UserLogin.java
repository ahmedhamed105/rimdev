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
@Table(name = "user_login", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "UserLogin.findAll", query = "SELECT u FROM UserLogin u")
    , @NamedQuery(name = "UserLogin.findById", query = "SELECT u FROM UserLogin u WHERE u.id = :id")
    , @NamedQuery(name = "UserLogin.findByUsertokean", query = "SELECT u FROM UserLogin u WHERE u.usertokean = :usertokean")
    , @NamedQuery(name = "UserLogin.findByExpiredate", query = "SELECT u FROM UserLogin u WHERE u.expiredate = :expiredate")
    , @NamedQuery(name = "UserLogin.findByCreatedate", query = "SELECT u FROM UserLogin u WHERE u.createdate = :createdate")
    , @NamedQuery(name = "UserLogin.findByUsername", query = "SELECT u FROM UserLogin u WHERE u.username = :username")
    , @NamedQuery(name = "UserLogin.findByPasswordEncy", query = "SELECT u FROM UserLogin u WHERE u.passwordEncy = :passwordEncy")
    , @NamedQuery(name = "UserLogin.findByLoginModfiy", query = "SELECT u FROM UserLogin u WHERE u.loginModfiy = :loginModfiy")
    , @NamedQuery(name = "UserLogin.findByLoginCreate", query = "SELECT u FROM UserLogin u WHERE u.loginCreate = :loginCreate")
    , @NamedQuery(name = "UserLogin.findByNotes", query = "SELECT u FROM UserLogin u WHERE u.notes = :notes")
    , @NamedQuery(name = "UserLogin.findByLoginkey", query = "SELECT u FROM UserLogin u WHERE u.loginkey = :loginkey")
    , @NamedQuery(name = "UserLogin.findByLoginFailed", query = "SELECT u FROM UserLogin u WHERE u.loginFailed = :loginFailed")
    , @NamedQuery(name = "UserLogin.findByLoginFlag", query = "SELECT u FROM UserLogin u WHERE u.loginFlag = :loginFlag")})
public class UserLogin implements Serializable {
	

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "User_tokean", length = 450)
    private String usertokean;
    @Column(name = "Expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredate;
    @Column(name = "Create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Basic(optional = false)
    @Column(name = "Username", nullable = false, length = 450)
    private String username;
    @Basic(optional = false)
    @Column(name = "password_ency", nullable = false, length = 450)
    private String passwordEncy;
    @Basic(optional = false)
    @Column(name = "login_modfiy", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginModfiy;
    @Basic(optional = false)
    @Column(name = "login_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginCreate;
    @Column(name = "Notes", length = 450)
    private String notes;
    @Basic(optional = false)
    @Column(name = "Login_key", nullable = false, length = 450)
    private String loginkey;
    @Column(name = "login_failed")
    private Integer loginFailed;
    @Basic(optional = false)
    @Column(name = "login_flag", nullable = false)
    private Integer loginFlag;
    @Column(name = "Application_ID")
    private Integer applicationID;
    @Column(name = "Group_priviledge_ID")
    private Integer grouppriviledgeID;
    @Column(name = "Pages_ID")
    private Integer pagesID;
    @Column(name = "User_ID")
    private Integer userID;
    @Column(name = "User_status_ID")
    private Integer userstatusID;
    @Column(name = "User_type_ID")
    private Integer usertypeID;
    
    
    



    public UserLogin(String usertokean, String username) {
		super();
		this.usertokean = usertokean;
		this.username = username;
	}

	public UserLogin(String usertokean, Date expiredate, Date createdate, String username, String passwordEncy,
			Date loginModfiy, Date loginCreate, String notes, String loginkey, Integer loginFailed, Integer loginFlag,
			Integer applicationID, Integer grouppriviledgeID, Integer pagesID, Integer userID, Integer userstatusID,
			Integer usertypeID) {
		super();
		this.usertokean = usertokean;
		this.expiredate = expiredate;
		this.createdate = createdate;
		this.username = username;
		this.passwordEncy = passwordEncy;
		this.loginModfiy = loginModfiy;
		this.loginCreate = loginCreate;
		this.notes = notes;
		this.loginkey = loginkey;
		this.loginFailed = loginFailed;
		this.loginFlag = loginFlag;
		this.applicationID = applicationID;
		this.grouppriviledgeID = grouppriviledgeID;
		this.pagesID = pagesID;
		this.userID = userID;
		this.userstatusID = userstatusID;
		this.usertypeID = usertypeID;
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
        if (!(object instanceof UserLogin)) {
            return false;
        }
        UserLogin other = (UserLogin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", usertokean=" + usertokean + ", expiredate=" + expiredate + ", createdate="
				+ createdate + ", username=" + username + ", passwordEncy=" + passwordEncy + ", loginModfiy="
				+ loginModfiy + ", loginCreate=" + loginCreate + ", notes=" + notes + ", loginkey=" + loginkey
				+ ", loginFailed=" + loginFailed + ", loginFlag=" + loginFlag + ", applicationID=" + applicationID
				+ ", grouppriviledgeID=" + grouppriviledgeID + ", pagesID=" + pagesID + ", userID=" + userID
				+ ", userstatusID=" + userstatusID + ", usertypeID=" + usertypeID + "]";
	}

	

    
}

