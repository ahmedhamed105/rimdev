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

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "component_file", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponentFile.findAll", query = "SELECT c FROM ComponentFile c")
    , @NamedQuery(name = "ComponentFile.findById", query = "SELECT c FROM ComponentFile c WHERE c.id = :id")})
public class ComponentFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Component_input_ID")
    private Integer componentinputID;
    @Column(name = "file_type_ID")
    private Integer filetypeID;

  
    

    public ComponentFile(Integer componentinputID, Integer filetypeID) {
		super();
		this.componentinputID = componentinputID;
		this.filetypeID = filetypeID;
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
        if (!(object instanceof ComponentFile)) {
            return false;
        }
        ComponentFile other = (ComponentFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ComponentFile[ id=" + id + " ]";
    }
    
}

