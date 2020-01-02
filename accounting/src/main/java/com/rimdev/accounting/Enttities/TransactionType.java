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
@Table(name = "transaction_type", catalog = "rim_accounting", schema = "")
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionType.findAll", query = "SELECT t FROM TransactionType t")
    , @NamedQuery(name = "TransactionType.findById", query = "SELECT t FROM TransactionType t WHERE t.id = :id")
    , @NamedQuery(name = "TransactionType.findByTRXtype", query = "SELECT t FROM TransactionType t WHERE t.tRXtype = :tRXtype")
    , @NamedQuery(name = "TransactionType.findByTRXdescrption", query = "SELECT t FROM TransactionType t WHERE t.tRXdescrption = :tRXdescrption")
    , @NamedQuery(name = "TransactionType.findByTRXtypestatus", query = "SELECT t FROM TransactionType t WHERE t.tRXtypestatus = :tRXtypestatus")})
public class TransactionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TRX_type", nullable = false, length = 45)
    private String tRXtype;
    @Column(name = "TRX_descrption", length = 45)
    private String tRXdescrption;
    @Column(name = "TRXtype_status", length = 45)
    private String tRXtypestatus;
    @Column(name = "payment_not")
    private int paymentNot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactiontypeID")
    private Collection<AccountProcess> accountProcessCollection;
    @Column(name = "Trxcode")
    private int trxcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactiontypeID")
    private Collection<HoldProcess> holdProcessCollection;
    
    
    private String error;

    public TransactionType() {
    }

    public TransactionType(Integer id) {
        this.id = id;
    }

    public TransactionType(Integer id, String error) {
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

    public String getTRXtype() {
        return tRXtype;
    }

    public void setTRXtype(String tRXtype) {
        this.tRXtype = tRXtype;
    }

    public String getTRXdescrption() {
        return tRXdescrption;
    }

    public void setTRXdescrption(String tRXdescrption) {
        this.tRXdescrption = tRXdescrption;
    }

    public String getTRXtypestatus() {
        return tRXtypestatus;
    }

    public void setTRXtypestatus(String tRXtypestatus) {
        this.tRXtypestatus = tRXtypestatus;
    }
    

    public int getPaymentNot() {
		return paymentNot;
	}

	public void setPaymentNot(int paymentNot) {
		this.paymentNot = paymentNot;
	}

	@XmlTransient
    @JsonIgnore
    public Collection<AccountProcess> getAccountProcessCollection() {
        return accountProcessCollection;
    }

    public void setAccountProcessCollection(Collection<AccountProcess> accountProcessCollection) {
        this.accountProcessCollection = accountProcessCollection;
    }
    
    public int getTrxcode() {
        return trxcode;
    }

    public void setTrxcode(int trxcode) {
        this.trxcode = trxcode;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<HoldProcess> getHoldProcessCollection() {
        return holdProcessCollection;
    }

    public void setHoldProcessCollection(Collection<HoldProcess> holdProcessCollection) {
        this.holdProcessCollection = holdProcessCollection;
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
        if (!(object instanceof TransactionType)) {
            return false;
        }
        TransactionType other = (TransactionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TransactionType[ id=" + id + " ]";
    }
    
}
