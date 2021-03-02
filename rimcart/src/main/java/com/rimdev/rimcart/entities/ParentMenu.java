package com.rimdev.rimcart.entities;


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
@Table(name = "parent_menu", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "ParentMenu.findAll", query = "SELECT p FROM ParentMenu p")
    , @NamedQuery(name = "ParentMenu.findById", query = "SELECT p FROM ParentMenu p WHERE p.id = :id")
    , @NamedQuery(name = "ParentMenu.findByPmenu", query = "SELECT p FROM ParentMenu p WHERE p.pmenu = :pmenu")
    , @NamedQuery(name = "ParentMenu.findByFaIcon", query = "SELECT p FROM ParentMenu p WHERE p.faIcon = :faIcon")
    , @NamedQuery(name = "ParentMenu.findByHasChild", query = "SELECT p FROM ParentMenu p WHERE p.hasChild = :hasChild")
    , @NamedQuery(name = "ParentMenu.findByParentLink", query = "SELECT p FROM ParentMenu p WHERE p.parentLink = :parentLink")})
public class ParentMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "P_menu", nullable = false, length = 450)
    private String pmenu;
    @Basic(optional = false)
    @Column(name = "fa_icon", nullable = false, length = 450)
    private String faIcon;
    @Basic(optional = false)
    @Column(name = "has_child", nullable = false)
    private int hasChild;
    @Column(name = "Parent_Link", length = 450)
    private String parentLink;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentmenuID")
    private Collection<MenuDisplay> menuDisplayCollection;
    
    @JoinColumn(name = "Pages_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Pages pagesID;
    
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public ParentMenu() {
    }

    public ParentMenu(Integer id) {
        this.id = id;
    }

    public ParentMenu(Integer id, String pmenu, String faIcon, int hasChild) {
        this.id = id;
        this.pmenu = pmenu;
        this.faIcon = faIcon;
        this.hasChild = hasChild;
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

    public String getPmenu() {
        return pmenu;
    }

    public void setPmenu(String pmenu) {
        this.pmenu = pmenu;
    }

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    public int getHasChild() {
        return hasChild;
    }

    public void setHasChild(int hasChild) {
        this.hasChild = hasChild;
    }

    public String getParentLink() {
        return parentLink;
    }

    public void setParentLink(String parentLink) {
        this.parentLink = parentLink;
    }

    @XmlTransient
    @JsonIgnore 
    public Collection<MenuDisplay> getMenuDisplayCollection() {
        return menuDisplayCollection;
    }

    public void setMenuDisplayCollection(Collection<MenuDisplay> menuDisplayCollection) {
        this.menuDisplayCollection = menuDisplayCollection;
    }

    public Pages getPagesID() {
        return pagesID;
    }

    public void setPagesID(Pages pagesID) {
        this.pagesID = pagesID;
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
        if (!(object instanceof ParentMenu)) {
            return false;
        }
        ParentMenu other = (ParentMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParentMenu[ id=" + id + " ]";
    }
    
}
