package com.rimdev.gateway.entities;


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
@Table(name = "file_type", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileType.findAll", query = "SELECT f FROM FileType f")
    , @NamedQuery(name = "FileType.findById", query = "SELECT f FROM FileType f WHERE f.id = :id")
    , @NamedQuery(name = "FileType.findByFtype", query = "SELECT f FROM FileType f WHERE f.ftype = :ftype")
    , @NamedQuery(name = "FileType.findByFmime", query = "SELECT f FROM FileType f WHERE f.fmime = :fmime")})
public class FileType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ftype", nullable = false, length = 450)
    private String ftype;
    @Basic(optional = false)
    @Column(name = "fmime", nullable = false, length = 450)
    private String fmime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filetypeID")
    private Collection<ComponentFile> componentFileCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filetypeID")
    private Collection<FilesUpload> filesUploadCollection;

    public FileType() {
    }

    public FileType(Integer id) {
        this.id = id;
    }

    public FileType(Integer id, String ftype, String fmime) {
        this.id = id;
        this.ftype = ftype;
        this.fmime = fmime;
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

    public String getFmime() {
        return fmime;
    }

    public void setFmime(String fmime) {
        this.fmime = fmime;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ComponentFile> getComponentFileCollection() {
        return componentFileCollection;
    }

    public void setComponentFileCollection(Collection<ComponentFile> componentFileCollection) {
        this.componentFileCollection = componentFileCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<FilesUpload> getFilesUploadCollection() {
        return filesUploadCollection;
    }

    public void setFilesUploadCollection(Collection<FilesUpload> filesUploadCollection) {
        this.filesUploadCollection = filesUploadCollection;
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
        if (!(object instanceof FileType)) {
            return false;
        }
        FileType other = (FileType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FileType[ id=" + id + " ]";
    }
    
}

