package nsgsw1.netcare.model.alarm.constant;

public enum AlarmClearType {

	EMS("EMS自动清除"), MANUAL("用户手工清除"), SYNC("同步时清除"), SYSTEM("后台清除");

	private final String description;

	AlarmClearType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
