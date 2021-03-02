package com.rimdev.rimlog.entities;


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

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "languages", catalog = "rim_language", schema = "rim_language")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Languages.findAll", query = "SELECT l FROM Languages l")
    , @NamedQuery(name = "Languages.findById", query = "SELECT l FROM Languages l WHERE l.id = :id")
    , @NamedQuery(name = "Languages.findByLanguagename", query = "SELECT l FROM Languages l WHERE l.languagename = :languagename")
    , @NamedQuery(name = "Languages.findByLanguagecode", query = "SELECT l FROM Languages l WHERE l.languagecode = :languagecode")})
public class Languages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Language_name", length = 45)
    private String languagename;
    @Column(name = "Language_code", length = 45)
    private String languagecode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languagesID")
    private Collection<TextConvertion> textConvertionCollection;
    @Basic(optional = false)
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    public Languages() {
    }

    public Languages(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguagename() {
        return languagename;
    }

    public void setLanguagename(String languagename) {
        this.languagename = languagename;
    }

    public String getLanguagecode() {
        return languagecode;
    }

    public void setLanguagecode(String languagecode) {
        this.languagecode = languagecode;
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

    @XmlTransient
    @JsonIgnore
    public Collection<TextConvertion> getTextConvertionCollection() {
        return textConvertionCollection;
    }

    public void setTextConvertionCollection(Collection<TextConvertion> textConvertionCollection) {
        this.textConvertionCollection = textConvertionCollection;
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
        if (!(object instanceof Languages)) {
            return false;
        }
        Languages other = (Languages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity1.Languages[ id=" + id + " ]";
    }
    
}
