package nsgsw1.netcare.model.res;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;

public class Ctp implements Serializable {

	@Indexed
	private String dn;

	private String name;

	private String fullCnName;

	private Integer kNumber;

	private Integer lNumber;

	private Integer mNumber;

	private Integer nNumber;

	private String nativeEmsName;

	private String additionalInfo;

	private String userLabel;

	private Integer rate;

	private Me me;

	private Shelf shelf;

	private Slot slot;

	private Card card;

	private Port port;

	private static final long serialVersionUID = -1749948582812250549L;

	public Ctp() {

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

	public Me getMe() {
		return me;
	}

	public void setMe(Me me) {
		this.me = me;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
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
		Ctp other = (Ctp) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ctp [dn=" + dn + "]";
	}
}
