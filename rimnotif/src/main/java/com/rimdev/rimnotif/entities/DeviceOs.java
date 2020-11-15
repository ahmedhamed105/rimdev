/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.rimnotif.entities;

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
@Table(name = "device_os", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "DeviceOs.findAll", query = "SELECT d FROM DeviceOs d")
    , @NamedQuery(name = "DeviceOs.findById", query = "SELECT d FROM DeviceOs d WHERE d.id = :id")
    , @NamedQuery(name = "DeviceOs.findByDeviceOS", query = "SELECT d FROM DeviceOs d WHERE d.deviceOS = :deviceOS")
    , @NamedQuery(name = "DeviceOs.findByDevicedesc", query = "SELECT d FROM DeviceOs d WHERE d.devicedesc = :devicedesc")
    , @NamedQuery(name = "DeviceOs.findByDevicemodify", query = "SELECT d FROM DeviceOs d WHERE d.devicemodify = :devicemodify")
    , @NamedQuery(name = "DeviceOs.findByDevicecreate", query = "SELECT d FROM DeviceOs d WHERE d.devicecreate = :devicecreate")})
public class DeviceOs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Device_OS", length = 45)
    private String deviceOS;
    @Column(name = "Device_desc", length = 45)
    private String devicedesc;
    @Basic(optional = false)
    @Column(name = "Device_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @XmlTransient
    @JsonIgnore
    private Date devicemodify;
    @Basic(optional = false)
    @Column(name = "Device_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @XmlTransient
    @JsonIgnore
    private Date devicecreate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceOSID")
    private Collection<Device> deviceCollection;

    public DeviceOs() {
    }

    public DeviceOs(Integer id) {
        this.id = id;
    }

    public DeviceOs(Integer id, Date devicemodify, Date devicecreate) {
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

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public String getDevicedesc() {
        return devicedesc;
    }

    public void setDevicedesc(String devicedesc) {
        this.devicedesc = devicedesc;
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

    @XmlTransient
    @JsonIgnore
    public Collection<Device> getDeviceCollection() {
        return deviceCollection;
    }

    public void setDeviceCollection(Collection<Device> deviceCollection) {
        this.deviceCollection = deviceCollection;
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
        if (!(object instanceof DeviceOs)) {
            return false;
        }
        DeviceOs other = (DeviceOs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DeviceOs[ id=" + id + " ]";
    }
    
}
