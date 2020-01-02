package com.rimdev.accounting.Enttities;



import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "hold_process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HoldProcess.findAll", query = "SELECT h FROM HoldProcess h")
    , @NamedQuery(name = "HoldProcess.findById", query = "SELECT h FROM HoldProcess h WHERE h.id = :id")
    , @NamedQuery(name = "HoldProcess.findByTRXAmount", query = "SELECT h FROM HoldProcess h WHERE h.tRXAmount = :tRXAmount")
    , @NamedQuery(name = "HoldProcess.findByTRXdescription", query = "SELECT h FROM HoldProcess h WHERE h.tRXdescription = :tRXdescription")
    , @NamedQuery(name = "HoldProcess.findByReferencenumber", query = "SELECT h FROM HoldProcess h WHERE h.referencenumber = :referencenumber")
    , @NamedQuery(name = "HoldProcess.findByHoldid", query = "SELECT h FROM HoldProcess h WHERE h.holdid = :holdid")})
public class HoldProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "TRX_Amount")
    private BigDecimal tRXAmount;
    @Basic(optional = false)
    @Column(name = "TRX_description")
    private String tRXdescription;
    @Basic(optional = false)
    @Column(name = "Reference_number")
    private String referencenumber;
    @Column(name = "Hold_id")
    private Integer holdid;
    @JoinColumn(name = "Currency_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Currency currencyID;
    @JoinColumn(name = "Error_codes_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ErrorCodes errorcodesID;
    @JoinColumn(name = "Flow_type_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private FlowType flowtypeID;
    @JoinColumn(name = "Transaction_type_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TransactionType transactiontypeID;
    @JoinColumn(name = "account_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Account accountID;

    public HoldProcess() {
    }

    public HoldProcess(Integer id) {
        this.id = id;
    }

    public HoldProcess(Integer id, BigDecimal tRXAmount, String tRXdescription, String referencenumber) {
        this.id = id;
        this.tRXAmount = tRXAmount;
        this.tRXdescription = tRXdescription;
        this.referencenumber = referencenumber;
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

    public Integer getHoldid() {
        return holdid;
    }

    public void setHoldid(Integer holdid) {
        this.holdid = holdid;
    }

    public Currency getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Currency currencyID) {
        this.currencyID = currencyID;
    }

    public ErrorCodes getErrorcodesID() {
        return errorcodesID;
    }

    public void setErrorcodesID(ErrorCodes errorcodesID) {
        this.errorcodesID = errorcodesID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HoldProcess)) {
            return false;
        }
        HoldProcess other = (HoldProcess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HoldProcess[ id=" + id + " ]";
    }
    
}

