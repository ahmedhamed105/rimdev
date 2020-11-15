package com.rimdev.rimlang.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "pages", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Pages.findAll", query = "SELECT p FROM Pages p")
    , @NamedQuery(name = "Pages.findById", query = "SELECT p FROM Pages p WHERE p.id = :id")
    , @NamedQuery(name = "Pages.findByPagename", query = "SELECT p FROM Pages p WHERE p.pagename = :pagename")
    , @NamedQuery(name = "Pages.findByPagemenu", query = "SELECT p FROM Pages p WHERE p.pagemenu = :pagemenu")})
public class Pages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Page_name", length = 45)
    private String pagename;
    @Column(name = "Page_menu", length = 45)
    private String pagemenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagesID")
    private Collection<DevicePage> devicePageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagesID")
    private Collection<ParentComponent> parentComponentCollection;
    
    @Column(name = "Link_router", length = 450)
    private String linkrouter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagesID")
    private Collection<MenuDisplay> menuDisplayCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagesID")
    private Collection<ParentMenu> parentMenuCollection;
    
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    
    @Column(name = "image_flag", nullable = false)
    private int imageFlag;
    @JoinColumn(name = "files_upload_ID", referencedColumnName = "ID")
    @ManyToOne
    private FilesUpload filesuploadID;
    

    public Pages() {
    }

    public Pages(Integer id) {
        this.id = id;
    }
    
    public int getImageFlag() {
        return imageFlag;
    }

    public void setImageFlag(int imageFlag) {
        this.imageFlag = imageFlag;
    }

    public FilesUpload getFilesuploadID() {
        return filesuploadID;
    }

    public void setFilesuploadID(FilesUpload filesuploadID) {
        this.filesuploadID = filesuploadID;
    }
    
    
    @XmlTransient
    @JsonIgnore
    public Date getDateModify() {
        return dateModify;
    }

    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }
    
    @XmlTransient
    @JsonIgnore
    public Date getDateCreate() {
        return dateCreate;
    }
    
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public String getPagemenu() {
        return pagemenu;
    }

    public void setPagemenu(String pagemenu) {
        this.pagemenu = pagemenu;
    }

    @XmlTransient
    @JsonIgnore   
    public Collection<DevicePage> getDevicePageCollection() {
        return devicePageCollection;
    }

    public void setDevicePageCollection(Collection<DevicePage> devicePageCollection) {
        this.devicePageCollection = devicePageCollection;
    }
    
    
    @XmlTransient
    @JsonIgnore 
    public Collection<ParentComponent> getParentComponentCollection() {
        return parentComponentCollection;
    }

    public void setParentComponentCollection(Collection<ParentComponent> parentComponentCollection) {
        this.parentComponentCollection = parentComponentCollection;
    }

    
    public String getLinkrouter() {
        return linkrouter;
    }

    public void setLinkrouter(String linkrouter) {
        this.linkrouter = linkrouter;
    }

    @XmlTransient
    @JsonIgnore 
    public Collection<MenuDisplay> getMenuDisplayCollection() {
        return menuDisplayCollection;
    }

    public void setMenuDisplayCollection(Collection<MenuDisplay> menuDisplayCollection) {
        this.menuDisplayCollection = menuDisplayCollection;
    }

    @XmlTransient
    @JsonIgnore 
    public Collection<ParentMenu> getParentMenuCollection() {
        return parentMenuCollection;
    }

    public void setParentMenuCollection(Collection<ParentMenu> parentMenuCollection) {
        this.parentMenuCollection = parentMenuCollection;
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
        if (!(object instanceof Pages)) {
            return false;
        }
        Pages other = (Pages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pages[ id=" + id + " ]";
    }
    
}