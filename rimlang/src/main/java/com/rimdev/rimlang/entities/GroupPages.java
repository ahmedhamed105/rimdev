package com.rimdev.rimlang.entities;


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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "group_pages", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "GroupPages.findAll", query = "SELECT g FROM GroupPages g")
    , @NamedQuery(name = "GroupPages.findById", query = "SELECT g FROM GroupPages g WHERE g.id = :id")})
public class GroupPages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "Group_priviledge_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private GroupPriviledge grouppriviledgeID;
    @JoinColumn(name = "pages_priviledge_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private PagesPriviledge pagespriviledgeID;

    public GroupPages() {
    }

    public GroupPages(Integer id) {
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

    public PagesPriviledge getPagespriviledgeID() {
        return pagespriviledgeID;
    }

    public void setPagespriviledgeID(PagesPriviledge pagespriviledgeID) {
        this.pagespriviledgeID = pagespriviledgeID;
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
        if (!(object instanceof GroupPages)) {
            return false;
        }
        GroupPages other = (GroupPages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupPages[ id=" + id + " ]";
    }
    
}