package com.rimdev.rimcart.entities;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "pages_priviledge", catalog = "rim_user", schema = "")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
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
    @JoinColumn(name = "Pages_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Pages pagesID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagespriviledgeID")
    private Collection<GroupPages> groupPagesCollection;

    public PagesPriviledge() {
    }

    public PagesPriviledge(Integer id) {
        this.id = id;
    }

    public PagesPriviledge(Integer id, int public1, int webDevice, int mobileDevice, int adminDevice, int isdesktop, int ismobile, int istablet) {
        this.id = id;
        this.public1 = public1;
        this.webDevice = webDevice;
        this.mobileDevice = mobileDevice;
        this.adminDevice = adminDevice;
        this.isdesktop = isdesktop;
        this.ismobile = ismobile;
        this.istablet = istablet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Pages getPagesID() {
        return pagesID;
    }

    public void setPagesID(Pages pagesID) {
        this.pagesID = pagesID;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GroupPages> getGroupPagesCollection() {
        return groupPagesCollection;
    }

    public void setGroupPagesCollection(Collection<GroupPages> groupPagesCollection) {
        this.groupPagesCollection = groupPagesCollection;
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
