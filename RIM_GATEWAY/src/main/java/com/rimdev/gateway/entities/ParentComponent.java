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
@Table(name = "parent_component", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "ParentComponent.findAll", query = "SELECT p FROM ParentComponent p")
    , @NamedQuery(name = "ParentComponent.findById", query = "SELECT p FROM ParentComponent p WHERE p.id = :id")
    , @NamedQuery(name = "ParentComponent.findByPcodeTittle", query = "SELECT p FROM ParentComponent p WHERE p.pcodeTittle = :pcodeTittle")
    , @NamedQuery(name = "ParentComponent.findByParentPostion", query = "SELECT p FROM ParentComponent p WHERE p.parentPostion = :parentPostion")
    , @NamedQuery(name = "ParentComponent.findByParentType", query = "SELECT p FROM ParentComponent p WHERE p.parentType = :parentType")
    , @NamedQuery(name = "ParentComponent.findByParentName", query = "SELECT p FROM ParentComponent p WHERE p.parentName = :parentName")})
public class ParentComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "pcode_tittle", nullable = false, length = 450)
    private String pcodeTittle;
    @Basic(optional = false)
    @Column(name = "parent_postion", nullable = false)
    private Integer parentPostion;
    @Column(name = "parent_type", length = 45)
    private String parentType;
    @Column(name = "parent_name", length = 45)
    private String parentName;
    @Column(name = "Pages_ID")
    private Integer pagesID;
    @Column(name = "First_method", length = 450)
    private String firstmethod;
    @Basic(optional = false)
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Basic(optional = false)
    @Column(name = "pagination", nullable = false)
    private Integer pagination;
    @Column(name = "Com_IP", nullable = true, length = 450)
    private String comIP;
    @Basic(optional = false)
    @Column(name = "Com_port", nullable = true, length = 45)
    private String comport;
    @Column(name = "com_table")
    private Integer comTable;
    @Column(name = "com_formid")
    private Integer comFormid;
    
    


    public ParentComponent(String pcodeTittle, Integer parentPostion, String parentType, String parentName,
			Integer pagesID, String firstmethod, Date dateModify, Date dateCreate, Integer pagination, String comIP,
			String comport, Integer comTable, Integer comFormid) {
		super();
		this.pcodeTittle = pcodeTittle;
		this.parentPostion = parentPostion;
		this.parentType = parentType;
		this.parentName = parentName;
		this.pagesID = pagesID;
		this.firstmethod = firstmethod;
		this.dateModify = dateModify;
		this.dateCreate = dateCreate;
		this.pagination = pagination;
		this.comIP = comIP;
		this.comport = comport;
		this.comTable = comTable;
		this.comFormid = comFormid;
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
        if (!(object instanceof ParentComponent)) {
            return false;
        }
        ParentComponent other = (ParentComponent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParentComponent[ id=" + id + " ]";
    }
    
}
