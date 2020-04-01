/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.user.entities;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user_login", catalog = "rim_user", schema = "")
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
    , @NamedQuery(name = "UserLogin.findByLoginCreate", query = "SELECT u FROM UserLogin u WHERE u.loginCreate = :loginCreate")})
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "Login_key", nullable = false, length = 450)
    private String loginkey;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userloginID")
    private Collection<Telephones> telephonesCollection;
    @JoinColumn(name = "User_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private User userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userloginID")
    private Collection<Email> emailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userloginID")
    private Collection<PasswordHistory> passwordHistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userloginID")
    private Collection<DevicePage> devicePageCollection;
    

    public UserLogin() {
    }

    public UserLogin(Integer id) {
        this.id = id;
    }

    
    public UserLogin(Integer id, String username, String passwordEncy, Date loginModfiy, Date loginCreate) {
        this.id = id;
        this.username = username;
        this.passwordEncy = passwordEncy;
        this.loginModfiy = loginModfiy;
        this.loginCreate = loginCreate;
    }
    
    
    @XmlTransient
    @JsonIgnore
    public Collection<Telephones> getTelephonesCollection() {
        return telephonesCollection;
    }

    public void setTelephonesCollection(Collection<Telephones> telephonesCollection) {
        this.telephonesCollection = telephonesCollection;
    }


    @XmlTransient
    @JsonIgnore
    public Collection<Email> getEmailCollection() {
        return emailCollection;
    }

    public void setEmailCollection(Collection<Email> emailCollection) {
        this.emailCollection = emailCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PasswordHistory> getPasswordHistoryCollection() {
        return passwordHistoryCollection;
    }

    public void setPasswordHistoryCollection(Collection<PasswordHistory> passwordHistoryCollection) {
        this.passwordHistoryCollection = passwordHistoryCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<DevicePage> getDevicePageCollection() {
        return devicePageCollection;
    }

    public void setDevicePageCollection(Collection<DevicePage> devicePageCollection) {
        this.devicePageCollection = devicePageCollection;
    }
   
    @XmlTransient
    @JsonIgnore
	public String getLoginkey() {
		return loginkey;
	}

	public void setLoginkey(String loginkey) {
		this.loginkey = loginkey;
	}

	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsertokean() {
        return usertokean;
    }

    public void setUsertokean(String usertokean) {
        this.usertokean = usertokean;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }
    @XmlTransient
    @JsonIgnore
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPasswordEncy() {
        return passwordEncy;
    }

    public void setPasswordEncy(String passwordEncy) {
        this.passwordEncy = passwordEncy;
    }
    @XmlTransient
    @JsonIgnore
    public Date getLoginModfiy() {
        return loginModfiy;
    }

    public void setLoginModfiy(Date loginModfiy) {
        this.loginModfiy = loginModfiy;
    }
    @XmlTransient
    @JsonIgnore
    public Date getLoginCreate() {
        return loginCreate;
    }

    public void setLoginCreate(Date loginCreate) {
        this.loginCreate = loginCreate;
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
        return "entity.UserLogin[ id=" + id + " ]";
    }
    
}
