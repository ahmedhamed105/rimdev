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
@Table(name = "group_web", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "GroupWeb.findAll", query = "SELECT g FROM GroupWeb g")
    , @NamedQuery(name = "GroupWeb.findById", query = "SELECT g FROM GroupWeb g WHERE g.id = :id")})
public class GroupWeb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Group_priviledge_ID")
    private Integer grouppriviledgeID;
    @Column(name = "Webservice_priviledge_ID")
    private Integer webservicepriviledgeID;

  

    public GroupWeb(Integer grouppriviledgeID, Integer webservicepriviledgeID) {
		super();
		this.grouppriviledgeID = grouppriviledgeID;
		this.webservicepriviledgeID = webservicepriviledgeID;
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
        if (!(object instanceof GroupWeb)) {
            return false;
        }
        GroupWeb other = (GroupWeb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupWeb[ id=" + id + " ]";
    }
    
}
