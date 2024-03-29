package com.rimdev.accounting.Enttities;



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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    })
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<AccountProcess> accountProcessCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<Account> accountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<HoldProcess> holdProcessCollection;
    @Column(name = "create_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @JoinColumn(name = "All_status_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private AllStatus allstatusID;
    
    
    public Currency() {
    }

    public Currency(Integer id) {
        this.id = id;
    }
    
    public AllStatus getAllstatusID() {
        return allstatusID;
    }

    public void setAllstatusID(AllStatus allstatusID) {
        this.allstatusID = allstatusID;
    }
    
    @XmlTransient
    @JsonIgnore
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @XmlTransient
    @JsonIgnore
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
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
        return "entity.Currency[ id=" + id + " ]" +allstatusID.getSdata();
    }
    
}
