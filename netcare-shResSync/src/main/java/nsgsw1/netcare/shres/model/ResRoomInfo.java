package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOMINFO")
public class ResRoomInfo implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	// 机房编号
	@Column(name = "ROOM_NO")
	private String roomNo;

	// 机房简称
	@Column(name = "CHINA_NAME_AB")
	private String chinaName;

	// 机房类型
	@Column(name = "TYPE_NAME")
	private String roomType;

	// 网络层级
	@Column(name = "RENT_UNIT_NAME")
	private String rentUnit;

	private static final long serialVersionUID = -7612313102177136635L;
	
	public ResRoomInfo(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getChinaName() {
		return chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRentUnit() {
		return rentUnit;
	}

	public void setRentUnit(String rentUnit) {
		this.rentUnit = rentUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomNo == null) ? 0 : roomNo.hashCode());
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
		ResRoomInfo other = (ResRoomInfo) obj;
		if (roomNo == null) {
			if (other.roomNo != null)
				return false;
		} else if (!roomNo.equals(other.roomNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResRoomInfo [roomNo=" + roomNo + ", chinaName=" + chinaName
				+ "]";
	}
}
