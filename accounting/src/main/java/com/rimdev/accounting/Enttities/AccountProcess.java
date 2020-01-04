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
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
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
@Table(name = "account_process", catalog = "rim_accounting", schema = "")
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountProcess.findAll", query = "SELECT a FROM AccountProcess a")
    , @NamedQuery(name = "AccountProcess.findById", query = "SELECT a FROM AccountProcess a WHERE a.id = :id")
    , @NamedQuery(name = "AccountProcess.findByTRXAmount", query = "SELECT a FROM AccountProcess a WHERE a.tRXAmount = :tRXAmount")
    , @NamedQuery(name = "AccountProcess.findByTRXdescription", query = "SELECT a FROM AccountProcess a WHERE a.tRXdescription = :tRXdescription")
    , @NamedQuery(name = "AccountProcess.findByReferencenumber", query = "SELECT a FROM AccountProcess a WHERE a.referencenumber = :referencenumber")})


@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "postonelegtransaction", 
                           procedureName = "rim_accounting.postonelegtransaction",
                           parameters = {
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "reference_no", type = String.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "Currency", type = String.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "acct_no", type = String.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = BigDecimal.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "Trantypecode", type = Integer.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "Trx_flow", type = String.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "Trx_desc", type = String.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "hold_id", type = Integer.class),
                              @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_code", type = String.class)
                           }),
@NamedStoredProcedureQuery(name = "posthold", 
procedureName = "rim_accounting.posthold",
parameters = {
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "reference_no", type = String.class),
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "Currency", type = String.class),
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "acct_no", type = String.class),
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = BigDecimal.class),
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "Trantypecode", type = Integer.class),
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "Trx_flow", type = String.class),
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "Trx_desc", type = String.class),
   @StoredProcedureParameter(mode = ParameterMode.IN, name = "hold_id", type = Integer.class),
   @StoredProcedureParameter(mode = ParameterMode.OUT, name = "error_code", type = String.class)
}) 
})
public class AccountProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TRX_Amount", nullable = false, length = 45)
    private BigDecimal tRXAmount;
    @Basic(optional = false)
    @Column(name = "TRX_description", nullable = false, length = 45)
    private String tRXdescription;
    @Basic(optional = false)
    @Column(name = "Reference_number", nullable = false, length = 500)
    private String referencenumber;
    @JoinColumn(name = "Currency_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Currency currencyID;
    @JoinColumn(name = "Flow_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private FlowType flowtypeID;
    @JoinColumn(name = "Transaction_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private TransactionType transactiontypeID;
    @JoinColumn(name = "account_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Account accountID;
    @JoinColumn(name = "Error_codes_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ErrorCodes errorcodesID;
    @Column(name = "Hold_id")
    private Integer holdid;
    @Column(name = "create_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;


    public AccountProcess() {
    }

    public AccountProcess(Integer id) {
        this.id = id;
    }

    public AccountProcess(Integer id, BigDecimal tRXAmount, String tRXdescription, String referencenumber) {
        this.id = id;
        this.tRXAmount = tRXAmount;
        this.tRXdescription = tRXdescription;
        this.referencenumber = referencenumber;
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

    public BigDecimal getTRXAmount() {
        return tRXAmount;
    }

    public void setTRXAmount(BigDecimal tRXAmount) {
        this.tRXAmount = tRXAmount;
    }

    public String getTRXdescription() {
        return tRXdescription;
    }

    public void setTRXdescription(String tRXdescription) {
        this.tRXdescription = tRXdescription;
    }

    public String getReferencenumber() {
        return referencenumber;
    }

    public void setReferencenumber(String referencenumber) {
        this.referencenumber = referencenumber;
    }

    public Currency getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Currency currencyID) {
        this.currencyID = currencyID;
    }

    public FlowType getFlowtypeID() {
        return flowtypeID;
    }

    public void setFlowtypeID(FlowType flowtypeID) {
        this.flowtypeID = flowtypeID;
    }

    public TransactionType getTransactiontypeID() {
        return transactiontypeID;
    }

    public void setTransactiontypeID(TransactionType transactiontypeID) {
        this.transactiontypeID = transactiontypeID;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
    }
    
    public ErrorCodes getErrorcodesID() {
        return errorcodesID;
    }

    public void setErrorcodesID(ErrorCodes errorcodesID) {
        this.errorcodesID = errorcodesID;
    }
    
    public Integer getHoldid() {
        return holdid;
    }

    public void setHoldid(Integer holdid) {
        this.holdid = holdid;
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
        if (!(object instanceof AccountProcess)) {
            return false;
        }
        AccountProcess other = (AccountProcess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccountProcess[ id=" + id + " ]";
    }
    
}
