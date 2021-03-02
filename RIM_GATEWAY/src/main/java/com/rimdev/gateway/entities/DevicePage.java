package com.rimdev.gateway.entities;


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

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "device_page", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "DevicePage.findAll", query = "SELECT d FROM DevicePage d")
    , @NamedQuery(name = "DevicePage.findById", query = "SELECT d FROM DevicePage d WHERE d.id = :id")})
public class DevicePage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "DEVICE_ID")
    private Integer deviceId;
    @Column(name = "Pages_ID")
    private Integer pagesID;
    
    @Basic(optional = false)
    @Column(name = "Visit_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date visittime;
    
    @Column(name = "User_login_ID")
    private Integer userloginID;
    
    @Basic(optional = false)
    @Column(name = "page_tokean", nullable = false, length = 450)
    private String pageTokean;

   

	public DevicePage(Integer deviceId, Integer pagesID, Date visittime, Integer userloginID, String pageTokean) {
		super();
		this.deviceId = deviceId;
		this.pagesID = pagesID;
		this.visittime = visittime;
		this.userloginID = userloginID;
		this.pageTokean = pageTokean;
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