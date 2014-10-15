package nsgsw1.netcare.model.alarm.constant;

public enum AlarmCategory {

	SDH("����澯"), DATA("���ݸ澯"), SWITCH("�����澯"), ACCESS("����澯"), GGSN("GGSN"), ATM(
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
