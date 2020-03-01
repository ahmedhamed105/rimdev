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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "component_select", catalog = "rim_user", schema = "")
@XmlRootElement
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "array_name", nullable = false, length = 450)
    private String arrayName;
    @Basic(optional = false)
    @Column(name = "array_object", nullable = false, length = 450)
    private String arrayObject;
    @Column(name = "select_value", length = 450)
    private String selectValue;
    @Column(name = "select_display", length = 450)
    private String selectDisplay;
    @JoinColumn(name = "Component_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Component componentID;

    public ComponentSelect() {
    }

    public ComponentSelect(Integer id) {
        this.id = id;
    }

    public ComponentSelect(Integer id, String arrayName, String arrayObject) {
        this.id = id;
        this.arrayName = arrayName;
        this.arrayObject = arrayObject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArrayName() {
        return arrayName;
    }

    public void setArrayName(String arrayName) {
        this.arrayName = arrayName;
    }

    public String getArrayObject() {
        return arrayObject;
    }

    public void setArrayObject(String arrayObject) {
        this.arrayObject = arrayObject;
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    public String getSelectDisplay() {
        return selectDisplay;
    }

    public void setSelectDisplay(String selectDisplay) {
        this.selectDisplay = selectDisplay;
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

