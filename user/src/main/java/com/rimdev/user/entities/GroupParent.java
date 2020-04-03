package com.rimdev.user.entities;


import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "group_parent", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupParent.findAll", query = "SELECT g FROM GroupParent g")
    , @NamedQuery(name = "GroupParent.findById", query = "SELECT g FROM GroupParent g WHERE g.id = :id")})
public class GroupParent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "Group_priviledge_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private GroupPriviledge grouppriviledgeID;
    @JoinColumn(name = "parent_priviledge_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ParentPriviledge parentpriviledgeID;

    public GroupParent() {
    }

    public GroupParent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GroupPriviledge getGrouppriviledgeID() {
        return grouppriviledgeID;
    }

    public void setGrouppriviledgeID(GroupPriviledge grouppriviledgeID) {
        this.grouppriviledgeID = grouppriviledgeID;
    }

    public ParentPriviledge getParentpriviledgeID() {
        return parentpriviledgeID;
    }

    public void setParentpriviledgeID(ParentPriviledge parentpriviledgeID) {
        this.parentpriviledgeID = parentpriviledgeID;
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
        if (!(object instanceof GroupParent)) {
            return false;
        }
        GroupParent other = (GroupParent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupParent[ id=" + id + " ]";
    }
    
}

