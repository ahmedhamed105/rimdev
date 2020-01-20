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
@Table(name = "device", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
    , @NamedQuery(name = "Device.findById", query = "SELECT d FROM Device d WHERE d.id = :id")
    , @NamedQuery(name = "Device.findByDevicename", query = "SELECT d FROM Device d WHERE d.devicename = :devicename")
    , @NamedQuery(name = "Device.findByDeviceinfo", query = "SELECT d FROM Device d WHERE d.deviceinfo = :deviceinfo")
    , @NamedQuery(name = "Device.findByDeviceip", query = "SELECT d FROM Device d WHERE d.deviceip = :deviceip")
    , @NamedQuery(name = "Device.findByDevicemac", query = "SELECT d FROM Device d WHERE d.devicemac = :devicemac")
    , @NamedQuery(name = "Device.findByDeviceosversion", query = "SELECT d FROM Device d WHERE d.deviceosversion = :deviceosversion")
    , @NamedQuery(name = "Device.findByDeviceosunknow", query = "SELECT d FROM Device d WHERE d.deviceosunknow = :deviceosunknow")
    , @NamedQuery(name = "Device.findByDevicetokean", query = "SELECT d FROM Device d WHERE d.devicetokean = :devicetokean")
    , @NamedQuery(name = "Device.findByTokeantime", query = "SELECT d FROM Device d WHERE d.tokeantime = :tokeantime")
    , @NamedQuery(name = "Device.findByDevicemodify", query = "SELECT d FROM Device d WHERE d.devicemodify = :devicemodify")
    , @NamedQuery(name = "Device.findByDevicecreate", query = "SELECT d FROM Device d WHERE d.devicecreate = :devicecreate")})
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Device_name", length = 45)
    private String devicename;
    @Column(name = "Device_info", length = 45)
    private String deviceinfo;
    @Column(name = "Device_ip", length = 45)
    private String deviceip;
    @Column(name = "Device_mac", length = 45)
    private String devicemac;
    @Column(name = "Device_os_version", length = 45)
    private String deviceosversion;
    @Column(name = "Device_os_unknow", length = 45)
    private String deviceosunknow;
    @Basic(optional = false)
    @Column(name = "Device_tokean", nullable = false, length = 45)
    private String devicetokean;
    @Basic(optional = false)
    @Column(name = "Tokean_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokeantime;
    @Basic(optional = false)
    @Column(name = "Device_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date devicemodify;
    @Basic(optional = false)
    @Column(name = "Device_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date devicecreate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceId")
    private Collection<UserDevice> userDeviceCollection;
    @JoinColumn(name = "Device_OS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DeviceOs deviceOSID;
    @JoinColumn(name = "Device_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DeviceType devicetypeID;

    public Device() {
    }

    public Device(Integer id) {
        this.id = id;
    }

    public Device(Integer id, String devicetokean, Date tokeantime, Date devicemodify, Date devicecreate) {
        this.id = id;
        this.devicetokean = devicetokean;
        this.tokeantime = tokeantime;
        this.devicemodify = devicemodify;
        this.devicecreate = devicecreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(String deviceinfo) {
        this.deviceinfo = deviceinfo;
    }

    public String getDeviceip() {
        return deviceip;
    }

    public void setDeviceip(String deviceip) {
        this.deviceip = deviceip;
    }

    public String getDevicemac() {
        return devicemac;
    }

    public void setDevicemac(String devicemac) {
        this.devicemac = devicemac;
    }

    public String getDeviceosversion() {
        return deviceosversion;
    }

    public void setDeviceosversion(String deviceosversion) {
        this.deviceosversion = deviceosversion;
    }

    public String getDeviceosunknow() {
        return deviceosunknow;
    }

    public void setDeviceosunknow(String deviceosunknow) {
        this.deviceosunknow = deviceosunknow;
    }

    public String getDevicetokean() {
        return devicetokean;
    }

    public void setDevicetokean(String devicetokean) {
        this.devicetokean = devicetokean;
    }

    public Date getTokeantime() {
        return tokeantime;
    }

    public void setTokeantime(Date tokeantime) {
        this.tokeantime = tokeantime;
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
    public Collection<UserDevice> getUserDeviceCollection() {
        return userDeviceCollection;
    }

    public void setUserDeviceCollection(Collection<UserDevice> userDeviceCollection) {
        this.userDeviceCollection = userDeviceCollection;
    }

    public DeviceOs getDeviceOSID() {
        return deviceOSID;
    }

    public void setDeviceOSID(DeviceOs deviceOSID) {
        this.deviceOSID = deviceOSID;
    }

    public DeviceType getDevicetypeID() {
        return devicetypeID;
    }

    public void setDevicetypeID(DeviceType devicetypeID) {
        this.devicetypeID = devicetypeID;
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Device[ id=" + id + " ]";
    }
    
}
