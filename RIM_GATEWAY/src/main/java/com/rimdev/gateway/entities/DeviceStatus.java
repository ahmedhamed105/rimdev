package com.rimdev.gateway.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Data
@Table(name = "device_status", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "DeviceStatus.findAll", query = "SELECT d FROM DeviceStatus d")
    , @NamedQuery(name = "DeviceStatus.findById", query = "SELECT d FROM DeviceStatus d WHERE d.id = :id")
    , @NamedQuery(name = "DeviceStatus.findByDstatus", query = "SELECT d FROM DeviceStatus d WHERE d.dstatus = :dstatus")})
public class DeviceStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "D_status", length = 45)
    private String dstatus;

    
    
    public DeviceStatus(String dstatus) {
		super();
		this.dstatus = dstatus;
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
        if (!(object instanceof DeviceStatus)) {
            return false;
        }
        DeviceStatus other = (DeviceStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DeviceStatus[ id=" + id + " ]";
    }
    
}

