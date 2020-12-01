/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.rimpriv.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaID")
    private Collection<Adress> adressCollection;

    public Area() {
    }

    public Area(Integer id) {
        this.id = id;
    }

    public Area(Integer id, String areaname, String arealong1, Date areamodify, Date areacreate) {
        this.id = id;
        this.areaname = areaname;
        this.arealong1 = arealong1;
        this.areamodify = areamodify;
        this.areacreate = areacreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getArealong1() {
        return arealong1;
    }

    public void setArealong1(String arealong1) {
        this.arealong1 = arealong1;
    }

    public String getArealong2() {
        return arealong2;
    }

    public void setArealong2(String arealong2) {
        this.arealong2 = arealong2;
    }

    public String getArealong3() {
        return arealong3;
    }

    public void setArealong3(String arealong3) {
        this.arealong3 = arealong3;
    }

    public String getArealat1() {
        return arealat1;
    }

    public void setArealat1(String arealat1) {
        this.arealat1 = arealat1;
    }

    public String getArealat2() {
        return arealat2;
    }

    public void setArealat2(String arealat2) {
        this.arealat2 = arealat2;
    }

    public String getArealat3() {
        return arealat3;
    }

    public void setArealat3(String arealat3) {
        this.arealat3 = arealat3;
    }

    public String getArealat4() {
        return arealat4;
    }

    public void setArealat4(String arealat4) {
        this.arealat4 = arealat4;
    }
    @XmlTransient
    @JsonIgnore
    public Date getAreamodify() {
        return areamodify;
    }

    public void setAreamodify(Date areamodify) {
        this.areamodify = areamodify;
    }
    @XmlTransient
    @JsonIgnore
    public Date getAreacreate() {
        return areacreate;
    }

    public void setAreacreate(Date areacreate) {
        this.areacreate = areacreate;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Adress> getAdressCollection() {
        return adressCollection;
    }

    public void setAdressCollection(Collection<Adress> adressCollection) {
        this.adressCollection = adressCollection;
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
