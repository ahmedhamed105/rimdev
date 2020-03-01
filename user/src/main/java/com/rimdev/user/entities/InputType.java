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
@Table(name = "input_type", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InputType.findAll", query = "SELECT i FROM InputType i")
    , @NamedQuery(name = "InputType.findById", query = "SELECT i FROM InputType i WHERE i.id = :id")
    , @NamedQuery(name = "InputType.findByItype", query = "SELECT i FROM InputType i WHERE i.itype = :itype")})
public class InputType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "itype", nullable = false, length = 45)
    private String itype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inputtypeID")
    private Collection<ComponentInput> componentInputCollection;

    public InputType() {
    }

    public InputType(Integer id) {
        this.id = id;
    }

    public InputType(Integer id, String itype) {
        this.id = id;
        this.itype = itype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ComponentInput> getComponentInputCollection() {
        return componentInputCollection;
    }

    public void setComponentInputCollection(Collection<ComponentInput> componentInputCollection) {
        this.componentInputCollection = componentInputCollection;
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
        if (!(object instanceof InputType)) {
            return false;
        }
        InputType other = (InputType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.InputType[ id=" + id + " ]";
    }
    
}

