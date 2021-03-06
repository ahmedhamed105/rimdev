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
@Table(name = "flow_type", catalog = "rim_accounting", schema = "")
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FlowType.findAll", query = "SELECT f FROM FlowType f")
    , @NamedQuery(name = "FlowType.findById", query = "SELECT f FROM FlowType f WHERE f.id = :id")
    , @NamedQuery(name = "FlowType.findByFlowtype", query = "SELECT f FROM FlowType f WHERE f.flowtype = :flowtype")
    , @NamedQuery(name = "FlowType.findByFlowdescription", query = "SELECT f FROM FlowType f WHERE f.flowdescription = :flowdescription")
    })
public class FlowType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String flowtype;
    @Column(length = 45)
    private String flowdescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flowtypeID")
    private Collection<AccountProcess> accountProcessCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flowtypeID")
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
    
    private String error;

    public FlowType() {
    }

    public FlowType(Integer id) {
        this.id = id;
    }

    public FlowType(Integer id, String error) {
        this.id = id;
        this.error = error;
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

    public String getFlowtype() {
        return flowtype;
    }

    public void setFlowtype(String flowtype) {
        this.flowtype = flowtype;
    }

    public String getFlowdescription() {
        return flowdescription;
    }

    public void setFlowdescription(String flowdescription) {
        this.flowdescription = flowdescription;
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
        if (!(object instanceof FlowType)) {
            return false;
        }
        FlowType other = (FlowType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlowType[ id=" + id + " ]";
    }
    
}
