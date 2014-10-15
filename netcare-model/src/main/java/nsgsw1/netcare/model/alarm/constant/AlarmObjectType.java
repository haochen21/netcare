package nsgsw1.netcare.model.alarm.constant;

public enum AlarmObjectType {

	EMS("EMS"), NE("��Ԫ"), PTP("PTP(�˿�)"), CTP("CTP(����)"), SHELF("SHELF(����)"), SLOT(
			"SLOT(�۵�)"), CIRCUITPACK("CIRCUITPACK(�忨)"), TRUNKGROUP("�м�Ⱥ");

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
