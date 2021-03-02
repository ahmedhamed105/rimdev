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

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "notification", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id")
    , @NamedQuery(name = "Notification.findByNotiftext", query = "SELECT n FROM Notification n WHERE n.notiftext = :notiftext")
    , @NamedQuery(name = "Notification.findByNotifDate", query = "SELECT n FROM Notification n WHERE n.notifDate = :notifDate")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Notif_text", nullable = false, length = 450)
    private String notiftext;
    @Column(name = "Notif_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notifDate;
    @Column(name = "Application_ID")
    private Integer applicationID;
    @Column(name = "Group_priviledge_ID")
    private Integer grouppriviledgeID;
    @Column(name = "User_login_ID")
    private Integer userloginID;

  
    

    public Notification(String notiftext, Date notifDate, Integer applicationID, Integer grouppriviledgeID,
			Integer userloginID) {
		super();
		this.notiftext = notiftext;
		this.notifDate = notifDate;
		this.applicationID = applicationID;
		this.grouppriviledgeID = grouppriviledgeID;
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
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Notification[ id=" + id + " ]";
    }
    
}
