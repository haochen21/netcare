package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMS")
public class ResEms implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "NATIVEEMSNAME")
	private String nativeEmsName;

	@Column(name = "USERLABEL")
	private String userLabel;

	@ManyToOne
	@JoinColumn(name = "REGIONID")
	private ResRegion region;

	@ManyToOne
	@JoinColumn(name = "VENDORID")
	private ResVendor vendor;

	private static final long serialVersionUID = -998268017107412285L;

	public ResEms() {

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

	public String getNativeEmsName() {
		return nativeEmsName;
	}

	public void setNativeEmsName(String nativeEmsName) {
		this.nativeEmsName = nativeEmsName;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public ResRegion getRegion() {
		return region;
	}

	public void setRegion(ResRegion region) {
		this.region = region;
	}

	public ResVendor getVendor() {
		return vendor;
	}

	public void setVendor(ResVendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ResEms other = (ResEms) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResEms [name=" + name + ", userLabel=" + userLabel + "]";
	}
}
