package nsgsw1.netcare.model.circuit;

import java.io.Serializable;

import nsgsw1.netcare.model.res.Ctp;

public class Channel implements Serializable {

	private Boolean primary;

	/**
	 * 1:1,1+1保护路由
	 */
	private Boolean protection;

	/**
	 * 保护类型：PGT_MSP_1_PLUS_1:1+1保护 PGT_2_FIBER_BLSR:双纤双向保护
	 */
	private String protectType;

	/**
	 * 切换模式
	 */
	private String switchType;

	/**
	 * 状态
	 */
	private String status;

	// A端及Z端端口为空,建一个虚拟channel
	private Boolean virtual;

	private Ctp channelACtp;

	private Ctp channelZCtp;

	private static final long serialVersionUID = 5416294092728847364L;

	public Channel() {

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

	public Ctp getChannelACtp() {
		return channelACtp;
	}

	public void setChannelACtp(Ctp channelACtp) {
		this.channelACtp = channelACtp;
	}

	public Ctp getChannelZCtp() {
		return channelZCtp;
	}

	public void setChannelZCtp(Ctp channelZCtp) {
		this.channelZCtp = channelZCtp;
	}

}
