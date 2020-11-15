package com.rimdev.rimlang.entities;


import java.io.Serializable;
import java.util.Date;

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
@Table(name = "menu_display", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "MenuDisplay.findAll", query = "SELECT m FROM MenuDisplay m")
    , @NamedQuery(name = "MenuDisplay.findById", query = "SELECT m FROM MenuDisplay m WHERE m.id = :id")
    , @NamedQuery(name = "MenuDisplay.findByMenuname", query = "SELECT m FROM MenuDisplay m WHERE m.menuname = :menuname")
    , @NamedQuery(name = "MenuDisplay.findByMenulink", query = "SELECT m FROM MenuDisplay m WHERE m.menulink = :menulink")
    , @NamedQuery(name = "MenuDisplay.findByFaIcon", query = "SELECT m FROM MenuDisplay m WHERE m.faIcon = :faIcon")})
public class MenuDisplay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Menu_name", nullable = false, length = 450)
    private String menuname;
    @Basic(optional = false)
    @Column(name = "Menu_link", nullable = false, length = 450)
    private String menulink;
    @Column(name = "fa_icon", length = 450)
    private String faIcon;
    @JoinColumn(name = "Pages_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Pages pagesID;
    @JoinColumn(name = "Parent_menu_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ParentMenu parentmenuID;
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public MenuDisplay() {
    }

    public MenuDisplay(Integer id) {
        this.id = id;
    }

    public MenuDisplay(Integer id, String menuname, String menulink) {
        this.id = id;
        this.menuname = menuname;
        this.menulink = menulink;
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

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenulink() {
        return menulink;
    }

    public void setMenulink(String menulink) {
        this.menulink = menulink;
    }

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    public Pages getPagesID() {
        return pagesID;
    }

    public void setPagesID(Pages pagesID) {
        this.pagesID = pagesID;
    }
    
    @XmlTransient
    @JsonIgnore 
    public ParentMenu getParentmenuID() {
        return parentmenuID;
    }

    public void setParentmenuID(ParentMenu parentmenuID) {
        this.parentmenuID = parentmenuID;
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
        if (!(object instanceof MenuDisplay)) {
            return false;
        }
        MenuDisplay other = (MenuDisplay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MenuDisplay[ id=" + id + " ]";
    }
    
}
