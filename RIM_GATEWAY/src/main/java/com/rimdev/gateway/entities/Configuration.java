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
@Table(name = "configuration", catalog = "rim_user", schema = "rim_user")
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "User_login_ID")
    private Integer userloginID;

    
    

    public Configuration(String configkey, String configvalue, Integer confignum, Integer configboolean,
			Date configDate, Date createDate, Date modifyDate, Integer userloginID) {
		super();
		this.configkey = configkey;
		this.configvalue = configvalue;
		this.confignum = confignum;
		this.configboolean = configboolean;
		this.configDate = configDate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
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

