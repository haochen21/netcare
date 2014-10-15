package nsgsw1.netcare.model.alarm;

import java.io.Serializable;
import java.util.Date;

import nsgsw1.netcare.model.security.User;

public class AckInfo implements Serializable {

	private User acker;

	// 用户确认告警信息
	private String ackMemo;

	// 告警用户确认时间
	private Date ackTime;

	private static final long serialVersionUID = 4412097524657803368L;

	public AckInfo() {

	}

	public User getAcker() {
		return acker;
	}

	public void setAcker(User acker) {
		this.acker = acker;
	}

	public String getAckMemo() {
		return ackMemo;
	}

	public void setAckMemo(String ackMemo) {
		this.ackMemo = ackMemo;
	}

	public Date getAckTime() {
		return ackTime;
	}

	public void setAckTime(Date ackTime) {
		this.ackTime = ackTime;
	}

}
