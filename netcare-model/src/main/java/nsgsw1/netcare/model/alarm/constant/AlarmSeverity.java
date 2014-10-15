package nsgsw1.netcare.model.alarm.constant;

public enum AlarmSeverity {

	CRITICAL("紧急"), MAJOR("重要"), MINOR("次要"), WARNING("警告"), UNKNOWN("不确定");

	private final String description;

	AlarmSeverity(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AlarmSeverity getSeverityByNameEn(String name) {
		if (name.equals("CRITICAL"))
			return CRITICAL;
		else if (name.equals("MAJOR"))
			return MAJOR;
		else if (name.equals("MINOR"))
			return MINOR;
		else if (name.equals("WARNING"))
			return WARNING;
		else if (name.equals("UNKNOWN"))
			return UNKNOWN;
		else
			return null;
	}

	public static AlarmSeverity getSeverityByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return AlarmSeverity.CRITICAL;
		} else if (ordinal == 1) {
			return AlarmSeverity.MAJOR;
		} else if (ordinal == 2) {
			return AlarmSeverity.MINOR;
		} else if (ordinal == 3) {
			return AlarmSeverity.WARNING;
		} else if (ordinal == 4)
			return AlarmSeverity.UNKNOWN;
		else
			return null;
	}
}
