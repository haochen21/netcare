package nsgsw1.netcare.model.alarm.constant;

public enum FaultOperation {

	NEW("new"), UPDATE("update"), DELETE("delete");

	private final String description;

	FaultOperation(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
