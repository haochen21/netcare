package nsgsw1.netcare.model.circuit.constant;


public enum CircuitBizStatus {

	NORMAL("正常"), BACKUP("备份");

	private final String description;

	CircuitBizStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static CircuitBizStatus getFieldTypeByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return CircuitBizStatus.NORMAL;
		} else if (ordinal == 1) {
			return CircuitBizStatus.BACKUP;
		}
		return null;
	}

}
