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
@Table(name = "fileapp_type", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileappType.findAll", query = "SELECT f FROM FileappType f")
    , @NamedQuery(name = "FileappType.findById", query = "SELECT f FROM FileappType f WHERE f.id = :id")
    , @NamedQuery(name = "FileappType.findByFtype", query = "SELECT f FROM FileappType f WHERE f.ftype = :ftype")
    , @NamedQuery(name = "FileappType.findByTypeDescr", query = "SELECT f FROM FileappType f WHERE f.typeDescr = :typeDescr")})
public class FileappType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "F_type", nullable = false, length = 500)
    private String ftype;
    @Column(name = "type_descr", length = 500)
    private String typeDescr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fileApptypeID")
    private Collection<UserFile> userFileCollection;

    public FileappType() {
    }

    public FileappType(Integer id) {
        this.id = id;
    }

    public FileappType(Integer id, String ftype) {
        this.id = id;
        this.ftype = ftype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype;
    }

    public String getTypeDescr() {
        return typeDescr;
    }

    public void setTypeDescr(String typeDescr) {
        this.typeDescr = typeDescr;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserFile> getUserFileCollection() {
        return userFileCollection;
    }

    public void setUserFileCollection(Collection<UserFile> userFileCollection) {
        this.userFileCollection = userFileCollection;
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
        if (!(object instanceof FileappType)) {
            return false;
        }
        FileappType other = (FileappType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FileappType[ id=" + id + " ]";
    }
    
}
