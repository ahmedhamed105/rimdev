package com.rimdev.rimnotif.entities;


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
@Table(name = "file_status", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FileStatus.findAll", query = "SELECT f FROM FileStatus f")
    , @NamedQuery(name = "FileStatus.findById", query = "SELECT f FROM FileStatus f WHERE f.id = :id")
    , @NamedQuery(name = "FileStatus.findByFileSt", query = "SELECT f FROM FileStatus f WHERE f.fileSt = :fileSt")})
public class FileStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "file_st", nullable = false, length = 450)
    private String fileSt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filestatusID")
    private Collection<FilesUpload> filesUploadCollection;

    public FileStatus() {
    }

    public FileStatus(Integer id) {
        this.id = id;
    }

    public FileStatus(Integer id, String fileSt) {
        this.id = id;
        this.fileSt = fileSt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileSt() {
        return fileSt;
    }

    public void setFileSt(String fileSt) {
        this.fileSt = fileSt;
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
        if (!(object instanceof FileStatus)) {
            return false;
        }
        FileStatus other = (FileStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FileStatus[ id=" + id + " ]";
    }
    
}
