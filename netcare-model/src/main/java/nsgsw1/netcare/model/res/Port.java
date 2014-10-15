package nsgsw1.netcare.model.res;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;

public class Port implements Serializable {

	private String name;

	private String nativeEmsName;

	private Integer no;

	@Indexed
	private String dn;

	private Integer ptpRate;

	private String additionalInfo;

	private static final long serialVersionUID = 3345821165933506492L;

	public Port() {

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
		Port other = (Port) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Port [dn=" + dn + "]";
	}
}
