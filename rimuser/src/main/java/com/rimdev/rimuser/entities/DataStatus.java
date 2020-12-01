package com.rimdev.rimuser.entities;


import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "data_status", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "DataStatus.findAll", query = "SELECT d FROM DataStatus d")
    , @NamedQuery(name = "DataStatus.findById", query = "SELECT d FROM DataStatus d WHERE d.id = :id")
    , @NamedQuery(name = "DataStatus.findByDstatus", query = "SELECT d FROM DataStatus d WHERE d.dstatus = :dstatus")
    , @NamedQuery(name = "DataStatus.findByDDesc", query = "SELECT d FROM DataStatus d WHERE d.dDesc = :dDesc")})
public class DataStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Dstatus", nullable = false, length = 45)
    private String dstatus;
    @Column(name = "DDesc", length = 500)
    private String dDesc;
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datastatusID")
    private Collection<Telephones> telephonesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "datastatusID")
    private Collection<Email> emailCollection;

    public DataStatus() {
    }

    public DataStatus(Integer id) {
        this.id = id;
    }

    public DataStatus(Integer id, String dstatus) {
        this.id = id;
        this.dstatus = dstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDstatus() {
        return dstatus;
    }

    public void setDstatus(String dstatus) {
        this.dstatus = dstatus;
    }

    public String getDDesc() {
        return dDesc;
    }

    public void setDDesc(String dDesc) {
        this.dDesc = dDesc;
    }

	@XmlTransient
    @JsonIgnore
    public Collection<Telephones> getTelephonesCollection() {
        return telephonesCollection;
    }

    public void setTelephonesCollection(Collection<Telephones> telephonesCollection) {
        this.telephonesCollection = telephonesCollection;
    }

	@XmlTransient
    @JsonIgnore
    public Collection<Email> getEmailCollection() {
        return emailCollection;
    }

    public void setEmailCollection(Collection<Email> emailCollection) {
        this.emailCollection = emailCollection;
    }
    
    @XmlTransient
    @JsonIgnore
    public Date getDateModify() {
        return dateModify;
    }

    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }
    
    @XmlTransient
    @JsonIgnore
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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
        if (!(object instanceof DataStatus)) {
            return false;
        }
        DataStatus other = (DataStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DataStatus[ id=" + id + " ]";
    }
    
}
