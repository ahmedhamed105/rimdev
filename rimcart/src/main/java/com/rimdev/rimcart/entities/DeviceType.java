/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.rimcart.entities;

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
@Table(name = "device_type", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "DeviceType.findAll", query = "SELECT d FROM DeviceType d")
    , @NamedQuery(name = "DeviceType.findById", query = "SELECT d FROM DeviceType d WHERE d.id = :id")
    , @NamedQuery(name = "DeviceType.findByDevtype", query = "SELECT d FROM DeviceType d WHERE d.devtype = :devtype")
    , @NamedQuery(name = "DeviceType.findByDevmodify", query = "SELECT d FROM DeviceType d WHERE d.devmodify = :devmodify")
    , @NamedQuery(name = "DeviceType.findByDevcreate", query = "SELECT d FROM DeviceType d WHERE d.devcreate = :devcreate")})
public class DeviceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Dev_type", length = 45)
    private String devtype;
    @Basic(optional = false)
    @Column(name = "Dev_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @XmlTransient
    @JsonIgnore
    private Date devmodify;
    @Basic(optional = false)
    @Column(name = "Dev_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @XmlTransient
    @JsonIgnore
    private Date devcreate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "devicetypeID")
    private Collection<Device> deviceCollection;

    public DeviceType() {
    }

    public DeviceType(Integer id) {
        this.id = id;
    }

    public DeviceType(Integer id, Date devmodify, Date devcreate) {
        this.id = id;
        this.devmodify = devmodify;
        this.devcreate = devcreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevtype() {
        return devtype;
    }

    public void setDevtype(String devtype) {
        this.devtype = devtype;
    }

    public Date getDevmodify() {
        return devmodify;
    }

    public void setDevmodify(Date devmodify) {
        this.devmodify = devmodify;
    }

    public Date getDevcreate() {
        return devcreate;
    }

    public void setDevcreate(Date devcreate) {
        this.devcreate = devcreate;
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
        if (!(object instanceof DeviceType)) {
            return false;
        }
        DeviceType other = (DeviceType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DeviceType[ id=" + id + " ]";
    }
    
}
