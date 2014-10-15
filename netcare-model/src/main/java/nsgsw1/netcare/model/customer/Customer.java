package nsgsw1.netcare.model.customer;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
public class Customer implements Serializable {

	@Id
	private ObjectId id;

	private String name;

	private String phone;

	private String email;

	private String linkMan;

	private String address;

	private String type;

	private String level;

	@Indexed(unique = true)
	private String resId;

	private Boolean fault;

	private Boolean faultAck;

	private Date updateTime;

	@Indexed
	private Collection<ObjectId> userIds = new HashSet<>();

	@Indexed
	private Collection<ObjectId> customerGroupIds = new HashSet<>();

	private static final long serialVersionUID = -1842343099827797253L;

	public Customer() {

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public Boolean getFault() {
		return fault;
	}

	public void setFault(Boolean fault) {
		this.fault = fault;
	}

	public Boolean getFaultAck() {
		return faultAck;
	}

	public void setFaultAck(Boolean faultAck) {
		this.faultAck = faultAck;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Collection<ObjectId> getUserIds() {
		return userIds;
	}

	public void setUserIds(Collection<ObjectId> userIds) {
		this.userIds = userIds;
	}

	public Collection<ObjectId> getCustomerGroupIds() {
		return customerGroupIds;
	}

	public void setCustomerGroupIds(Collection<ObjectId> customerGroupIds) {
		this.customerGroupIds = customerGroupIds;
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", fault=" + fault
				+ "]";
	}
}
