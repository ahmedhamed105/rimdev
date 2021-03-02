/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "area", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
    , @NamedQuery(name = "Area.findById", query = "SELECT a FROM Area a WHERE a.id = :id")
    , @NamedQuery(name = "Area.findByAreaname", query = "SELECT a FROM Area a WHERE a.areaname = :areaname")
    , @NamedQuery(name = "Area.findByArealong1", query = "SELECT a FROM Area a WHERE a.arealong1 = :arealong1")
    , @NamedQuery(name = "Area.findByArealong2", query = "SELECT a FROM Area a WHERE a.arealong2 = :arealong2")
    , @NamedQuery(name = "Area.findByArealong3", query = "SELECT a FROM Area a WHERE a.arealong3 = :arealong3")
    , @NamedQuery(name = "Area.findByArealat1", query = "SELECT a FROM Area a WHERE a.arealat1 = :arealat1")
    , @NamedQuery(name = "Area.findByArealat2", query = "SELECT a FROM Area a WHERE a.arealat2 = :arealat2")
    , @NamedQuery(name = "Area.findByArealat3", query = "SELECT a FROM Area a WHERE a.arealat3 = :arealat3")
    , @NamedQuery(name = "Area.findByArealat4", query = "SELECT a FROM Area a WHERE a.arealat4 = :arealat4")
    , @NamedQuery(name = "Area.findByAreamodify", query = "SELECT a FROM Area a WHERE a.areamodify = :areamodify")
    , @NamedQuery(name = "Area.findByAreacreate", query = "SELECT a FROM Area a WHERE a.areacreate = :areacreate")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Area_name", nullable = false, length = 45)
    private String areaname;
    @Basic(optional = false)
    @Column(name = "Area_long1", nullable = false, length = 45)
    private String arealong1;
    @Column(name = "Area_long2", length = 45)
    private String arealong2;
    @Column(name = "Area_long3", length = 45)
    private String arealong3;
    @Column(name = "Area_lat1", length = 45)
    private String arealat1;
    @Column(name = "Area_lat2", length = 45)
    private String arealat2;
    @Column(name = "Area_lat3", length = 45)
    private String arealat3;
    @Column(name = "Area_lat4", length = 45)
    private String arealat4;
    @Basic(optional = false)
    @Column(name = "Area_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date areamodify;
    @Basic(optional = false)
    @Column(name = "Area_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date areacreate;
  
    
    

   
    public Area(String areaname, String arealong1, String arealong2, String arealong3, String arealat1, String arealat2,
			String arealat3, String arealat4, Date areamodify, Date areacreate) {
		super();
		this.areaname = areaname;
		this.arealong1 = arealong1;
		this.arealong2 = arealong2;
		this.arealong3 = arealong3;
		this.arealat1 = arealat1;
		this.arealat2 = arealat2;
		this.arealat3 = arealat3;
		this.arealat4 = arealat4;
		this.areamodify = areamodify;
		this.areacreate = areacreate;
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
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Area[ id=" + id + " ]";
    }
    
}
