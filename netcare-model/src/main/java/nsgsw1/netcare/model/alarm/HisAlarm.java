package nsgsw1.netcare.model.alarm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.alarm.constant.AlarmSeverity;
import nsgsw1.netcare.model.alarm.constant.AlarmType;
import nsgsw1.netcare.model.circuit.Circuit;
import nsgsw1.netcare.model.customer.Customer;
import nsgsw1.netcare.model.customer.CustomerGroup;
import nsgsw1.netcare.model.res.Card;
import nsgsw1.netcare.model.res.Ctp;
import nsgsw1.netcare.model.res.Me;
import nsgsw1.netcare.model.res.Port;
import nsgsw1.netcare.model.res.Shelf;
import nsgsw1.netcare.model.res.Slot;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HisAlarm")
public class HisAlarm implements Serializable {

	@Id
	private ObjectId id;

	// 厂商网管的告警ID
	private String uid;

	private String deviceUid;

	// 告警名称
	private String name;

	private String customizeName;

	private AlarmSeverity severity;

	private AlarmEventStatus status;

	// 告警是否确认 0:未确认 1:已确认
	private Boolean ack;

	private AlarmCategory category;

	private AlarmType type;

	private AlarmObjectType objectType;

	private Boolean customerAlarm;

	// 是否影响业务
	private Boolean business;

	// 告警接收系统创建时间
	private Date createTime;

	// 告警接收系统修改时间
	private Date updateTime;

	// 告警接收系统删除时间
	private Date clearTime;

	// EMS告警产生时间
	private Date emsCreateTime;

	// EMS告警修改时间
	private Date emsUpdateTime;

	private Date emsClearTime;

	// 网元告警产生时间
	private Date meCreateTime;

	// 网元告警修改时间
	private Date meUpdateTime;

	// 网元告警清除时间
	private Date meClearTime;

	private String description;

	private String additionalInfo;

	private String deviceName;

	private Me me;

	private Shelf shelf;

	private Slot slot;

	private Card card;

	private Port port;

	private Ctp ctp;

	private AckInfo ackInfo;

	private ClearInfo clearInfo;

	private String customerInfo;

	private String customerGroupInfo;

	private String circuitInfo;

	@Indexed
	private Collection<ObjectId> userIds = new HashSet<>();

	@Indexed
	private Collection<ObjectId> customerIds = new HashSet<>();

	@Indexed
	private Collection<ObjectId> customerGroupIds = new HashSet<>();

	@Indexed
	private Collection<ObjectId> sncCircuitIds = new HashSet<>();

	@Indexed
	private Collection<ObjectId> channelCircuitIds = new HashSet<>();

	@Transient
	private Collection<Customer> customers = new HashSet<>();

	@Transient
	private Collection<CustomerGroup> customerGroups = new HashSet<>();

	@Transient
	private Collection<Circuit> sncCircuits = new HashSet<>();

	@Transient
	private Collection<Circuit> channelCircuits = new HashSet<>();

	private AlarmKnowledge alarmKnowledge;

	private static final long serialVersionUID = -3939970069383187884L;

	public HisAlarm() {

	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomizeName() {
		return customizeName;
	}

	public void setCustomizeName(String customizeName) {
		this.customizeName = customizeName;
	}

	public AlarmSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(AlarmSeverity severity) {
		this.severity = severity;
	}

	public AlarmEventStatus getStatus() {
		return status;
	}

	public void setStatus(AlarmEventStatus status) {
		this.status = status;
	}

	public Boolean getAck() {
		return ack;
	}

	public void setAck(Boolean ack) {
		this.ack = ack;
	}

	public AlarmCategory getCategory() {
		return category;
	}

	public void setCategory(AlarmCategory category) {
		this.category = category;
	}

	public AlarmType getType() {
		return type;
	}

	public void setType(AlarmType type) {
		this.type = type;
	}

	public AlarmObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(AlarmObjectType objectType) {
		this.objectType = objectType;
	}

	public Boolean getCustomerAlarm() {
		return customerAlarm;
	}

	public void setCustomerAlarm(Boolean customerAlarm) {
		this.customerAlarm = customerAlarm;
	}

	public Boolean getBusiness() {
		return business;
	}

	public void setBusiness(Boolean business) {
		this.business = business;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Date getEmsUpdateTime() {
		return emsUpdateTime;
	}

	public void setEmsUpdateTime(Date emsUpdateTime) {
		this.emsUpdateTime = emsUpdateTime;
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

	public Date getMeUpdateTime() {
		return meUpdateTime;
	}

	public void setMeUpdateTime(Date meUpdateTime) {
		this.meUpdateTime = meUpdateTime;
	}

	public Date getMeClearTime() {
		return meClearTime;
	}

	public void setMeClearTime(Date meClearTime) {
		this.meClearTime = meClearTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Me getMe() {
		return me;
	}

	public void setMe(Me me) {
		this.me = me;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public Ctp getCtp() {
		return ctp;
	}

	public void setCtp(Ctp ctp) {
		this.ctp = ctp;
	}

	public AckInfo getAckInfo() {
		return ackInfo;
	}

	public void setAckInfo(AckInfo ackInfo) {
		this.ackInfo = ackInfo;
	}

	public ClearInfo getClearInfo() {
		return clearInfo;
	}

	public void setClearInfo(ClearInfo clearInfo) {
		this.clearInfo = clearInfo;
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

	public String getCircuitInfo() {
		return circuitInfo;
	}

	public void setCircuitInfo(String circuitInfo) {
		this.circuitInfo = circuitInfo;
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

	public Collection<ObjectId> getSncCircuitIds() {
		return sncCircuitIds;
	}

	public void setSncCircuitIds(Collection<ObjectId> sncCircuitIds) {
		this.sncCircuitIds = sncCircuitIds;
	}

	public Collection<ObjectId> getChannelCircuitIds() {
		return channelCircuitIds;
	}

	public void setChannelCircuitIds(Collection<ObjectId> channelCircuitIds) {
		this.channelCircuitIds = channelCircuitIds;
	}

	public Collection<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Collection<Customer> customers) {
		this.customers = customers;
	}

	public Collection<CustomerGroup> getCustomerGroups() {
		return customerGroups;
	}

	public void setCustomerGroups(Collection<CustomerGroup> customerGroups) {
		this.customerGroups = customerGroups;
	}

	public Collection<Circuit> getSncCircuits() {
		return sncCircuits;
	}

	public void setSncCircuits(Collection<Circuit> sncCircuits) {
		this.sncCircuits = sncCircuits;
	}

	public Collection<Circuit> getChannelCircuits() {
		return channelCircuits;
	}

	public void setChannelCircuits(Collection<Circuit> channelCircuits) {
		this.channelCircuits = channelCircuits;
	}

	public AlarmKnowledge getAlarmKnowledge() {
		return alarmKnowledge;
	}

	public void setAlarmKnowledge(AlarmKnowledge alarmKnowledge) {
		this.alarmKnowledge = alarmKnowledge;
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
		HisAlarm other = (HisAlarm) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
