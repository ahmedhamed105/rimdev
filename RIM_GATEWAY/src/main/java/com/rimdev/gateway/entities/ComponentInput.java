package com.rimdev.gateway.entities;


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

import lombok.Data;

/**
 *
 * @author ahmed.elemam
 */
@Entity
@Data
@Table(name = "component_input", catalog = "rim_user", schema = "")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
@DynamicUpdate
@NamedQueries({
    @NamedQuery(name = "ComponentInput.findAll", query = "SELECT c FROM ComponentInput c")
    , @NamedQuery(name = "ComponentInput.findById", query = "SELECT c FROM ComponentInput c WHERE c.id = :id")
    , @NamedQuery(name = "ComponentInput.findByInputActions", query = "SELECT c FROM ComponentInput c WHERE c.inputActions = :inputActions")})
public class ComponentInput implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "input_Actions", nullable = false, length = 45)
    private String inputActions;
    @JoinColumn(name = "Component_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Component componentID;
    @JoinColumn(name = "input_type_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private InputType inputtypeID;
    @Column(name = "date_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModify;
    @Basic(optional = false)
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "insert_serv", length = 450)
    private String insertServ;
    @Column(name = "delete_serv", length = 450)
    private String deleteServ;
    @Column(name = "insert_parameter", length = 450)
    private String insertParameter;
    @Column(name = "delete_parameter", length = 450)
    private String deleteParameter;
    @Column(name = "file_count")
    private Integer fileCount;
    @Column(name = "file_size")
    private Integer fileSize;
    @Column(name = "file_counterr", length = 450)
    private String fileCounterr;
    @Column(name = "file_sizeerr", length = 450)
    private String fileSizeerr;
    @Column(name = "file_typeerror", length = 450)
    private String fileTypeerror;   
    @Column(name = "insert_ip", length = 450)
    private String insertIp;
    @Column(name = "insert_port", length = 45)
    private String insertPort;
    @Column(name = "delete_ip", length = 450)
    private String deleteIp;
    @Column(name = "delete_port", length = 45)
    private String deletePort;
    


    public ComponentInput(String inputActions, Component componentID, InputType inputtypeID, Date dateModify,
			Date dateCreate, String insertServ, String deleteServ, String insertParameter, String deleteParameter,
			Integer fileCount, Integer fileSize, String fileCounterr, String fileSizeerr, String fileTypeerror,
			String insertIp, String insertPort, String deleteIp, String deletePort) {
		super();
		this.inputActions = inputActions;
		this.componentID = componentID;
		this.inputtypeID = inputtypeID;
		this.dateModify = dateModify;
		this.dateCreate = dateCreate;
		this.insertServ = insertServ;
		this.deleteServ = deleteServ;
		this.insertParameter = insertParameter;
		this.deleteParameter = deleteParameter;
		this.fileCount = fileCount;
		this.fileSize = fileSize;
		this.fileCounterr = fileCounterr;
		this.fileSizeerr = fileSizeerr;
		this.fileTypeerror = fileTypeerror;
		this.insertIp = insertIp;
		this.insertPort = insertPort;
		this.deleteIp = deleteIp;
		this.deletePort = deletePort;
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
        if (!(object instanceof ComponentInput)) {
            return false;
        }
        ComponentInput other = (ComponentInput) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ComponentInput[ id=" + id + " ]";
    }
    
}
