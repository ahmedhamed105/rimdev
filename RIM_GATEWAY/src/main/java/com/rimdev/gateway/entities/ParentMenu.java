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
@Table(name = "parent_menu", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore all null fields
@DynamicUpdate
@NamedQueries({ @NamedQuery(name = "ParentMenu.findAll", query = "SELECT p FROM ParentMenu p"),
		@NamedQuery(name = "ParentMenu.findById", query = "SELECT p FROM ParentMenu p WHERE p.id = :id"),
		@NamedQuery(name = "ParentMenu.findByPmenu", query = "SELECT p FROM ParentMenu p WHERE p.pmenu = :pmenu"),
		@NamedQuery(name = "ParentMenu.findByFaIcon", query = "SELECT p FROM ParentMenu p WHERE p.faIcon = :faIcon"),
		@NamedQuery(name = "ParentMenu.findByHasChild", query = "SELECT p FROM ParentMenu p WHERE p.hasChild = :hasChild"),
		@NamedQuery(name = "ParentMenu.findByParentLink", query = "SELECT p FROM ParentMenu p WHERE p.parentLink = :parentLink") })
public class ParentMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private Integer hasChild;
	@Column(name = "Parent_Link", length = 450)
	private String parentLink;

	@Column(name = "Pages_ID")
	private Integer pagesID;

	@Column(name = "date_modify", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModify;
	@Basic(optional = false)
	@Column(name = "date_create", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreate;

	public ParentMenu(String pmenu, String faIcon, Integer hasChild, String parentLink, Integer pagesID,
			Date dateModify, Date dateCreate) {
		super();
		this.pmenu = pmenu;
		this.faIcon = faIcon;
		this.hasChild = hasChild;
		this.parentLink = parentLink;
		this.pagesID = pagesID;
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
