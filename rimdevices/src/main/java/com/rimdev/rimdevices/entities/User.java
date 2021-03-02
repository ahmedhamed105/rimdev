/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.rimdevices.entities;



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
@Table(name = "user", catalog = "rim_user", schema = "rim_user")
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
    , @NamedQuery(name = "User.findByIDnumber", query = "SELECT u FROM User u WHERE u.iDnumber = :iDnumber")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<UserLogin> userLoginCollection;
    
    @JoinColumn(name = "files_upload_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private FilesUpload filesuploadID;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }
    

    public User(Integer id, Date usermodify, Date usercreate) {
        this.id = id;
        this.usermodify = usermodify;
        this.usercreate = usercreate;
    }
    
    public FilesUpload getFilesuploadID() {
        return filesuploadID;
    }

    public void setFilesuploadID(FilesUpload filesuploadID) {
        this.filesuploadID = filesuploadID;
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

  

    public String getiDnumber() {
		return iDnumber;
	}

	public void setiDnumber(String iDnumber) {
		this.iDnumber = iDnumber;
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
				+ ", userLoginCollection=" + userLoginCollection + ", filesuploadID=" + filesuploadID + "]";
	}

 
    
}

