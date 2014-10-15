package nsgsw1.netcare.model.alarm;

import java.io.Serializable;

import nsgsw1.netcare.model.alarm.constant.AlarmClearType;
import nsgsw1.netcare.model.security.User;

public class ClearInfo implements Serializable {

	private User clearer;

	private AlarmClearType clearType;

	private static final long serialVersionUID = -7171194199900329453L;

	public ClearInfo(){
		
	}

	public User getClearer() {
		return clearer;
	}

	public void setClearer(User clearer) {
		this.clearer = clearer;
	}

	public AlarmClearType getClearType() {
		return clearType;
	}

	public void setClearType(AlarmClearType clearType) {
		this.clearType = clearType;
	}
}
