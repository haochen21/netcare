package nsgsw1.netcare.model.res;

import java.io.Serializable;

public class RoomInfo implements Serializable {

	// 机房编号
	private String roomNo;

	// 机房简称
	private String chinaName;

	private String roomType;

	// 网络层级
	private String rentUnit;
	
	private static final long serialVersionUID = -6295941899984222713L;

	public RoomInfo(){
		
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
		RoomInfo other = (RoomInfo) obj;
		if (roomNo == null) {
			if (other.roomNo != null)
				return false;
		} else if (!roomNo.equals(other.roomNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomInfo [roomNo=" + roomNo + ", chinaName=" + chinaName + "]";
	}
}
