package com.rimdev.product.entities;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "product_image", catalog = "rim_product", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductImage.findAll", query = "SELECT p FROM ProductImage p")
    , @NamedQuery(name = "ProductImage.findById", query = "SELECT p FROM ProductImage p WHERE p.id = :id")
    , @NamedQuery(name = "ProductImage.findByImageId", query = "SELECT p FROM ProductImage p WHERE p.imageId = :imageId")})
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "image_id", nullable = false)
    private int imageId;
    @JoinColumn(name = "Product_main_Product", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ProductMain productmainProduct;

    public ProductImage() {
    }

    public ProductImage(Integer id) {
        this.id = id;
    }

    public ProductImage(Integer id, int imageId) {
        this.id = id;
        this.imageId = imageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
        if (!(object instanceof ProductImage)) {
            return false;
        }
        ProductImage other = (ProductImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.ProductImage[ id=" + id + " ]";
    }
    
}
