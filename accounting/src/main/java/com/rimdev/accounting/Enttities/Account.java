package com.rimdev.accounting.Enttities;


import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(catalog = "rim_accounting", schema = "")
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id")
    
    
    , @NamedQuery(name = "Account.findByAcctNumber", query = "SELECT a FROM Account a WHERE a.acctNumber = :acctNumber")
    , @NamedQuery(name = "Account.findByAcctName", query = "SELECT a FROM Account a WHERE a.acctName = :acctName")
    , @NamedQuery(name = "Account.findByCurrbalance", query = "SELECT a FROM Account a WHERE a.currbalance = :currbalance")
    , @NamedQuery(name = "Account.findByAvalbalance", query = "SELECT a FROM Account a WHERE a.avalbalance = :avalbalance")
    , @NamedQuery(name = "Account.findByLastmodification", query = "SELECT a FROM Account a WHERE a.lastmodification = :lastmodification")
    , @NamedQuery(name = "Account.findByCreatedate", query = "SELECT a FROM Account a WHERE a.createdate = :createdate")
    , @NamedQuery(name = "Account.findByCustomernumber", query = "SELECT a FROM Account a WHERE a.customernumber = :customernumber")
    , @NamedQuery(name = "Account.findByAcctstatus", query = "SELECT a FROM Account a WHERE a.acctstatus = :acctstatus")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "acct_number", nullable = false, length = 100)
    private String acctNumber;
    @Basic(optional = false)
    @Column(name = "acct_name", nullable = false, length = 45)
    private String acctName;
    @Basic(optional = false)
    @Column(name = "Curr_balance", nullable = false, length = 45)
    private BigDecimal currbalance;
    @Basic(optional = false)
    @Column(name = "Aval_balance", nullable = false, length = 45)
    private BigDecimal avalbalance;
    @Basic(optional = false)
    @Column(name = "Last_modification", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodification;
    @Basic(optional = false)
    @Column(name = "Create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Basic(optional = false)
    @Column(name = "Customer_number", nullable = false, length = 45)
    private String customernumber;
    @Basic(optional = false)
    @Column(name = "Acct_status", nullable = false, length = 45)
    private String acctstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountID")
    private Collection<AccountProcess> accountProcessCollection;
    @JoinColumn(name = "Currency_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Currency currencyID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<HoldProcess> holdProcessCollection;
    
    
    private String error;

    public Account() {
    }

    public Account(Integer id,String error) {
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

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public BigDecimal getCurrbalance() {
        return currbalance;
    }

    public void setCurrbalance(BigDecimal currbalance) {
        this.currbalance = currbalance;
    }

    public BigDecimal getAvalbalance() {
        return avalbalance;
    }

    public void setAvalbalance(BigDecimal avalbalance) {
        this.avalbalance = avalbalance;
    }

    public Date getLastmodification() {
        return lastmodification;
    }

    public void setLastmodification(Date lastmodification) {
        this.lastmodification = lastmodification;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber;
    }

    public String getAcctstatus() {
        return acctstatus;
    }

    public void setAcctstatus(String acctstatus) {
        this.acctstatus = acctstatus;
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

    public Currency getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Currency currencyID) {
        this.currencyID = currencyID;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Account[ id=" + id + " ]";
    }
    
}
