package nsgsw1.netcare.model.res;

import java.io.Serializable;

import nsgsw1.netcare.model.res.constant.MeDeviceType;

import org.springframework.data.mongodb.core.index.Indexed;

public class Me implements Serializable {
 
	@Indexed
	private String dn;

	private String name;

	private String userLabel;

	private MeDeviceType type;

	private String productName;

	private String softwareVersion;

	private Ems ems;	

	private RoomInfo roomInfo;

	private static final long serialVersionUID = -7590414143460142723L;

	public Me() {

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

	public MeDeviceType getType() {
		return type;
	}

	public void setType(MeDeviceType type) {
		this.type = type;
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

	public Ems getEms() {
		return ems;
	}

	public void setEms(Ems ems) {
		this.ems = ems;
	}	

	public RoomInfo getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(RoomInfo roomInfo) {
		this.roomInfo = roomInfo;
	}
}
