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
@Table(name = "SLOT")
public class ResSlot implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NO")
	private String no;

	@Column(name = "DN")
	private String dn;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEID")
	private ResMe me;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHELFID")
	private ResShelf shelf;

	private static final long serialVersionUID = -7454244610750900282L;

	public ResSlot(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		ResSlot other = (ResSlot) obj;
		if (dn == null) {
			if (other.dn != null)
				return false;
		} else if (!dn.equals(other.dn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResSlot [no=" + no + "]";
	}
}
