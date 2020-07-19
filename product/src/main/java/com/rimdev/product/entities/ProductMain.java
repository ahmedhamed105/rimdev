package com.rimdev.product.entities;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "product_main", catalog = "rim_product", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductMain.findAll", query = "SELECT p FROM ProductMain p")
    , @NamedQuery(name = "ProductMain.findById", query = "SELECT p FROM ProductMain p WHERE p.id = :id")
    , @NamedQuery(name = "ProductMain.findByProductName", query = "SELECT p FROM ProductMain p WHERE p.productName = :productName")
    , @NamedQuery(name = "ProductMain.findByProductIcon", query = "SELECT p FROM ProductMain p WHERE p.productIcon = :productIcon")
    , @NamedQuery(name = "ProductMain.findByProductParagraph", query = "SELECT p FROM ProductMain p WHERE p.productParagraph = :productParagraph")
    , @NamedQuery(name = "ProductMain.findByProductPrice", query = "SELECT p FROM ProductMain p WHERE p.productPrice = :productPrice")
    , @NamedQuery(name = "ProductMain.findByProductSale", query = "SELECT p FROM ProductMain p WHERE p.productSale = :productSale")
    , @NamedQuery(name = "ProductMain.findByUsercreated", query = "SELECT p FROM ProductMain p WHERE p.usercreated = :usercreated")
    , @NamedQuery(name = "ProductMain.findByCreatedDate", query = "SELECT p FROM ProductMain p WHERE p.createdDate = :createdDate")
    , @NamedQuery(name = "ProductMain.findByModifiedDate", query = "SELECT p FROM ProductMain p WHERE p.modifiedDate = :modifiedDate")})
public class ProductMain implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "product_name", nullable = false, length = 450)
    private String productName;
    @Column(name = "product_Icon", length = 45)
    private String productIcon;
    @Column(name = "product_paragraph", length = 5000)
    private String productParagraph;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "product_price", nullable = false, precision = 20, scale = 6)
    private BigDecimal productPrice;
    @Basic(optional = false)
    @Column(name = "product_sale", nullable = false)
    private int productSale;
    @Basic(optional = false)
    @Column(name = "User_created", nullable = false)
    private int usercreated;
    @Basic(optional = false)
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "modified_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productmainID")
    private Collection<ProductComments> productCommentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productmainProduct")
    private Collection<ProductImage> productImageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productmainID")
    private Collection<ProductSuncatagory> productSuncatagoryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productmainProduct")
    private Collection<ProductCatagory> productCatagoryCollection;

    public ProductMain() {
    }

    public ProductMain(Integer id) {
        this.id = id;
    }

    public ProductMain(Integer id, String productName, BigDecimal productPrice, int productSale, int usercreated, Date createdDate, Date modifiedDate) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productSale = productSale;
        this.usercreated = usercreated;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public String getProductParagraph() {
        return productParagraph;
    }

    public void setProductParagraph(String productParagraph) {
        this.productParagraph = productParagraph;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductSale() {
        return productSale;
    }

    public void setProductSale(int productSale) {
        this.productSale = productSale;
    }

    public int getUsercreated() {
        return usercreated;
    }

    public void setUsercreated(int usercreated) {
        this.usercreated = usercreated;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @XmlTransient
    public Collection<ProductComments> getProductCommentsCollection() {
        return productCommentsCollection;
    }

    public void setProductCommentsCollection(Collection<ProductComments> productCommentsCollection) {
        this.productCommentsCollection = productCommentsCollection;
    }

    @XmlTransient
    public Collection<ProductImage> getProductImageCollection() {
        return productImageCollection;
    }

    public void setProductImageCollection(Collection<ProductImage> productImageCollection) {
        this.productImageCollection = productImageCollection;
    }

    @XmlTransient
    public Collection<ProductSuncatagory> getProductSuncatagoryCollection() {
        return productSuncatagoryCollection;
    }

    public void setProductSuncatagoryCollection(Collection<ProductSuncatagory> productSuncatagoryCollection) {
        this.productSuncatagoryCollection = productSuncatagoryCollection;
    }

    @XmlTransient
    public Collection<ProductCatagory> getProductCatagoryCollection() {
        return productCatagoryCollection;
    }

    public void setProductCatagoryCollection(Collection<ProductCatagory> productCatagoryCollection) {
        this.productCatagoryCollection = productCatagoryCollection;
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
        if (!(object instanceof ProductMain)) {
            return false;
        }
        ProductMain other = (ProductMain) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "product.ProductMain[ id=" + id + " ]";
    }
    
}
