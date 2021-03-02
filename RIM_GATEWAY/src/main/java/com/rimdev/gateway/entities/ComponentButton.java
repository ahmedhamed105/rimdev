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
@Table(name = "component_button", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "ComponentButton.findAll", query = "SELECT c FROM ComponentButton c")
    , @NamedQuery(name = "ComponentButton.findById", query = "SELECT c FROM ComponentButton c WHERE c.id = :id")
    , @NamedQuery(name = "ComponentButton.findByButtonClass", query = "SELECT c FROM ComponentButton c WHERE c.buttonClass = :buttonClass")
    , @NamedQuery(name = "ComponentButton.findByButtonType", query = "SELECT c FROM ComponentButton c WHERE c.buttonType = :buttonType")})
public class ComponentButton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "button_class", nullable = false, length = 450)
    private String buttonClass;
    @Column(name = "button_type", length = 450)
    private String buttonType;
    @Column(name = "Component_ID")
    private Integer componentID;
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "valid")
    private int valid;
    @Column(name = "alert_after")
    private Integer alertAfter;
    @Column(name = "empty_message", length = 900)
    private String emptyMessage;

    
    
    
  


	public ComponentButton(Integer id, String buttonClass, String buttonType, Integer componentID, Date dateModify,
			Date dateCreate, int valid, Integer alertAfter, String emptyMessage) {
		super();
		this.id = id;
		this.buttonClass = buttonClass;
		this.buttonType = buttonType;
		this.componentID = componentID;
		this.dateModify = dateModify;
		this.dateCreate = dateCreate;
		this.valid = valid;
		this.alertAfter = alertAfter;
		this.emptyMessage = emptyMessage;
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
        if (!(object instanceof ComponentButton)) {
            return false;
        }
        ComponentButton other = (ComponentButton) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ComponentButton[ id=" + id + " ]";
    }
    
}
