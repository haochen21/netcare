package nsgsw1.netcare.model.circuit;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import nsgsw1.netcare.model.circuit.constant.CircuitBizStatus;
import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.Me;
import nsgsw1.netcare.model.res.Port;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Circuit")
public class Circuit implements Serializable {

	@Id
	private ObjectId id;

	// 电路编号
	@Indexed(unique = true)
	private String no;

	// 长途或集团电路编号
	private String groupNo;

	private Boolean hasFault;

	// false:退网(snc为空) true:正常
	private Boolean status;

	// 电路速率
	private String rate;

	// 业务状态
	private String bizState;

	// 业务属性
	private String bizProperty;

	private CircuitBizStatus bizStatus;

	private Date updateTime;

	// 资源同步结果
	private Boolean resSyncResult;

	// 资源同步日志
	private String resSyncLog;

	private String aCustomerName;

	@Indexed
	private ObjectId aCustomerId;

	private String zCustomerName;

	@Indexed
	private ObjectId zCustomerId;

	private Me aMe;

	private Port aPort;

	private Ctp aCtp;

	private Me zMe;

	private Port zPort;

	private Ctp zCtp;

	@Indexed
	private Collection<ObjectId> userIds = new HashSet<>();

	private Collection<ObjectId> exclusionDayIds = new HashSet<>();

	@Version
	private Long version;

	@Transient
	private Customer aCustomer;

	@Transient
	private Customer zCustomer;

	private static final long serialVersionUID = 4440513825808764016L;

	public Circuit() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
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

	public Boolean getHasFault() {
		return hasFault;
	}

	public void setHasFault(Boolean hasFault) {
		this.hasFault = hasFault;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public CircuitBizStatus getBizStatus() {
		return bizStatus;
	}

	public void setBizStatus(CircuitBizStatus bizStatus) {
		this.bizStatus = bizStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getResSyncResult() {
		return resSyncResult;
	}

	public void setResSyncResult(Boolean resSyncResult) {
		this.resSyncResult = resSyncResult;
	}

	public String getResSyncLog() {
		return resSyncLog;
	}

	public void setResSyncLog(String resSyncLog) {
		this.resSyncLog = resSyncLog;
	}

	public String getaCustomerName() {
		return aCustomerName;
	}

	public void setaCustomerName(String aCustomerName) {
		this.aCustomerName = aCustomerName;
	}

	public ObjectId getaCustomerId() {
		return aCustomerId;
	}

	public void setaCustomerId(ObjectId aCustomerId) {
		this.aCustomerId = aCustomerId;
	}

	public String getzCustomerName() {
		return zCustomerName;
	}

	public void setzCustomerName(String zCustomerName) {
		this.zCustomerName = zCustomerName;
	}

	public ObjectId getzCustomerId() {
		return zCustomerId;
	}

	public void setzCustomerId(ObjectId zCustomerId) {
		this.zCustomerId = zCustomerId;
	}

	public Me getaMe() {
		return aMe;
	}

	public void setaMe(Me aMe) {
		this.aMe = aMe;
	}

	public Port getaPort() {
		return aPort;
	}

	public void setaPort(Port aPort) {
		this.aPort = aPort;
	}

	public Ctp getaCtp() {
		return aCtp;
	}

	public void setaCtp(Ctp aCtp) {
		this.aCtp = aCtp;
	}

	public Me getzMe() {
		return zMe;
	}

	public void setzMe(Me zMe) {
		this.zMe = zMe;
	}

	public Port getzPort() {
		return zPort;
	}

	public void setzPort(Port zPort) {
		this.zPort = zPort;
	}

	public Ctp getzCtp() {
		return zCtp;
	}

	public void setzCtp(Ctp zCtp) {
		this.zCtp = zCtp;
	}

	public Collection<ObjectId> getUserIds() {
		return userIds;
	}

	public void setUserIds(Collection<ObjectId> userIds) {
		this.userIds = userIds;
	}

	public Collection<ObjectId> getExclusionDayIds() {
		return exclusionDayIds;
	}

	public void setExclusionDayIds(Collection<ObjectId> exclusionDayIds) {
		this.exclusionDayIds = exclusionDayIds;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Customer getaCustomer() {
		return aCustomer;
	}

	public void setaCustomer(Customer aCustomer) {
		this.aCustomer = aCustomer;
	}

	public Customer getzCustomer() {
		return zCustomer;
	}

	public void setzCustomer(Customer zCustomer) {
		this.zCustomer = zCustomer;
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
		Circuit other = (Circuit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Circuit [no=" + no + "]";
	}
}
