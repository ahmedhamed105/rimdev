package com.rimdev.rimpriv.entities;


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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "application", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a")
    , @NamedQuery(name = "Application.findById", query = "SELECT a FROM Application a WHERE a.id = :id")
    , @NamedQuery(name = "Application.findByAppname", query = "SELECT a FROM Application a WHERE a.appname = :appname")
    , @NamedQuery(name = "Application.findByAppsearch", query = "SELECT a FROM Application a WHERE a.appsearch = :appsearch")
    , @NamedQuery(name = "Application.findBySearchWebserv", query = "SELECT a FROM Application a WHERE a.searchWebserv = :searchWebserv")
    , @NamedQuery(name = "Application.findByNotificationFalg", query = "SELECT a FROM Application a WHERE a.notificationFalg = :notificationFalg")})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "App_name", nullable = false, length = 450)
    private String appname;
    @Basic(optional = false)
    @Column(name = "App_search", nullable = false)
    private int appsearch;
    @Column(name = "search_webserv", length = 450)
    private String searchWebserv;
    @Basic(optional = false)
    @Column(name = "notification_falg", nullable = false)
    private int notificationFalg;
    @Basic(optional = false)
    @Column(name = "footer_html", nullable = false, length = 10000)
    private String footerHtml;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationID")
    private Collection<Notification> notificationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationID")
    private Collection<UserLogin> userLoginCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationID")
    private Collection<Device> deviceCollection;

    public Application() {
    }
    

    public Application(Integer id) {
        this.id = id;
    }

    public Application(Integer id, String appname, int appsearch, int notificationFalg) {
        this.id = id;
        this.appname = appname;
        this.appsearch = appsearch;
        this.notificationFalg = notificationFalg;
    }
    
    

    public String getFooterHtml() {
		return footerHtml;
	}

	public void setFooterHtml(String footerHtml) {
		this.footerHtml = footerHtml;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public int getAppsearch() {
        return appsearch;
    }

    public void setAppsearch(int appsearch) {
        this.appsearch = appsearch;
    }

    public String getSearchWebserv() {
        return searchWebserv;
    }

    public void setSearchWebserv(String searchWebserv) {
        this.searchWebserv = searchWebserv;
    }

    public int getNotificationFalg() {
        return notificationFalg;
    }

    public void setNotificationFalg(int notificationFalg) {
        this.notificationFalg = notificationFalg;
    }

	@XmlTransient
    @JsonIgnore
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

	@XmlTransient
    @JsonIgnore
    public Collection<UserLogin> getUserLoginCollection() {
        return userLoginCollection;
    }

    public void setUserLoginCollection(Collection<UserLogin> userLoginCollection) {
        this.userLoginCollection = userLoginCollection;
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
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Application[ id=" + id + " ]";
    }
    
}
