package com.rimdev.gateway.entities;


import java.io.Serializable;
import java.math.BigDecimal;

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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "files_upload", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "FilesUpload.findAll", query = "SELECT f FROM FilesUpload f")
    , @NamedQuery(name = "FilesUpload.findById", query = "SELECT f FROM FilesUpload f WHERE f.id = :id")
    , @NamedQuery(name = "FilesUpload.findByFilesUrl", query = "SELECT f FROM FilesUpload f WHERE f.filesUrl = :filesUrl")
    , @NamedQuery(name = "FilesUpload.findByFilesSize", query = "SELECT f FROM FilesUpload f WHERE f.filesSize = :filesSize")
    , @NamedQuery(name = "FilesUpload.findByFilesName", query = "SELECT f FROM FilesUpload f WHERE f.filesName = :filesName")})
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
    @Column(name = "file_type_ID")
    private Integer filetypeID;
    @Column(name = "filecomruntime", precision = 20, scale = 6)
    private BigDecimal filecomruntime;

    @Column(name = "file_status_ID")
    private Integer filestatusID;
    
    @Column(name = "file_path", length = 450)
    private String filePath;
    
    @Column(name = "file_public", nullable = false)
    private int filePublic;


    
    



    public FilesUpload(String filesUrl, BigDecimal filesSize, String filesName, Integer filetypeID,
			BigDecimal filecomruntime, Integer filestatusID, String filePath, int filePublic) {
		super();
		this.filesUrl = filesUrl;
		this.filesSize = filesSize;
		this.filesName = filesName;
		this.filetypeID = filetypeID;
		this.filecomruntime = filecomruntime;
		this.filestatusID = filestatusID;
		this.filePath = filePath;
		this.filePublic = filePublic;
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
