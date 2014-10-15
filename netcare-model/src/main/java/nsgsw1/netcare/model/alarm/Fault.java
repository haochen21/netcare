package nsgsw1.netcare.model.alarm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import nsgsw1.netcare.model.alarm.constant.FaultOperation;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Fault")
public class Fault implements Serializable {

	@Id
	private ObjectId id;

	@Indexed(unique = true)
	private ObjectId circuitId;

	private String no;

	private String groupNo;

	private String rate;

	private String bizState;

	private String bizProperty;

	private Date beginTime;

	private Date meCreateTime;

	private Boolean faultAck;

	@Indexed
	private Collection<ObjectId> userIds = new HashSet<>();

	@Indexed
	private Collection<ObjectId> customerIds = new ArrayList<>();

	@Indexed
	private Collection<ObjectId> customerGroupIds = new ArrayList<>();

	private String customerInfo;

	private String customerGroupInfo;
	
	private Collection<ObjectId> currAlarmIds = new ArrayList<>();

	private Collection<ObjectId> hisAlarmIds = new ArrayList<>();

	@Version
	private Long version;

	@Transient
	FaultOperation operation;

	@Transient
	@JsonIgnore
	private Circuit circuit;

	@Transient
	@JsonIgnore
	private Collection<CustomerGroup> customerGroups = new HashSet<>();
	
	@Transient
	@JsonIgnore
	private Collection<Customer> customers = new HashSet<>();

	@Transient
	@JsonIgnore
	private Collection<CurrAlarm> currAlarms = new HashSet<>();

	@Transient
	@JsonIgnore
	private Collection<HisAlarm> hisAlarms = new HashSet<>();

	private static final long serialVersionUID = -4979890189215124598L;

	public Fault() {

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

	public Date getMeCreateTime() {
		return meCreateTime;
	}

	public void setMeCreateTime(Date meCreateTime) {
		this.meCreateTime = meCreateTime;
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

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public String getCustomerGroupInfo() {
		return customerGroupInfo;
	}

	public void setCustomerGroupInfo(String customerGroupInfo) {
		this.customerGroupInfo = customerGroupInfo;
	}

	public Collection<ObjectId> getCurrAlarmIds() {
		return currAlarmIds;
	}

	public void setCurrAlarmIds(Collection<ObjectId> currAlarmIds) {
		this.currAlarmIds = currAlarmIds;
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

	public FaultOperation getOperation() {
		return operation;
	}

	public void setOperation(FaultOperation operation) {
		this.operation = operation;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
	}

	public Collection<CurrAlarm> getCurrAlarms() {
		return currAlarms;
	}

	public void setCurrAlarms(Collection<CurrAlarm> currAlarms) {
		this.currAlarms = currAlarms;
	}

	public Collection<HisAlarm> getHisAlarms() {
		return hisAlarms;
	}

	public void setHisAlarms(Collection<HisAlarm> hisAlarms) {
		this.hisAlarms = hisAlarms;
	}

	public Collection<CustomerGroup> getCustomerGroups() {
		return customerGroups;
	}

	public void setCustomerGroups(Collection<CustomerGroup> customerGroups) {
		this.customerGroups = customerGroups;
	}

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
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
		Fault other = (Fault) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fault [circuit=" + no + ", beginTime=" + beginTime + "]";
	}
}
