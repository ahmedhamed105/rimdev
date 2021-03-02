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
@Table(name = "pages_priviledge", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "PagesPriviledge.findAll", query = "SELECT p FROM PagesPriviledge p")
    , @NamedQuery(name = "PagesPriviledge.findById", query = "SELECT p FROM PagesPriviledge p WHERE p.id = :id")
    , @NamedQuery(name = "PagesPriviledge.findByPublic1", query = "SELECT p FROM PagesPriviledge p WHERE p.public1 = :public1")
    , @NamedQuery(name = "PagesPriviledge.findByWebDevice", query = "SELECT p FROM PagesPriviledge p WHERE p.webDevice = :webDevice")
    , @NamedQuery(name = "PagesPriviledge.findByMobileDevice", query = "SELECT p FROM PagesPriviledge p WHERE p.mobileDevice = :mobileDevice")
    , @NamedQuery(name = "PagesPriviledge.findByAdminDevice", query = "SELECT p FROM PagesPriviledge p WHERE p.adminDevice = :adminDevice")
    , @NamedQuery(name = "PagesPriviledge.findByIsdesktop", query = "SELECT p FROM PagesPriviledge p WHERE p.isdesktop = :isdesktop")
    , @NamedQuery(name = "PagesPriviledge.findByIsmobile", query = "SELECT p FROM PagesPriviledge p WHERE p.ismobile = :ismobile")
    , @NamedQuery(name = "PagesPriviledge.findByIstablet", query = "SELECT p FROM PagesPriviledge p WHERE p.istablet = :istablet")})
public class PagesPriviledge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
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
    private Integer isdesktop;
    @Basic(optional = false)
    @Column(name = "Ismobile", nullable = false)
    private Integer ismobile;
    @Basic(optional = false)
    @Column(name = "Istablet", nullable = false)
    private Integer istablet;
    @Column(name = "Pages_ID")
    private Integer pagesID;

  

    public PagesPriviledge(Integer public1, Integer webDevice, Integer mobileDevice, Integer adminDevice,
			Integer isdesktop, Integer ismobile, Integer istablet, Integer pagesID) {
		super();
		this.public1 = public1;
		this.webDevice = webDevice;
		this.mobileDevice = mobileDevice;
		this.adminDevice = adminDevice;
		this.isdesktop = isdesktop;
		this.ismobile = ismobile;
		this.istablet = istablet;
		this.pagesID = pagesID;
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
        if (!(object instanceof PagesPriviledge)) {
            return false;
        }
        PagesPriviledge other = (PagesPriviledge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PagesPriviledge[ id=" + id + " ]";
    }
    
}
