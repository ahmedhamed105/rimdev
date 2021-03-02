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
@Table(name = "pages", catalog = "rim_user", schema = "rim_user")
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Page_name", length = 45)
    private String pagename;
    @Column(name = "Page_menu", length = 45)
    private String pagemenu;

    
    @Column(name = "Link_router", length = 450)
    private String linkrouter;

    
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    
    @Column(name = "image_flag", nullable = false)
    private Integer imageFlag;
    @Column(name = "files_upload_ID")
    private Integer filesuploadID;
    

  

    public Pages(String pagename, String pagemenu, String linkrouter, Date dateModify, Date dateCreate,
			Integer imageFlag, Integer filesuploadID) {
		super();
		this.pagename = pagename;
		this.pagemenu = pagemenu;
		this.linkrouter = linkrouter;
		this.dateModify = dateModify;
		this.dateCreate = dateCreate;
		this.imageFlag = imageFlag;
		this.filesuploadID = filesuploadID;
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