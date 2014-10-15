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

	// ������
	private String vendorName;

	// ��������
	private String emsName;

	// ��Ӫ���豸����
	private String deviceName;

	// �����豸����
	private String emsDeviceName;

	// �澯�豸����: ���䣬���ݣ�����������
	private AlarmCategory category;

	// �澯�豸��������:��Ԫ���򡢲ۡ������ڡ�ʱ϶�澯
	private AlarmObjectType objectType;

	// �澯����
	private String name;

	// ģ���
	private String modelNo;

	// ���ܺ�
	private String rackNo;

	// ���
	private String shelfNo;

	// �ۺ�
	private String slotNo;

	// ����
	private String cardNo;

	// �˿ں�
	private String portNo;

	// ʱ϶��
	private String ctpNo;

	// ��·�� ����רҵʹ��
	private String linkNo;

	// ���ܸ澯��ˮ��
	private String serialNo;

	// ���ܸ澯����ʱ��
	private Date emsCreateTime;

	// ��Ԫ�澯����ʱ��
	private Date meCreateTime;

	// �澯����
	private AlarmSeverity severity;

	// �澯״̬ 0:���� 1:���
	private AlarmEventStatus status;

	// �澯ԭ��
	private String cause;

	// �澯����
	private AlarmType type;

	// �澯��
	private String code;

	// ���������
	private String spcIndex;

	// �ӽڵ��/�ӵ�Ԫ��
	private String sNode;

	// Դ��ַ
	private String sourceAddress;

	// Ŀ��ַ
	private String targetAddress;

	// �澯Դ
	private String source;

	// ����������Ϣ
	private String additionalInfo;

	// �޸�����
	private String repairRecommend;

	// �澯ԭʼ�ı�
	private String originalText;

	// �豸Ψһ��ʶ��
	private String deviceUid;

	// ���ָ���ά��Ա���豸��Ϣ
	private String deviceLocationInfo;

	// �澯Ψһ��ʶ�� һ�����豸��ʶ��+�澯���ƻ���ˮ�����
	private String uid;

	private long triggerTime;

	// �Ƿ���ͬ���澯�ɷ�
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
