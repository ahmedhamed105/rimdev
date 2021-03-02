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
@Data // Lombok: adds getters and setters
@Table(name = "adress", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Adress.findAll", query = "SELECT a FROM Adress a")
    , @NamedQuery(name = "Adress.findById", query = "SELECT a FROM Adress a WHERE a.id = :id")
    , @NamedQuery(name = "Adress.findByAdname", query = "SELECT a FROM Adress a WHERE a.adname = :adname")
    , @NamedQuery(name = "Adress.findByAdStreet", query = "SELECT a FROM Adress a WHERE a.adStreet = :adStreet")
    , @NamedQuery(name = "Adress.findByAdbuilding", query = "SELECT a FROM Adress a WHERE a.adbuilding = :adbuilding")
    , @NamedQuery(name = "Adress.findByAdArea", query = "SELECT a FROM Adress a WHERE a.adArea = :adArea")
    , @NamedQuery(name = "Adress.findByAdlogtiude", query = "SELECT a FROM Adress a WHERE a.adlogtiude = :adlogtiude")
    , @NamedQuery(name = "Adress.findByAdlatitude", query = "SELECT a FROM Adress a WHERE a.adlatitude = :adlatitude")
    , @NamedQuery(name = "Adress.findByAddModify", query = "SELECT a FROM Adress a WHERE a.addModify = :addModify")
    , @NamedQuery(name = "Adress.findByAddCreate", query = "SELECT a FROM Adress a WHERE a.addCreate = :addCreate")})
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Ad_name", nullable = false, length = 45)
    private String adname;
    @Column(name = "ad_street", length = 45)
    private String adStreet;
    @Column(name = "Ad_building", length = 45)
    private String adbuilding;
    @Column(name = "Ad_Area", length = 45)
    private String adArea;
    @Column(name = "Ad_logtiude", length = 45)
    private String adlogtiude;
    @Column(name = "Ad_latitude", length = 45)
    private String adlatitude;
    @Basic(optional = false)
    @Column(name = "add_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addModify;
    @Basic(optional = false)
    @Column(name = "add_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addCreate;
    @Column(name = "Area_ID")
    private Integer areaID;
    @Column(name = "User_login_ID")
    private Integer userloginID;

   

	public Adress(String adname, String adStreet, String adbuilding, String adArea, String adlogtiude,
			String adlatitude, Date addModify, Date addCreate, Integer areaID, Integer userloginID) {
		super();
		this.adname = adname;
		this.adStreet = adStreet;
		this.adbuilding = adbuilding;
		this.adArea = adArea;
		this.adlogtiude = adlogtiude;
		this.adlatitude = adlatitude;
		this.addModify = addModify;
		this.addCreate = addCreate;
		this.areaID = areaID;
		this.userloginID = userloginID;
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
        if (!(object instanceof Adress)) {
            return false;
        }
        Adress other = (Adress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Adress[ id=" + id + " ]";
    }
    
}

