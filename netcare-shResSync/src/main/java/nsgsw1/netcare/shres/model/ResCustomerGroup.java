package nsgsw1.netcare.shres.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "CUSTOMERGROUP")
public class ResCustomerGroup implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ALIAS")
	private String alias;

	@Column(name = "LOGONAME")
	private String portrait;

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

	@Column(name = "RESCUSTOMERID")
	private String resId;

	@ManyToMany(mappedBy = "resCustomerGroups")
	Collection<ResCustomer> resCustomers;

	@Version
	private Long version;

	private static final long serialVersionUID = -5580972376988800757L;

	public ResCustomerGroup() {

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
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

	public Collection<ResCustomer> getResCustomers() {
		return resCustomers;
	}

	public void setResCustomers(Collection<ResCustomer> resCustomers) {
		this.resCustomers = resCustomers;
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
		ResCustomerGroup other = (ResCustomerGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShCustomerGroup [id=" + id + ", name=" + name + ", resId="
				+ resId + "]";
	}
}
