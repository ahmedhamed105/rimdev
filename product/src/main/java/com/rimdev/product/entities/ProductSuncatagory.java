package com.rimdev.product.entities;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "product_suncatagory", catalog = "rim_product", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductSuncatagory.findAll", query = "SELECT p FROM ProductSuncatagory p")
    , @NamedQuery(name = "ProductSuncatagory.findById", query = "SELECT p FROM ProductSuncatagory p WHERE p.id = :id")})
public class ProductSuncatagory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "Product_main_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ProductMain productmainID;
    @JoinColumn(name = "sub_catalog_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private SubCatalog subcatalogID;

    public ProductSuncatagory() {
    }

    public ProductSuncatagory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductMain getProductmainID() {
        return productmainID;
    }

    public void setProductmainID(ProductMain productmainID) {
        this.productmainID = productmainID;
    }

    public SubCatalog getSubcatalogID() {
        return subcatalogID;
    }

    public void setSubcatalogID(SubCatalog subcatalogID) {
        this.subcatalogID = subcatalogID;
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
        if (!(object instanceof ProductSuncatagory)) {
            return false;
        }
        ProductSuncatagory other = (ProductSuncatagory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.ProductSuncatagory[ id=" + id + " ]";
    }
    
}
