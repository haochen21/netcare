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
@Table(name = "PHYSICALTERMINATIONPOINT")
public class ResPort implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "NATIVEEMSNAME")
	private String nativeEmsName;

	@Column(name = "NO")
	private Integer no;

	@Column(name = "DN")
	private String dn;

	@Column(name = "PTPRATE")
	private Integer ptpRate;

	@Column(name = "ADDITIONALINFO")
	private String additionalInfo;

	@ManyToOne
	@JoinColumn(name = "MEID")
	private ResMe me;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELFID")
	private ResShelf shelf;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CARDID")
	private ResCard card;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SLOTID")
	private ResSlot slot;

	private static final long serialVersionUID = -2802688561492053051L;

	public ResPort() {

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

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public Integer getPtpRate() {
		return ptpRate;
	}

	public void setPtpRate(Integer ptpRate) {
		this.ptpRate = ptpRate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
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

	public ResCard getCard() {
		return card;
	}

	public void setCard(ResCard card) {
		this.card = card;
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
		result = prime * result + ((dn == null) ? 0 : dn.hashCode());
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
		ResPort other = (ResPort) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResPort [name=" + name + "]";
	}
}
