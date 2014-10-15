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
@Table(name = "CONNECTIONTERMINATIONPOINT")
public class ResCtp implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "DN")
	private String dn;

	@Column(name = "NAME")
	private String name;

	@Column(name = "FULLCNNAME")
	private String fullCnName;

	@Column(name = "KNUMBER")
	private Integer kNumber;

	@Column(name = "LNUMBER")
	private Integer lNumber;

	@Column(name = "MNUMBER")
	private Integer mNumber;

	@Column(name = "NNUMBER")
	private Integer nNumber;

	@Column(name = "NATIVEEMSNAME")
	private String nativeEmsName;

	@Column(name = "ADDITIONALINFO")
	private String additionalInfo;

	@Column(name = "USERLABEL")
	private String userLabel;

	@Column(name = "RATE")
	private Integer rate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARDID")
	private ResCard card;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMSID")
	private ResEms ems;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEID")
	private ResMe me;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PTPID")
	private ResPort port;

	private static final long serialVersionUID = 552571231487231645L;

	public ResCtp() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullCnName() {
		return fullCnName;
	}

	public void setFullCnName(String fullCnName) {
		this.fullCnName = fullCnName;
	}

	public Integer getkNumber() {
		return kNumber;
	}

	public void setkNumber(Integer kNumber) {
		this.kNumber = kNumber;
	}

	public Integer getlNumber() {
		return lNumber;
	}

	public void setlNumber(Integer lNumber) {
		this.lNumber = lNumber;
	}

	public Integer getmNumber() {
		return mNumber;
	}

	public void setmNumber(Integer mNumber) {
		this.mNumber = mNumber;
	}

	public Integer getnNumber() {
		return nNumber;
	}

	public void setnNumber(Integer nNumber) {
		this.nNumber = nNumber;
	}

	public String getNativeEmsName() {
		return nativeEmsName;
	}

	public void setNativeEmsName(String nativeEmsName) {
		this.nativeEmsName = nativeEmsName;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public ResCard getCard() {
		return card;
	}

	public void setCard(ResCard card) {
		this.card = card;
	}

	public ResEms getEms() {
		return ems;
	}

	public void setEms(ResEms ems) {
		this.ems = ems;
	}

	public ResMe getMe() {
		return me;
	}

	public void setMe(ResMe me) {
		this.me = me;
	}

	public ResPort getPort() {
		return port;
	}

	public void setPort(ResPort port) {
		this.port = port;
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
		ResCtp other = (ResCtp) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResCtp [dn=" + dn + "]";
	}

}
