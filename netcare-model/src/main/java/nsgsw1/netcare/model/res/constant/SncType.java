package nsgsw1.netcare.model.res.constant;

public enum SncType {

	ROUTE("传输路由"), DDF("配线"), LINK("跨专业路由");

	private final String description;

	SncType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static SncType getSncTypeByOrdinal(int ordinal) {
		if (ordinal == 0) {
			return SncType.ROUTE;
		} else if (ordinal == 1) {
			return SncType.DDF;
		} else if (ordinal == 2) {
			return SncType.LINK;
		}
		return null;
	}
}
