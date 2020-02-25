package com.rimdev.user.entities;


import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "files_upload", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FilesUpload.findAll", query = "SELECT f FROM FilesUpload f")
    , @NamedQuery(name = "FilesUpload.findById", query = "SELECT f FROM FilesUpload f WHERE f.id = :id")
    , @NamedQuery(name = "FilesUpload.findByFilesUrl", query = "SELECT f FROM FilesUpload f WHERE f.filesUrl = :filesUrl")
    , @NamedQuery(name = "FilesUpload.findByFilesSize", query = "SELECT f FROM FilesUpload f WHERE f.filesSize = :filesSize")
    , @NamedQuery(name = "FilesUpload.findByFilesName", query = "SELECT f FROM FilesUpload f WHERE f.filesName = :filesName")
    , @NamedQuery(name = "FilesUpload.findByFilesType", query = "SELECT f FROM FilesUpload f WHERE f.filesType = :filesType")})
public class FilesUpload implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "files_url", nullable = false, length = 500)
    private String filesUrl;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "files_size", nullable = false, precision = 20, scale = 6)
    private BigDecimal filesSize;
    @Basic(optional = false)
    @Column(name = "files_name", nullable = false, length = 45)
    private String filesName;
    @Basic(optional = false)
    @Column(name = "files_type", nullable = false, length = 500)
    private String filesType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "filesuploadID")
    private Collection<UserFile> userFileCollection;
    @Column(name = "filecomruntime", precision = 20, scale = 6)
    private BigDecimal filecomruntime;


    public FilesUpload() {
    }

    public FilesUpload(Integer id) {
        this.id = id;
    }

    public FilesUpload(Integer id, String filesUrl, BigDecimal filesSize, String filesName, String filesType) {
        this.id = id;
        this.filesUrl = filesUrl;
        this.filesSize = filesSize;
        this.filesName = filesName;
        this.filesType = filesType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilesUrl() {
        return filesUrl;
    }

    public void setFilesUrl(String filesUrl) {
        this.filesUrl = filesUrl;
    }

    public BigDecimal getFilesSize() {
        return filesSize;
    }

    public void setFilesSize(BigDecimal filesSize) {
        this.filesSize = filesSize;
    }

    public String getFilesName() {
        return filesName;
    }

    public void setFilesName(String filesName) {
        this.filesName = filesName;
    }

    public String getFilesType() {
        return filesType;
    }

    public void setFilesType(String filesType) {
        this.filesType = filesType;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UserFile> getUserFileCollection() {
        return userFileCollection;
    }

    public void setUserFileCollection(Collection<UserFile> userFileCollection) {
        this.userFileCollection = userFileCollection;
    }
    
    public BigDecimal getFilecomruntime() {
        return filecomruntime;
    }

    public void setFilecomruntime(BigDecimal filecomruntime) {
        this.filecomruntime = filecomruntime;
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
        if (!(object instanceof FilesUpload)) {
            return false;
        }
        FilesUpload other = (FilesUpload) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FilesUpload[ id=" + id + " ]";
    }
    
}