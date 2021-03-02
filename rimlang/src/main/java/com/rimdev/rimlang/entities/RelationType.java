package com.rimdev.rimlang.entities;


import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "relation_type", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelationType.findAll", query = "SELECT r FROM RelationType r")
    , @NamedQuery(name = "RelationType.findById", query = "SELECT r FROM RelationType r WHERE r.id = :id")
    , @NamedQuery(name = "RelationType.findByRtype", query = "SELECT r FROM RelationType r WHERE r.rtype = :rtype")})
public class RelationType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Rtype", nullable = false, length = 450)
    private String rtype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relationtypeID")
    private Collection<RelationComp> relationCompCollection;

    public RelationType() {
    }

    public RelationType(Integer id) {
        this.id = id;
    }

    public RelationType(Integer id, String rtype) {
        this.id = id;
        this.rtype = rtype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<RelationComp> getRelationCompCollection() {
        return relationCompCollection;
    }

    public void setRelationCompCollection(Collection<RelationComp> relationCompCollection) {
        this.relationCompCollection = relationCompCollection;
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
