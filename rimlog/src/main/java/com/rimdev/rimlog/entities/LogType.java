package com.rimdev.rimlog.entities;


import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "log_type", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogType.findAll", query = "SELECT l FROM LogType l")
    , @NamedQuery(name = "LogType.findById", query = "SELECT l FROM LogType l WHERE l.id = :id")
    , @NamedQuery(name = "LogType.findByLtype", query = "SELECT l FROM LogType l WHERE l.ltype = :ltype")})
public class LogType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Ltype", nullable = false, length = 450)
    private String ltype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logtypeID")
    private Collection<LogFatal> logFatalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logtypeID")
    private Collection<LogOther> logOtherCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logtypeID")
    private Collection<LogInfo> logInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logtypeID")
    private Collection<LogError> logErrorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logtypeID")
    private Collection<LogWarning> logWarningCollection;

    public LogType() {
    }

    public LogType(Integer id) {
        this.id = id;
    }

    public LogType(Integer id, String ltype) {
        this.id = id;
        this.ltype = ltype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype;
    }

    @XmlTransient
    public Collection<LogFatal> getLogFatalCollection() {
        return logFatalCollection;
    }

    public void setLogFatalCollection(Collection<LogFatal> logFatalCollection) {
        this.logFatalCollection = logFatalCollection;
    }

    @XmlTransient
    public Collection<LogOther> getLogOtherCollection() {
        return logOtherCollection;
    }

    public void setLogOtherCollection(Collection<LogOther> logOtherCollection) {
        this.logOtherCollection = logOtherCollection;
    }

    @XmlTransient
    public Collection<LogInfo> getLogInfoCollection() {
        return logInfoCollection;
    }

    public void setLogInfoCollection(Collection<LogInfo> logInfoCollection) {
        this.logInfoCollection = logInfoCollection;
    }

    @XmlTransient
    public Collection<LogError> getLogErrorCollection() {
        return logErrorCollection;
    }

    public void setLogErrorCollection(Collection<LogError> logErrorCollection) {
        this.logErrorCollection = logErrorCollection;
    }

    @XmlTransient
    public Collection<LogWarning> getLogWarningCollection() {
        return logWarningCollection;
    }

    public void setLogWarningCollection(Collection<LogWarning> logWarningCollection) {
        this.logWarningCollection = logWarningCollection;
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
        if (!(object instanceof LogType)) {
            return false;
        }
        LogType other = (LogType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LogType[ id=" + id + " ]";
    }
    
}

