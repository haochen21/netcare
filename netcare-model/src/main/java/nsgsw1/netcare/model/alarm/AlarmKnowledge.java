package nsgsw1.netcare.model.alarm;

import java.io.Serializable;

import nsgsw1.netcare.model.alarm.constant.AlarmCategory;
import nsgsw1.netcare.model.res.Vendor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AlarmKnowledge")
public class AlarmKnowledge implements Serializable {

	@Id
	private ObjectId id;

	private String name;

	private String customizeName;

	private String description;

	private String problemCause;

	private Vendor vendor;

	// 是否影响业务
	private Boolean business;

	private AlarmCategory category;

	// 是否网络告警(不关联电路也显示)
	private Boolean netAlarm;

	@Version
	private Long version;

	@Transient
	private String key;

	private static final long serialVersionUID = 6350360856059016798L;

	public AlarmKnowledge() {

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

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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

	public String getKey() {
		String key = "";
		if (this.getCategory() == AlarmCategory.SDH) {
			if (this.getVendor() != null && this.getName() != null) {
				key = this.getVendor().getName() + "~" + this.getName();
			} else {
				key = this.getCategory() + "~" + this.getName();
			}
		} else if (this.getCategory() == AlarmCategory.SWITCH) {
			if (this.getVendor() != null && this.getName() != null) {
				key = this.getVendor().getName() + "~" + this.getName();
			} else
				key = this.getCategory() + "~" + this.getName();
		} else {
			key = this.getCategory() + "~" + this.getName();
		}
		return key;
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
		AlarmKnowledge other = (AlarmKnowledge) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlarmKnowledge [name=" + name + ", vendor=" + vendor
				+ ", business=" + business + ", category=" + category + "]";
	}
}
