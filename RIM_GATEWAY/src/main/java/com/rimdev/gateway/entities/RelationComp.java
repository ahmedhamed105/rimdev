package com.rimdev.gateway.entities;



import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "relation_comp", catalog = "rim_user", schema = "rim_user")
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "Component_ID")
    private Integer componentID;
    @Column(name = "Relation_type_ID")
    private Integer relationtypeID;
    @Column(name = "Data_type", length = 45)
    private String datatype;
    @Column(name = "routing_ind", nullable = false)
    private Integer routingInd;
    @Column(name = "routing_loc", length = 450)
    private String routingLoc;
    @Column(name = "alert_after")
    private Integer alertAfter;
    @Column(name = "sucess_message", length = 900)
    private String sucessMessage;
    @Column(name = "resetind", nullable = false)
    private Integer resetind;
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



    public RelationComp(String service, String emptyServ, String relparentServempty, String comIPpar, String comportpar,
			String comIP, String comport, Integer relatedComponent, Integer relatedParent, String parServ,
			Integer componentID, Integer relationtypeID, String datatype, Integer routingInd, String routingLoc,
			Integer alertAfter, String sucessMessage, Integer resetind, Integer resetParent, Integer enableComp,
			Integer visibleComp, Integer disableComp, Integer hideComp) {
		super();
		this.service = service;
		this.emptyServ = emptyServ;
		this.relparentServempty = relparentServempty;
		this.comIPpar = comIPpar;
		this.comportpar = comportpar;
		this.comIP = comIP;
		this.comport = comport;
		this.relatedComponent = relatedComponent;
		this.relatedParent = relatedParent;
		this.parServ = parServ;
		this.componentID = componentID;
		this.relationtypeID = relationtypeID;
		this.datatype = datatype;
		this.routingInd = routingInd;
		this.routingLoc = routingLoc;
		this.alertAfter = alertAfter;
		this.sucessMessage = sucessMessage;
		this.resetind = resetind;
		this.resetParent = resetParent;
		this.enableComp = enableComp;
		this.visibleComp = visibleComp;
		this.disableComp = disableComp;
		this.hideComp = hideComp;
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
