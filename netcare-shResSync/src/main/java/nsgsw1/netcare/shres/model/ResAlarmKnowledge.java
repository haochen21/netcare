package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ALARMKNOWLEDGE")
public class ResAlarmKnowledge implements Serializable {

	public enum AlarmCategory {
		SDH(), DATA(), SWITCH(), ACCESS(), GGSN(), ATM();
	}

	public enum AlarmType {
		COMMUNICATIONALARM(), ENVIROMENTALARM(), EQUIPMENTALARM(), QOSALARM(), PROCESSINGERRORALARM(), OTHER();
	}

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CUSTOMIZENAME")
	private String customizeName;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PROBLEMCAUSE")
	private String problemCause;

	@Column(name = "FAULTHANDLE")
	private String faultHandle;

	@ManyToOne
	@JoinColumn(name = "VENDORID")
	private ResVendor vendor;

	@Column(name = "TYPE")
	private AlarmType type;

	// 是否影响业务
	@Column(name = "ISBUSINESS")
	private Boolean business;

	@Column(name = "CATEGORY")
	private AlarmCategory category;

	// 是否网络告警(不关联电路也显示)
	@Column(name = "ISNETALARM")
	private Boolean netAlarm;

	@Version
	private Long version;

	private static final long serialVersionUID = 3214736299821934361L;

	public ResAlarmKnowledge(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProblemCause() {
		return problemCause;
	}

	public void setProblemCause(String problemCause) {
		this.problemCause = problemCause;
	}

	public String getFaultHandle() {
		return faultHandle;
	}

	public void setFaultHandle(String faultHandle) {
		this.faultHandle = faultHandle;
	}

	public ResVendor getVendor() {
		return vendor;
	}

	public void setVendor(ResVendor vendor) {
		this.vendor = vendor;
	}

	public AlarmType getType() {
		return type;
	}

	public void setType(AlarmType type) {
		this.type = type;
	}

	public Boolean getBusiness() {
		return business;
	}

	public void setBusiness(Boolean business) {
		this.business = business;
	}

	public AlarmCategory getCategory() {
		return category;
	}

	public void setCategory(AlarmCategory category) {
		this.category = category;
	}

	public Boolean getNetAlarm() {
		return netAlarm;
	}

	public void setNetAlarm(Boolean netAlarm) {
		this.netAlarm = netAlarm;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
