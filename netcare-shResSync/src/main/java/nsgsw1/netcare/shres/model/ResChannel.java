package nsgsw1.netcare.shres.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHANNEL")
public class ResChannel implements Serializable {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "ISPRIMARY")
	private Boolean primary;

	/**
	 * 1:1,1+1保护路由
	 */
	@Column(name = "ISPROTECTION")
	private Boolean protection;

	/**
	 * 保护类型：PGT_MSP_1_PLUS_1:1+1保护 PGT_2_FIBER_BLSR:双纤双向保护
	 */
	@Column(name = "PROTECT_TYPE")
	private String protectType;

	/**
	 * 切换模式
	 */
	@Column(name = "SWITCH_TYPE")
	private String switchType;

	/**
	 * 状态
	 */
	@Column(name = "STATUS")
	private String status;

	// A端及Z端端口为空,建一个虚拟channel
	@Column(name = "ISVIRTUAL")
	private Boolean virtual;

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

	private static final long serialVersionUID = -6342495011958108456L;

	public ResChannel(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public Boolean getProtection() {
		return protection;
	}

	public void setProtection(Boolean protection) {
		this.protection = protection;
	}

	public String getProtectType() {
		return protectType;
	}

	public void setProtectType(String protectType) {
		this.protectType = protectType;
	}

	public String getSwitchType() {
		return switchType;
	}

	public void setSwitchType(String switchType) {
		this.switchType = switchType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getVirtual() {
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		this.virtual = virtual;
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
		ResChannel other = (ResChannel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResChannel [id=" + id + "]";
	}
}
