package nsgsw1.netcare.model.alarm;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.alarm.constant.AlarmClearType;
import nsgsw1.netcare.model.alarm.constant.AlarmEventStatus;
import nsgsw1.netcare.model.alarm.constant.AlarmObjectType;
import nsgsw1.netcare.model.alarm.constant.AlarmSeverity;
import nsgsw1.netcare.model.alarm.constant.AlarmType;

public class AlarmEvent implements Serializable, Delayed {

	// 厂商名
	private String vendorName;

	// 网管名称
	private String emsName;

	// 运营商设备名称
	private String deviceName;

	// 网管设备名称
	private String emsDeviceName;

	// 告警设备分类: 传输，数据，交换，接入
	private AlarmCategory category;

	// 告警设备对象类型:网元、框、槽、卡、口、时隙告警
	private AlarmObjectType objectType;

	// 告警名称
	private String name;

	// 模块号
	private String modelNo;

	// 机架号
	private String rackNo;

	// 框号
	private String shelfNo;

	// 槽号
	private String slotNo;

	// 卡号
	private String cardNo;

	// 端口号
	private String portNo;

	// 时隙号
	private String ctpNo;

	// 链路号 交换专业使用
	private String linkNo;

	// 网管告警流水号
	private String serialNo;

	// 网管告警发生时间
	private Date emsCreateTime;

	// 网元告警发生时间
	private Date meCreateTime;

	// 告警级别
	private AlarmSeverity severity;

	// 告警状态 0:激活 1:清除
	private AlarmEventStatus status;

	// 告警原因
	private String cause;

	// 告警类型
	private AlarmType type;

	// 告警码
	private String code;

	// 信令点索引
	private String spcIndex;

	// 子节点号/子单元号
	private String sNode;

	// 源地址
	private String sourceAddress;

	// 目地址
	private String targetAddress;

	// 告警源
	private String source;

	// 其他描述信息
	private String additionalInfo;

	// 修复建议
	private String repairRecommend;

	// 告警原始文本
	private String originalText;

	// 设备唯一标识符
	private String deviceUid;

	// 呈现给运维人员的设备信息
	private String deviceLocationInfo;

	// 告警唯一标识符 一般由设备标识符+告警名称或流水号组成
	private String uid;

	private long triggerTime;

	// 是否由同步告警派发
	private boolean syncAlarm;

	private AlarmClearType clearType;

	private static final long serialVersionUID = 6536228479326297912L;

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEmsName() {
		return emsName;
	}

	public void setEmsName(String emsName) {
		this.emsName = emsName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getEmsDeviceName() {
		return emsDeviceName;
	}

	public void setEmsDeviceName(String emsDeviceName) {
		this.emsDeviceName = emsDeviceName;
	}

	public AlarmCategory getCategory() {
		return category;
	}

	public void setCategory(AlarmCategory category) {
		this.category = category;
	}

	public AlarmObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(AlarmObjectType objectType) {
		this.objectType = objectType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getRackNo() {
		return rackNo;
	}

	public void setRackNo(String rackNo) {
		this.rackNo = rackNo;
	}

	public String getShelfNo() {
		return shelfNo;
	}

	public void setShelfNo(String shelfNo) {
		this.shelfNo = shelfNo;
	}

	public String getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(String slotNo) {
		this.slotNo = slotNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getCtpNo() {
		return ctpNo;
	}

	public void setCtpNo(String ctpNo) {
		this.ctpNo = ctpNo;
	}

	public String getLinkNo() {
		return linkNo;
	}

	public void setLinkNo(String linkNo) {
		this.linkNo = linkNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Date getEmsCreateTime() {
		return emsCreateTime;
	}

	public void setEmsCreateTime(Date emsCreateTime) {
		this.emsCreateTime = emsCreateTime;
	}

	public Date getMeCreateTime() {
		return meCreateTime;
	}

	public void setMeCreateTime(Date meCreateTime) {
		this.meCreateTime = meCreateTime;
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

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public AlarmType getType() {
		return type;
	}

	public void setType(AlarmType type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSpcIndex() {
		return spcIndex;
	}

	public void setSpcIndex(String spcIndex) {
		this.spcIndex = spcIndex;
	}

	public String getsNode() {
		return sNode;
	}

	public void setsNode(String sNode) {
		this.sNode = sNode;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getTargetAddress() {
		return targetAddress;
	}

	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getRepairRecommend() {
		return repairRecommend;
	}

	public void setRepairRecommend(String repairRecommend) {
		this.repairRecommend = repairRecommend;
	}

	public String getOriginalText() {
		return originalText;
	}

	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public String getDeviceUid() {
		return deviceUid;
	}

	public void setDeviceUid(String deviceUid) {
		this.deviceUid = deviceUid;
	}

	public String getDeviceLocationInfo() {
		return deviceLocationInfo;
	}

	public void setDeviceLocationInfo(String deviceLocationInfo) {
		this.deviceLocationInfo = deviceLocationInfo;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public boolean isSyncAlarm() {
		return syncAlarm;
	}

	public void setSyncAlarm(boolean syncAlarm) {
		this.syncAlarm = syncAlarm;
	}

	public AlarmClearType getClearType() {
		return clearType;
	}

	public void setClearType(AlarmClearType clearType) {
		this.clearType = clearType;
	}

	public void setDelayTime(int delayTime) {
		this.triggerTime = System.nanoTime()
				+ TimeUnit.NANOSECONDS
						.convert(delayTime, TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed arg) {
		AlarmEvent that = (AlarmEvent) arg;
		if (triggerTime < that.triggerTime)
			return -1;
		if (triggerTime > that.triggerTime)
			return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long delayed = unit.convert(triggerTime - System.nanoTime(),
				TimeUnit.NANOSECONDS);
		return delayed;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof AlarmEvent))
			return false;
		AlarmEvent alarmEvent = (AlarmEvent) obj;
		return alarmEvent.getUid().equals(uid);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + getName().hashCode();
		result = 31 * result + getSerialNo().hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "AlarmEvent [category=" + category + ", emsName=" + emsName
				+ ", uid=" + uid + ", status=" + status + ", deviceUid="
				+ deviceUid + ", name=" + name + "]";
	}

}
