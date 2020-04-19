package com.rimdev.user.entities;


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

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "log_other", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogOther.findAll", query = "SELECT l FROM LogOther l")
    , @NamedQuery(name = "LogOther.findById", query = "SELECT l FROM LogOther l WHERE l.id = :id")
    , @NamedQuery(name = "LogOther.findByUserId", query = "SELECT l FROM LogOther l WHERE l.userId = :userId")
    , @NamedQuery(name = "LogOther.findByLogText", query = "SELECT l FROM LogOther l WHERE l.logText = :logText")
    , @NamedQuery(name = "LogOther.findByLogException", query = "SELECT l FROM LogOther l WHERE l.logException = :logException")
    , @NamedQuery(name = "LogOther.findByLogTime", query = "SELECT l FROM LogOther l WHERE l.logTime = :logTime")})
public class LogOther implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic(optional = false)
    @Column(name = "log_text", nullable = false, length = 450)
    private String logText;
    @Column(name = "log_exception", length = 450)
    private String logException;
    @Basic(optional = false)
    @Column(name = "log_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTime;
    @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Device deviceId;
    @JoinColumn(name = "Log_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private LogType logtypeID;
    @Basic(optional = false)
    @Column(name = "web_service", nullable = false, length = 450)
    private String webService;
    @Basic(optional = false)
    @Column(name = "Error_code", nullable = false, length = 450)
    private String errorcode;
    @Basic(optional = false)
    @Column(name = "Ip_address", nullable = false, length = 450)
    private String ipaddress;

    public LogOther() {
    }

    public LogOther(Integer id) {
        this.id = id;
    }

    public LogOther(Integer id, int userId, String logText, Date logTime) {
        this.id = id;
        this.userId = userId;
        this.logText = logText;
        this.logTime = logTime;
    }
    
    

    public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getWebService() {
		return webService;
	}

	public void setWebService(String webService) {
		this.webService = webService;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogText() {
        return logText;
    }

    public void setLogText(String logText) {
        this.logText = logText;
    }

    public String getLogException() {
        return logException;
    }

    public void setLogException(String logException) {
        this.logException = logException;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
    }

    public LogType getLogtypeID() {
        return logtypeID;
    }

    public void setLogtypeID(LogType logtypeID) {
        this.logtypeID = logtypeID;
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
        if (!(object instanceof LogOther)) {
            return false;
        }
        LogOther other = (LogOther) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LogOther[ id=" + id + " ]";
    }
    
}
