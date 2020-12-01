package com.rimdev.rimuser.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "text_convertion", catalog = "rim_language", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TextConvertion.findAll", query = "SELECT t FROM TextConvertion t")
    , @NamedQuery(name = "TextConvertion.findById", query = "SELECT t FROM TextConvertion t WHERE t.id = :id")
    , @NamedQuery(name = "TextConvertion.findByReturnLang", query = "SELECT t FROM TextConvertion t WHERE t.returnLang = :returnLang")})
public class TextConvertion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "return_lang", nullable = false, length = 45)
    private String returnLang;
    @Basic(optional = false)
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    
    
    @JoinColumn(name = "Languages_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Languages languagesID;
    @JoinColumn(name = "language_map_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private LanguageMap languagemapID;

    public TextConvertion() {
    }

    public TextConvertion(Integer id) {
        this.id = id;
    }

    public TextConvertion(Integer id, String returnLang) {
        this.id = id;
        this.returnLang = returnLang;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReturnLang() {
        return returnLang;
    }

    public void setReturnLang(String returnLang) {
        this.returnLang = returnLang;
    }

    public Languages getLanguagesID() {
        return languagesID;
    }

    public void setLanguagesID(Languages languagesID) {
        this.languagesID = languagesID;
    }

    public LanguageMap getLanguagemapID() {
        return languagemapID;
    }

    public void setLanguagemapID(LanguageMap languagemapID) {
        this.languagemapID = languagemapID;
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
        if (!(object instanceof TextConvertion)) {
            return false;
        }
        TextConvertion other = (TextConvertion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity1.TextConvertion[ id=" + id + " ]";
    }
    
}
