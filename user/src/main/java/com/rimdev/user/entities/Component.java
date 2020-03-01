package com.rimdev.user.entities;


import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "component", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c")
    , @NamedQuery(name = "Component.findById", query = "SELECT c FROM Component c WHERE c.id = :id")
    , @NamedQuery(name = "Component.findByName", query = "SELECT c FROM Component c WHERE c.name = :name")
    , @NamedQuery(name = "Component.findByGroupname", query = "SELECT c FROM Component c WHERE c.groupname = :groupname")
    , @NamedQuery(name = "Component.findByLabelname", query = "SELECT c FROM Component c WHERE c.labelname = :labelname")
    , @NamedQuery(name = "Component.findByFormname", query = "SELECT c FROM Component c WHERE c.formname = :formname")
    , @NamedQuery(name = "Component.findByCcode", query = "SELECT c FROM Component c WHERE c.ccode = :ccode")
    , @NamedQuery(name = "Component.findByCtype", query = "SELECT c FROM Component c WHERE c.ctype = :ctype")
    , @NamedQuery(name = "Component.findByCrequired", query = "SELECT c FROM Component c WHERE c.crequired = :crequired")
    , @NamedQuery(name = "Component.findByCpattern", query = "SELECT c FROM Component c WHERE c.cpattern = :cpattern")
    , @NamedQuery(name = "Component.findByPatterndesgin", query = "SELECT c FROM Component c WHERE c.patterndesgin = :patterndesgin")})
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 450)
    private String name;
    @Column(name = "Group_name", length = 45)
    private String groupname;
    @Column(name = "Label_name", length = 500)
    private String labelname;
    @Column(name = "Form_name", length = 45)
    private String formname;
    @Basic(optional = false)
    @Column(name = "C_code", nullable = false, length = 450)
    private String ccode;
    @Column(name = "C_type", length = 45)
    private String ctype;
    @Basic(optional = false)
    @Column(name = "C_required", nullable = false)
    private int crequired;
    @Basic(optional = false)
    @Column(name = "C_pattern", nullable = false)
    private int cpattern;
    @Column(name = "patterndesgin", length = 500)
    private String patterndesgin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentID")
    private Collection<ComponentSelect> componentSelectCollection;
    @JoinColumn(name = "Pages_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Pages pagesID;

    public Component() {
    }

    public Component(Integer id) {
        this.id = id;
    }

    public Component(Integer id, String name, String ccode, int crequired, int cpattern) {
        this.id = id;
        this.name = name;
        this.ccode = ccode;
        this.crequired = crequired;
        this.cpattern = cpattern;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public int getCrequired() {
        return crequired;
    }

    public void setCrequired(int crequired) {
        this.crequired = crequired;
    }

    public int getCpattern() {
        return cpattern;
    }

    public void setCpattern(int cpattern) {
        this.cpattern = cpattern;
    }

    public String getPatterndesgin() {
        return patterndesgin;
    }

    public void setPatterndesgin(String patterndesgin) {
        this.patterndesgin = patterndesgin;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ComponentSelect> getComponentSelectCollection() {
        return componentSelectCollection;
    }

    public void setComponentSelectCollection(Collection<ComponentSelect> componentSelectCollection) {
        this.componentSelectCollection = componentSelectCollection;
    }
    @XmlTransient
    @JsonIgnore
    public Pages getPagesID() {
        return pagesID;
    }

    public void setPagesID(Pages pagesID) {
        this.pagesID = pagesID;
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
        if (!(object instanceof Component)) {
            return false;
        }
        Component other = (Component) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Component[ id=" + id + " ]";
    }
    
}
