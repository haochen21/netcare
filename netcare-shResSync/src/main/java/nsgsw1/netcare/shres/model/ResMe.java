package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGEDELEMENT")
public class ResMe implements Serializable {

	public enum DeviceType {
		SDH, SWITCH, DATA, ACCESS,GGSN,OTHER,ATM;
	}

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DN")
	private String dn;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "USERLABEL")
	private String userLabel;
	
	@Column(name = "DEVICETYPE")
	@Enumerated(EnumType.ORDINAL)
	private ResMe.DeviceType deviceType;
	
	@Column(name = "PRODUCTNAME")
	private String productName;
	
	@Column(name = "SOFTWAREVERSION")
	private String softwareVersion;
	
	@ManyToOne
	@JoinColumn(name = "EMSID")
	private ResEms ems;
	
	@ManyToOne
	@JoinColumn(name = "VENDORID")
	private ResVendor vendor;	

	@ManyToOne
	@JoinColumn(name = "ROOMINFOID")
	private ResRoomInfo roomInfo;
	
	private static final long serialVersionUID = -2669890767735183098L;

	public ResMe(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public ResMe.DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(ResMe.DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public ResEms getEms() {
		return ems;
	}

	public void setEms(ResEms ems) {
		this.ems = ems;
	}

	public ResVendor getVendor() {
		return vendor;
	}

	public void setVendor(ResVendor vendor) {
		this.vendor = vendor;
	}

	public ResRoomInfo getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(ResRoomInfo roomInfo) {
		this.roomInfo = roomInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dn == null) ? 0 : dn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResMe other = (ResMe) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResMe [name=" + name + "]";
	}
}
