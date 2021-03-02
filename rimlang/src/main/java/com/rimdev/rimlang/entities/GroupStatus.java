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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "group_status", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "GroupStatus.findAll", query = "SELECT g FROM GroupStatus g")
    , @NamedQuery(name = "GroupStatus.findById", query = "SELECT g FROM GroupStatus g WHERE g.id = :id")
    , @NamedQuery(name = "GroupStatus.findByGstatus", query = "SELECT g FROM GroupStatus g WHERE g.gstatus = :gstatus")})
public class GroupStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Gstatus", nullable = false, length = 45)
    private String gstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupstatusID")
    private Collection<GroupPriviledge> groupPriviledgeCollection;

    public GroupStatus() {
    }

    public GroupStatus(Integer id) {
        this.id = id;
    }

    public GroupStatus(Integer id, String gstatus) {
        this.id = id;
        this.gstatus = gstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGstatus() {
        return gstatus;
    }

    public void setGstatus(String gstatus) {
        this.gstatus = gstatus;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GroupPriviledge> getGroupPriviledgeCollection() {
        return groupPriviledgeCollection;
    }

    public void setGroupPriviledgeCollection(Collection<GroupPriviledge> groupPriviledgeCollection) {
        this.groupPriviledgeCollection = groupPriviledgeCollection;
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
        if (!(object instanceof GroupStatus)) {
            return false;
        }
        GroupStatus other = (GroupStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupStatus[ id=" + id + " ]";
    }
    
}

