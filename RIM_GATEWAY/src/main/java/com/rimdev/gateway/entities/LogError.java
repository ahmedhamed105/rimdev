package com.rimdev.gateway.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "log_error", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogError.findAll", query = "SELECT l FROM LogError l")
    , @NamedQuery(name = "LogError.findById", query = "SELECT l FROM LogError l WHERE l.id = :id")
    , @NamedQuery(name = "LogError.findByUserId", query = "SELECT l FROM LogError l WHERE l.userId = :userId")
    , @NamedQuery(name = "LogError.findByLogText", query = "SELECT l FROM LogError l WHERE l.logText = :logText")
    , @NamedQuery(name = "LogError.findByLogException", query = "SELECT l FROM LogError l WHERE l.logException = :logException")
    , @NamedQuery(name = "LogError.findByLogTime", query = "SELECT l FROM LogError l WHERE l.logTime = :logTime")})
public class LogError implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic(optional = false)
    @Column(name = "log_text", nullable = false, length = 9000)
    private String logText;
    @Column(name = "log_exception", length = 450)
    private String logException;
    @Basic(optional = false)
    @Column(name = "log_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTime;
    @Column(name = "DEVICE_ID")
    private Integer deviceId;
    @Column(name = "Log_type_ID")
    @ManyToOne(optional = false)
    private Integer logtypeID;
    @Basic(optional = false)
    @Column(name = "web_service", nullable = false, length = 450)
    private String webService;
    @Basic(optional = false)
    @Column(name = "Error_code", nullable = false, length = 450)
    private String errorcode;
    @Basic(optional = false)
    @Column(name = "Ip_address", nullable = false, length = 450)
    private String ipaddress;

    

 

	public LogError(int userId, String logText, String logException, Date logTime, Integer deviceId, Integer logtypeID,
			String webService, String errorcode, String ipaddress) {
		super();
		this.userId = userId;
		this.logText = logText;
		this.logException = logException;
		this.logTime = logTime;
		this.deviceId = deviceId;
		this.logtypeID = logtypeID;
		this.webService = webService;
		this.errorcode = errorcode;
		this.ipaddress = ipaddress;
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
        if (!(object instanceof LogError)) {
            return false;
        }
        LogError other = (LogError) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LogError[ id=" + id + " ]";
    }
    
}
