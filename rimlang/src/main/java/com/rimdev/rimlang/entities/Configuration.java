package com.rimdev.rimlang.entities;



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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "configuration", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c")
    , @NamedQuery(name = "Configuration.findById", query = "SELECT c FROM Configuration c WHERE c.id = :id")
    , @NamedQuery(name = "Configuration.findByConfigkey", query = "SELECT c FROM Configuration c WHERE c.configkey = :configkey")
    , @NamedQuery(name = "Configuration.findByConfigvalue", query = "SELECT c FROM Configuration c WHERE c.configvalue = :configvalue")
    , @NamedQuery(name = "Configuration.findByConfignum", query = "SELECT c FROM Configuration c WHERE c.confignum = :confignum")
    , @NamedQuery(name = "Configuration.findByConfigboolean", query = "SELECT c FROM Configuration c WHERE c.configboolean = :configboolean")
    , @NamedQuery(name = "Configuration.findByConfigDate", query = "SELECT c FROM Configuration c WHERE c.configDate = :configDate")
    , @NamedQuery(name = "Configuration.findByCreateDate", query = "SELECT c FROM Configuration c WHERE c.createDate = :createDate")
    , @NamedQuery(name = "Configuration.findByModifyDate", query = "SELECT c FROM Configuration c WHERE c.modifyDate = :modifyDate")})
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Config_key", nullable = false, length = 450)
    private String configkey;
    @Column(name = "Config_value", length = 450)
    private String configvalue;
    @Column(name = "Config_num")
    private Integer confignum;
    @Column(name = "Config_boolean")
    private Integer configboolean;
    @Column(name = "Config_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date configDate;
    @Basic(optional = false)
    @Column(name = "Create_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @Column(name = "modify_Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    @JoinColumn(name = "User_login_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private UserLogin userloginID;

    public Configuration() {
    }

    public Configuration(Integer id) {
        this.id = id;
    }

    public Configuration(Integer id, String configkey, Date createDate, Date modifyDate) {
        this.id = id;
        this.configkey = configkey;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigkey() {
        return configkey;
    }

    public void setConfigkey(String configkey) {
        this.configkey = configkey;
    }

    public String getConfigvalue() {
        return configvalue;
    }

    public void setConfigvalue(String configvalue) {
        this.configvalue = configvalue;
    }

    public Integer getConfignum() {
        return confignum;
    }

    public void setConfignum(Integer confignum) {
        this.confignum = confignum;
    }

    public Integer getConfigboolean() {
        return configboolean;
    }

    public void setConfigboolean(Integer configboolean) {
        this.configboolean = configboolean;
    }

    public Date getConfigDate() {
        return configDate;
    }

    public void setConfigDate(Date configDate) {
        this.configDate = configDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public UserLogin getUserloginID() {
        return userloginID;
    }

    public void setUserloginID(UserLogin userloginID) {
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
        if (!(object instanceof Configuration)) {
            return false;
        }
        Configuration other = (Configuration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Configuration[ id=" + id + " ]";
    }
    
}

