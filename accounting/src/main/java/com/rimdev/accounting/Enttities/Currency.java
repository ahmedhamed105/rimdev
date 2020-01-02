package com.rimdev.accounting.Enttities;



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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "currency",catalog = "rim_accounting", schema = "")
@DynamicUpdate(true)
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c")
    , @NamedQuery(name = "Currency.findById", query = "SELECT c FROM Currency c WHERE c.id = :id")
    , @NamedQuery(name = "Currency.findByCurrencyname", query = "SELECT c FROM Currency c WHERE c.currencyname = :currencyname")
    , @NamedQuery(name = "Currency.findByCurrencydescription", query = "SELECT c FROM Currency c WHERE c.currencydescription = :currencydescription")
    , @NamedQuery(name = "Currency.findByCurrencyISO", query = "SELECT c FROM Currency c WHERE c.currencyISO = :currencyISO")
    , @NamedQuery(name = "Currency.findByCurrencystatus", query = "SELECT c FROM Currency c WHERE c.currencystatus = :currencystatus")})
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Currency_name", nullable = false, length = 45)
    private String currencyname;
    @Column(name = "Currency_description", length = 45)
    private String currencydescription;
    @Basic(optional = false)
    @Column(name = "Currency_ISO", nullable = false, length = 45)
    private String currencyISO;
    @Basic(optional = false)
    @Column(name = "Currency_status", nullable = false, length = 45)
    private String currencystatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<AccountProcess> accountProcessCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<Account> accountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<HoldProcess> holdProcessCollection;
    
    private String error;

    public Currency() {
    }

    public Currency(Integer id,String error) {
        this.id = id;
        this.error = error;
    }
    
  
    public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyname() {
        return currencyname;
    }

    public void setCurrencyname(String currencyname) {
        this.currencyname = currencyname;
    }

    public String getCurrencydescription() {
        return currencydescription;
    }

    public void setCurrencydescription(String currencydescription) {
        this.currencydescription = currencydescription;
    }

    public String getCurrencyISO() {
        return currencyISO;
    }

    public void setCurrencyISO(String currencyISO) {
    	 this.currencyISO = currencyISO;
    }

    public String getCurrencystatus() {
        return currencystatus;
    }

    public void setCurrencystatus(String currencystatus) {
        this.currencystatus = currencystatus;
    }
    
    @XmlTransient
    @JsonIgnore
    public Collection<HoldProcess> getHoldProcessCollection() {
        return holdProcessCollection;
    }

    public void setHoldProcessCollection(Collection<HoldProcess> holdProcessCollection) {
        this.holdProcessCollection = holdProcessCollection;
    }


    @XmlTransient
    @JsonIgnore
    public Collection<AccountProcess> getAccountProcessCollection() {
        return accountProcessCollection;
    }

    public void setAccountProcessCollection(Collection<AccountProcess> accountProcessCollection) {
        this.accountProcessCollection = accountProcessCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
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
        if (!(object instanceof Currency)) {
            return false;
        }
        Currency other = (Currency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Currency[ id=" + id + " ]" +currencystatus;
    }
    
}
