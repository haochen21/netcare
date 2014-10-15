package nsgsw1.netcare.model.alarm.constant;

public enum AlarmObjectType {

	EMS("EMS"), NE("网元"), PTP("PTP(端口)"), CTP("CTP(端子)"), SHELF("SHELF(机框)"), SLOT(
			"SLOT(槽道)"), CIRCUITPACK("CIRCUITPACK(板卡)"), TRUNKGROUP("中继群");

	private final String description;

	AlarmObjectType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AlarmObjectType getFieldTypeByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return AlarmObjectType.EMS;
		} else if (ordinal == 1) {
			return AlarmObjectType.NE;
		} else if (ordinal == 2) {
			return AlarmObjectType.PTP;
		} else if (ordinal == 3) {
			return AlarmObjectType.CTP;
		} else if (ordinal == 4) {
			return AlarmObjectType.SHELF;
		} else if (ordinal == 5) {
			return AlarmObjectType.SLOT;
		} else if (ordinal == 6) {
			return AlarmObjectType.CIRCUITPACK;
		} else if (ordinal == 7) {
			return AlarmObjectType.TRUNKGROUP;
		}
		return null;
	}
}
