package nsgsw1.netcare.model.alarm.constant;

public enum AlarmClearType {

	EMS("EMS�Զ����"), MANUAL("�û��ֹ����"), SYNC("ͬ��ʱ���"), SYSTEM("��̨���");

	private final String description;

	AlarmClearType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
