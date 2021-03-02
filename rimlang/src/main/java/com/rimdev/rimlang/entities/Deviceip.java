package com.rimdev.rimlang.entities;


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
@Table(name = "deviceip", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Deviceip.findAll", query = "SELECT d FROM Deviceip d")
    , @NamedQuery(name = "Deviceip.findById", query = "SELECT d FROM Deviceip d WHERE d.id = :id")
    , @NamedQuery(name = "Deviceip.findByIpAddress", query = "SELECT d FROM Deviceip d WHERE d.ipAddress = :ipAddress")
    , @NamedQuery(name = "Deviceip.findByCountry", query = "SELECT d FROM Deviceip d WHERE d.country = :country")
    , @NamedQuery(name = "Deviceip.findByCity", query = "SELECT d FROM Deviceip d WHERE d.city = :city")
    , @NamedQuery(name = "Deviceip.findByState", query = "SELECT d FROM Deviceip d WHERE d.state = :state")
    , @NamedQuery(name = "Deviceip.findByLatitude", query = "SELECT d FROM Deviceip d WHERE d.latitude = :latitude")
    , @NamedQuery(name = "Deviceip.findByLongitude", query = "SELECT d FROM Deviceip d WHERE d.longitude = :longitude")
    , @NamedQuery(name = "Deviceip.findBySubneting", query = "SELECT d FROM Deviceip d WHERE d.subneting = :subneting")
    , @NamedQuery(name = "Deviceip.findByTimezone", query = "SELECT d FROM Deviceip d WHERE d.timezone = :timezone")})
public class Deviceip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ip_address", nullable = false, length = 450)
    private String ipAddress;
    @Column(name = "country", length = 450)
    private String country;
    @Column(name = "city", length = 450)
    private String city;
    @Column(name = "state", length = 450)
    private String state;
    @Column(name = "latitude", length = 450)
    private String latitude;
    @Column(name = "longitude", length = 450)
    private String longitude;
    @Column(name = "subneting", length = 450)
    private String subneting;
    @Column(name = "timezone", length = 450)
    private String timezone;
    @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Device deviceId;
    
    @Basic(optional = false)
    @Column(name = "Deviceip_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deviceipmodify;
    @Basic(optional = false)
    @Column(name = "Deviceip_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deviceipcreate;
    

    public Deviceip() {
    }

    public Deviceip(Integer id) {
        this.id = id;
    }

    public Deviceip(Integer id, String ipAddress) {
        this.id = id;
        this.ipAddress = ipAddress;
    }
    
    
    public Date getDeviceipmodify() {
        return deviceipmodify;
    }

    public void setDeviceipmodify(Date deviceipmodify) {
        this.deviceipmodify = deviceipmodify;
    }

    public Date getDeviceipcreate() {
        return deviceipcreate;
    }

    public void setDeviceipcreate(Date deviceipcreate) {
        this.deviceipcreate = deviceipcreate;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSubneting() {
        return subneting;
    }

    public void setSubneting(String subneting) {
        this.subneting = subneting;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
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
        if (!(object instanceof Deviceip)) {
            return false;
        }
        Deviceip other = (Deviceip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Deviceip[ id=" + id + " ]";
    }
    
}
