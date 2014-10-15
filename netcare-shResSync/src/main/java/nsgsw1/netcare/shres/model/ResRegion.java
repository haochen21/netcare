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
@Table(name = "REGION")
public class ResRegion implements Serializable{

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAMECN")
	private String nameCn;
	
	@Column(name = "ABBREVCN")
	private String abbrevCn;
	
	@Column(name = "CODE")
	private Integer code;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENTREGIONID")
	private ResRegion parentRegion;	
	
	private static final long serialVersionUID = 6045443444836702830L;
	
	public ResRegion(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ResRegion getParentRegion() {
		return parentRegion;
	}

	public void setParentRegion(ResRegion parentRegion) {
		this.parentRegion = parentRegion;
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
		ResRegion other = (ResRegion) obj;
		if (nameCn == null) {
			if (other.nameCn != null)
				return false;
		} else if (!nameCn.equals(other.nameCn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResRegion [nameCn=" + nameCn + "]";
	}

}
