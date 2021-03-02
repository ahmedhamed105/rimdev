/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rimdev.gateway.entities;


import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "device", catalog = "rim_user", schema = "rim_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
    , @NamedQuery(name = "Device.findById", query = "SELECT d FROM Device d WHERE d.id = :id")
    , @NamedQuery(name = "Device.findByDevicename", query = "SELECT d FROM Device d WHERE d.devicename = :devicename")
    , @NamedQuery(name = "Device.findByDeviceinfo", query = "SELECT d FROM Device d WHERE d.deviceinfo = :deviceinfo")
    , @NamedQuery(name = "Device.findByDeviceip", query = "SELECT d FROM Device d WHERE d.deviceip = :deviceip")
    , @NamedQuery(name = "Device.findByDevicecode", query = "SELECT d FROM Device d WHERE d.devicecode = :devicecode")
    , @NamedQuery(name = "Device.findByDevicemac", query = "SELECT d FROM Device d WHERE d.devicemac = :devicemac")
    , @NamedQuery(name = "Device.findByDeviceosversion", query = "SELECT d FROM Device d WHERE d.deviceosversion = :deviceosversion")
    , @NamedQuery(name = "Device.findByDeviceosunknow", query = "SELECT d FROM Device d WHERE d.deviceosunknow = :deviceosunknow")
    , @NamedQuery(name = "Device.findByDevicemodify", query = "SELECT d FROM Device d WHERE d.devicemodify = :devicemodify")
    , @NamedQuery(name = "Device.findByDevicecreate", query = "SELECT d FROM Device d WHERE d.devicecreate = :devicecreate")
    , @NamedQuery(name = "Device.findByDevicelong", query = "SELECT d FROM Device d WHERE d.devicelong = :devicelong")
    , @NamedQuery(name = "Device.findByDevicelatitude", query = "SELECT d FROM Device d WHERE d.devicelatitude = :devicelatitude")
    , @NamedQuery(name = "Device.findByDevicebrowser", query = "SELECT d FROM Device d WHERE d.devicebrowser = :devicebrowser")
    , @NamedQuery(name = "Device.findByDeviceBVersion", query = "SELECT d FROM Device d WHERE d.deviceBVersion = :deviceBVersion")
    , @NamedQuery(name = "Device.findByMobile", query = "SELECT d FROM Device d WHERE d.mobile = :mobile")
    , @NamedQuery(name = "Device.findByDesktopDevice", query = "SELECT d FROM Device d WHERE d.desktopDevice = :desktopDevice")
    , @NamedQuery(name = "Device.findByTablet", query = "SELECT d FROM Device d WHERE d.tablet = :tablet")
    , @NamedQuery(name = "Device.findByPage", query = "SELECT d FROM Device d WHERE d.page = :page")
    , @NamedQuery(name = "Device.findByLogintypeID", query = "SELECT d FROM Device d WHERE d.logintypeID = :logintypeID")})
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "Device_name", length = 45)
    private String devicename;
    @Column(name = "Device_info", length = 45)
    private String deviceinfo;
    @Column(name = "Device_ip", length = 45)
    private String deviceip;
    @Basic(optional = false)
    @Column(name = "Device_code", nullable = false, length = 450)
    private String devicecode;
    @Column(name = "Device_mac", length = 45)
    private String devicemac;
    @Column(name = "Device_os_version", length = 45)
    private String deviceosversion;
    @Column(name = "Device_os_unknow", length = 1000)
    private String deviceosunknow;
    @Basic(optional = false)
    @Column(name = "Device_modify", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date devicemodify;
    @Basic(optional = false)
    @Column(name = "Device_create", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date devicecreate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Device_long", precision = 10, scale = 7)
    private BigDecimal devicelong;
    @Column(name = "Device_latitude", precision = 10, scale = 7)
    private BigDecimal devicelatitude;
    @Column(name = "Device_browser", length = 45)
    private String devicebrowser;
    @Column(name = "Device_BVersion", length = 45)
    private String deviceBVersion;
    @Column(name = "Mobile")
    private boolean mobile;
    @Column(name = "Desktop_Device")
    private boolean desktopDevice;
    @Column(name = "Tablet")
    private boolean tablet;
    @Column(name = "Page")
    private Integer page;
    @Basic(optional = false)
    @Column(name = "login_type_ID", nullable = false)
    private Integer logintypeID;
   
    @Column(name = "Application_ID")
    private Integer applicationID;
    @Column(name = "Device_OS_ID")
    private Integer deviceOSID;
    @Column(name = "Device_status_ID")
    private Integer devicestatusID;
    @Column(name = "Device_type_ID")
    private Integer devicetypeID;

    
    
    
    public Device(Integer id, String devicecode) {
		super();
		this.id = id;
		this.devicecode = devicecode;
	}

	public Device(String devicename, String deviceinfo, String deviceip, String devicecode, String devicemac,
			String deviceosversion, String deviceosunknow, Date devicemodify, Date devicecreate, BigDecimal devicelong,
			BigDecimal devicelatitude, String devicebrowser, String deviceBVersion, boolean mobile,
			boolean desktopDevice, boolean tablet, Integer page, Integer logintypeID, Integer applicationID,
			Integer deviceOSID, Integer devicestatusID, Integer devicetypeID) {
		super();
		this.devicename = devicename;
		this.deviceinfo = deviceinfo;
		this.deviceip = deviceip;
		this.devicecode = devicecode;
		this.devicemac = devicemac;
		this.deviceosversion = deviceosversion;
		this.deviceosunknow = deviceosunknow;
		this.devicemodify = devicemodify;
		this.devicecreate = devicecreate;
		this.devicelong = devicelong;
		this.devicelatitude = devicelatitude;
		this.devicebrowser = devicebrowser;
		this.deviceBVersion = deviceBVersion;
		this.mobile = mobile;
		this.desktopDevice = desktopDevice;
		this.tablet = tablet;
		this.page = page;
		this.logintypeID = logintypeID;
		this.applicationID = applicationID;
		this.deviceOSID = deviceOSID;
		this.devicestatusID = devicestatusID;
		this.devicetypeID = devicetypeID;
	}
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getDeviceinfo() {
		return deviceinfo;
	}

	public void setDeviceinfo(String deviceinfo) {
		this.deviceinfo = deviceinfo;
	}

	public String getDeviceip() {
		return deviceip;
	}

	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}

	public String getDevicecode() {
		return devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	public String getDevicemac() {
		return devicemac;
	}

	public void setDevicemac(String devicemac) {
		this.devicemac = devicemac;
	}

	public String getDeviceosversion() {
		return deviceosversion;
	}

	public void setDeviceosversion(String deviceosversion) {
		this.deviceosversion = deviceosversion;
	}

	public String getDeviceosunknow() {
		return deviceosunknow;
	}

	public void setDeviceosunknow(String deviceosunknow) {
		this.deviceosunknow = deviceosunknow;
	}

	public Date getDevicemodify() {
		return devicemodify;
	}

	public void setDevicemodify(Date devicemodify) {
		this.devicemodify = devicemodify;
	}

	public Date getDevicecreate() {
		return devicecreate;
	}

	public void setDevicecreate(Date devicecreate) {
		this.devicecreate = devicecreate;
	}

	public BigDecimal getDevicelong() {
		return devicelong;
	}

	public void setDevicelong(BigDecimal devicelong) {
		this.devicelong = devicelong;
	}

	public BigDecimal getDevicelatitude() {
		return devicelatitude;
	}

	public void setDevicelatitude(BigDecimal devicelatitude) {
		this.devicelatitude = devicelatitude;
	}

	public String getDevicebrowser() {
		return devicebrowser;
	}

	public void setDevicebrowser(String devicebrowser) {
		this.devicebrowser = devicebrowser;
	}

	public String getDeviceBVersion() {
		return deviceBVersion;
	}

	public void setDeviceBVersion(String deviceBVersion) {
		this.deviceBVersion = deviceBVersion;
	}

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	public boolean isDesktopDevice() {
		return desktopDevice;
	}

	public void setDesktopDevice(boolean desktopDevice) {
		this.desktopDevice = desktopDevice;
	}

	public boolean isTablet() {
		return tablet;
	}

	public void setTablet(boolean tablet) {
		this.tablet = tablet;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLogintypeID() {
		return logintypeID;
	}

	public void setLogintypeID(Integer logintypeID) {
		this.logintypeID = logintypeID;
	}

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public Integer getDeviceOSID() {
		return deviceOSID;
	}

	public void setDeviceOSID(Integer deviceOSID) {
		this.deviceOSID = deviceOSID;
	}

	public Integer getDevicestatusID() {
		return devicestatusID;
	}

	public void setDevicestatusID(Integer devicestatusID) {
		this.devicestatusID = devicestatusID;
	}

	public Integer getDevicetypeID() {
		return devicetypeID;
	}

	public void setDevicetypeID(Integer devicetypeID) {
		this.devicetypeID = devicetypeID;
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


}

