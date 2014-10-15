package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARD")
public class ResCard implements Serializable{

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "NATIVEEMSNAME")
	private String nativeEmsName;

	@Column(name = "NO")
	private Integer no;	

	@Column(name = "USERLABEL")
	private String userLabel;
	
	@Column(name = "FULLCNNAME")
	private String fullCnName;
	
	@Column(name = "DN")
	private String dn;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEID")
	private ResMe me;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELFID")
	private ResShelf shelf;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SLOTID")
	private ResSlot slot;
	
	private static final long serialVersionUID = -6660354409907635065L;

	public ResCard(){
		
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

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public String getFullCnName() {
		return fullCnName;
	}

	public void setFullCnName(String fullCnName) {
		this.fullCnName = fullCnName;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public ResMe getMe() {
		return me;
	}

	public void setMe(ResMe me) {
		this.me = me;
	}

	public ResShelf getShelf() {
		return shelf;
	}

	public void setShelf(ResShelf shelf) {
		this.shelf = shelf;
	}

	public ResSlot getSlot() {
		return slot;
	}

	public void setSlot(ResSlot slot) {
		this.slot = slot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fullCnName == null) ? 0 : fullCnName.hashCode());
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
		ResCard other = (ResCard) obj;
		if (fullCnName == null) {
			if (other.fullCnName != null)
				return false;
		} else if (!fullCnName.equals(other.fullCnName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResCard [userLabel=" + userLabel + "]";
	}
}
