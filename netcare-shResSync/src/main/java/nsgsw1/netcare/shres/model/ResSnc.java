package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SUBNETWORKCONNECTION")
@NamedQueries( {
	@NamedQuery(name = "ResSnc.findByCircuitId", query = "SELECT snc from ResSnc snc where snc.circuitId = :circuitId") })
public class ResSnc implements Serializable {

	public enum SncType {
		ROUTE("传输路由"), DDF("配线"), LINK("跨专业路由");

		private final String description;

		SncType(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "CIRCUITID")
	private Long circuitId;

	@Column(name = "TYPE")
	@Enumerated(EnumType.ORDINAL)
	private SncType type;

	@Column(name = "AVLANVALUE")
	private String aVlanValue;

	@Column(name = "ZVLANVALUE")
	private String zVlanValue;

	@ManyToOne
	@JoinColumn(name = "AMEID")
	private ResMe resAMe;

	@ManyToOne
	@JoinColumn(name = "ZMEID")
	private ResMe resZMe;

	@ManyToOne
	@JoinColumn(name = "APTPID")
	private ResPort resAPort;

	@ManyToOne
	@JoinColumn(name = "ZPTPID")
	private ResPort resZPort;

	@ManyToOne
	@JoinColumn(name = "ACTPID")
	private ResCtp resACtp;

	@ManyToOne
	@JoinColumn(name = "ZCTPID")
	private ResCtp resZCtp;

	@ManyToOne
	@JoinColumn(name = "REGIONID")
	private ResRegion resRegion;

	private static final long serialVersionUID = 2454584471121895693L;

	public ResSnc(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCircuitId() {
		return circuitId;
	}

	public void setCircuitId(Long circuitId) {
		this.circuitId = circuitId;
	}

	public SncType getType() {
		return type;
	}

	public void setType(SncType type) {
		this.type = type;
	}

	public String getaVlanValue() {
		return aVlanValue;
	}

	public void setaVlanValue(String aVlanValue) {
		this.aVlanValue = aVlanValue;
	}

	public String getzVlanValue() {
		return zVlanValue;
	}

	public void setzVlanValue(String zVlanValue) {
		this.zVlanValue = zVlanValue;
	}

	public ResMe getResAMe() {
		return resAMe;
	}

	public void setResAMe(ResMe resAMe) {
		this.resAMe = resAMe;
	}

	public ResMe getResZMe() {
		return resZMe;
	}

	public void setResZMe(ResMe resZMe) {
		this.resZMe = resZMe;
	}

	public ResPort getResAPort() {
		return resAPort;
	}

	public void setResAPort(ResPort resAPort) {
		this.resAPort = resAPort;
	}

	public ResPort getResZPort() {
		return resZPort;
	}

	public void setResZPort(ResPort resZPort) {
		this.resZPort = resZPort;
	}

	public ResCtp getResACtp() {
		return resACtp;
	}

	public void setResACtp(ResCtp resACtp) {
		this.resACtp = resACtp;
	}

	public ResCtp getResZCtp() {
		return resZCtp;
	}

	public void setResZCtp(ResCtp resZCtp) {
		this.resZCtp = resZCtp;
	}

	public ResRegion getResRegion() {
		return resRegion;
	}

	public void setResRegion(ResRegion resRegion) {
		this.resRegion = resRegion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ResSnc other = (ResSnc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResSnc [circuitId=" + circuitId + ", type=" + type + "]";
	}
}
