package com.rimdev.rimlang.entities;



import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "group_priviledge", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupPriviledge.findAll", query = "SELECT g FROM GroupPriviledge g")
    , @NamedQuery(name = "GroupPriviledge.findById", query = "SELECT g FROM GroupPriviledge g WHERE g.id = :id")
    , @NamedQuery(name = "GroupPriviledge.findByGroupname", query = "SELECT g FROM GroupPriviledge g WHERE g.groupname = :groupname")})
public class GroupPriviledge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Group_name", nullable = false, length = 450)
    private String groupname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grouppriviledgeID")
    private Collection<Notification> notificationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grouppriviledgeID")
    private Collection<UserLogin> userLoginCollection;
    @JoinColumn(name = "Group_status_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private GroupStatus groupstatusID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grouppriviledgeID")
    private Collection<GroupParent> groupParentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grouppriviledgeID")
    private Collection<GroupWeb> groupWebCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grouppriviledgeID")
    private Collection<GroupPages> groupPagesCollection;

    public GroupPriviledge() {
    }

    public GroupPriviledge(Integer id) {
        this.id = id;
    }

    public GroupPriviledge(Integer id, String groupname) {
        this.id = id;
        this.groupname = groupname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserLogin> getUserLoginCollection() {
        return userLoginCollection;
    }

    public void setUserLoginCollection(Collection<UserLogin> userLoginCollection) {
        this.userLoginCollection = userLoginCollection;
    }

    public GroupStatus getGroupstatusID() {
        return groupstatusID;
    }

    public void setGroupstatusID(GroupStatus groupstatusID) {
        this.groupstatusID = groupstatusID;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GroupParent> getGroupParentCollection() {
        return groupParentCollection;
    }

    public void setGroupParentCollection(Collection<GroupParent> groupParentCollection) {
        this.groupParentCollection = groupParentCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GroupWeb> getGroupWebCollection() {
        return groupWebCollection;
    }

    public void setGroupWebCollection(Collection<GroupWeb> groupWebCollection) {
        this.groupWebCollection = groupWebCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GroupPages> getGroupPagesCollection() {
        return groupPagesCollection;
    }

    public void setGroupPagesCollection(Collection<GroupPages> groupPagesCollection) {
        this.groupPagesCollection = groupPagesCollection;
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
        if (!(object instanceof GroupPriviledge)) {
            return false;
        }
        GroupPriviledge other = (GroupPriviledge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupPriviledge[ id=" + id + " ]";
    }
    
}

