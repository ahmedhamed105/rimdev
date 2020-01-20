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
@Table(name = "device_tokean", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "DeviceTokean.findAll", query = "SELECT d FROM DeviceTokean d")
    , @NamedQuery(name = "DeviceTokean.findById", query = "SELECT d FROM DeviceTokean d WHERE d.id = :id")
    , @NamedQuery(name = "DeviceTokean.findByDevicetokean", query = "SELECT d FROM DeviceTokean d WHERE d.devicetokean = :devicetokean")
    , @NamedQuery(name = "DeviceTokean.findByDevicetime", query = "SELECT d FROM DeviceTokean d WHERE d.devicetime = :devicetime")})
public class DeviceTokean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Device_tokean", nullable = false, length = 45)
    private String devicetokean;
    @Basic(optional = false)
    @Column(name = "Device_time", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date devicetime;

    public DeviceTokean() {
    }

    public DeviceTokean(Integer id) {
        this.id = id;
    }

    public DeviceTokean(Integer id, String devicetokean, Date devicetime) {
        this.id = id;
        this.devicetokean = devicetokean;
        this.devicetime = devicetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevicetokean() {
        return devicetokean;
    }

    public void setDevicetokean(String devicetokean) {
        this.devicetokean = devicetokean;
    }

    public Date getDevicetime() {
        return devicetime;
    }

    public void setDevicetime(Date devicetime) {
        this.devicetime = devicetime;
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
        if (!(object instanceof DeviceTokean)) {
            return false;
        }
        DeviceTokean other = (DeviceTokean) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DeviceTokean[ id=" + id + " ]";
    }
    
}
