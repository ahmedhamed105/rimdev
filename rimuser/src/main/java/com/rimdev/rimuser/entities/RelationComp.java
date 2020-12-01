package com.rimdev.rimuser.entities;



import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Table(name = "relation_comp", catalog = "rim_user", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelationComp.findAll", query = "SELECT r FROM RelationComp r")
    , @NamedQuery(name = "RelationComp.findById", query = "SELECT r FROM RelationComp r WHERE r.id = :id")
    , @NamedQuery(name = "RelationComp.findByService", query = "SELECT r FROM RelationComp r WHERE r.service = :service")
    , @NamedQuery(name = "RelationComp.findByComIP", query = "SELECT r FROM RelationComp r WHERE r.comIP = :comIP")
    , @NamedQuery(name = "RelationComp.findByComport", query = "SELECT r FROM RelationComp r WHERE r.comport = :comport")
    , @NamedQuery(name = "RelationComp.findByRelatedComponent", query = "SELECT r FROM RelationComp r WHERE r.relatedComponent = :relatedComponent")
    , @NamedQuery(name = "RelationComp.findByRelatedParent", query = "SELECT r FROM RelationComp r WHERE r.relatedParent = :relatedParent")})
public class RelationComp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "service", length = 450)
    private String service;
    @Column(name = "empty_serv", length = 450)
    private String emptyServ;
    @Column(name = "relparent_servempty", length = 450)
    private String relparentServempty;
    @Column(name = "Com_IPpar", length = 450)
    private String comIPpar;
    @Column(name = "Com_portpar", length = 45)
    private String comportpar;    
    @Basic(optional = false)
    @Column(name = "Com_IP", nullable = false, length = 450)
    private String comIP;
    @Basic(optional = false)
    @Column(name = "Com_port", nullable = false, length = 45)
    private String comport;
    @Column(name = "related_component")
    private Integer relatedComponent;
    @Column(name = "related_parent")
    private Integer relatedParent;
    @Column(name = "par_serv", length = 450)
    private String parServ;
    @JoinColumn(name = "Component_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Component componentID;
    @JoinColumn(name = "Relation_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private RelationType relationtypeID;
    @Column(name = "Data_type", length = 45)
    private String datatype;
    @Column(name = "routing_ind", nullable = false)
    private int routingInd;
    @Column(name = "routing_loc", length = 450)
    private String routingLoc;
    @Column(name = "alert_after")
    private Integer alertAfter;
    @Column(name = "sucess_message", length = 900)
    private String sucessMessage;
    @Column(name = "resetind", nullable = false)
    private int resetind;
    @Column(name = "reset_parent")
    private Integer resetParent;
    @Column(name = "enable_comp")
    private Integer enableComp;
    @Column(name = "visible_comp")
    private Integer visibleComp;
    @Column(name = "disable_comp")
    private Integer disableComp;
    @Column(name = "hide_comp")
    private Integer hideComp;


    public RelationComp() {
    }

    public RelationComp(Integer id) {
        this.id = id;
    }

    public RelationComp(Integer id, String comIP, String comport) {
        this.id = id;
        this.comIP = comIP;
        this.comport = comport;
    } 
    
    


	public Integer getDisableComp() {
		return disableComp;
	}

	public void setDisableComp(Integer disableComp) {
		this.disableComp = disableComp;
	}

	public Integer getHideComp() {
		return hideComp;
	}

	public void setHideComp(Integer hideComp) {
		this.hideComp = hideComp;
	}

	public Integer getEnableComp() {
		return enableComp;
	}

	public void setEnableComp(Integer enableComp) {
		this.enableComp = enableComp;
	}

	public Integer getVisibleComp() {
		return visibleComp;
	}

	public void setVisibleComp(Integer visibleComp) {
		this.visibleComp = visibleComp;
	}

	public Integer getResetParent() {
		return resetParent;
	}

	public void setResetParent(Integer resetParent) {
		this.resetParent = resetParent;
	}

	public int getResetind() {
		return resetind;
	}

	public void setResetind(int resetind) {
		this.resetind = resetind;
	}

	public Integer getAlertAfter() {
		return alertAfter;
	}

	public void setAlertAfter(Integer alertAfter) {
		this.alertAfter = alertAfter;
	}

	public String getSucessMessage() {
		return sucessMessage;
	}

	public void setSucessMessage(String sucessMessage) {
		this.sucessMessage = sucessMessage;
	}

	public int getRoutingInd() {
		return routingInd;
	}

	public void setRoutingInd(int routingInd) {
		this.routingInd = routingInd;
	}

	public String getRoutingLoc() {
		return routingLoc;
	}

	public void setRoutingLoc(String routingLoc) {
		this.routingLoc = routingLoc;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getParServ() {
		return parServ;
	}

	public void setParServ(String parServ) {
		this.parServ = parServ;
	}

	public String getRelparentServempty() {
		return relparentServempty;
	}

	public void setRelparentServempty(String relparentServempty) {
		this.relparentServempty = relparentServempty;
	}

	public String getComIPpar() {
		return comIPpar;
	}

	public void setComIPpar(String comIPpar) {
		this.comIPpar = comIPpar;
	}

	public String getComportpar() {
		return comportpar;
	}

	public void setComportpar(String comportpar) {
		this.comportpar = comportpar;
	}

	public String getEmptyServ() {
		return emptyServ;
	}

	public void setEmptyServ(String emptyServ) {
		this.emptyServ = emptyServ;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getComIP() {
        return comIP;
    }

    public void setComIP(String comIP) {
        this.comIP = comIP;
    }

    public String getComport() {
        return comport;
    }

    public void setComport(String comport) {
        this.comport = comport;
    }

    public Integer getRelatedComponent() {
        return relatedComponent;
    }

    public void setRelatedComponent(Integer relatedComponent) {
        this.relatedComponent = relatedComponent;
    }

    public Integer getRelatedParent() {
        return relatedParent;
    }

    public void setRelatedParent(Integer relatedParent) {
        this.relatedParent = relatedParent;
    }

    public Component getComponentID() {
        return componentID;
    }

    public void setComponentID(Component componentID) {
        this.componentID = componentID;
    }

    public RelationType getRelationtypeID() {
        return relationtypeID;
    }

    public void setRelationtypeID(RelationType relationtypeID) {
        this.relationtypeID = relationtypeID;
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
        if (!(object instanceof RelationComp)) {
            return false;
        }
        RelationComp other = (RelationComp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RelationComp[ id=" + id + " ]";
    }
    
}
