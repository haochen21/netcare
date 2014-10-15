package nsgsw1.netcare.shres.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CIRCUIT")
@NamedQueries({ @NamedQuery(name = "ResCircuit.findByNo", query = "SELECT cir from ResCircuit cir where cir.no = :no"), })
public class ResCircuit implements Serializable {

	public enum ResCircuitBizStatus {
		NORMAL, BACKUP;
	}

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NO")
	private String no;

	@Column(name = "GROUPNO")
	private String groupNo;

	@Column(name = "STATUS")
	private Boolean status;

	@Column(name = "RESRATE")
	private String rate;

	@Column(name = "RESCIRCUITSTATE")
	private String bizState;

	@Column(name = "RESBIZPROPERTY")
	private String bizProperty;

	@Column(name = "BIZSTATUS")
	@Enumerated(EnumType.ORDINAL)
	private ResCircuitBizStatus bizStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	private Date updateTime;

	@Column(name = "RESSYNCRESULT")
	private Boolean resSyncResult;

	@Column(name = "RESSYNCLOG")
	private String resSyncLog;

	@ManyToOne
	@JoinColumn(name = "ACUSTOMERID")
	private ResCustomer aCustomer;

	@ManyToOne
	@JoinColumn(name = "ZCUSTOMERID")
	private ResCustomer zCustomer;

	@ManyToOne
	@JoinColumn(name = "AMEID")
	private ResMe resAMe;

	@ManyToOne
	@JoinColumn(name = "ZMEID")
	private ResMe resZMe;

	@ManyToOne
	@JoinColumn(name = "APORTID")
	private ResPort resAPort;

	@ManyToOne
	@JoinColumn(name = "ZPORTID")
	private ResPort resZPort;

	@ManyToOne
	@JoinColumn(name = "ACTPID")
	private ResCtp resACtp;

	@ManyToOne
	@JoinColumn(name = "ZCTPID")
	private ResCtp resZCtp;

	private static final long serialVersionUID = 4359370675760055578L;

	public ResCircuit() {

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

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getBizState() {
		return bizState;
	}

	public void setBizState(String bizState) {
		this.bizState = bizState;
	}

	public String getBizProperty() {
		return bizProperty;
	}

	public void setBizProperty(String bizProperty) {
		this.bizProperty = bizProperty;
	}

	public ResCircuitBizStatus getBizStatus() {
		return bizStatus;
	}

	public void setBizStatus(ResCircuitBizStatus bizStatus) {
		this.bizStatus = bizStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getResSyncResult() {
		return resSyncResult;
	}

	public void setResSyncResult(Boolean resSyncResult) {
		this.resSyncResult = resSyncResult;
	}

	public String getResSyncLog() {
		return resSyncLog;
	}

	public void setResSyncLog(String resSyncLog) {
		this.resSyncLog = resSyncLog;
	}

	public ResCustomer getaCustomer() {
		return aCustomer;
	}

	public void setaCustomer(ResCustomer aCustomer) {
		this.aCustomer = aCustomer;
	}

	public ResCustomer getzCustomer() {
		return zCustomer;
	}

	public void setzCustomer(ResCustomer zCustomer) {
		this.zCustomer = zCustomer;
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
		result = prime * result + ((no == null) ? 0 : no.hashCode());
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
		ResCircuit other = (ResCircuit) obj;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResCircuit [no=" + no + "]";
	}
}
