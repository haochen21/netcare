package nsgsw1.netcare.model.res;

import java.io.Serializable;

public class Region implements Serializable{

	private String nameCn;
	
	private String abbrevCn;
	
	private Integer code;
	
	private static final long serialVersionUID = -4077318801080047873L;

	public Region(){
		
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getAbbrevCn() {
		return abbrevCn;
	}

	public void setAbbrevCn(String abbrevCn) {
		this.abbrevCn = abbrevCn;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameCn == null) ? 0 : nameCn.hashCode());
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
		Region other = (Region) obj;
		if (nameCn == null) {
			if (other.nameCn != null)
				return false;
		} else if (!nameCn.equals(other.nameCn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Region [nameCn=" + nameCn + "]";
	}
}
