package com.rimdev.product.entities;



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
@Table(name = "main_catalog", catalog = "rim_product", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MainCatalog.findAll", query = "SELECT m FROM MainCatalog m")
    , @NamedQuery(name = "MainCatalog.findById", query = "SELECT m FROM MainCatalog m WHERE m.id = :id")
    , @NamedQuery(name = "MainCatalog.findByCatalogname", query = "SELECT m FROM MainCatalog m WHERE m.catalogname = :catalogname")
    , @NamedQuery(name = "MainCatalog.findByCataloginfo", query = "SELECT m FROM MainCatalog m WHERE m.cataloginfo = :cataloginfo")})
public class MainCatalog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Catalog_name", nullable = false, length = 45)
    private String catalogname;
    @Column(name = "Catalog_info", length = 4500)
    private String cataloginfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maincatalogID")
    private Collection<SubCatalog> subCatalogCollection;

    public MainCatalog() {
    }

    public MainCatalog(Integer id) {
        this.id = id;
    }

    public MainCatalog(Integer id, String catalogname) {
        this.id = id;
        this.catalogname = catalogname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatalogname() {
        return catalogname;
    }

    public void setCatalogname(String catalogname) {
        this.catalogname = catalogname;
    }

    public String getCataloginfo() {
        return cataloginfo;
    }

    public void setCataloginfo(String cataloginfo) {
        this.cataloginfo = cataloginfo;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SubCatalog> getSubCatalogCollection() {
        return subCatalogCollection;
    }

    public void setSubCatalogCollection(Collection<SubCatalog> subCatalogCollection) {
        this.subCatalogCollection = subCatalogCollection;
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
        if (!(object instanceof MainCatalog)) {
            return false;
        }
        MainCatalog other = (MainCatalog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.MainCatalog[ id=" + id + " ]";
    }
    
}

