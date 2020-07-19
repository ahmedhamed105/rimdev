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
@Table(name = "product_catagory", catalog = "rim_product", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCatagory.findAll", query = "SELECT p FROM ProductCatagory p")
    , @NamedQuery(name = "ProductCatagory.findById", query = "SELECT p FROM ProductCatagory p WHERE p.id = :id")})
public class ProductCatagory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "Main_catalog_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private MainCatalog maincatalogID;
    @JoinColumn(name = "Product_main_Product", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ProductMain productmainProduct;

    public ProductCatagory() {
    }

    public ProductCatagory(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MainCatalog getMaincatalogID() {
        return maincatalogID;
    }

    public void setMaincatalogID(MainCatalog maincatalogID) {
        this.maincatalogID = maincatalogID;
    }

    public ProductMain getProductmainProduct() {
        return productmainProduct;
    }

    public void setProductmainProduct(ProductMain productmainProduct) {
        this.productmainProduct = productmainProduct;
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
        if (!(object instanceof ProductCatagory)) {
            return false;
        }
        ProductCatagory other = (ProductCatagory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.ProductCatagory[ id=" + id + " ]";
    }
    
}
