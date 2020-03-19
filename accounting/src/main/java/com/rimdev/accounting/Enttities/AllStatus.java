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
@Table(name = "all_status", catalog = "rim_accounting", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "AllStatus.findAll", query = "SELECT a FROM AllStatus a")
    , @NamedQuery(name = "AllStatus.findById", query = "SELECT a FROM AllStatus a WHERE a.id = :id")
    , @NamedQuery(name = "AllStatus.findBySdata", query = "SELECT a FROM AllStatus a WHERE a.sdata = :sdata")})
public class AllStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Sdata", nullable = false, length = 450)
    private String sdata;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "allstatusID")
    private Collection<FlowType> flowTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "allstatusID")
    private Collection<Currency> currencyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "allstatusID")
    private Collection<TransactionType> transactionTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "allstatusID")
    private Collection<Account> accountCollection;

    public AllStatus() {
    }

    public AllStatus(Integer id) {
        this.id = id;
    }

    public AllStatus(Integer id, String sdata) {
        this.id = id;
        this.sdata = sdata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSdata() {
        return sdata;
    }

    public void setSdata(String sdata) {
        this.sdata = sdata;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<FlowType> getFlowTypeCollection() {
        return flowTypeCollection;
    }

    public void setFlowTypeCollection(Collection<FlowType> flowTypeCollection) {
        this.flowTypeCollection = flowTypeCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Currency> getCurrencyCollection() {
        return currencyCollection;
    }

    public void setCurrencyCollection(Collection<Currency> currencyCollection) {
        this.currencyCollection = currencyCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<TransactionType> getTransactionTypeCollection() {
        return transactionTypeCollection;
    }

    public void setTransactionTypeCollection(Collection<TransactionType> transactionTypeCollection) {
        this.transactionTypeCollection = transactionTypeCollection;
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
        if (!(object instanceof AllStatus)) {
            return false;
        }
        AllStatus other = (AllStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity2.AllStatus[ id=" + id + " ]";
    }
    
}

