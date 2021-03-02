package com.rimdev.user.entities;


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
@Table(name = "component", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
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
    @Column(name = "seq_num", nullable = false)
    private int seqNum;
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
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "disable", nullable = false)
    private int disable;
    @Column(name = "label_icon", length = 450)
    private String labelIcon;
    @Column(name = "parent_group", length = 450)
    private String parentGroup;
    @Basic(optional = false)
    @Column(name = "field_encry", nullable = false)
    private int fieldEncry;
    @Column(name = "required_error", length = 450)
    private String requiredError;
    @Column(name = "patern_error", length = 450)
    private String paternError;
    @Basic(optional = false)
    @Column(name = "visible", nullable = false)
    private int visible;
    @Basic(optional = false)
    @Column(name = "table_visible", nullable = false)
    private int tableVisible;
    @Column(name = "Amethod", length = 450)
    private String amethod;
    @Column(name = "table_disable")
    private Integer tableDisable;
    @Column(name = "display_data_error")
    private Integer displayDataError;
        
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentID")
    private Collection<ComponentSelect> componentSelectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentID")
    private Collection<ComponentInput> componentInputCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentID")
    private Collection<ComponentButton> componentButtonCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentID")
    private Collection<UserFile> userFileCollection;
    
    @JoinColumn(name = "parent_component_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private ParentComponent parentcomponentID;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componentID")
    private Collection<RelationComp> relationCompCollection;
    

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
    
   
    

	public Integer getDisplayDataError() {
		return displayDataError;
	}

	public void setDisplayDataError(Integer displayDataError) {
		this.displayDataError = displayDataError;
	}

	public Integer getTableDisable() {
		return tableDisable;
	}

	public void setTableDisable(Integer tableDisable) {
		this.tableDisable = tableDisable;
	}

	public String getAmethod() {
		return amethod;
	}

	public void setAmethod(String amethod) {
		this.amethod = amethod;
	}

	public int getTableVisible() {
		return tableVisible;
	}

	public void setTableVisible(int tableVisible) {
		this.tableVisible = tableVisible;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public String getRequiredError() {
		return requiredError;
	}

	public void setRequiredError(String requiredError) {
		this.requiredError = requiredError;
	}

	public String getPaternError() {
		return paternError;
	}

	public void setPaternError(String paternError) {
		this.paternError = paternError;
	}

	public String getParentGroup() {
		return parentGroup;
	}

	public void setParentGroup(String parentGroup) {
		this.parentGroup = parentGroup;
	}

	public int getFieldEncry() {
		return fieldEncry;
	}

	public void setFieldEncry(int fieldEncry) {
		this.fieldEncry = fieldEncry;
	}

	public String getLabelIcon() {
        return labelIcon;
    }

    public void setLabelIcon(String labelIcon) {
        this.labelIcon = labelIcon;
    }
    
    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }
    
    @XmlTransient
    @JsonIgnore
    public Collection<UserFile> getUserFileCollection() {
        return userFileCollection;
    }

    public void setUserFileCollection(Collection<UserFile> userFileCollection) {
        this.userFileCollection = userFileCollection;
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
    
    

    public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
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
    public Collection<RelationComp> getRelationCompCollection() {
        return relationCompCollection;
    }

    public void setRelationCompCollection(Collection<RelationComp> relationCompCollection) {
        this.relationCompCollection = relationCompCollection;
    }
    
    
    @XmlTransient
    @JsonIgnore
    public Collection<ComponentInput> getComponentInputCollection() {
        return componentInputCollection;
    }

    public void setComponentInputCollection(Collection<ComponentInput> componentInputCollection) {
        this.componentInputCollection = componentInputCollection;
    }
    
    @XmlTransient
    @JsonIgnore
    public Collection<ComponentButton> getComponentButtonCollection() {
        return componentButtonCollection;
    }

    public void setComponentButtonCollection(Collection<ComponentButton> componentButtonCollection) {
        this.componentButtonCollection = componentButtonCollection;
    }
    
    
    @XmlTransient
    @JsonIgnore
    public ParentComponent getParentcomponentID() {
        return parentcomponentID;
    }

    public void setParentcomponentID(ParentComponent parentcomponentID) {
        this.parentcomponentID = parentcomponentID;
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
