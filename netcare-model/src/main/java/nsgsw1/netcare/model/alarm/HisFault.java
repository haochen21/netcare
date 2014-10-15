package nsgsw1.netcare.model.alarm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import nsgsw1.netcare.model.circuit.Circuit;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HisFault")
public class HisFault implements Serializable {

	@Id
	private ObjectId id;

	@Indexed
	private ObjectId circuitId;

	private String no;

	private String groupNo;

	private String rate;

	private String bizState;

	private String bizProperty;

	private Date beginTime;

	private Date endTime;

	private Date meCreateTime;

	private Date meClearTime;

	// 故障持续时间-秒
	private Long duration;

	private Boolean faultAck;

	@Indexed
	private Collection<ObjectId> userIds = new HashSet<>();

	@Indexed
	private Collection<ObjectId> customerIds = new ArrayList<>();

	@Indexed
	private Collection<ObjectId> customerGroupIds = new ArrayList<>();

	private Collection<ObjectId> hisAlarmIds = new ArrayList<>();

	@Version
	private Long version;

	private static final long serialVersionUID = -724666366443924587L;

	@Transient
	private Circuit circuit;

	public HisFault() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(ObjectId circuitId) {
		this.circuitId = circuitId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getBizState() {
		return bizState;
	}

	public void setBizState(String bizState) {
		this.bizState = bizState;
	}

	public String getBizProperty() {
		return bizProperty;
	}

	public void setBizProperty(String bizProperty) {
		this.bizProperty = bizProperty;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Boolean getFaultAck() {
		return faultAck;
	}

	public void setFaultAck(Boolean faultAck) {
		this.faultAck = faultAck;
	}

	public Collection<ObjectId> getUserIds() {
		return userIds;
	}

	public void setUserIds(Collection<ObjectId> userIds) {
		this.userIds = userIds;
	}

	public Collection<ObjectId> getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(Collection<ObjectId> customerIds) {
		this.customerIds = customerIds;
	}

	public Collection<ObjectId> getCustomerGroupIds() {
		return customerGroupIds;
	}

	public void setCustomerGroupIds(Collection<ObjectId> customerGroupIds) {
		this.customerGroupIds = customerGroupIds;
	}

	public Collection<ObjectId> getHisAlarmIds() {
		return hisAlarmIds;
	}

	public void setHisAlarmIds(Collection<ObjectId> hisAlarmIds) {
		this.hisAlarmIds = hisAlarmIds;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
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
		HisFault other = (HisFault) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HisFault [no=" + no + ", beginTime=" + beginTime + ", endTime="
				+ endTime + "]";
	}

}
