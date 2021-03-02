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

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "application", catalog = "rim_user", schema = "")
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
