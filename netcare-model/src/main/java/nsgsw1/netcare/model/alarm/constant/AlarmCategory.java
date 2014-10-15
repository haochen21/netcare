package nsgsw1.netcare.model.alarm.constant;

public enum AlarmCategory {

	SDH("传输告警"), DATA("数据告警"), SWITCH("交换告警"), ACCESS("接入告警"), GGSN("GGSN"), ATM(
			"ATM");

	private final String description;

	AlarmCategory(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AlarmCategory getFieldTypeByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return AlarmCategory.SDH;
		} else if (ordinal == 1) {
			return AlarmCategory.DATA;
		} else if (ordinal == 2) {
			return AlarmCategory.SWITCH;
		} else if (ordinal == 3) {
			return AlarmCategory.ACCESS;
		} else if (ordinal == 4) {
			return AlarmCategory.GGSN;
		} else if (ordinal == 5) {
			return AlarmCategory.ATM;
		}
		return null;
	}
}
