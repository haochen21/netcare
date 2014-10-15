package nsgsw1.netcare.model.res.constant;

public enum MeDeviceType {

	SDH("传输"), SWITCH("交换"), DATA("数据"), ACCESS("接入"), GGSN("GGSN"), OTHER("其它"), ATM(
			"ATM");

	private final String description;

	MeDeviceType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static MeDeviceType getMeDeviceTypeByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return MeDeviceType.SDH;
		} else if (ordinal == 1) {
			return MeDeviceType.SWITCH;
		} else if (ordinal == 2) {
			return MeDeviceType.DATA;
		} else if (ordinal == 3) {
			return MeDeviceType.ACCESS;
		} else if (ordinal == 4) {
			return MeDeviceType.GGSN;
		} else if (ordinal == 5) {
			return MeDeviceType.OTHER;
		} else if (ordinal == 6) {
			return MeDeviceType.ATM;
		}
		return null;
	}
}
