package com.rimdev.gateway.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "parent_priviledge", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore all null fields
@DynamicUpdate
@NamedQueries({ @NamedQuery(name = "ParentPriviledge.findAll", query = "SELECT p FROM ParentPriviledge p"),
		@NamedQuery(name = "ParentPriviledge.findById", query = "SELECT p FROM ParentPriviledge p WHERE p.id = :id"),
		@NamedQuery(name = "ParentPriviledge.findByPublic1", query = "SELECT p FROM ParentPriviledge p WHERE p.public1 = :public1"),
		@NamedQuery(name = "ParentPriviledge.findByWebDevice", query = "SELECT p FROM ParentPriviledge p WHERE p.webDevice = :webDevice"),
		@NamedQuery(name = "ParentPriviledge.findByMobileDevice", query = "SELECT p FROM ParentPriviledge p WHERE p.mobileDevice = :mobileDevice"),
		@NamedQuery(name = "ParentPriviledge.findByAdminDevice", query = "SELECT p FROM ParentPriviledge p WHERE p.adminDevice = :adminDevice"),
		@NamedQuery(name = "ParentPriviledge.findByIsdesktop", query = "SELECT p FROM ParentPriviledge p WHERE p.isdesktop = :isdesktop"),
		@NamedQuery(name = "ParentPriviledge.findByIsmobile", query = "SELECT p FROM ParentPriviledge p WHERE p.ismobile = :ismobile"),
		@NamedQuery(name = "ParentPriviledge.findByIstablet", query = "SELECT p FROM ParentPriviledge p WHERE p.istablet = :istablet") })
public class ParentPriviledge implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@Column(name = "public", nullable = false)
	private Integer public1;
	@Basic(optional = false)
	@Column(name = "Web_Device", nullable = false)
	private Integer webDevice;
	@Basic(optional = false)
	@Column(name = "Mobile_Device", nullable = false)
	private Integer mobileDevice;
	@Basic(optional = false)
	@Column(name = "Admin_Device", nullable = false)
	private Integer adminDevice;
	@Basic(optional = false)
	@Column(name = "Isdesktop", nullable = false)
	private Boolean isdesktop;
	@Basic(optional = false)
	@Column(name = "Ismobile", nullable = false)
	private Boolean ismobile;
	@Basic(optional = false)
	@Column(name = "Istablet", nullable = false)
	private Boolean istablet;
	@Column(name = "parent_component_ID")
	private Integer parentcomponentID;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ParentPriviledge)) {
			return false;
		}
		ParentPriviledge other = (ParentPriviledge) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "entity.ParentPriviledge[ id=" + id + " ]";
	}

}
