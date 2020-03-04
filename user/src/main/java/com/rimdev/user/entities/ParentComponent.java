package com.rimdev.user.entities;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "parent_component", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParentComponent.findAll", query = "SELECT p FROM ParentComponent p")
    , @NamedQuery(name = "ParentComponent.findById", query = "SELECT p FROM ParentComponent p WHERE p.id = :id")
    , @NamedQuery(name = "ParentComponent.findByPcodeTittle", query = "SELECT p FROM ParentComponent p WHERE p.pcodeTittle = :pcodeTittle")
    , @NamedQuery(name = "ParentComponent.findByParentPostion", query = "SELECT p FROM ParentComponent p WHERE p.parentPostion = :parentPostion")
    , @NamedQuery(name = "ParentComponent.findByParentType", query = "SELECT p FROM ParentComponent p WHERE p.parentType = :parentType")
    , @NamedQuery(name = "ParentComponent.findByParentName", query = "SELECT p FROM ParentComponent p WHERE p.parentName = :parentName")})
public class ParentComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "pcode_tittle", nullable = false, length = 450)
    private String pcodeTittle;
    @Basic(optional = false)
    @Column(name = "parent_postion", nullable = false)
    private int parentPostion;
    @Column(name = "parent_type", length = 45)
    private String parentType;
    @Column(name = "parent_name", length = 45)
    private String parentName;
    @JoinColumn(name = "Pages_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Pages pagesID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentcomponentID")
    private Collection<Component> componentCollection;

    public ParentComponent() {
    }

    public ParentComponent(Integer id) {
        this.id = id;
    }

    public ParentComponent(Integer id, String pcodeTittle, int parentPostion) {
        this.id = id;
        this.pcodeTittle = pcodeTittle;
        this.parentPostion = parentPostion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPcodeTittle() {
        return pcodeTittle;
    }

    public void setPcodeTittle(String pcodeTittle) {
        this.pcodeTittle = pcodeTittle;
    }

    public int getParentPostion() {
        return parentPostion;
    }

    public void setParentPostion(int parentPostion) {
        this.parentPostion = parentPostion;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
    @XmlTransient
    @JsonIgnore
    public Pages getPagesID() {
        return pagesID;
    }

    public void setPagesID(Pages pagesID) {
        this.pagesID = pagesID;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Component> getComponentCollection() {
        return componentCollection;
    }

    public void setComponentCollection(Collection<Component> componentCollection) {
        this.componentCollection = componentCollection;
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
        if (!(object instanceof ParentComponent)) {
            return false;
        }
        ParentComponent other = (ParentComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParentComponent[ id=" + id + " ]";
    }
    
}
