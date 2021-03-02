package com.rimdev.gateway.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "text_convertion", catalog = "rim_language", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TextConvertion.findAll", query = "SELECT t FROM TextConvertion t")
    , @NamedQuery(name = "TextConvertion.findById", query = "SELECT t FROM TextConvertion t WHERE t.id = :id")
    , @NamedQuery(name = "TextConvertion.findByReturnLang", query = "SELECT t FROM TextConvertion t WHERE t.returnLang = :returnLang")})
public class TextConvertion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    
    
    @Column(name = "Languages_ID")
    private Integer languagesID;
    @Column(name = "language_map_ID")
    private Integer languagemapID;

   
    
    public TextConvertion(String returnLang, Date dateModify, Date dateCreate, Integer languagesID,
			Integer languagemapID) {
		super();
		this.returnLang = returnLang;
		this.dateModify = dateModify;
		this.dateCreate = dateCreate;
		this.languagesID = languagesID;
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
