package com.rimdev.user.entities;


import java.io.Serializable;
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
import java.util.Date;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "device_page", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "DevicePage.findAll", query = "SELECT d FROM DevicePage d")
    , @NamedQuery(name = "DevicePage.findById", query = "SELECT d FROM DevicePage d WHERE d.id = :id")})
public class DevicePage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Device deviceId;
    @JoinColumn(name = "Pages_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Pages pagesID;
    
    @Basic(optional = false)
    @Column(name = "Visit_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date visittime;

    public DevicePage() {
    }

    public DevicePage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
    }

    public Pages getPagesID() {
        return pagesID;
    }

    public void setPagesID(Pages pagesID) {
        this.pagesID = pagesID;
    }
    

    public Date getVisittime() {
		return visittime;
	}

	public void setVisittime(Date visittime) {
		this.visittime = visittime;
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
        if (!(object instanceof DevicePage)) {
            return false;
        }
        DevicePage other = (DevicePage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DevicePage[ id=" + id + " ]";
    }
    
}