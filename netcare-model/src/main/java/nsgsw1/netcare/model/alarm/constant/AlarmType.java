package nsgsw1.netcare.model.alarm.constant;

public enum AlarmType {

	COMMUNICATIONALARM("通信告警"), ENVIROMENTALARM("环境告警"), EQUIPMENTALARM("设备告警"), QOSALARM(
			"服务质量告警"), PROCESSINGERRORALARM("处理错误告警"), OTHER("其他告警");

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
