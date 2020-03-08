package com.rimdev.user.entities;


import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "component_button", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "ComponentButton.findAll", query = "SELECT c FROM ComponentButton c")
    , @NamedQuery(name = "ComponentButton.findById", query = "SELECT c FROM ComponentButton c WHERE c.id = :id")
    , @NamedQuery(name = "ComponentButton.findByButtonClass", query = "SELECT c FROM ComponentButton c WHERE c.buttonClass = :buttonClass")
    , @NamedQuery(name = "ComponentButton.findByButtonMethod", query = "SELECT c FROM ComponentButton c WHERE c.buttonMethod = :buttonMethod")
    , @NamedQuery(name = "ComponentButton.findByButtonType", query = "SELECT c FROM ComponentButton c WHERE c.buttonType = :buttonType")})
public class ComponentButton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "button_class", nullable = false, length = 450)
    private String buttonClass;
    @Basic(optional = false)
    @Column(name = "button_method", nullable = false, length = 450)
    private String buttonMethod;
    @Column(name = "button_type", length = 450)
    private String buttonType;
    @Column(name = "button_service", length = 450)
    private String buttonService;
    @JoinColumn(name = "Component_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Component componentID;

    public ComponentButton() {
    }

    public ComponentButton(Integer id) {
        this.id = id;
    }

    public ComponentButton(Integer id, String buttonClass, String buttonMethod) {
        this.id = id;
        this.buttonClass = buttonClass;
        this.buttonMethod = buttonMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getButtonClass() {
        return buttonClass;
    }

    public void setButtonClass(String buttonClass) {
        this.buttonClass = buttonClass;
    }

    public String getButtonMethod() {
        return buttonMethod;
    }

    public void setButtonMethod(String buttonMethod) {
        this.buttonMethod = buttonMethod;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    
	public String getButtonService() {
		return buttonService;
	}

	public void setButtonService(String buttonService) {
		this.buttonService = buttonService;
	}

	@XmlTransient
    @JsonIgnore
    public Component getComponentID() {
        return componentID;
    }

    public void setComponentID(Component componentID) {
        this.componentID = componentID;
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
