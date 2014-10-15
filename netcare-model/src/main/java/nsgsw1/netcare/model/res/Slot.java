package nsgsw1.netcare.model.res;

import java.io.Serializable;

import org.springframework.data.mongodb.core.index.Indexed;

public class Slot implements Serializable{

	@Indexed
	private String dn;
	
	private String no;
	
	private static final long serialVersionUID = 1111675143161052634L;

	public Slot(){
		
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
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
		Slot other = (Slot) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Slot [dn=" + dn + "]";
	}
}
