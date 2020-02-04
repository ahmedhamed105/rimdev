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
import javax.persistence.FetchType;
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
@Table(name = "user", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "User.findByMiddleName", query = "SELECT u FROM User u WHERE u.middleName = :middleName")
    , @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "User.findByBirthdate", query = "SELECT u FROM User u WHERE u.birthdate = :birthdate")
    , @NamedQuery(name = "User.findByUseridnumber", query = "SELECT u FROM User u WHERE u.useridnumber = :useridnumber")
    , @NamedQuery(name = "User.findByPassportnumber", query = "SELECT u FROM User u WHERE u.passportnumber = :passportnumber")
    , @NamedQuery(name = "User.findByIdImage", query = "SELECT u FROM User u WHERE u.idImage = :idImage")
    , @NamedQuery(name = "User.findByPassportImage", query = "SELECT u FROM User u WHERE u.passportImage = :passportImage")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByUsernameConfig", query = "SELECT u FROM User u WHERE u.usernameConfig = :usernameConfig")
    , @NamedQuery(name = "User.findByUsermodify", query = "SELECT u FROM User u WHERE u.usermodify = :usermodify")
    , @NamedQuery(name = "User.findByUsercreate", query = "SELECT u FROM User u WHERE u.usercreate = :usercreate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "User_id_number", length = 45)
    private String useridnumber;
    @Column(name = "Passport_number", length = 45)
    private String passportnumber;
    @Column(name = "id_image", length = 45)
    private String idImage;
    @Column(name = "passport_image", length = 45)
    private String passportImage;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic(optional = false)
    @Column(name = "username_config", nullable = false)
    private int usernameConfig;
    @Basic(optional = false)
    @Column(name = "User_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date usermodify;
    @Basic(optional = false)
    @Column(name = "User_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date usercreate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Telephones> telephonesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Adress> adressCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<UserDevice> userDeviceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<UserLog> userLogCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<UserLogin> userLoginCollection;
    @JoinColumn(name = "User_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private UserType usertypeID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Email> emailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<PasswordHistory> passwordHistoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<UserFile> userFileCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String password, int usernameConfig, Date usermodify, Date usercreate) {
        this.id = id;
        this.password = password;
        this.usernameConfig = usernameConfig;
        this.usermodify = usermodify;
        this.usercreate = usercreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getUseridnumber() {
        return useridnumber;
    }

    public void setUseridnumber(String useridnumber) {
        this.useridnumber = useridnumber;
    }

    public String getPassportnumber() {
        return passportnumber;
    }

    public void setPassportnumber(String passportnumber) {
        this.passportnumber = passportnumber;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getPassportImage() {
        return passportImage;
    }

    public void setPassportImage(String passportImage) {
        this.passportImage = passportImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsernameConfig() {
        return usernameConfig;
    }

    public void setUsernameConfig(int usernameConfig) {
        this.usernameConfig = usernameConfig;
    }
    @XmlTransient
    @JsonIgnore
    public Date getUsermodify() {
        return usermodify;
    }

    public void setUsermodify(Date usermodify) {
        this.usermodify = usermodify;
    }
    @XmlTransient
    @JsonIgnore
    public Date getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(Date usercreate) {
        this.usercreate = usercreate;
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
    public Collection<Adress> getAdressCollection() {
        return adressCollection;
    }

    public void setAdressCollection(Collection<Adress> adressCollection) {
        this.adressCollection = adressCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserDevice> getUserDeviceCollection() {
        return userDeviceCollection;
    }

    public void setUserDeviceCollection(Collection<UserDevice> userDeviceCollection) {
        this.userDeviceCollection = userDeviceCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserLog> getUserLogCollection() {
        return userLogCollection;
    }

    public void setUserLogCollection(Collection<UserLog> userLogCollection) {
        this.userLogCollection = userLogCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserLogin> getUserLoginCollection() {
        return userLoginCollection;
    }

    public void setUserLoginCollection(Collection<UserLogin> userLoginCollection) {
        this.userLoginCollection = userLoginCollection;
    }

    public UserType getUsertypeID() {
        return usertypeID;
    }

    public void setUsertypeID(UserType usertypeID) {
        this.usertypeID = usertypeID;
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
    public Collection<UserFile> getUserFileCollection() {
        return userFileCollection;
    }

    public void setUserFileCollection(Collection<UserFile> userFileCollection) {
        this.userFileCollection = userFileCollection;
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
        return "entity.User[ id=" + id + " ]";
    }
    
}
