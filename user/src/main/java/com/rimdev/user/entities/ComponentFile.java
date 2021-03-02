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

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "component_file", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponentFile.findAll", query = "SELECT c FROM ComponentFile c")
    , @NamedQuery(name = "ComponentFile.findById", query = "SELECT c FROM ComponentFile c WHERE c.id = :id")})
public class ComponentFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @JoinColumn(name = "Component_input_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ComponentInput componentinputID;
    @JoinColumn(name = "file_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private FileType filetypeID;

    public ComponentFile() {
    }

    public ComponentFile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ComponentInput getComponentinputID() {
        return componentinputID;
    }

    public void setComponentinputID(ComponentInput componentinputID) {
        this.componentinputID = componentinputID;
    }

    public FileType getFiletypeID() {
        return filetypeID;
    }

    public void setFiletypeID(FileType filetypeID) {
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

