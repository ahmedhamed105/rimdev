package com.rimdev.gateway.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "Pages_ID")
    private Integer pagesID;
    @Column(name = "Parent_menu_ID")
    private Integer parentmenuID;
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

   

    public MenuDisplay(String menuname, String menulink, String faIcon, Integer pagesID, Integer parentmenuID,
			Date dateModify, Date dateCreate) {
		super();
		this.menuname = menuname;
		this.menulink = menulink;
		this.faIcon = faIcon;
		this.pagesID = pagesID;
		this.parentmenuID = parentmenuID;
		this.dateModify = dateModify;
		this.dateCreate = dateCreate;
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
