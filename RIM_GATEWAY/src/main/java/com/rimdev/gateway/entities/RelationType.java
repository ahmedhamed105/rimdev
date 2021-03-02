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

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "relation_type", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelationType.findAll", query = "SELECT r FROM RelationType r")
    , @NamedQuery(name = "RelationType.findById", query = "SELECT r FROM RelationType r WHERE r.id = :id")
    , @NamedQuery(name = "RelationType.findByRtype", query = "SELECT r FROM RelationType r WHERE r.rtype = :rtype")})
public class RelationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Rtype", nullable = false, length = 450)
    private String rtype;

    

    public RelationType(String rtype) {
		super();
		this.rtype = rtype;
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
        if (!(object instanceof RelationType)) {
            return false;
        }
        RelationType other = (RelationType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RelationType[ id=" + id + " ]";
    }
    
}
