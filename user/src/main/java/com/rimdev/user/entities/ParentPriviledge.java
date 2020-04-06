package com.rimdev.user.entities;


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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "parent_priviledge", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentPriviledge.findAll", query = "SELECT p FROM ParentPriviledge p")
    , @NamedQuery(name = "ParentPriviledge.findById", query = "SELECT p FROM ParentPriviledge p WHERE p.id = :id")
    , @NamedQuery(name = "ParentPriviledge.findByPublic1", query = "SELECT p FROM ParentPriviledge p WHERE p.public1 = :public1")
    , @NamedQuery(name = "ParentPriviledge.findByWebDevice", query = "SELECT p FROM ParentPriviledge p WHERE p.webDevice = :webDevice")
    , @NamedQuery(name = "ParentPriviledge.findByMobileDevice", query = "SELECT p FROM ParentPriviledge p WHERE p.mobileDevice = :mobileDevice")
    , @NamedQuery(name = "ParentPriviledge.findByAdminDevice", query = "SELECT p FROM ParentPriviledge p WHERE p.adminDevice = :adminDevice")
    , @NamedQuery(name = "ParentPriviledge.findByIsdesktop", query = "SELECT p FROM ParentPriviledge p WHERE p.isdesktop = :isdesktop")
    , @NamedQuery(name = "ParentPriviledge.findByIsmobile", query = "SELECT p FROM ParentPriviledge p WHERE p.ismobile = :ismobile")
    , @NamedQuery(name = "ParentPriviledge.findByIstablet", query = "SELECT p FROM ParentPriviledge p WHERE p.istablet = :istablet")})
public class ParentPriviledge implements Serializable {

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
    @JoinColumn(name = "parent_component_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ParentComponent parentcomponentID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentpriviledgeID")
    private Collection<GroupParent> groupParentCollection;

    public ParentPriviledge() {
    }

    public ParentPriviledge(Integer id) {
        this.id = id;
    }

    public ParentPriviledge(Integer id, int public1, int webDevice, int mobileDevice, int adminDevice, int isdesktop, int ismobile, int istablet) {
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

    public ParentComponent getParentcomponentID() {
        return parentcomponentID;
    }

    public void setParentcomponentID(ParentComponent parentcomponentID) {
        this.parentcomponentID = parentcomponentID;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GroupParent> getGroupParentCollection() {
        return groupParentCollection;
    }

    public void setGroupParentCollection(Collection<GroupParent> groupParentCollection) {
        this.groupParentCollection = groupParentCollection;
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
        if (!(object instanceof ParentPriviledge)) {
            return false;
        }
        ParentPriviledge other = (ParentPriviledge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParentPriviledge[ id=" + id + " ]";
    }
    
}
