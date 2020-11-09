package com.rimdev.rimpages.entities;


import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "webservice_priviledge", catalog = "rim_user", schema = "")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Web_Service", nullable = false, length = 45)
    private String webService;
    @Basic(optional = false)
    @Column(name = "public", nullable = false)
    private int public1;
    @Basic(optional = false)
    @Column(name = "Web_Device", nullable = false)
    private int webDevice;
    @Basic(optional = false)
    @Column(name = "Mobile_Device", nullable = false)
    private int mobileDevice;
    @Basic(optional = false)
    @Column(name = "Admin_Device", nullable = false)
    private int adminDevice;
    @Basic(optional = false)
    @Column(name = "Isdesktop", nullable = false)
    private int isdesktop;
    @Basic(optional = false)
    @Column(name = "Ismobile", nullable = false)
    private int ismobile;
    @Basic(optional = false)
    @Column(name = "Istablet", nullable = false)
    private int istablet;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "webservicepriviledgeID")
    private Collection<GroupWeb> groupWebCollection;

    public WebservicePriviledge() {
    }

    public WebservicePriviledge(Integer id) {
        this.id = id;
    }

    public WebservicePriviledge(Integer id, String webService, int public1, int webDevice, int mobileDevice, int adminDevice) {
        this.id = id;
        this.webService = webService;
        this.public1 = public1;
        this.webDevice = webDevice;
        this.mobileDevice = mobileDevice;
        this.adminDevice = adminDevice;
    }
    
    public int getIsdesktop() {
        return isdesktop;
    }

    public void setIsdesktop(int isdesktop) {
        this.isdesktop = isdesktop;
    }

    public int getIsmobile() {
        return ismobile;
    }

    public void setIsmobile(int ismobile) {
        this.ismobile = ismobile;
    }

    public int getIstablet() {
        return istablet;
    }

    public void setIstablet(int istablet) {
        this.istablet = istablet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebService() {
        return webService;
    }

    public void setWebService(String webService) {
        this.webService = webService;
    }

    public int getPublic1() {
        return public1;
    }

    public void setPublic1(int public1) {
        this.public1 = public1;
    }

    public int getWebDevice() {
        return webDevice;
    }

    public void setWebDevice(int webDevice) {
        this.webDevice = webDevice;
    }

    public int getMobileDevice() {
        return mobileDevice;
    }

    public void setMobileDevice(int mobileDevice) {
        this.mobileDevice = mobileDevice;
    }

    public int getAdminDevice() {
        return adminDevice;
    }

    public void setAdminDevice(int adminDevice) {
        this.adminDevice = adminDevice;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GroupWeb> getGroupWebCollection() {
        return groupWebCollection;
    }

    public void setGroupWebCollection(Collection<GroupWeb> groupWebCollection) {
        this.groupWebCollection = groupWebCollection;
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
