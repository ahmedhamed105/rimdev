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
@Table(name = "product_comments", catalog = "rim_product", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductComments.findAll", query = "SELECT p FROM ProductComments p")
    , @NamedQuery(name = "ProductComments.findById", query = "SELECT p FROM ProductComments p WHERE p.id = :id")
    , @NamedQuery(name = "ProductComments.findByComments", query = "SELECT p FROM ProductComments p WHERE p.comments = :comments")
    , @NamedQuery(name = "ProductComments.findByRating", query = "SELECT p FROM ProductComments p WHERE p.rating = :rating")
    , @NamedQuery(name = "ProductComments.findByUserid", query = "SELECT p FROM ProductComments p WHERE p.userid = :userid")})
public class ProductComments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "comments", nullable = false, length = 4500)
    private String comments;
    @Basic(optional = false)
    @Column(name = "rating", nullable = false)
    private int rating;
    @Column(name = "User_id")
    private Integer userid;
    @JoinColumn(name = "Product_main_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ProductMain productmainID;

    public ProductComments() {
    }

    public ProductComments(Integer id) {
        this.id = id;
    }

    public ProductComments(Integer id, String comments, int rating) {
        this.id = id;
        this.comments = comments;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public ProductMain getProductmainID() {
        return productmainID;
    }

    public void setProductmainID(ProductMain productmainID) {
        this.productmainID = productmainID;
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
        if (!(object instanceof ProductComments)) {
            return false;
        }
        ProductComments other = (ProductComments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.ProductComments[ id=" + id + " ]";
    }
    
}

