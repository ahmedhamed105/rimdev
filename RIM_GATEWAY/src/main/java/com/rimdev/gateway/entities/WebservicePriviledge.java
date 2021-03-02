package com.rimdev.gateway.entities;


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "webservice_priviledge", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "WebservicePriviledge.findAll", query = "SELECT w FROM WebservicePriviledge w")
    , @NamedQuery(name = "WebservicePriviledge.findById", query = "SELECT w FROM WebservicePriviledge w WHERE w.id = :id")
    , @NamedQuery(name = "WebservicePriviledge.findByWebService", query = "SELECT w FROM WebservicePriviledge w WHERE w.webService = :webService")
    , @NamedQuery(name = "WebservicePriviledge.findByPublic1", query = "SELECT w FROM WebservicePriviledge w WHERE w.public1 = :public1")
    , @NamedQuery(name = "WebservicePriviledge.findByWebDevice", query = "SELECT w FROM WebservicePriviledge w WHERE w.webDevice = :webDevice")
    , @NamedQuery(name = "WebservicePriviledge.findByMobileDevice", query = "SELECT w FROM WebservicePriviledge w WHERE w.mobileDevice = :mobileDevice")
    , @NamedQuery(name = "WebservicePriviledge.findByAdminDevice", query = "SELECT w FROM WebservicePriviledge w WHERE w.adminDevice = :adminDevice")})
public class WebservicePriviledge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Web_Service", nullable = false, length = 45)
    private String webService;
    @Basic(optional = false)
    @Column(name = "public", nullable = false)
    private Integer public1;
    @Basic(optional = false)
    @Column(name = "Web_Device", nullable = false)
    private Integer webDevice;
    @Basic(optional = false)
    @Column(name = "Mobile_Device", nullable = false)
    private Integer mobileDevice;
    @Basic(optional = false)
    @Column(name = "Admin_Device", nullable = false)
    private Integer adminDevice;
    @Basic(optional = false)
    @Column(name = "Isdesktop", nullable = false)
    private boolean isdesktop;
    @Basic(optional = false)
    @Column(name = "Ismobile", nullable = false)
    private boolean ismobile;
    @Basic(optional = false)
    @Column(name = "Istablet", nullable = false)
    private boolean istablet;

    
    

    public WebservicePriviledge(String webService, Integer public1, Integer webDevice, Integer mobileDevice,
			Integer adminDevice, boolean isdesktop, boolean ismobile, boolean istablet) {
		super();
		this.webService = webService;
		this.public1 = public1;
		this.webDevice = webDevice;
		this.mobileDevice = mobileDevice;
		this.adminDevice = adminDevice;
		this.isdesktop = isdesktop;
		this.ismobile = ismobile;
		this.istablet = istablet;
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
        if (!(object instanceof WebservicePriviledge)) {
            return false;
        }
        WebservicePriviledge other = (WebservicePriviledge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WebservicePriviledge[ id=" + id + " ]";
    }
    
}
