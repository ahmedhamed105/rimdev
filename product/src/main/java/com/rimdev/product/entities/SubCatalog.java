package com.rimdev.product.entities;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "sub_catalog", catalog = "rim_product", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCatalog.findAll", query = "SELECT s FROM SubCatalog s")
    , @NamedQuery(name = "SubCatalog.findById", query = "SELECT s FROM SubCatalog s WHERE s.id = :id")
    , @NamedQuery(name = "SubCatalog.findBySubcatalogName", query = "SELECT s FROM SubCatalog s WHERE s.subcatalogName = :subcatalogName")
    , @NamedQuery(name = "SubCatalog.findBySubcatalogInfo", query = "SELECT s FROM SubCatalog s WHERE s.subcatalogInfo = :subcatalogInfo")})
public class SubCatalog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "subcatalog_name", nullable = false, length = 45)
    private String subcatalogName;
    @Column(name = "subcatalog_info", length = 4500)
    private String subcatalogInfo;
    @JoinColumn(name = "Main_catalog_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private MainCatalog maincatalogID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subcatalogID")
    private Collection<ProductSuncatagory> productSuncatagoryCollection;

    public SubCatalog() {
    }

    public SubCatalog(Integer id) {
        this.id = id;
    }

    public SubCatalog(Integer id, String subcatalogName) {
        this.id = id;
        this.subcatalogName = subcatalogName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubcatalogName() {
        return subcatalogName;
    }

    public void setSubcatalogName(String subcatalogName) {
        this.subcatalogName = subcatalogName;
    }

    public String getSubcatalogInfo() {
        return subcatalogInfo;
    }

    public void setSubcatalogInfo(String subcatalogInfo) {
        this.subcatalogInfo = subcatalogInfo;
    }

    public MainCatalog getMaincatalogID() {
        return maincatalogID;
    }

    public void setMaincatalogID(MainCatalog maincatalogID) {
        this.maincatalogID = maincatalogID;
    }

    @XmlTransient
    public Collection<ProductSuncatagory> getProductSuncatagoryCollection() {
        return productSuncatagoryCollection;
    }

    public void setProductSuncatagoryCollection(Collection<ProductSuncatagory> productSuncatagoryCollection) {
        this.productSuncatagoryCollection = productSuncatagoryCollection;
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
        if (!(object instanceof SubCatalog)) {
            return false;
        }
        SubCatalog other = (SubCatalog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.SubCatalog[ id=" + id + " ]";
    }
    
}
