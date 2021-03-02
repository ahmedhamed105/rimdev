package com.rimdev.gateway.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "component_select", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "ComponentSelect.findAll", query = "SELECT c FROM ComponentSelect c")
    , @NamedQuery(name = "ComponentSelect.findById", query = "SELECT c FROM ComponentSelect c WHERE c.id = :id")
    , @NamedQuery(name = "ComponentSelect.findByArrayName", query = "SELECT c FROM ComponentSelect c WHERE c.arrayName = :arrayName")
    , @NamedQuery(name = "ComponentSelect.findByArrayObject", query = "SELECT c FROM ComponentSelect c WHERE c.arrayObject = :arrayObject")
    , @NamedQuery(name = "ComponentSelect.findBySelectValue", query = "SELECT c FROM ComponentSelect c WHERE c.selectValue = :selectValue")
    , @NamedQuery(name = "ComponentSelect.findBySelectDisplay", query = "SELECT c FROM ComponentSelect c WHERE c.selectDisplay = :selectDisplay")})
public class ComponentSelect implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "array_name", nullable = false, length = 450)
    private String arrayName;
    @Basic(optional = false)
    @Column(name = "array_object", nullable = false)
    private Integer arrayObject;
    @Column(name = "select_value", length = 450)
    private String selectValue;
    @Column(name = "select_display", length = 450)
    private String selectDisplay;
    @Column(name = "web_service", length = 450)
    private String webService;
    @Column(name = "Component_ID")
    private Integer componentID;
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "Com_IP", nullable = true, length = 450)
    private String comIP;
    @Basic(optional = false)
    @Column(name = "Com_port", nullable = true, length = 45)
    private String comport;

   
    
    public ComponentSelect(String arrayName, Integer arrayObject, String selectValue, String selectDisplay,
			String webService, Integer componentID, Date dateModify, Date dateCreate, String comIP, String comport) {
		super();
		this.arrayName = arrayName;
		this.arrayObject = arrayObject;
		this.selectValue = selectValue;
		this.selectDisplay = selectDisplay;
		this.webService = webService;
		this.componentID = componentID;
		this.dateModify = dateModify;
		this.dateCreate = dateCreate;
		this.comIP = comIP;
		this.comport = comport;
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
        if (!(object instanceof ComponentSelect)) {
            return false;
        }
        ComponentSelect other = (ComponentSelect) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ComponentSelect[ id=" + id + " ]";
    }
    
}

