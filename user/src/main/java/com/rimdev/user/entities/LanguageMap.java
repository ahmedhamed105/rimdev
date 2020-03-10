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
@Table(name = "language_map", catalog = "rim_language", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LanguageMap.findAll", query = "SELECT l FROM LanguageMap l")
    , @NamedQuery(name = "LanguageMap.findById", query = "SELECT l FROM LanguageMap l WHERE l.id = :id")
    , @NamedQuery(name = "LanguageMap.findByTextcode", query = "SELECT l FROM LanguageMap l WHERE l.textcode = :textcode")})
public class LanguageMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Text_code", length = 45)
    private String textcode;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languagemapID")
    private Collection<TextConvertion> textConvertionCollection;

    public LanguageMap() {
    }

    public LanguageMap(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextcode() {
        return textcode;
    }

    public void setTextcode(String textcode) {
        this.textcode = textcode;
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
        if (!(object instanceof LanguageMap)) {
            return false;
        }
        LanguageMap other = (LanguageMap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity1.LanguageMap[ id=" + id + " ]";
    }
    
}
