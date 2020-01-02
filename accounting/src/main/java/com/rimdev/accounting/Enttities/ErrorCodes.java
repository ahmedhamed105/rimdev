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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "error_codes", catalog = "rim_accounting", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"error_code"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ErrorCodes.findAll", query = "SELECT e FROM ErrorCodes e")
    , @NamedQuery(name = "ErrorCodes.findById", query = "SELECT e FROM ErrorCodes e WHERE e.id = :id")
    , @NamedQuery(name = "ErrorCodes.findByErrorCode", query = "SELECT e FROM ErrorCodes e WHERE e.errorCode = :errorCode")
    , @NamedQuery(name = "ErrorCodes.findByErrordescription", query = "SELECT e FROM ErrorCodes e WHERE e.errordescription = :errordescription")})
public class ErrorCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "error_code", nullable = false)
    private int errorCode;
    @Basic(optional = false)
    @Column(name = "Error_description", nullable = false, length = 45)
    private String errordescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "errorcodesID")
    private Collection<AccountProcess> accountProcessCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currencyID")
    private Collection<HoldProcess> holdProcessCollection;

    public ErrorCodes() {
    }

    public ErrorCodes(Integer errorCode,String errordescription) {
        this.errorCode = errorCode;
        this.errordescription = errordescription;
    }

    public ErrorCodes(Integer id, int errorCode, String errordescription) {
        this.id = id;
        this.errorCode = errorCode;
        this.errordescription = errordescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrordescription() {
        return errordescription;
    }

    public void setErrordescription(String errordescription) {
        this.errordescription = errordescription;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrorCodes)) {
            return false;
        }
        ErrorCodes other = (ErrorCodes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ErrorCodes[ id=" + id + " ]";
    }
    
}
