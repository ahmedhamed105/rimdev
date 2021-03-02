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
@Table(name = "group_priviledge", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupPriviledge.findAll", query = "SELECT g FROM GroupPriviledge g")
    , @NamedQuery(name = "GroupPriviledge.findById", query = "SELECT g FROM GroupPriviledge g WHERE g.id = :id")
    , @NamedQuery(name = "GroupPriviledge.findByGroupname", query = "SELECT g FROM GroupPriviledge g WHERE g.groupname = :groupname")})
public class GroupPriviledge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Group_name", nullable = false, length = 450)
    private String groupname;
    @Column(name = "Group_status_ID")
    private Integer groupstatusID;

    

    public GroupPriviledge(String groupname, Integer groupstatusID) {
		super();
		this.groupname = groupname;
		this.groupstatusID = groupstatusID;
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

