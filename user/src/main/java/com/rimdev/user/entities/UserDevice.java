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
@Table(name = "user_device", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "UserDevice.findAll", query = "SELECT u FROM UserDevice u")
    , @NamedQuery(name = "UserDevice.findById", query = "SELECT u FROM UserDevice u WHERE u.id = :id")
    , @NamedQuery(name = "UserDevice.findByDevicemodify", query = "SELECT u FROM UserDevice u WHERE u.devicemodify = :devicemodify")
    , @NamedQuery(name = "UserDevice.findByDevicecreate", query = "SELECT u FROM UserDevice u WHERE u.devicecreate = :devicecreate")})
public class UserDevice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Device_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date devicemodify;
    @Basic(optional = false)
    @Column(name = "Device_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date devicecreate;
    @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Device deviceId;
    @JoinColumn(name = "User_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private User userID;

    public UserDevice() {
    }

    public UserDevice(Integer id) {
        this.id = id;
    }

    public UserDevice(Integer id, Date devicemodify, Date devicecreate) {
        this.id = id;
        this.devicemodify = devicemodify;
        this.devicecreate = devicecreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDevicemodify() {
        return devicemodify;
    }

    public void setDevicemodify(Date devicemodify) {
        this.devicemodify = devicemodify;
    }

    public Date getDevicecreate() {
        return devicecreate;
    }

    public void setDevicecreate(Date devicecreate) {
        this.devicecreate = devicecreate;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
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
        if (!(object instanceof UserDevice)) {
            return false;
        }
        UserDevice other = (UserDevice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserDevice[ id=" + id + " ]";
    }
    
}
