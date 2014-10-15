package nsgsw1.netcare.model.alarm.constant;

public enum AlarmEventStatus {

	CLEARED("Çå³ý"), ACTIVE("¼¤»î");

	private final String description;

	AlarmEventStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AlarmEventStatus getStatusByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return AlarmEventStatus.CLEARED;
		} else if (ordinal == 1) {
			return AlarmEventStatus.ACTIVE;
		} else
			return null;
	}
}
