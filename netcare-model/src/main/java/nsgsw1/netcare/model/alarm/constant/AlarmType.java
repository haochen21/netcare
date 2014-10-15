package nsgsw1.netcare.model.alarm.constant;

public enum AlarmType {

	COMMUNICATIONALARM("ͨ�Ÿ澯"), ENVIROMENTALARM("�����澯"), EQUIPMENTALARM("�豸�澯"), QOSALARM(
			"���������澯"), PROCESSINGERRORALARM("�������澯"), OTHER("�����澯");

	private final String description;

	AlarmType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AlarmType getAlarmTypeByName(String name) {
		if (name.equals(AlarmType.COMMUNICATIONALARM.getDescription())) {
			return AlarmType.COMMUNICATIONALARM;
		} else if (name.equals(AlarmType.ENVIROMENTALARM.getDescription())) {
			return AlarmType.ENVIROMENTALARM;
		} else if (name.equals(AlarmType.EQUIPMENTALARM.getDescription())) {
			return AlarmType.EQUIPMENTALARM;
		} else if (name.equals(AlarmType.PROCESSINGERRORALARM.getDescription())) {
			return AlarmType.PROCESSINGERRORALARM;
		} else if (name.equals(AlarmType.QOSALARM.getDescription())) {
			return AlarmType.QOSALARM;
		} else
			return AlarmType.OTHER;
	}

	public static AlarmType getTypeByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return AlarmType.COMMUNICATIONALARM;
		} else if (ordinal == 1) {
			return AlarmType.ENVIROMENTALARM;
		} else if (ordinal == 2) {
			return AlarmType.EQUIPMENTALARM;
		} else if (ordinal == 3) {
			return AlarmType.QOSALARM;
		} else if (ordinal == 4) {
			return AlarmType.PROCESSINGERRORALARM;
		} else if (ordinal == 5) {
			return AlarmType.OTHER;
		} else
			return null;
	}
}
