package nsgsw1.netcare.model.alarm;

import java.io.Serializable;
import java.util.Date;

import nsgsw1.netcare.model.alarm.constant.AlarmSeverity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AlarmInfo")
public class AlarmInfo implements Serializable {

	@Id
	private ObjectId id;

	// 厂商网管的告警ID
	@Indexed
	private String uid;

	// 设备DN~告警名称
	@Indexed
	private String deviceAlarmUid;

	private Date createTime;

	private Date clearTime;

	private Date emsCreateTime;

	private Date emsClearTime;

	private Date meCreateTime;

	private Date meClearTime;

	private AlarmSeverity severity;	

	private static final long serialVersionUID = 8297799935166778527L;

	public AlarmInfo() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDeviceAlarmUid() {
		return deviceAlarmUid;
	}

	public void setDeviceAlarmUid(String deviceAlarmUid) {
		this.deviceAlarmUid = deviceAlarmUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getClearTime() {
		return clearTime;
	}

	public void setClearTime(Date clearTime) {
		this.clearTime = clearTime;
	}

	public Date getEmsCreateTime() {
		return emsCreateTime;
	}

	public void setEmsCreateTime(Date emsCreateTime) {
		this.emsCreateTime = emsCreateTime;
	}

	public Date getEmsClearTime() {
		return emsClearTime;
	}

	public void setEmsClearTime(Date emsClearTime) {
		this.emsClearTime = emsClearTime;
	}

	public Date getMeCreateTime() {
		return meCreateTime;
	}

	public void setMeCreateTime(Date meCreateTime) {
		this.meCreateTime = meCreateTime;
	}

	public Date getMeClearTime() {
		return meClearTime;
	}

	public void setMeClearTime(Date meClearTime) {
		this.meClearTime = meClearTime;
	}

	public AlarmSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(AlarmSeverity severity) {
		this.severity = severity;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AlarmInfo other = (AlarmInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlarmInfo [uid=" + uid + "]";
	}
}
