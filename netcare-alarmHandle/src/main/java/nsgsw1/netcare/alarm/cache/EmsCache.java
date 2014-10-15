package nsgsw1.netcare.alarm.cache;

import java.util.HashMap;
import java.util.Map;

public class EmsCache {

	private Map<String, String> emsMap;

	public EmsCache() {

	}  

	public void start() {
		emsMap = new HashMap<>();
		emsMap.put("Huawei-wdm", "Huawei/U2000XJQ-WDM");
		emsMap.put("Huawei-HuLian", "Huawei/U2000-hulianhutong");
		emsMap.put("Huawei-sh251", "Huawei/U2000XA");
		emsMap.put("Huawei-U2000XHP", "Huawei/U2000XHP-2");
		emsMap.put("Huawei-U2000XHP3", "Huawei/U2000XHP-3");
		emsMap.put("Huawei-OTN", "Huawei/U2000XHP-1");   
		emsMap.put("ZTEU31", "U31-SDH");
	}

	public String getNativeEmsName(String emsName) {
		if (emsMap.containsKey(emsName))
			return emsMap.get(emsName);
		else
			return emsName;
	}
}
