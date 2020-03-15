package com.rimdev.user.entities;


import java.io.Serializable;
import java.util.Date;

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
@Table(name = "component_input", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "ComponentInput.findAll", query = "SELECT c FROM ComponentInput c")
    , @NamedQuery(name = "ComponentInput.findById", query = "SELECT c FROM ComponentInput c WHERE c.id = :id")
    , @NamedQuery(name = "ComponentInput.findByInputActions", query = "SELECT c FROM ComponentInput c WHERE c.inputActions = :inputActions")})
public class ComponentInput implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "input_Actions", nullable = false, length = 45)
    private String inputActions;
    @JoinColumn(name = "Component_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Component componentID;
    @JoinColumn(name = "input_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private InputType inputtypeID;
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "insert_serv", length = 450)
    private String insertServ;
    @Column(name = "delete_serv", length = 450)
    private String deleteServ;
    @Column(name = "insert_parameter", length = 450)
    private String insertParameter;
    @Column(name = "delete_parameter", length = 450)
    private String deleteParameter;

    public ComponentInput() {
    }

    public ComponentInput(Integer id) {
        this.id = id;
    }

    public ComponentInput(Integer id, String inputActions) {
        this.id = id;
        this.inputActions = inputActions;
    }
    
    
    public String getInsertServ() {
        return insertServ;
    }

    public void setInsertServ(String insertServ) {
        this.insertServ = insertServ;
    }

    public String getDeleteServ() {
        return deleteServ;
    }

    public void setDeleteServ(String deleteServ) {
        this.deleteServ = deleteServ;
    }

    public String getInsertParameter() {
        return insertParameter;
    }

    public void setInsertParameter(String insertParameter) {
        this.insertParameter = insertParameter;
    }

    public String getDeleteParameter() {
        return deleteParameter;
    }

    public void setDeleteParameter(String deleteParameter) {
        this.deleteParameter = deleteParameter;
    }

    @XmlTransient
    @JsonIgnore
    public Date getDateModify() {
        return dateModify;
    }

    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }
    
    @XmlTransient
    @JsonIgnore
    public Date getDateCreate() {
        return dateCreate;
    }
    
    
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInputActions() {
        return inputActions;
    }

    public void setInputActions(String inputActions) {
        this.inputActions = inputActions;
    }
	@XmlTransient
    @JsonIgnore
    public Component getComponentID() {
        return componentID;
    }

    public void setComponentID(Component componentID) {
        this.componentID = componentID;
    }

    public InputType getInputtypeID() {
        return inputtypeID;
    }

    public void setInputtypeID(InputType inputtypeID) {
        this.inputtypeID = inputtypeID;
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
        if (!(object instanceof ComponentInput)) {
            return false;
        }
        ComponentInput other = (ComponentInput) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ComponentInput[ id=" + id + " ]";
    }
    
}
