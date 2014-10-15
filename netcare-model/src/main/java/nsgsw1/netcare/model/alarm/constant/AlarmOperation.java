package nsgsw1.netcare.model.alarm.constant;

public enum AlarmOperation {

	NEW("new"), UPDATE("update"), DELETE("delete"), ACK("ack");

	private final String description;

	AlarmOperation(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AlarmOperation getOperationByName(String operationName) {
		if (AlarmOperation.NEW.getDescription().equals(operationName))
			return AlarmOperation.NEW;
		else if (AlarmOperation.UPDATE.getDescription().equals(operationName))
			return AlarmOperation.UPDATE;
		else if (AlarmOperation.DELETE.getDescription().equals(operationName))
			return AlarmOperation.DELETE;
		else if (AlarmOperation.ACK.getDescription().equals(operationName))
			return AlarmOperation.ACK;
		return null;
	}

	public static AlarmOperation getOperationByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return AlarmOperation.NEW;
		} else if (ordinal == 1) {
			return AlarmOperation.UPDATE;
		} else if (ordinal == 2) {
			return AlarmOperation.DELETE;
		} else if (ordinal == 3) {
			return AlarmOperation.ACK;
		}
		return null;
	}
}
