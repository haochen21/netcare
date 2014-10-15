package nsgsw1.netcare.shres.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "CUSTOMER")
public class ResCustomer implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "TELEPHONE")
	private String phone;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CONTACTORNAME")
	private String linkMan;

	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "PROFESSION")
	private String type;

	@Column(name = "CLASSIFY")
	private String level;

	// 资源同步时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCTIME")
	private Date syncTime;

	@Column(name = "NO")
	private String resId;

	@ManyToMany
	@JoinTable(name = "CUSTOMERGROUPRELATION", joinColumns = @JoinColumn(name = "CUSTOMERID"), inverseJoinColumns = @JoinColumn(name = "CUSTOMERGROUPID"))
	Collection<ResCustomerGroup> resCustomerGroups;

	@Version
	private Long version;

	private static final long serialVersionUID = 1119193793185622743L;

	public ResCustomer() {

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

	public Collection<ResCustomerGroup> getResCustomerGroups() {
		return resCustomerGroups;
	}

	public void setResCustomerGroups(
			Collection<ResCustomerGroup> resCustomerGroups) {
		this.resCustomerGroups = resCustomerGroups;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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
		ResCustomer other = (ResCustomer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}

}